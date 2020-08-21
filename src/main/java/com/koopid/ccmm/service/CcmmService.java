package com.koopid.ccmm.service;

import static com.koopid.ccmm.utility.Constants.CHAT_MESSAGE_FROM_AGENT;
import static com.koopid.ccmm.utility.Constants.DELIVERED;
import static com.koopid.ccmm.utility.Constants.END;
import static com.koopid.ccmm.utility.Constants.SESSION_DISCONNECT_BY_CUSTOMER;
import static com.koopid.ccmm.utility.Constants.START;
import static com.koopid.ccmm.utility.Constants.TYPING;
import static com.koopid.ccmm.utility.Constants.WELCOME_MESSAGE;
import static com.koopid.ccmm.utility.Constants.CUST_NAME;
import static com.koopid.ccmm.utility.Constants.CUST_EMAIL;
import static com.koopid.ccmm.utility.Constants.CUST_PHONE;
import static com.koopid.ccmm.utility.Constants.DEFAULT_SKILL;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koopid.ccmm.config.CcmmConfig;
import com.koopid.ccmm.entity.AgentContext;
import com.koopid.ccmm.entity.AgentQueued;
import com.koopid.ccmm.entity.AgentSkillset;
import com.koopid.ccmm.entity.CcmmMeta;
import com.koopid.ccmm.entity.ConfigObjects;
import com.koopid.ccmm.entity.ConversationContext;
import com.koopid.ccmm.entity.ConversationMessageContext;
import com.koopid.ccmm.entity.ConversationTimestamp;
import com.koopid.ccmm.entity.Customer;
import com.koopid.ccmm.entity.CustomerContext;
import com.koopid.ccmm.entity.KoopidMeta;
import com.koopid.ccmm.entity.KoopidProvider;
import com.koopid.ccmm.entity.KoopidProviderConfig;
import com.koopid.ccmm.entity.Message;
import com.koopid.ccmm.entity.MessageContext;
import com.koopid.ccmm.entity.ReadChatContext;
import com.koopid.ccmm.entity.Route;
import com.koopid.ccmm.entity.RoutingAttrs;
import com.koopid.ccmm.entity.WebchatConfig;
import com.koopid.ccmm.redis.RedisUtility;
import com.koopid.ccmm.repo.AgentQueuedRepository;
import com.koopid.ccmm.repo.CcmmMetaRepository;
import com.koopid.ccmm.repo.ConversationTimestampRepository;
import com.koopid.ccmm.repo.CustomerRepository;
import com.koopid.ccmm.repo.KoopidConfigRepository;
import com.koopid.ccmm.repo.KoopidMetaRepository;
import com.koopid.ccmm.repo.RouteRepository;
import com.koopid.ccmm.utility.CcmmUtility;
import com.koopid.ccmm.utility.CommonUtility;
import com.koopid.ccmm.utility.Constants;
import com.koopid.ccmm.utility.Helper;
import com.koopid.ccmm.wsdl.ci_customer.RequestTextChatResponse;
import com.koopid.ccmm.wsdl.ci_skill_set.GetSkillsetByName;
import com.koopid.ccmm.wsdl.ci_skill_set.GetSkillsetByNameResponse;
import com.koopid.ccmm.wsdl.ci_utility.GetAnonymousSessionKeyResponse;
import com.koopid.ccmm.wsdl.ci_utility.GetTotalQueuedToSkillset;
import com.koopid.ccmm.wsdl.ci_utility.GetTotalQueuedToSkillsetResponse;
import com.koopid.ccmm.wsdl.ci_webcomm.CIChatMessageReadType;
import com.koopid.ccmm.wsdl.ci_webcomm.CIChatMessageType;
import com.koopid.ccmm.wsdl.ci_webcomm.ReadChatMessageResponse;
import com.koopid.ccmm.wsdl.ci_webcomm.WriteChatMessageResponse;

import javassist.NotFoundException;

@Service
public class CcmmService implements KoopidClient {

	private static final Logger log = LoggerFactory.getLogger(CcmmService.class);

	@Autowired
	private SessionKeyClient sessionKeyClient;
	@Autowired
	private CcmmUtility ccmmUtility;
	@Autowired
	private KoopidService koopidService;
	@Autowired
	private CcmmMetaRepository ccmmMetaRepository;
	@Autowired
	private RouteRepository routeRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AgentQueuedRepository agentQueuedRepository;
	@Autowired
	private ConversationTimestampRepository conversationTimestampRepository;
	@Autowired
	private KoopidMetaRepository koopidMetaRepository;
	@Autowired
	private KoopidConfigRepository koopidConfigRepository;
	@Autowired
	private CommonUtility commonUtility;
	@Autowired
	RedisUtility redisUtil;
	@Autowired
	private Helper helper;

	@Override
	public AgentContext routing(CustomerContext customerContext, String partnerId, String providerId) {
		
		long skillsetId = 0;
		AgentContext agentContext = new AgentContext();
		
		try
		{
			// Get provider coonfiguration details 
			ConfigObjects configObjects = commonUtility.getProviderConfigObjects(partnerId, providerId);
			log.debug("ccmm routing : config details are " + configObjects.toString());
	
			// Get web chat configuration details 
			WebchatConfig webchatConfig = configObjects.getWebchatConfig();
			String ccmmWebserviceUrl = webchatConfig.getWebServiceUrl();
			String ccmmWebserviceEndPoint = webchatConfig.getWebServiceEndpoint();
			// sessionKeyClient.setDefaultUri(ccmmWebserviceEndPoint);
			log.debug("ccmm routing :  ccmmWebserviceUrl" + ccmmWebserviceUrl);
			log.debug("ccmm routing :  ccmmWebserviceEndPoint" + ccmmWebserviceUrl);
			
			// Get session key from Avaya CCMM
			GetAnonymousSessionKeyResponse getAnonymousSessionKeyResponse = sessionKeyClient.getSessionKey(ccmmWebserviceUrl, ccmmWebserviceEndPoint);
			String anonymousSessionKey = getAnonymousSessionKeyResponse.getGetAnonymousSessionKeyResult().getSessionKey();
			log.debug("ccmm routing : anonymousSessionKey " + anonymousSessionKey);

			GetSkillsetByName getSkillsetByName = new GetSkillsetByName();
			getSkillsetByName.setSessionKey(anonymousSessionKey);
			/**
			 * Get skillset from 'agent.skill' as per the updated route request
			 */
			RoutingAttrs routingAttrs = customerContext.getRoutingAttrs();
			String skillSetName = null;
			if(routingAttrs != null) {
				String agentSkillDetails = routingAttrs.getAgentSkill();
				AgentSkillset agentSkillset =  commonUtility.getAgentSkillset(agentSkillDetails);
				String agentWithSkill = null;
				if(agentSkillset != null) {
					agentWithSkill = agentSkillset.getSkillset();
					if (webchatConfig.getSkillset().containsKey(agentWithSkill)) {
						skillSetName = agentWithSkill;
						getSkillsetByName.setSkillsetName(agentWithSkill);
					}
				}
			}
			if(skillSetName == null) {
				getSkillsetByName.setSkillsetName(DEFAULT_SKILL);
			}

			log.debug("ccmm routing : skillset " + skillSetName);

			GetSkillsetByNameResponse getSkillsetByNameResponse = sessionKeyClient
					.getSkillsetByNameResponse(getSkillsetByName, ccmmWebserviceUrl, ccmmWebserviceEndPoint);

			skillsetId = getSkillsetByNameResponse.getGetSkillsetByNameResult().getId();
			log.debug("ccmm routing : skillsetId " + skillsetId);

			GetTotalQueuedToSkillset getTotalQueuedToSkillset = new GetTotalQueuedToSkillset();
			getTotalQueuedToSkillset.setSessionKey(anonymousSessionKey);
			getTotalQueuedToSkillset.setSkillsetID(skillsetId);

			GetTotalQueuedToSkillsetResponse getTotalQueuedToSkillsetResponse = sessionKeyClient
					.getTotalQueuedToSkillset(getTotalQueuedToSkillset, ccmmWebserviceUrl, ccmmWebserviceEndPoint);
			
			KoopidProvider koopidProvider = commonUtility.getKoopidProvider(partnerId, providerId);
			log.debug("ccmm routing : koopidProvider details are " + koopidProvider.toString());
			
			Map<String, String> custDetails = commonUtility.getCustomerContextDetails(routingAttrs.getChannel(), customerContext);
			String customerName = custDetails.get(CUST_NAME);
			String email = custDetails.get(CUST_EMAIL);
			String phone = custDetails.get(CUST_PHONE);
			
			Long myCustomerID = ccmmUtility.getCustomerIdUtility(getAnonymousSessionKeyResponse, customerContext.getRoutingContext(), partnerId,
					providerId, getSkillsetByName.getSkillsetName(), koopidProvider.getWebchatType(), customerName, email, phone);

			log.debug("ccmm routing : Customer id is " + myCustomerID);
			String routingContext = customerContext.getRoutingContext();
			log.debug("ccmm routing : routingContext details are " + routingContext);
			
			AgentQueued agentQueued = new AgentQueued();
			agentQueued.setContext(routingContext);
			agentQueued.setSkillSetId(skillsetId);
			agentQueued.setQueued(getTotalQueuedToSkillsetResponse.getGetTotalQueuedToSkillsetResult());
			agentQueuedRepository.save(agentQueued);
			log.debug("ccmm routing : AgentQueued details are " + agentQueued.toString());

			
			RequestTextChatResponse requestTextChatResponse = ccmmUtility.requestTextChatUtility(myCustomerID,
					anonymousSessionKey, routingContext, ccmmWebserviceUrl, ccmmWebserviceEndPoint, skillsetId, routingAttrs.getTopic());

			Customer customer = new Customer();
			customer.setContext(routingContext);
			customer.setName(customerName);
			customer.setEmail(email);
			customer.setPhone(phone);
			customerRepository.save(customer);
			log.debug("ccmm routing : Customer details are " + customer.toString());

			CcmmMeta ccmmMeta = new CcmmMeta();
			ccmmMeta.setContext(routingContext);
			ccmmMeta.setSessionkey(anonymousSessionKey);
			ccmmMeta.setCustID(myCustomerID);
			ccmmMeta.setContactID(requestTextChatResponse.getRequestTextChatResult());
			ccmmMetaRepository.save(ccmmMeta);
			log.debug("ccmm routing : Customer details are " + customer.toString());
			
			// Return agent context stage as a wait in case of success then he will initiate the conversation
			agentContext = koopidService.getAgentContext(customerContext, providerId,"wait");
			log.debug("[{}] CCMM RouteResponse:: wait: {} ", customerContext.getRoutingContext(), agentContext.toString());
			
		}catch(Exception e)
		{
			log.error("Exception while processing the ccmm route request " + e.getMessage());

			// Print stack trace
			e.printStackTrace();
			
			// Set agent context stage as failure so client will get to know about this.
			agentContext = koopidService.getAgentContext(customerContext, providerId, "failure");	

			// Do cleanup activity if there is problem in the routing			
			String routingContext = customerContext.getRoutingContext();
			ccmmUtility.clearByContext(routingContext);
						
		}
		
		return agentContext;

	}

	@Override
	public void msgrecv(MessageContext messageContext) {

		log.debug("ccmm msgrecv : MessageContext details are " + messageContext.toString());

		KoopidMeta koopidMeta = ccmmUtility.getKoopidMeta(messageContext);
		koopidMetaRepository.save(koopidMeta);

		CcmmMeta ccmmMeta = ccmmMetaRepository.findByContext(messageContext.getRoutingContext());
		Route route = routeRepository.findByContext(messageContext.getRoutingContext());
		String PartnerId = route.getPartnerId();
		String ProviderId = messageContext.getProviderId();
		ConfigObjects configObjects = commonUtility.getProviderConfigObjects(PartnerId, ProviderId);
		log.debug("ccmm msgrecv : configObjects details are " + configObjects.toString());

		WebchatConfig webchatConfig = configObjects.getWebchatConfig();
		String ccmmWebserviceUrl = webchatConfig.getWebServiceUrl();
		String ccmmWebserviceEndPoint = webchatConfig.getWebServiceEndpoint();
		
		if (messageContext.getMsg().getType().equals(TYPING)) {
			log.debug("ccmm msgrecv : inside is typing ");			
			ccmmUtility.updateAliveTimeAndUpdateIsTypingUtility(messageContext, ccmmMeta.getSessionkey(),
					ccmmWebserviceUrl, ccmmWebserviceEndPoint);
		} else {
			Customer customer = customerRepository.findByContext(messageContext.getRoutingContext());

			String text = "[" + customer.getName() + "]" + " " + messageContext.getMsg().getText();
			log.debug("ccmm msgrecv : message text details are " + text);			

			WriteChatMessageResponse response = ccmmUtility.writeChatMessageUtility(text, ccmmMeta.getContactID(),
					ccmmMeta.getSessionkey(), CIChatMessageType.CHAT_MESSAGE_FROM_CUSTOMER,
					messageContext.getRoutingContext(), ccmmWebserviceUrl, ccmmWebserviceEndPoint);
			
			log.debug("ccmm msgrecv : WriteChatMessageResponse details are " + response.getWriteChatMessageResult());	
			if (response.getWriteChatMessageResult() == 1) {

				log.debug("ccmm msgrecv : ack details are " + messageContext.getMsg().isAck() + " Message id is " + messageContext.getMsg().getId());
				
				if (messageContext.getMsg().isAck() && messageContext.getMsg().getId() != null) {
						
					MessageContext messageContextUpdate = messageContext;
					Message msg = new Message();
					msg.setType("ack");
					msg.setMediatype("text");
					msg.setText(DELIVERED);
					msg.setSenderName(messageContext.getMsg().getSenderName());
					msg.setId(messageContext.getMsg().getId());
					msg.setTz(messageContext.getMsg().getTz());
					msg.setTs(System.currentTimeMillis());

					messageContextUpdate.setMsg(msg);
					log.debug("ccmm msgrecv : send delivery update to the customer");
					koopidService.sendMsgDeliveredUpdateToCustomer(messageContextUpdate, ProviderId,
							PartnerId);
				}
			}

		}
	}

	@Override
	public void conversation(ConversationContext conversationContext, String providerId, String partnerId) {
		CcmmMeta ccmmMeta = ccmmMetaRepository.findByContext(conversationContext.getRoutingContext());		
		ccmmMeta.setChatId(conversationContext.getChatId());
		
		log.debug("ccmm conversation : CcmmMeta details are " + ccmmMeta.toString());		
		log.debug("ccmm conversation : chat id is " + conversationContext.getChatId());

		ccmmMetaRepository.save(ccmmMeta);
		Customer customer = customerRepository.findByContext(conversationContext.getRoutingContext());
		log.debug("ccmm conversation : customer details are " + customer.toString());

		log.debug("ccmm conversation : customer command details are " + conversationContext.getCmd());		
		if (START.equals(conversationContext.getCmd())) {
			
			log.debug("ccmm conversation : starting conversation");
			String text = "[" + customer.getName() + "] CustomerName:" + customer.getName() + ", PhoneNumber:"
					+ customer.getPhone() + ", EmailID:" + customer.getEmail();
			/**
			 * This method needs to refactor, can remove duplicate code 
			 */
			log.debug("ccmm conversation : text details are" + text);
			
			ReadChatContext readChatContext = new ReadChatContext();
			readChatContext.setRoutingContext(ccmmMeta.getContext());
			readChatContext.setContactID(ccmmMeta.getContactID());
			readChatContext.setSessionkey(ccmmMeta.getSessionkey());

			ConfigObjects configObjects = commonUtility.getProviderConfigObjects(partnerId, providerId);
			WebchatConfig webchatConfig = configObjects.getWebchatConfig();
			String ccmmWebserviceUrl = webchatConfig.getWebServiceUrl();
			String ccmmWebserviceEndPoint = webchatConfig.getWebServiceEndpoint();
			WriteChatMessageResponse writeChatMessageResponse = ccmmUtility.writeChatMessageUtility(text,
					ccmmMeta.getContactID(), ccmmMeta.getSessionkey(), CIChatMessageType.CHAT_MESSAGE_FROM_CUSTOMER,
					conversationContext.getRoutingContext(), ccmmWebserviceUrl, ccmmWebserviceEndPoint);
			try {
				writeChatMessageResponse.getWriteChatMessageResult();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
			log.debug("ccmm conversation : readChatContext is " + readChatContext);
			//log.debug("ccmm conversation : ccmmWebserviceUrl is " + ccmmWebserviceUrl);
			//log.debug("ccmm conversation : ccmmWebserviceUrl is " + ccmmWebserviceEndPoint);
			
			readchat(readChatContext, ccmmWebserviceUrl, ccmmWebserviceEndPoint);
			
			log.debug("ccmm conversation : Updating route to active state");
			Route route = routeRepository.findByContext(conversationContext.getRoutingContext());
			route.setActive(true);
			routeRepository.save(route);

			ConversationMessageContext conversationMessageContext = ccmmUtility.getMessageContext(conversationContext);
			ConversationTimestamp conversationTimestamp = conversationTimestampRepository
					.findByContext(ccmmMeta.getContext());

			log.debug("ccmm conversation : Sending welcome message to customer");
			koopidService.sendMsgToCustomer(WELCOME_MESSAGE, conversationTimestamp.getTimestamp(),
					conversationMessageContext, route.getProviderId(), route.getPartnerId());

		} else if (END.equals(conversationContext.getCmd())) {

			log.debug("ccmm conversation : ending conversation");
			ReadChatContext readChatContext = new ReadChatContext();
			readChatContext.setRoutingContext(ccmmMeta.getContext());
			readChatContext.setContactID(ccmmMeta.getContactID());
			readChatContext.setSessionkey(ccmmMeta.getSessionkey());

			ConfigObjects configObjects = commonUtility.getProviderConfigObjects(partnerId, providerId);
			WebchatConfig webchatConfig = configObjects.getWebchatConfig();
			String ccmmWebserviceUrl = webchatConfig.getWebServiceUrl();
			String ccmmWebserviceEndPoint = webchatConfig.getWebServiceEndpoint();			
			log.debug("ccmm conversation : ending conversation readChatContext is " + readChatContext);
			//log.debug("ccmm conversation : ending conversation ccmmWebserviceUrl is " + ccmmWebserviceUrl);
			//log.debug("ccmm conversation : ending conversation ccmmWebserviceUrl is " + ccmmWebserviceEndPoint);

			WriteChatMessageResponse writeChatMessageResponse = ccmmUtility.writeChatMessageUtility(
					SESSION_DISCONNECT_BY_CUSTOMER, ccmmMeta.getContactID(), ccmmMeta.getSessionkey(),
					CIChatMessageType.SESSION_DISCONNECTED_BY_CUSTOMER, conversationContext.getRoutingContext(),
					ccmmWebserviceUrl, ccmmWebserviceEndPoint);
			try {
				writeChatMessageResponse.getWriteChatMessageResult();
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			log.debug("[{}] chatIdleReset: Close conversation from Koopid", conversationContext.getRoutingContext());
			log.debug("ccmm conversation :  Updating route to inactive state");
			
			Route route = new Route();
			route.setActive(false);
			route.setContext(conversationContext.getRoutingContext());
			route.setProviderId(providerId);
			route.setPartnerId(partnerId);
			routeRepository.save(route);
			
			// TODO : check with Deepak A that we need to clear details over here as well
			String routingContext = conversationContext.getRoutingContext();
			ccmmUtility.clearByContext(routingContext);
			
		}
	}

	public String readchat(ReadChatContext readChatContext, String ccmmWebserviceUrl, 
			String ccmmWebserviceEndPoint) {

		log.debug("ccmm readchat :  Inside readchat");
		ReadChatMessageResponse chatResponse = ccmmUtility.readChatMessageUtility(readChatContext.getRoutingContext(),
				readChatContext.getContactID(), readChatContext.getSessionkey(), ccmmWebserviceUrl, 
				ccmmWebserviceEndPoint);

		long currentTimeStamp = 0;
		ToStringBuilder allMessages = new ToStringBuilder(this);

		try {
			List<CIChatMessageReadType> arrOfMessages = chatResponse.getReadChatMessageResult().getListOfChatMessages()
					.getCIChatMessageReadType();
			if (arrOfMessages != null) {

				currentTimeStamp = chatResponse.getReadChatMessageResult().getLastReadTime().getMilliseconds();
				ConversationTimestamp conversationTimestamp = new ConversationTimestamp();
				conversationTimestamp.setContext(readChatContext.getRoutingContext());
				conversationTimestamp.setTimestamp(currentTimeStamp);
				conversationTimestampRepository.save(conversationTimestamp);

				for (int i = 0; i < arrOfMessages.size(); i++) {

					if (arrOfMessages.get(i).getChatMessageType().toString().equals(CHAT_MESSAGE_FROM_AGENT)
							&& arrOfMessages.get(i).getWriteTime().getMilliseconds() > currentTimeStamp) {
						allMessages.append(arrOfMessages.get(i).getChatMessage()).append("\n");
					}
				}
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		log.debug("ccmm readchat :  end of readchat function");

		return allMessages.toString();
	}

	/**
	 * Currently, this method will update is_active flag with the provided value
	 * In future, the scope of this method can be enhanced to update any attribute available
	 * within the provider configuration
	 * @param providerConfig
	 * @param partnerId
	 * @param providerId
	 * @return Nothing
	 * @throws NotFoundException 
	 * @throws JSONException 
	 */
	public void updateProviderConfig(KoopidProviderConfig providerConfig, String partnerId, String providerId) throws NotFoundException, JSONException {		
		
		// ToDO : Change inside this function for Redis = DONE
		KoopidProvider koopidProvider = commonUtility.getKoopidProvider(partnerId, providerId);

		if(koopidProvider == null) {
			throw new NotFoundException(String.format(Constants.PARTNER_PROVIDER_NOT_FOUND, partnerId, providerId));
		}

		boolean isProviderActive = providerConfig.isActive();
		if(koopidProvider.isActive() != isProviderActive)
		{
			koopidProvider.setActive(isProviderActive);
			koopidConfigRepository.save(koopidProvider);
			
			// TODO :- Update object in the cache as well (call redisUtil function) = DONE
			redisUtil.delCacheObject(partnerId, Constants.PROVIDER_FIELD);
			// Convert provider to JSON object as redis expecting JSON  and add it
			JSONObject providerJSON = new JSONObject(helper.objectToJson(koopidProvider));		
			redisUtil.addCacheObject(partnerId, Constants.PROVIDER_FIELD, providerJSON);
						
			List<KoopidProvider> listKoopidPartnerProvider = CcmmConfig.koopidProviderList;
			if(isProviderActive && !listKoopidPartnerProvider.contains(koopidProvider))
				listKoopidPartnerProvider.add(koopidProvider);
			else if(!isProviderActive && listKoopidPartnerProvider.contains(koopidProvider))
				listKoopidPartnerProvider.remove(koopidProvider);
				
		}
	}
	/**
	 * Fetch config object from the Koopid Provider for supplied partner/provider
	 * @param partnerId
	 * @param providerId
	 * @return String json object presenting provider details
	 * @throws NotFoundException 
	 */
	public String getConfig(String partnerId, String providerId) throws NotFoundException {
		
		// TO_DO :- Change inside this function
		KoopidProvider koopidProvider = commonUtility.getKoopidProvider(partnerId, providerId);
		
		if(koopidProvider == null) {
			throw new NotFoundException(String.format(Constants.PARTNER_PROVIDER_NOT_FOUND, partnerId, providerId));
		}
		return koopidProvider.getConfigObjects();
	}	
}
