package com.koopid.ccmm.service;

import static com.koopid.ccmm.utility.Constants.CALLBACKS;
import static com.koopid.ccmm.utility.Constants.CONVERSATION;
import static com.koopid.ccmm.utility.Constants.DELIVERED;
import static com.koopid.ccmm.utility.Constants.ROUTING;
import static com.koopid.ccmm.utility.Constants.SEND_MESSAGE;
import static com.koopid.ccmm.utility.Constants.WHITELIST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.koopid.basesvcs.koopidcache.CacheManager;
import com.koopid.ccmm.entity.AgentContext;
import com.koopid.ccmm.entity.Callback;
import com.koopid.ccmm.entity.ConfigObjects;
import com.koopid.ccmm.entity.ConversationContext;
import com.koopid.ccmm.entity.ConversationMessageContext;
import com.koopid.ccmm.entity.CustomerContext;
import com.koopid.ccmm.entity.IsTypingResponse;
import com.koopid.ccmm.entity.KoopidProvider;
import com.koopid.ccmm.entity.Message;
import com.koopid.ccmm.entity.MessageContext;
import com.koopid.ccmm.entity.MessageWhitelist;
import com.koopid.ccmm.entity.ProviderConfig;
import com.koopid.ccmm.entity.ProviderPartnerId;
import com.koopid.ccmm.entity.Route;
import com.koopid.ccmm.entity.RouteResponse;
import com.koopid.ccmm.entity.SendMessage;
import com.koopid.ccmm.redis.RedisUtility;
import com.koopid.ccmm.repo.KoopidConfigRepository;
import com.koopid.ccmm.repo.RouteRepository;
import com.koopid.ccmm.utility.CcmmUtility;
import com.koopid.ccmm.utility.CommonUtility;
import com.koopid.ccmm.utility.Constants;
import com.koopid.ccmm.utility.Helper;

@Service
public class KoopidService {

	private static final Logger log = LoggerFactory.getLogger(KoopidService.class);

	@Autowired
	private KoopidConfigRepository koopidConfigRepository;
	@Autowired
	private Helper helper;
	@Autowired
	private CommonUtility commonUtility;
	@Autowired
	private CcmmUtility ccmmUtility;
	@Autowired
	RedisUtility redisUtil;
	@Autowired
	private RouteRepository routeRepository;

	RestTemplate restTemplate = new RestTemplate();
	List<Callback> callbacks = new ArrayList<>();

	Callback msgRecvCallback = new Callback();
	Callback conversationCallback = new Callback();
	ProviderConfig providerConfig = new ProviderConfig();

	public void callbackRegistration(KoopidProvider koopidProvider) {
		log.info("* Koopid {} Gateway ", koopidProvider.getWebchatType());
		ConfigObjects configObjects = new ConfigObjects();
		configObjects = (ConfigObjects) helper.jsonStrToObject(koopidProvider.getConfigObjects(), configObjects);
		log.info("*    listening    => {}", configObjects.getWebchatConfig().getWebServiceUrl());
		log.info("* Koopid Callbacks:");

		Map<String, String> providerpartnerMap = new HashMap<>();
		providerpartnerMap.put(koopidProvider.getProviderPartnerId().getPartnerId(),
				koopidProvider.getProviderPartnerId().getProviderId());
		for (Callback callback : configObjects.getProviderConfig().getCallbacks()) {
			Callback routeCallback = new Callback();
			String callBackApi = callback.getCallback();

			String customerContext = "?webChatType=" + koopidProvider.getWebchatType() + "&authKey=" + koopidProvider.getAuthKey();
			callBackApi = callBackApi + "/" + koopidProvider.getProviderPartnerId().getPartnerId() + "/"
					+ koopidProvider.getProviderPartnerId().getProviderId() + customerContext;
			routeCallback.setApi(callback.getApi());

			log.info("*    {}   => {}", callback.getApi(), callback.getCallback());
			routeCallback.setCallback(callBackApi);
			callbacks.add(routeCallback);
		}
		providerConfig.setProviderId(koopidProvider.getProviderPartnerId().getProviderId());
		providerConfig.setCallbacks(callbacks);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ProviderConfig> entity = new HttpEntity<>(providerConfig, headers);
		String callbackReg = commonUtility.getKoopidURL(configObjects, CALLBACKS);
		log.info("Register callbacks at Koopid, URL {} ", callbackReg);
		String result = restTemplate.exchange(callbackReg, HttpMethod.POST, entity, String.class).getBody();
		log.info("**********************************************************************");
		log.debug("[PostCallbacks] PostCallbacks::Callbacks - {}", helper.objectToJson(providerConfig));
		log.debug("[PostCallbacks] Callbacks::Response - {}", result);
	}

	public void messageWhitelisting(KoopidProvider koopidProvider) {
		ConfigObjects configObjects = new ConfigObjects();
		configObjects = (ConfigObjects) helper.jsonStrToObject(koopidProvider.getConfigObjects(), configObjects);
		MessageWhitelist messageWhitelist = new MessageWhitelist();
		messageWhitelist.setProviderId(koopidProvider.getProviderPartnerId().getProviderId());
		messageWhitelist.setEntity(configObjects.getMessageWhitelist().getEntity());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<MessageWhitelist> entity = new HttpEntity<>(messageWhitelist, headers);
		String msgWhiteListing = commonUtility.getKoopidURL(configObjects, WHITELIST);
		String result = restTemplate.exchange(msgWhiteListing, HttpMethod.POST, entity, String.class).getBody();
		log.debug("[PostCallbacks] PostCallbacks::Whitelist - {}", helper.objectToJson(messageWhitelist));
		log.debug("[PostCallbacks] Whitelist::Response - {}", result);
	}

	public AgentContext getAgentContext(CustomerContext customerContext, String providerId, String state) {
		AgentContext agentContext = new AgentContext();
		agentContext.setRoutingContext(customerContext.getRoutingContext());
		agentContext.setProviderId(providerId);
		agentContext.setState(state);
		return agentContext;
	}

	public void routingToKoopid(RouteResponse routeResponse, String partnerId) {
		//ProviderPartnerId providerPartnerId = commonUtility.getProviderPartnerId(partnerId,routeResponse.getProviderId());
		
		// TO-DO :- Take object from the Redis cache = DONE
		//KoopidProvider koopidProvider = koopidConfigRepository.findByProviderPartnerId(providerPartnerId);
		KoopidProvider koopidProvider =  getProviderFromCache(routeResponse.getProviderId());
		
		String routingContext = routeResponse.getRoutingContext();
		ConfigObjects configObjects = new ConfigObjects();
		configObjects = (ConfigObjects) helper.jsonStrToObject(koopidProvider.getConfigObjects(), configObjects);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<RouteResponse> entity = new HttpEntity<>(routeResponse, headers);
		String koopidRoutingUrl = commonUtility.getKoopidURL(configObjects, ROUTING);
		log.debug("[{}] PostRoutingResponse: {}", routingContext, helper.objectToJson(routeResponse));
		String response = restTemplate.exchange(koopidRoutingUrl, HttpMethod.POST, entity, String.class).getBody();
		log.debug("[{}] PostRoutingResponse: {}", routingContext, response);
	}
	
	/**
	 * This is new function to give cached provider object
	 * @return KoopidProviderObject
	 */
	public KoopidProvider getProviderFromCache(String providerId){
		
		CacheManager<JSONObject> jsonCacheManager = redisUtil.getCacheObject();
		JSONObject koopidJson = jsonCacheManager.hget(providerId, Constants.PROVIDER_FIELD);
		KoopidProvider koopidProvider = new KoopidProvider();
		koopidProvider = (KoopidProvider) helper.jsonStrToObject(koopidJson.toString(), koopidProvider);
		
		return koopidProvider;

	}

	public void sendMsgToCustomer(String msgText, Long timeStamp, ConversationMessageContext conversationMessageContext,
			String providerId, String partnerId) {

		//ProviderPartnerId providerPartnerId = commonUtility.getProviderPartnerId(partnerId, providerId);

		// TO-DO :- Take object from the Redis cache = DONE
		//KoopidProvider koopidProvider = koopidConfigRepository.findByProviderPartnerId(providerPartnerId);
		KoopidProvider koopidProvider =  getProviderFromCache(providerId);
		
		String routingContext = conversationMessageContext.getRoutingContext();
		String configObjectJson = koopidProvider.getConfigObjects();
		ConfigObjects configObjects = new ConfigObjects();
		configObjects = (ConfigObjects) helper.jsonStrToObject(configObjectJson, configObjects);

		SendMessage msg = commonUtility.getSendMessage(msgText, timeStamp);
		conversationMessageContext.setMsg(msg);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ConversationMessageContext> entity = new HttpEntity<>(conversationMessageContext, headers);
		String koopidSendMsgUrl = commonUtility.getKoopidURL(configObjects, SEND_MESSAGE);
		log.debug("[{}] MsgToKoopid {} ", routingContext, helper.objectToJson(conversationMessageContext));
		String response = restTemplate.exchange(koopidSendMsgUrl, HttpMethod.POST, entity, String.class).getBody();
		log.debug("[{}] MsgToKoopidResponse: {}", routingContext, response);

	}

	public void sendIsTypingUpdateToCustomer(String message, String routingContext) throws JSONException {
		
		Route route = routeRepository.findByContext(routingContext);
		if(null != route)
		{
			KoopidProvider koopidProvider = getProviderFromCache(route.getProviderId());

			ConfigObjects configObjects = new ConfigObjects();
			configObjects = (ConfigObjects) helper.jsonStrToObject(koopidProvider.getConfigObjects(), configObjects);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			
			JSONObject isTypingJSON = new JSONObject(message);
			JSONObject isTypingBody = isTypingJSON.getJSONObject("body"); 
			String agentId = isTypingBody.getString("agentId");
			String displayName = isTypingBody.getString("displayName");
			
			// Create isTyping response
/*			IsTypingResponse isTypingObject = new IsTypingResponse();
			isTypingObject.setSenderName(displayName);
			isTypingObject.setSender(agentId);
			isTypingObject.setFlag(true);
			isTypingObject.setMediaType("boolean");
			isTypingObject.setType("typing");
			log.debug("isTyping object details are " + isTypingObject.toString());
*/			
			MessageContext messageContextUpdate = new MessageContext();
			messageContextUpdate.setAgentId(agentId);
			messageContextUpdate.setProviderId(route.getProviderId());
			messageContextUpdate.setRoutingContext(routingContext);
			
			Message msg = new Message();
			msg.setType("typing");
			msg.setMediatype("boolean");
			msg.setText("typing");
			msg.setSenderName(displayName);
			msg.setId(agentId);
			msg.setTs(System.currentTimeMillis());
			msg.setFlag("true");
			msg.setTz("-0700");
			msg.setSender(displayName);

			messageContextUpdate.setMsg(msg);

			
			// Send message to Koopid server
			HttpEntity<MessageContext> entity = new HttpEntity<>(messageContextUpdate, headers);
			String koopidSendMsgUrl = commonUtility.getKoopidURL(configObjects, SEND_MESSAGE);
			log.debug("[{}] IsTyPingDetails {} ", routingContext, helper.objectToJson(messageContextUpdate));
			String response = restTemplate.exchange(koopidSendMsgUrl, HttpMethod.POST, entity, String.class).getBody();
			log.debug("[{}] IsTypingResponse: {}", routingContext, response);
			
		}
	
	}
	
	public void sendMsgDeliveredUpdateToCustomer(MessageContext messageContext, String providerId, String partnerId) {

		//ProviderPartnerId providerPartnerId = commonUtility.getProviderPartnerId(partnerId, providerId);
		
		// TO-DO :- Take object from the Redis cache :- DONE
		//KoopidProvider koopidProvider = koopidConfigRepository.findByProviderPartnerId(providerPartnerId);
		KoopidProvider koopidProvider =  getProviderFromCache(providerId);
		
		String routingContext = messageContext.getRoutingContext();
		String configObjectJson = koopidProvider.getConfigObjects();
		ConfigObjects configObjects = new ConfigObjects();
		configObjects = (ConfigObjects) helper.jsonStrToObject(configObjectJson, configObjects);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<MessageContext> entity = new HttpEntity<>(messageContext, headers);
		String koopidSendMsgUrl = commonUtility.getKoopidURL(configObjects, SEND_MESSAGE);
		log.debug("[{}] MsgDeliveredUpdate {} ", routingContext, helper.objectToJson(messageContext));
		String response = restTemplate.exchange(koopidSendMsgUrl, HttpMethod.POST, entity, String.class).getBody();
		log.debug("[{}] MsgDeliveredUpdateResponse: {}", routingContext, response);
	}

	public void sendConversationClose(ConversationContext conversationContext, Route route) {
		
		//ProviderPartnerId providerPartnerId = commonUtility.getProviderPartnerId(route.getPartnerId(),route.getProviderId());
		
		// TO-DO :- Take object from the Redis cache = DONE 
		//KoopidProvider koopidProvider = koopidConfigRepository.findByProviderPartnerId(providerPartnerId);
		KoopidProvider koopidProvider =  getProviderFromCache(route.getProviderId());

		String routingContext = route.getContext();
		String configObjectJson = koopidProvider.getConfigObjects();
		ConfigObjects configObjects = new ConfigObjects();
		configObjects = (ConfigObjects) helper.jsonStrToObject(configObjectJson, configObjects);

		ccmmUtility.setRouteInactive(routingContext);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<ConversationContext> entity = new HttpEntity<>(conversationContext, headers);
		String koopidSendMsgUrl = commonUtility.getKoopidURL(configObjects, CONVERSATION);
		log.debug("[{}] CloseConveration {} ", routingContext, helper.objectToJson(conversationContext));
		String response = restTemplate.exchange(koopidSendMsgUrl, HttpMethod.POST, entity, String.class).getBody();
		log.debug("[{}] CloseConverationResponse: {}", routingContext, response);
	}
	public void updateProviderConfig() {
		
	}	
}
