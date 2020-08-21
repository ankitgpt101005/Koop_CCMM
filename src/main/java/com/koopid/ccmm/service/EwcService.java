package com.koopid.ccmm.service;

import static com.koopid.ccmm.utility.Constants.DEFAULT_SKILL;
import static com.koopid.ccmm.utility.Constants.END;
import static com.koopid.ccmm.utility.Constants.START;
import static com.koopid.ccmm.utility.Constants.TYPING;
import static com.koopid.ccmm.utility.Constants.WELCOME_MESSAGE;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koopid.ccmm.config.CollectionConfig;
import com.koopid.ccmm.entity.AgentContext;
import com.koopid.ccmm.entity.AgentSkillset;
import com.koopid.ccmm.entity.ConfigObjects;
import com.koopid.ccmm.entity.ConversationContext;
import com.koopid.ccmm.entity.ConversationMessageContext;
import com.koopid.ccmm.entity.Customer;
import com.koopid.ccmm.entity.CustomerContext;
import com.koopid.ccmm.entity.EwcMeta;
import com.koopid.ccmm.entity.KoopidMeta;
import com.koopid.ccmm.entity.MessageContext;
import com.koopid.ccmm.entity.Route;
import com.koopid.ccmm.entity.RoutingAttrs;
import com.koopid.ccmm.entity.WebchatConfig;
import com.koopid.ccmm.ewcentity.CloseConversation;
import com.koopid.ccmm.ewcentity.IsTyping;
import com.koopid.ccmm.ewcentity.NewMessage;
import com.koopid.ccmm.ewcentity.RequestChat;
import com.koopid.ccmm.exception.KoopidEntityNotFoundException;
import com.koopid.ccmm.repo.CustomerRepository;
import com.koopid.ccmm.repo.EwcMetaRepository;
import com.koopid.ccmm.repo.KoopidMetaRepository;
import com.koopid.ccmm.repo.RouteRepository;
import com.koopid.ccmm.utility.CcmmUtility;
import com.koopid.ccmm.utility.CommonUtility;
import com.koopid.ccmm.utility.EwcUtility;
import com.koopid.ccmm.utility.Helper;
import com.koopid.ccmm.websocket.client.EwcWebsocketClientEndpoint;

@Service
public class EwcService implements KoopidClient {

	private static final Logger log = LoggerFactory.getLogger(EwcService.class);

	@Autowired
	private EwcUtility ewcUtility;
	@Autowired
	private Helper helper;
	@Autowired
	EwcWebsocketClientEndpoint clientEndPoint;
	@Autowired
	private KoopidService koopidService;
	@Autowired
	private RouteRepository routeRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private KoopidMetaRepository koopidMetaRepository;
	@Autowired
	private EwcMetaRepository ewcMetaRepository;
	@Autowired
	private CcmmUtility ccmmUtility;
	@Autowired
	private CommonUtility commonUtility;

	@Override
	public AgentContext routing(CustomerContext customerContext, String partnerId, String providerId) {

		AgentContext agentContext = new AgentContext();
		
		try
		{

			String routingContext = customerContext.getRoutingContext();
			log.debug("EWC Routing context details are :" + routingContext);

			ConfigObjects configObjects = commonUtility.getProviderConfigObjects(partnerId, providerId);
			log.debug("EWC config object details are :" + configObjects.toString());
			
			Map<String, List<String>> skill = configObjects.getWebchatConfig().getSkillset();

			/**
			 * Get skillset from 'agent.skill' as per the updated route request
			 */
			// TODO : If agent skill is null use skill mapping based on channel or default
			RoutingAttrs routingAttrs = customerContext.getRoutingAttrs();
			String skillSetName = null;
			if(routingAttrs != null) {
				String agentSkillDetails = routingAttrs.getAgentSkill();
				AgentSkillset agentSkillset =  commonUtility.getAgentSkillset(agentSkillDetails);
				String agentWithSkill = null;
				if(agentSkillset != null) {
					agentWithSkill = agentSkillset.getSkillset();
					skillSetName = (skill.containsKey(agentWithSkill)) ? agentWithSkill
							: DEFAULT_SKILL;
				}
				else {
					// TODO :- Verify from deepak that its correct logic					
					// Get skillset from provider config based on chanel
					// if its null then use channel/language to look up in skill other wise use defult.
				}
			}
			if(skillSetName == null) {
				skillSetName = DEFAULT_SKILL;
			}	
			
			log.debug("ews skillset name is " + skillSetName);

			RequestChat payload = ewcUtility.populateRequestChat(customerContext, 0, null, skillSetName);

			// TODO check payload is null return failure in the response
			if(null == payload)
			{
				log.error("Payload value is null, please check logs for more details");
				// Clear all the details if there is an error in the flow
				ewcUtility.clearByContext(routingContext);
				
				// Set agent context stage as failure so client will get to know about this.
				agentContext = koopidService.getAgentContext(customerContext, providerId, "failure");			
				
			}
			else
			{				
				WebchatConfig webchatConfig = configObjects.getWebchatConfig();
				log.debug("EWC RequestChat WebchatConfig details are :" + webchatConfig.toString());			
				
				// Connect to server
				ewcUtility.connectToServer(routingContext, webchatConfig.getWebSocketEndpoint());
				
				String payloadJson = helper.objectToJson(payload);
				
				// Create route details
				Route route = new Route();
				route.setPartnerId(partnerId);
				route.setProviderId(providerId);
				route.setContext(routingContext);
				route.setActive(false);
				route.setSkill(skillSetName);
				route.setType("ewc");
				routeRepository.save(route);
			
				// Save customer details
				// NOTE :- Customer is being created using customer json not business ID JSON
				Customer customer = ewcUtility.getCustomer(customerContext);
				customerRepository.save(customer);
			
				// Request for chat.
				clientEndPoint.sendMessage(payloadJson, CollectionConfig.getChatSessionMap().get(routingContext));
			
				// Return agent context stage as a wait in case of success then he will initiate the conversation
				agentContext = koopidService.getAgentContext(customerContext, providerId, "wait");
				log.debug("[{}] EWC RouteResponse:: wait: {} ", customerContext.getRoutingContext(), agentContext.toString());
				
			}
						
		}catch(Exception e)
		{
			// TODO :- Print stack trace in file not on the console
			log.error("Exception while processing the ewc route request " + e);
						
			// Clear all the details if there is an error in the flow
			String routingContext = customerContext.getRoutingContext();
			ewcUtility.clearByContext(routingContext);
			
			// Set agent context stage as failure so client will get to know about this.
			agentContext = koopidService.getAgentContext(customerContext, providerId, "failure");			
		}
		
		return agentContext;
	}

	@Override
	public void msgrecv(MessageContext messageContext) {

		String routingContext = messageContext.getRoutingContext();
		log.debug("EWC msgrecv context details are :" + routingContext);

		if (!commonUtility.ifRouteExists(routingContext)) {
			log.warn("Routing context not found {}", routingContext);
			throw new KoopidEntityNotFoundException(Route.class, "routing context", routingContext);
		}

		Route route = routeRepository.findByContext(routingContext);
		KoopidMeta koopidMeta = ccmmUtility.getKoopidMeta(messageContext);
		koopidMetaRepository.save(koopidMeta);

		log.debug("EWC msgrecv context details are :" + routingContext);
		Session userSession = CollectionConfig.getChatSessionMap().get(routingContext);
		EwcMeta ewcMeta = ewcMetaRepository.findByContext(messageContext.getRoutingContext());
		log.debug("EWC msgrecv EwcMeta details are :" + ewcMeta.toString());

		
		ConfigObjects configObjects = commonUtility.getProviderConfigObjects(route.getPartnerId(), route.getProviderId());
		WebchatConfig webchatConfig = configObjects.getWebchatConfig();
		log.debug("EWC msgrecv WebchatConfig details are :" + webchatConfig.toString());

		if (messageContext.getMsg().getType().equals(TYPING)) {
			IsTyping isTyping = ewcUtility.getIsTypingRequest();
			String isTypingJson = helper.objectToJson(isTyping);
			if (userSession != null && userSession.isOpen())
			{
				log.debug("EWC msgrecv session is open");				
				clientEndPoint.sendMessage(isTypingJson, userSession);
			}
			else {
				log.debug("EWC msgrecv reconnecting to the server");
				ewcUtility.reConnectToServer(routingContext, route, ewcMeta, webchatConfig.getWebSocketEndpoint());
				clientEndPoint.sendMessage(isTypingJson, CollectionConfig.getChatSessionMap().get(routingContext));
			}
		} else {

			String messageForAgent = messageContext.getMsg().getText();
			log.debug("EWC msgrecv message for agent is" + messageForAgent);
			
			NewMessage msgToAgent = ewcUtility.getNewAgentMessage(messageForAgent);
			String msgToAgentJson = helper.objectToJson(msgToAgent);
			if (userSession != null && userSession.isOpen())
				clientEndPoint.sendMessage(msgToAgentJson, userSession);
			else {
				ewcUtility.reConnectToServer(routingContext, route, ewcMeta, webchatConfig.getWebSocketEndpoint());
				clientEndPoint.sendMessage(msgToAgentJson, CollectionConfig.getChatSessionMap().get(routingContext));
			}
			// Send message delivered status to Koopid. Here we are assuming that the
			// message is successfully delivered.
			commonUtility.koopidMsgDeliveryUpdate(messageContext, route);
		}
	}

	@Override
	public void conversation(ConversationContext conversationContext, String providerId, String partnerId) {

		String routingContext = conversationContext.getRoutingContext();
		log.debug("EWC conversation routingcontext is" + routingContext);
		
		if (!commonUtility.ifRouteExists(routingContext)) {
			log.warn("Routing context not found {}", routingContext);
			throw new KoopidEntityNotFoundException(Route.class, "routing context", routingContext);
		}
		
		EwcMeta ewcMeta = ewcMetaRepository.findByContext(conversationContext.getRoutingContext());
		ewcMeta.setChatId(conversationContext.getChatId());
		ewcMeta.setAgentId(conversationContext.getAgentId());
		log.debug("EWC conversation EwcMeta details are is" + ewcMeta.toString());

		Route route = routeRepository.findByContext(routingContext);
		route.setActive(true);
		routeRepository.save(route);
		ewcMetaRepository.save(ewcMeta);
		log.debug("EWC conversation route details after set active true are " + route.toString());
			
		log.debug("conversationContext.getCmd() {}", conversationContext.getCmd());
		if (START.equals(conversationContext.getCmd())) {
			log.debug("EWC conversation start conversation ");
			
			Customer customer = customerRepository.findByContext(routingContext);
			String customerInfo = "CustomerName: " + customer.getName() + ", PhoneNumber: " + customer.getPhone()
					+ ", EmailID: " + customer.getEmail();

			NewMessage msgToAgent = ewcUtility.getNewAgentMessage(customerInfo);
			String msgToAgentJson = helper.objectToJson(msgToAgent);
			log.debug(" EWC conversation msgToAgentJson {}", msgToAgentJson);
			Session userSession = CollectionConfig.getChatSessionMap().get(routingContext);

			if (userSession != null && userSession.isOpen()) {
				clientEndPoint.sendMessage(msgToAgentJson, userSession);
			} else {
				ConfigObjects configObjects = commonUtility.getProviderConfigObjects(partnerId, providerId);
				WebchatConfig webchatConfig = configObjects.getWebchatConfig();
				log.debug(" EWC conversation msgToAgentJson reconnecting to server");
				
				ewcUtility.reConnectToServer(routingContext, route, ewcMeta, webchatConfig.getWebSocketEndpoint());
				clientEndPoint.sendMessage(msgToAgentJson, CollectionConfig.getChatSessionMap().get(routingContext));
			}

			ConversationMessageContext conversationMessageContext = ccmmUtility.getMessageContext(conversationContext);

			log.info("EWC conversation conversationMessageContext {}", conversationMessageContext);
			koopidService.sendMsgToCustomer(WELCOME_MESSAGE, System.currentTimeMillis(), conversationMessageContext,
					route.getProviderId(), route.getPartnerId());

		} else if (END.equals(conversationContext.getCmd())) {
			
			log.debug("EWC conversation end conversation ");
			CloseConversation closeConversation = ewcUtility.getCloseConversation("closeConversation");
			route.setActive(false);
			routeRepository.save(route);
			String payloadJson = helper.objectToJson(closeConversation);
			log.debug("EWC conversation Close conversation called {}", payloadJson);
	
			clientEndPoint.sendMessage(payloadJson, CollectionConfig.getChatSessionMap().get(routingContext));

			try {
				log.debug("EWC conversation Close connection");				
				CollectionConfig.getChatSessionMap().get(routingContext).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}