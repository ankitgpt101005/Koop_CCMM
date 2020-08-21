package com.koopid.ccmm.utility;

import static com.koopid.ccmm.utility.Constants.CHAT_NOT_STARTED;
import static com.koopid.ccmm.utility.Constants.INVALID_SESSION;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koopid.basesvcs.koopidcache.CacheManager;
import com.koopid.ccmm.config.CollectionConfig;
import com.koopid.ccmm.entity.Agent;
import com.koopid.ccmm.entity.ConfigObjects;
import com.koopid.ccmm.entity.ConversationContext;
import com.koopid.ccmm.entity.ConversationMessageContext;
import com.koopid.ccmm.entity.EwcMeta;
import com.koopid.ccmm.entity.KoopidProvider;
import com.koopid.ccmm.entity.ProviderPartnerId;
import com.koopid.ccmm.entity.Route;
import com.koopid.ccmm.entity.RouteResponse;
import com.koopid.ccmm.ewcentity.NewMessageNotification;
import com.koopid.ccmm.ewcentity.NewParticipantNotification;
import com.koopid.ccmm.ewcentity.ParticipantLeaveNotification;
import com.koopid.ccmm.ewcentity.PushPageMessageNotification;
import com.koopid.ccmm.ewcentity.RequestChatNotification;
import com.koopid.ccmm.redis.RedisUtility;
import com.koopid.ccmm.repo.EwcMetaRepository;
import com.koopid.ccmm.repo.KoopidConfigRepository;
import com.koopid.ccmm.repo.RouteRepository;
import com.koopid.ccmm.service.KoopidService;

@Service
public class EwcNotificationUtility {

	private static final Logger log = LoggerFactory.getLogger(EwcNotificationUtility.class);

	@Autowired
	private Helper helper;
	@Autowired
	private EwcMetaRepository ewcMetaRepository;
	@Autowired
	private RouteRepository routeRepository;
	@Autowired
	private KoopidConfigRepository koopidConfigRepository;
	@Autowired
	private KoopidService koopidService;
	@Autowired
	private CommonUtility commonUtility;
	@Autowired
	private RedisUtility redisUtil;
	@Autowired
	private EwcUtility ewcUtil;
	

	public void requestChatNotification(String message, String routingContext) {

		RequestChatNotification reqChat = (RequestChatNotification) helper.jsonStrToObject(message,
				new RequestChatNotification());

		EwcMeta ewcMeta = new EwcMeta();
		ewcMeta.setAuthKey(reqChat.getBody().getAuthenticationKey());
		ewcMeta.setContext(routingContext);
		ewcMeta.setGuid(reqChat.getBody().getGuid());

		Boolean isContext = ewcMetaRepository.existsById(routingContext);

		// In case of reconnection.
		if (Boolean.TRUE.equals(isContext)) {
			EwcMeta ewcM = ewcMetaRepository.findByContext(routingContext);
			ewcMeta.setAgentId(ewcM.getAgentId());
			ewcMeta.setChatId(ewcM.getChatId());
		}

		ewcMetaRepository.save(ewcMeta);
	}

	// Queue status is not used in current implementation.
	public void queueStatusNotification(String message, String routingContext) {

		log.info("Queue Status Notification - Message {} Routing context {}", message, routingContext);
	}

	public void newParticipantNotification(String message, String routingContext) {

		NewParticipantNotification newParticipant = (NewParticipantNotification) helper.jsonStrToObject(message,
				new NewParticipantNotification());
		
		EwcMeta ewcMeta = ewcMetaRepository.findByContext(routingContext);
		Route route = routeRepository.findByContext(routingContext);
		if(!route.isActive())
		{
			//ProviderPartnerId providerPartnerId = commonUtility.getProviderPartnerId(route.getPartnerId(),route.getProviderId());	
			//KoopidProvider koopidProvider = koopidConfigRepository.findByProviderPartnerId(providerPartnerId);
			// Following is the new code for cache
			// TODO : Method refactoring : move it to utility
			CacheManager<JSONObject> jsonCacheManager = redisUtil.getCacheObject();
			JSONObject koopidJson = jsonCacheManager.hget(route.getProviderId(), Constants.PROVIDER_FIELD);
			KoopidProvider koopidProvider = new KoopidProvider();
			koopidProvider = (KoopidProvider) helper.jsonStrToObject(koopidJson.toString(), koopidProvider);

			ConfigObjects configObjects = new ConfigObjects();
			configObjects = (ConfigObjects) helper.jsonStrToObject(koopidProvider.getConfigObjects(), configObjects);

			RouteResponse routeResponse = new RouteResponse();
			routeResponse.setProviderId(route.getProviderId());
			routeResponse.setRoutingContext(routingContext);
			routeResponse.setState("route");

			ewcMeta.setAgentId(newParticipant.getBody().getAgentId());
			ewcMetaRepository.save(ewcMeta);
			String customAgentId = configObjects.getAgentConfig().getIdPrefix() + "-"
					+ newParticipant.getBody().getAgentId() + "@" + configObjects.getAgentConfig().getDomain();

			Agent agent = new Agent();
			agent.setName(newParticipant.getBody().getDisplayName());
			agent.setEmail(customAgentId);
			agent.setPassword(configObjects.getAgentConfig().getDefaultPassword());
			agent.setPhone(configObjects.getAgentConfig().getDefaultPhone());
			routeResponse.setAgent(agent);
			koopidService.routingToKoopid(routeResponse, route.getPartnerId());			
		}
		else {
			
			// TODO : Create message format and send it to the Koopid 
			// If chat is already exist and new participent add then send info type of message that new agent is joined
			log.debug("Inside newParticipantNotification : Route is not active");
		}
		
	}

	public void participantLeaveNotification(String message, String routingContext) {

		ParticipantLeaveNotification participantLeave = (ParticipantLeaveNotification) helper.jsonStrToObject(message,
				new ParticipantLeaveNotification());

		// if endchat = true, intimate client about the same.
		// We are not checking number of participents in the message
		if (Boolean.TRUE.equals(participantLeave.getBody().getEndChatFlag())) {
			
			ConversationContext conversationContext = new ConversationContext();
			EwcMeta ewcMeta = ewcMetaRepository.findByContext(routingContext);
			Route route = routeRepository.findByContext(routingContext);

			conversationContext.setAgentId(ewcMeta.getAgentId());
			conversationContext.setChatId(ewcMeta.getChatId());
			conversationContext.setCmd("end");
			conversationContext.setProviderId(route.getProviderId());
			conversationContext.setRoutingContext(routingContext);

			koopidService.sendConversationClose(conversationContext, route);
			
			// TODO Once chat ends delete the details = DONE
			ewcUtil.clearByContext(routingContext);
		}
		else
		{
			//TODO We need to send notification to the koopid
			log.debug("Participent leaves the conversation");
		}
		 
	}

	public void closeConversation(String routingContext)
	{
		log.debug("Closing the conversation");
		
		try {
			log.debug("EWC conversation Close connection");				
			CollectionConfig.getChatSessionMap().get(routingContext).close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Delete the context details
		ewcUtil.clearByContext(routingContext);

	}

	public void pingMessageNotification(String message, String routingContext) {

		log.debug("Inside send ping message {} ", message);

	}

	public void isTypingNotification(String message, String routingContext) {

		log.debug("Inside isTypingNotification message {} ", message);
		
		try {
			
			koopidService.sendIsTypingUpdateToCustomer(message,routingContext);
		
		} catch (JSONException e) {
			
			log.error("JSON Exception while processing the isTypingrequest", e);
		}
	}

	public void newMessageNotification(String message, String routingContext) {
		NewMessageNotification newMessage = (NewMessageNotification) helper.jsonStrToObject(message,
				new NewMessageNotification());

		Route route = routeRepository.findByContext(routingContext);
		EwcMeta ewcMeta = ewcMetaRepository.findByContext(routingContext);
		if (ewcMeta.getChatId() != null) {
			log.debug("newMessage {} ", newMessage);
			sendEwcMessage(newMessage.getBody().getMessage(), newMessage.getBody().getTimestamp(), route, ewcMeta);
		}
	}

	public void handleErrorMessage(String message, String routingContext) {
		if (message.contains(CHAT_NOT_STARTED) || message.contains(INVALID_SESSION)) {
			CollectionConfig.getChatSessionMap().remove(routingContext);
		}
		log.error("Error from websocket server for routing context {} : {}", routingContext, message);
	}

	public void newPushPageMessageNotification(String message, String routingContext) {
		PushPageMessageNotification newPushMessage = (PushPageMessageNotification) helper.jsonStrToObject(message,
				new PushPageMessageNotification());

		Route route = routeRepository.findByContext(routingContext);
		EwcMeta ewcMeta = ewcMetaRepository.findByContext(routingContext);
		if (ewcMeta.getChatId() != null) {
			log.debug("newPushMessage {} ", newPushMessage);
			sendEwcMessage(newPushMessage.getBody().getPagePushURL(), newPushMessage.getBody().getTimestamp(), route,
					ewcMeta);
		}
	}

	public void sendEwcMessage(String msg, Long timestamp, Route route, EwcMeta ewcMeta) {
		ConversationMessageContext conversationMessageContext = new ConversationMessageContext();
		conversationMessageContext.setProviderId(route.getProviderId());
		conversationMessageContext.setAgentId(ewcMeta.getAgentId());
		conversationMessageContext.setRoutingContext(route.getContext());
		conversationMessageContext.setChatId(ewcMeta.getChatId());

		koopidService.sendMsgToCustomer(msg, timestamp, conversationMessageContext, route.getProviderId(),
				route.getPartnerId());
	}

}
