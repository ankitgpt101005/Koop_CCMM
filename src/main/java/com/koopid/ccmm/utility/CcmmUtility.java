package com.koopid.ccmm.utility;

import static com.koopid.ccmm.utility.Constants.AGENT_LEFT_MESSAGE;
import static com.koopid.ccmm.utility.Constants.CHAT_EXPIRY;
import static com.koopid.ccmm.utility.Constants.CHAT_EXPIRY_MSG;
import static com.koopid.ccmm.utility.Constants.CHAT_MESSAGE_FROM_AGENT;
import static com.koopid.ccmm.utility.Constants.CONTACT_ID_NOT_RECOGNISED;
import static com.koopid.ccmm.utility.Constants.EMPTY;
import static com.koopid.ccmm.utility.Constants.INVALID_SESSION_KEY;
import static com.koopid.ccmm.utility.Constants.POLLDURATION;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koopid.basesvcs.koopidcache.CacheManager;
import com.koopid.ccmm.config.CollectionConfig;
import com.koopid.ccmm.entity.AgentQueued;
import com.koopid.ccmm.entity.CcmmMeta;
import com.koopid.ccmm.entity.ConfigObjects;
import com.koopid.ccmm.entity.ConversationContext;
import com.koopid.ccmm.entity.ConversationMessageContext;
import com.koopid.ccmm.entity.ConversationTimestamp;
import com.koopid.ccmm.entity.Customer;
import com.koopid.ccmm.entity.EwcMeta;
import com.koopid.ccmm.entity.KoopidMeta;
import com.koopid.ccmm.entity.KoopidProvider;
import com.koopid.ccmm.entity.MessageContext;
import com.koopid.ccmm.entity.ProviderPartnerId;
import com.koopid.ccmm.entity.Route;
import com.koopid.ccmm.entity.WebchatConfig;
import com.koopid.ccmm.redis.RedisUtility;
import com.koopid.ccmm.repo.AgentQueuedRepository;
import com.koopid.ccmm.repo.CcmmMetaRepository;
import com.koopid.ccmm.repo.ConversationTimestampRepository;
import com.koopid.ccmm.repo.CustomerRepository;
import com.koopid.ccmm.repo.KoopidConfigRepository;
import com.koopid.ccmm.repo.KoopidMetaRepository;
import com.koopid.ccmm.repo.RouteRepository;
import com.koopid.ccmm.service.KoopidService;
import com.koopid.ccmm.service.SessionKeyClient;
import com.koopid.ccmm.wsdl.ci_contact.ReadContact;
import com.koopid.ccmm.wsdl.ci_contact.ReadContactResponse;
import com.koopid.ccmm.wsdl.ci_customer.CIContactPriority;
import com.koopid.ccmm.wsdl.ci_customer.CIContactWriteType;
import com.koopid.ccmm.wsdl.ci_customer.RequestTextChat;
import com.koopid.ccmm.wsdl.ci_customer.RequestTextChatResponse;
import com.koopid.ccmm.wsdl.ci_utility.CICustomerReadType;
import com.koopid.ccmm.wsdl.ci_utility.GetAndUpdateAnonymousCustomerID;
import com.koopid.ccmm.wsdl.ci_utility.GetAndUpdateAnonymousCustomerIDResponse;
import com.koopid.ccmm.wsdl.ci_utility.GetAnonymousSessionKeyResponse;
import com.koopid.ccmm.wsdl.ci_utility.GetTotalQueuedToSkillset;
import com.koopid.ccmm.wsdl.ci_webcomm.ArrayOfCIChatMessageReadType;
import com.koopid.ccmm.wsdl.ci_webcomm.CIChatMessageReadType;
import com.koopid.ccmm.wsdl.ci_webcomm.CIChatMessageType;
import com.koopid.ccmm.wsdl.ci_webcomm.CIDateTime;
import com.koopid.ccmm.wsdl.ci_webcomm.ReadChatMessage;
import com.koopid.ccmm.wsdl.ci_webcomm.ReadChatMessageResponse;
import com.koopid.ccmm.wsdl.ci_webcomm.UpdateAliveTimeAndUpdateIsTyping;
import com.koopid.ccmm.wsdl.ci_webcomm.UpdateAliveTimeAndUpdateIsTypingResponse;
import com.koopid.ccmm.wsdl.ci_webcomm.WriteChatMessage;
import com.koopid.ccmm.wsdl.ci_webcomm.WriteChatMessageResponse;

@Service
public class CcmmUtility {

	private static final Logger log = LoggerFactory.getLogger(CcmmUtility.class);

	@Autowired
	private SessionKeyClient sessionKeyClient;
	@Autowired
	private RouteRepository routeRepository;
	@Autowired
	private ConversationTimestampRepository conversationTimestampRepository;
	@Autowired
	private KoopidService koopidService;
	@Autowired
	private CcmmMetaRepository ccmmMetaRepository;
	@Autowired
	private AgentQueuedRepository agentQueuedRepository;
	@Autowired
	private KoopidConfigRepository koopidConfigRepository;
	@Autowired	
	KoopidMetaRepository koopidMetaRepository;
	@Autowired
	private CommonUtility commonUtility;
	@Autowired
	private CustomerRepository customerRepository;	
	@Autowired
	private Helper helper;
	@Autowired
	RedisUtility redisUtil;

	public Long getCustomerIdUtility(GetAnonymousSessionKeyResponse getAnonymousSessionKeyResponse,
			String routeContext, String partnerId, String providerId, String skill, String type,
			String customerName, String email, String phone) {
		GetAndUpdateAnonymousCustomerID getAndUpdateAnonymousCustomerID;
		GetAndUpdateAnonymousCustomerIDResponse getAndUpdateAnonymousCustomerIDResponse;
		Long myCustomerID;
		CICustomerReadType ciCustomerReadType = new CICustomerReadType();
		ciCustomerReadType.setFirstName(customerName);
		getAndUpdateAnonymousCustomerID = new GetAndUpdateAnonymousCustomerID();

		getAndUpdateAnonymousCustomerID.setEmailAddress(email);
		getAndUpdateAnonymousCustomerID
				.setLoginResult(getAnonymousSessionKeyResponse.getGetAnonymousSessionKeyResult());
		getAndUpdateAnonymousCustomerID.setPhoneNumber(phone);
		getAndUpdateAnonymousCustomerID.setThisCustomer(ciCustomerReadType);
		
		//ProviderPartnerId providerPartnerId = commonUtility.getProviderPartnerId(partnerId,providerId);
		
		// TO_DO :- Take object from the cache instead of database
		//KoopidProvider koopidProvider = koopidConfigRepository.findByProviderPartnerId(providerPartnerId);
		
		// This is new code to fetch details from cache
		CacheManager<JSONObject> jsonCacheManager = redisUtil.getCacheObject();
		JSONObject koopidJson = jsonCacheManager.hget(providerId, Constants.PROVIDER_FIELD);
		KoopidProvider koopidProvider = new KoopidProvider();
		koopidProvider = (KoopidProvider) helper.jsonStrToObject(koopidJson.toString(), koopidProvider);

		
		ConfigObjects configObjects = new ConfigObjects();
		configObjects = (ConfigObjects) helper.jsonStrToObject(koopidProvider.getConfigObjects(), configObjects);
		
		WebchatConfig webchatConfig = configObjects.getWebchatConfig();
		
		getAndUpdateAnonymousCustomerIDResponse = sessionKeyClient.getMyCustomerId(getAndUpdateAnonymousCustomerID,
				webchatConfig.getWebServiceUrl(), webchatConfig.getWebServiceEndpoint());
		myCustomerID = getAndUpdateAnonymousCustomerIDResponse.getGetAndUpdateAnonymousCustomerIDResult();
		Route route = new Route();
		route.setPartnerId(partnerId);
		route.setProviderId(providerId);
		route.setContext(routeContext);
		route.setSkill(skill);
		route.setType(type);
		routeRepository.save(route);
		return myCustomerID;
	}

	public RequestTextChatResponse requestTextChatUtility(Long myCustomerID, String anonymousSessionKey,
			String routingContext, String ccmmWebserviceUrl, String ccmmWebserviceEndPoint, Long skillsetId, String topic) {
		
		CIContactWriteType ciContactWriteType = new CIContactWriteType();
		// TODO :- change following hardcoded value
		ciContactWriteType.setSkillsetID(skillsetId);
		ciContactWriteType.setPriority(CIContactPriority.PRIORITY_3_MEDIUM_HIGH);
		ciContactWriteType.setSubject(topic);
		ciContactWriteType.setTextHTML("");
		RequestTextChat requestTextChat;
		RequestTextChatResponse requestTextChatResponse;
		requestTextChat = new RequestTextChat();
		requestTextChat.setCustID(myCustomerID);
		requestTextChat.setCreateAsClosed(false);
		requestTextChat.setSessionKey(anonymousSessionKey);
		requestTextChat.setNewContact(ciContactWriteType);
		requestTextChatResponse = sessionKeyClient.requestTextChat(requestTextChat, ccmmWebserviceUrl, 
				ccmmWebserviceEndPoint);
		return requestTextChatResponse;
	}

	public ReadChatMessageResponse readChatMessageUtility(String routintContext, Long contactId,
			String anonymousSessionKey, String ccmmWebserviceUrl, 
			String ccmmWebserviceEndPoint) {
		ReadChatMessageResponse readChatMessageResponse = null;
		ReadChatMessage readChatMessage = new ReadChatMessage();
		CIDateTime ciDateTime = new CIDateTime();
		readChatMessage.setSessionKey(anonymousSessionKey);
		readChatMessage.setContactID(contactId);
		readChatMessage.setIsWriting(false);
		readChatMessage.setLastReadTime(ciDateTime);
		readChatMessageResponse = hasAgentAccepted(readChatMessage, routintContext, 
				ccmmWebserviceUrl, ccmmWebserviceEndPoint);
		ConversationTimestamp conversationTimestamp = new ConversationTimestamp();
		conversationTimestamp.setContext(routintContext);
		if (readChatMessageResponse != null) {
			conversationTimestamp.setTimestamp(
					readChatMessageResponse.getReadChatMessageResult().getLastReadTime().getMilliseconds());
		}
		conversationTimestampRepository.save(conversationTimestamp);
		return readChatMessageResponse;

	}

	private ReadChatMessageResponse hasAgentAccepted(ReadChatMessage readChatMessage, String routingContext, 
			String ccmmWebserviceUrl, String ccmmWebserviceEndPoint) {
		ReadChatMessageResponse readChatMessageResponse = null;
		ArrayOfCIChatMessageReadType arrOfMessages = null;
		AgentQueued agentQueued = agentQueuedRepository.findByContext(routingContext);
		try {
			readChatMessageResponse = sessionKeyClient.getReadChatMessage(readChatMessage, ccmmWebserviceUrl,
					ccmmWebserviceEndPoint);

			for (int i = 0; i < 3; i++) {
				if (readChatMessageResponse != null) {
					arrOfMessages = readChatMessageResponse.getReadChatMessageResult().getListOfChatMessages();
					if (arrOfMessages != null) {
						break;
					}
					Thread.sleep(3000);
					readChatMessageResponse = sessionKeyClient.getReadChatMessage(readChatMessage, ccmmWebserviceUrl,
							ccmmWebserviceEndPoint);
				}
			}

		} catch (Exception e) {
			if (e.getMessage().contains(CONTACT_ID_NOT_RECOGNISED) || e.getMessage().contains(INVALID_SESSION_KEY)) {
				if (agentQueued != null) {
					setRouteInactive(routingContext);
					agentQueuedRepository.delete(agentQueued);
				}

				log.debug("[{}] {}  ", routingContext, AGENT_LEFT_MESSAGE);
			}
		}

		return readChatMessageResponse;
	}

	public WriteChatMessageResponse writeChatMessageUtility(String text, Long contactId, String anonymousSessionKey,
			CIChatMessageType ciChatMessageType, String routingContext, String ccmmWebserviceUrl, 
			String ccmmWebserviceEndPoint) {
		WriteChatMessage writeChatMessage;
		WriteChatMessageResponse writeChatMessageResponse = null;
		writeChatMessage = new WriteChatMessage();
		writeChatMessage.setContactID(contactId);
		writeChatMessage.setSessionKey(anonymousSessionKey);
		writeChatMessage.setChatMessageType(ciChatMessageType);
		writeChatMessage.setMessage(text);
		AgentQueued agentQueued = agentQueuedRepository.findByContext(routingContext);
		try {
			writeChatMessageResponse = sessionKeyClient.getWriteChatMessage(writeChatMessage, ccmmWebserviceUrl,
					ccmmWebserviceEndPoint);
		} catch (Exception e) {
			if (e.getMessage().contains(CONTACT_ID_NOT_RECOGNISED) || e.getMessage().contains(INVALID_SESSION_KEY)) {
				if (agentQueued != null) {
					agentQueuedRepository.delete(agentQueued);
				}

				setRouteInactive(routingContext);
				CollectionConfig.getRouteList().remove(routingContext);
				log.debug("[{}] {}  ", routingContext, AGENT_LEFT_MESSAGE);
			}
		}
		return writeChatMessageResponse;
	}

	public UpdateAliveTimeAndUpdateIsTypingResponse updateAliveTimeAndUpdateIsTypingUtility(
			MessageContext messageContext, String anonymousSessionKey, String ccmmWebserviceUrl, 
			String ccmmWebserviceEndPoint) {
		CcmmMeta ccmmMeta = ccmmMetaRepository.findByContext(messageContext.getRoutingContext());
		UpdateAliveTimeAndUpdateIsTyping updateAliveTimeAndUpdateIsTyping;
		UpdateAliveTimeAndUpdateIsTypingResponse updateAliveTimeAndUpdateIsTypingResponse = null;
		updateAliveTimeAndUpdateIsTyping = new UpdateAliveTimeAndUpdateIsTyping();
		updateAliveTimeAndUpdateIsTyping.setContactID(ccmmMeta.getContactID());
		updateAliveTimeAndUpdateIsTyping.setIsTyping(true);
		updateAliveTimeAndUpdateIsTyping.setSessionKey(anonymousSessionKey);
		AgentQueued agentQueued = agentQueuedRepository.findByContext(messageContext.getRoutingContext());
		try {
			updateAliveTimeAndUpdateIsTypingResponse = sessionKeyClient
					.updateAliveTimeAndUpdateIsTyping(updateAliveTimeAndUpdateIsTyping, ccmmWebserviceUrl,
							ccmmWebserviceEndPoint);
		} catch (Exception e) {
			if (e.getMessage().contains(CONTACT_ID_NOT_RECOGNISED) || e.getMessage().contains(INVALID_SESSION_KEY)) {
				if (agentQueued != null) {
					setRouteInactive(messageContext.getRoutingContext());
					agentQueuedRepository.delete(agentQueued);
				}

				log.debug("[{}] {}  ", messageContext.getRoutingContext(), AGENT_LEFT_MESSAGE);
			}
		}

		return updateAliveTimeAndUpdateIsTypingResponse;
	}

	public void pollForMessage(String routingContext, Long contactId, String anonymousSessionKey, String providerId,
			String partnerId) {
		Route route = routeRepository.findByContext(routingContext);
		ConversationTimestamp conversationTimestamp = conversationTimestampRepository.findByContext(routingContext);
		CcmmMeta ccmmMeta = ccmmMetaRepository.findByContext(routingContext);
		Long msgTime = conversationTimestamp.getTimestamp();

		ConversationMessageContext conversationMessageContext = getConversationMessageContext(routingContext,
				providerId, ccmmMeta);
		ConfigObjects configObjects = commonUtility.getProviderConfigObjects(partnerId, providerId);
		WebchatConfig webchatConfig = configObjects.getWebchatConfig();

		if (msgTime != null) {
			do {
				ReadChatMessageResponse readChatMessageResponse = null;
				CIDateTime ciDateTime = new CIDateTime();
				ciDateTime.setMilliseconds(msgTime);
				try {
					readChatMessageResponse = getReadChatMessage(contactId, anonymousSessionKey, 
							ciDateTime, webchatConfig.getWebServiceUrl(), webchatConfig.getWebServiceEndpoint());
					List<CIChatMessageReadType> arrOfMessages = readChatMessageResponse.getReadChatMessageResult()
							.getListOfChatMessages().getCIChatMessageReadType();
					if (arrOfMessages != null) {

						processMsgFromAgent(providerId, partnerId, conversationTimestamp, msgTime,
								conversationMessageContext, arrOfMessages);
						msgTime = readChatMessageResponse.getReadChatMessageResult().getLastReadTime()
								.getMilliseconds();
						conversationTimestamp.setTimestamp(msgTime);
						conversationTimestampRepository.save(conversationTimestamp);
					} else {
						checkChatExpiry(route.getContext(), conversationTimestamp.getTimestamp(),
								readChatMessageResponse);
					}

					Thread.sleep(POLLDURATION);
				} catch (Exception ex) {
					try {
						if (ex.getMessage().contains(CONTACT_ID_NOT_RECOGNISED)
								|| ex.getMessage().contains(INVALID_SESSION_KEY)) {
							log.debug("[{}] {}  ", routingContext, AGENT_LEFT_MESSAGE);
							CollectionConfig.getRouteList().remove(routingContext);
							setRouteInactive(routingContext);
							route = routeRepository.findByContext(routingContext);
						}
						Thread.sleep(POLLDURATION);
					} catch (Exception e) {
						// Every time while polling, if there is no message from agent it goes in
						// exception with null in message.
					}
				}
			} while (route.isActive());
		}
	}

	public void processMsgFromAgent(String providerId, String partnerId, ConversationTimestamp conversationTimestamp,
			Long msgTime, ConversationMessageContext conversationMessageContext,
			List<CIChatMessageReadType> arrOfMessages) {
		ArrayList<String> allMessages = new ArrayList<>();
		for (int i = 0; i < arrOfMessages.size(); i++) {
			if (arrOfMessages.get(i).getChatMessageType().toString().equals(CHAT_MESSAGE_FROM_AGENT)
					&& arrOfMessages.get(i).getWriteTime().getMilliseconds() > msgTime) {
				allMessages.add(arrOfMessages.get(i).getChatMessage());
			}
		}

		if (!allMessages.isEmpty()) {
			allMessages.forEach(message -> {
				if (!message.equals(EMPTY)) {
					koopidService.sendMsgToCustomer(message, conversationTimestamp.getTimestamp(),
							conversationMessageContext, providerId, partnerId);
				}
			});
			allMessages.clear();
			arrOfMessages.clear();
		}
	}

	public ReadChatMessageResponse getReadChatMessage(Long contactId, String anonymousSessionKey,
			CIDateTime ciDateTime, String ccmmWebserviceUrl, String ccmmWebserviceEndPoint) {
		ReadChatMessageResponse readChatMessageResponse;
		ReadChatMessage readChatMessage = new ReadChatMessage();
		readChatMessage.setSessionKey(anonymousSessionKey);
		readChatMessage.setContactID(contactId);
		readChatMessage.setIsWriting(false);
		readChatMessage.setLastReadTime(ciDateTime);
		readChatMessageResponse = sessionKeyClient.getReadChatMessage(readChatMessage, ccmmWebserviceUrl, 
				ccmmWebserviceEndPoint);
		return readChatMessageResponse;
	}

	public ConversationMessageContext getConversationMessageContext(String routingContext, String providerId,
			CcmmMeta ccmmMeta) {
		ConversationMessageContext conversationMessageContext = new ConversationMessageContext();
		conversationMessageContext.setProviderId(providerId);
		conversationMessageContext.setAgentId(ccmmMeta.getAgentId());
		conversationMessageContext.setRoutingContext(routingContext);
		conversationMessageContext.setChatId(ccmmMeta.getChatId());
		return conversationMessageContext;
	}

	private void checkChatExpiry(String routingContext, Long lastReadMsg,
			ReadChatMessageResponse readChatMessageResponse) {

		long maxDuration = MILLISECONDS.convert(CHAT_EXPIRY, MINUTES);
		try {
			long duration = readChatMessageResponse.getReadChatMessageResult().getLastReadTime().getMilliseconds()
					- lastReadMsg;
			if (duration >= maxDuration) {
				setRouteInactive(routingContext);
			}
		} catch (Exception e) {

			if (e.getMessage().contains(CONTACT_ID_NOT_RECOGNISED) || e.getMessage().contains(INVALID_SESSION_KEY)) {
				setRouteInactive(routingContext);
				log.debug("[{}] {}  ", routingContext, CHAT_EXPIRY_MSG);
			}
		}
	}

	public KoopidMeta getKoopidMeta(MessageContext messageContext) {
		KoopidMeta koopidMeta = new KoopidMeta();
		koopidMeta.setContext(messageContext.getRoutingContext());
		koopidMeta.setSenderName(messageContext.getMsg().getSenderName());
		if (messageContext.getMsg().getData() != null) {
			koopidMeta.setPath(messageContext.getMsg().getData().getAction().getPath());
		}
		koopidMeta.setSender(messageContext.getMsg().getSender());
		koopidMeta.setTz(messageContext.getMsg().getTz());
		koopidMeta.setAck(messageContext.getMsg().isAck());
		koopidMeta.setText(messageContext.getMsg().getText());
		koopidMeta.setId(messageContext.getMsg().getId());
		koopidMeta.setType(messageContext.getMsg().getType());
		koopidMeta.setMediatype(messageContext.getMsg().getMediatype());
		koopidMeta.setTs(messageContext.getMsg().getTs());
		return koopidMeta;
	}

	public ConversationMessageContext getMessageContext(ConversationContext conversationContext) {
		ConversationMessageContext conversationMessageContext = new ConversationMessageContext();
		conversationMessageContext.setProviderId(conversationContext.getProviderId());
		conversationMessageContext.setAgentId(conversationContext.getAgentId());
		conversationMessageContext.setRoutingContext(conversationContext.getRoutingContext());
		conversationMessageContext.setChatId(conversationContext.getChatId());
		return conversationMessageContext;
	}

	public void setRouteInactive(String routingContext) {
		Route route = routeRepository.findByContext(routingContext);
		route.setActive(false);
		routeRepository.save(route);
	}

	public String getAgentIdReadContact(String routingContext) {
		CcmmMeta ccmmMeta = ccmmMetaRepository.findByContext(routingContext);

		Route route = routeRepository.findByContext(routingContext);
		ConfigObjects configObjects = commonUtility.getProviderConfigObjects(route.getPartnerId(), route.getProviderId());
		WebchatConfig webchatConfig = configObjects.getWebchatConfig();

		ReadContact readContact = new ReadContact();
		readContact.setId(ccmmMeta.getContactID());
		readContact.setSessionKey(ccmmMeta.getSessionkey());
		ReadContactResponse readContactResponse = sessionKeyClient.getReadContactResponse(readContact, 
				webchatConfig.getWebServiceUrl(), webchatConfig.getWebServiceEndpoint());

		return configObjects.getAgentConfig().getIdPrefix() + "-"
				+ readContactResponse.getReadContactResult().getAgent().getId() + "@"
				+ configObjects.getAgentConfig().getDomain();
	}

	public GetTotalQueuedToSkillset getAgentPollBody(AgentQueued agentQueued, CcmmMeta ccmmMeta) {
		GetTotalQueuedToSkillset getTotalQueuedToSkillset = new GetTotalQueuedToSkillset();
		getTotalQueuedToSkillset.setSessionKey(ccmmMeta.getSessionkey());
		getTotalQueuedToSkillset.setSkillsetID(agentQueued.getSkillSetId());
		return getTotalQueuedToSkillset;
	}
	
	/**
	 * Clear all the details of the context
	 * @param routingContext
	 */
	public void clearByContext(String routingContext)
	{
		if(null != routingContext)
		{
			ccmmMetaRepository.deleteById(routingContext);
			
			conversationTimestampRepository.deleteById(routingContext);

			koopidMetaRepository.deleteById(routingContext);
						
			customerRepository.deleteById(routingContext);
		
			routeRepository.deleteById(routingContext);
			
		}
		
	}


}
