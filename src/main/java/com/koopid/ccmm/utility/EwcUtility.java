package com.koopid.ccmm.utility;

import static com.koopid.ccmm.utility.Constants.CUST_EMAIL;
import static com.koopid.ccmm.utility.Constants.CUST_NAME;
import static com.koopid.ccmm.utility.Constants.CUST_PHONE;
import static com.koopid.ccmm.utility.Constants.EWC_DEVICE_TYPE;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koopid.ccmm.config.CollectionConfig;
import com.koopid.ccmm.entity.ConversationTimestamp;
import com.koopid.ccmm.entity.Customer;
import com.koopid.ccmm.entity.CustomerContext;
import com.koopid.ccmm.entity.EwcMeta;
import com.koopid.ccmm.entity.KoopidMeta;
import com.koopid.ccmm.entity.Route;
import com.koopid.ccmm.entity.RoutingAttrs;
import com.koopid.ccmm.ewcentity.CloseConversation;
import com.koopid.ccmm.ewcentity.CloseConversationBody;
import com.koopid.ccmm.ewcentity.IsTyping;
import com.koopid.ccmm.ewcentity.IsTypingBody;
import com.koopid.ccmm.ewcentity.MessageType;
import com.koopid.ccmm.ewcentity.NewMessage;
import com.koopid.ccmm.ewcentity.NewMessageBody;
import com.koopid.ccmm.ewcentity.RequestChat;
import com.koopid.ccmm.ewcentity.RequestChatBody;
import com.koopid.ccmm.ewcentity.RequestChatIntrinsics;
import com.koopid.ccmm.repo.ConversationTimestampRepository;
import com.koopid.ccmm.repo.CustomerRepository;
import com.koopid.ccmm.repo.EwcMetaRepository;
import com.koopid.ccmm.repo.KoopidMetaRepository;
import com.koopid.ccmm.repo.RouteRepository;
import com.koopid.ccmm.websocket.client.EwcWebsocketClientEndpoint;

@Service
public class EwcUtility {

	private static final Logger log = LoggerFactory.getLogger(EwcUtility.class);

	@Autowired
	private Helper helper;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EwcMetaRepository ewcMetaRepository;
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	EwcWebsocketClientEndpoint clientEndPoint;
	@Autowired
	EwcNotificationUtility ewcNotificationUtility;
	@Autowired
	private CommonUtility commonUtility;
	@Autowired
	ConversationTimestampRepository conversationTimestampRepository;
	@Autowired
	KoopidMetaRepository koopidMetaRepository;
	

	public RequestChat populateRequestChat(CustomerContext customerContext, int guid, String authKey,
			String skillSetName) {

		RequestChat reqChat = new RequestChat();

		reqChat.setApiVersion("1.0");
		reqChat.setType("request");

		RequestChatBody reqChatBody = new RequestChatBody();
		reqChatBody.setMethod("requestChat");
		if (guid != 0)
			reqChatBody.setGuid(guid);
		else
			reqChatBody.setGuid(null);
		reqChatBody.setAuthenticationKey(authKey);
		reqChatBody.setDeviceType(EWC_DEVICE_TYPE);
		reqChatBody.setRequestTranscript(false);
		
		/**
		 * 'channel'/'businessId' - will not be available in case of reConnectToServer
		 * In absence of channel for reconnectToServer, customer context will be picked up
		 * from the customer table which has already being processsed based on 'channel'/'businessId'
		 */
		RoutingAttrs routingAttrs = customerContext.getRoutingAttrs();
		String channel = null;
		if(routingAttrs != null) {
			channel = routingAttrs.getChannel();
		}
		Map<String, String> custDetails = commonUtility.getCustomerContextDetails(channel, customerContext);
		String customerName = custDetails.get(CUST_NAME);
		String email = custDetails.get(CUST_EMAIL);
		String phone = custDetails.get(CUST_PHONE);
		
		RequestChatIntrinsics reqChatIntrinsics = new RequestChatIntrinsics();
		reqChatIntrinsics.setEmail(email);
		reqChatIntrinsics.setName(customerName);
		reqChatIntrinsics.setPhoneNumber(phone);
		reqChatIntrinsics.setSkillset(skillSetName);

		reqChatBody.setIntrinsics(reqChatIntrinsics);
		reqChat.setBody(reqChatBody);

		return reqChat;
	}

	public void clearByContext(String routingContext)
	{	
		// TODO and check delete by routing context
		// And if its working remove all above find details		
		if(null != routingContext)
		{
			conversationTimestampRepository.deleteById(routingContext);

			koopidMetaRepository.deleteById(routingContext);
		
			ewcMetaRepository.deleteById(routingContext);
				
			customerRepository.deleteById(routingContext);

			routeRepository.deleteById(routingContext);
		
		}
		else {
			log.error("Routing context is null");
		}
		
	}
	
	
	public void connectToServer(String routingContext, String ewcWebcoketEndpoint) {
		EwcWebsocketClientEndpoint webSocketContainer = new EwcWebsocketClientEndpoint();
		webSocketContainer.setRoutingContext(routingContext);

		URI uri = null;
		try {
			uri = new URI(ewcWebcoketEndpoint);
		} catch (URISyntaxException e) {
			log.error("Incorrect URL - {}", e.getMessage());
		}

		Session socketSession = null;
		socketSession = webSocketContainer.ewcWebsocketClientEndpointContainer(uri);

		webSocketContainer.addMessageHandler(new EwcWebsocketClientEndpoint.MessageHandler() {
			public void handleMessage(String message) {
				log.debug("[{}] Process message wsMessage: {}", webSocketContainer.getRoutingContext(), message);
				processMessage(message, webSocketContainer.getRoutingContext());
			}
		});

		CollectionConfig.getChatSessionMap().put(routingContext, socketSession);
	}

	public void processMessage(String message, String routingContext) {

		MessageType notificationType = new MessageType();
		notificationType = (MessageType) helper.jsonStrToObject(message, notificationType);

		switch (notificationType.getBody().getMethod()) {

		case "newMessage":
			if (!notificationType.getType().equals("acknowledgement"))
				ewcNotificationUtility.newMessageNotification(message, routingContext);
			break;

		case "ping":			 
			ewcNotificationUtility.pingMessageNotification(message, routingContext);
			break;

		case "isTyping":			 
			ewcNotificationUtility.isTypingNotification(message, routingContext);
			log.debug("isTyping received from agent {}", routingContext);
			break;
	
		case "requestChat":
			ewcNotificationUtility.requestChatNotification(message, routingContext);
			break;

		case "queueStatus":
			ewcNotificationUtility.queueStatusNotification(message, routingContext);
			break;

		case "newParticipant":
			ewcNotificationUtility.newParticipantNotification(message, routingContext);
			break;

		case "participantLeave":
			ewcNotificationUtility.participantLeaveNotification(message, routingContext);
			break;

		case "newPushPageMessage":
			ewcNotificationUtility.newPushPageMessageNotification(message, routingContext);
			break;

		case "closeConversation":
			log.debug("Conversation closed successfully for Route {}", routingContext);			
			ewcNotificationUtility.closeConversation(routingContext);
			break;

		case "error":
			ewcNotificationUtility.handleErrorMessage(message, routingContext);
			break;

		default:
			log.info("Unhandled Notification: {}", notificationType.getBody().getMethod());
		}
	}

	public void reConnectToServer(String routingContext, Route route, EwcMeta ewcMeta, String ewcWebcoketEndpoint) {

		log.debug("[ reConnectToServer  {}  wsReset ]", routingContext);
		connectToServer(routingContext, ewcWebcoketEndpoint);
		Session userSession = CollectionConfig.getChatSessionMap().get(routingContext);

		if (userSession.isOpen()) {
			log.debug("reConnectToServer : user session is open ");
			Customer customer = customerRepository.findByContext(routingContext);

			CustomerContext customerContext = new CustomerContext();
			customerContext.setRoutingContext(routingContext);
			customerContext.setCustomerContext(routingContext);
			customerContext.setProviderId(route.getProviderId());
			customerContext.setSkillset(route.getSkill());
			customerContext.setCustomer(customer);

			RequestChat payload = populateRequestChat(customerContext, ewcMeta.getGuid(), ewcMeta.getAuthKey(),
					route.getSkill());
			log.debug("reConnectToServer request chat payload details are " + payload.toString());
			String payloadJson = helper.objectToJson(payload);

			// Request for existing chat.
			clientEndPoint.sendMessage(payloadJson, userSession);
		}

	}

	public void sendPingRequest(String pingReqJson) {
		CollectionConfig.getChatSessionMap().forEach((route, userSess) -> {
			if (userSess.isOpen()) {
				clientEndPoint.sendMessage(pingReqJson, userSess);
			}
		});
	}

	public IsTyping getIsTypingRequest() {
		IsTypingBody isTypingBody = new IsTypingBody();
		isTypingBody.setIsTyping(true);
		isTypingBody.setMethod("isTyping");

		IsTyping isTyping = new IsTyping();
		isTyping.setType("request");
		isTyping.setBody(isTypingBody);
		return isTyping;
	}

	public CloseConversation getCloseConversation(String method) {
		CloseConversation closeConversation = new CloseConversation();
		CloseConversationBody closeConversationBody = new CloseConversationBody();
		closeConversationBody.setMethod(method);
		closeConversation.setApiVersion("1.0");
		closeConversation.setType("request");
		closeConversation.setBody(closeConversationBody);
		return closeConversation;
	}

	public Customer getCustomer(CustomerContext customerContext) {
		Customer customer = new Customer();
		customer.setContext(customerContext.getRoutingContext());
		customer.setName(customerContext.getCustomer().getName());
		customer.setEmail(customerContext.getCustomer().getEmail());
		customer.setPhone(customerContext.getCustomer().getPhone());
		return customer;
	}

	public NewMessage getNewAgentMessage(String message) {
		NewMessage msgToAgent = new NewMessage();
		NewMessageBody msgToAgentBody = new NewMessageBody();

		msgToAgentBody.setMethod("newMessage");
		msgToAgentBody.setMessage(message);
		msgToAgent.setApiVersion("1.0");
		msgToAgent.setType("request");
		msgToAgent.setBody(msgToAgentBody);

		return msgToAgent;
	}

}
