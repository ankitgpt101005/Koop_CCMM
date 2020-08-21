package com.koopid.ccmm.utility;

import static com.koopid.ccmm.utility.Constants.API_KEY_AS_STRING;
import static com.koopid.ccmm.utility.Constants.CUST_EMAIL;
import static com.koopid.ccmm.utility.Constants.CUST_NAME;
import static com.koopid.ccmm.utility.Constants.CUST_PHONE;
import static com.koopid.ccmm.utility.Constants.DELIVERED;
import static com.koopid.ccmm.utility.Constants.PARTNER_ID_AS_STRING;
import static com.koopid.ccmm.utility.Constants.PROVIDER_ID_AS_STRING;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.koopid.basesvcs.koopidcache.CacheManager;
import com.koopid.ccmm.entity.AgentSkillset;
import com.koopid.ccmm.entity.BusinessId;
import com.koopid.ccmm.entity.ConfigObjects;
import com.koopid.ccmm.entity.Customer;
import com.koopid.ccmm.entity.CustomerContext;
import com.koopid.ccmm.entity.KoopidProvider;
import com.koopid.ccmm.entity.Message;
import com.koopid.ccmm.entity.MessageContext;
import com.koopid.ccmm.entity.ProviderPartnerId;
import com.koopid.ccmm.entity.Route;
import com.koopid.ccmm.entity.SendMessage;
import com.koopid.ccmm.exception.ForbiddenAccessException;
import com.koopid.ccmm.redis.RedisUtility;
import com.koopid.ccmm.repo.KoopidConfigRepository;
import com.koopid.ccmm.repo.RouteRepository;
import com.koopid.ccmm.service.KoopidService;
import com.koopid.ccmm.websocket.client.EwcWebsocketClientEndpoint;

@Service
public class CommonUtility {
	
	private static final Logger log = LoggerFactory.getLogger(CommonUtility.class);

	@Autowired
	private RouteRepository routeRepository;
	@Autowired
	private KoopidConfigRepository koopidConfigRepository;	
	@Autowired
	private KoopidService koopidService;
	@Autowired
	EwcWebsocketClientEndpoint clientEndPoint;
	@Autowired
	EwcNotificationUtility ewcNotificationUtility;
	@Autowired
	private Helper helper;
	@Autowired
	private RedisUtility redisUtil;

	public ProviderPartnerId getProviderPartnerId(String partnerId, String providerId) {
		ProviderPartnerId providerPartnerId = new ProviderPartnerId();
		providerPartnerId.setPartnerId(partnerId);
		providerPartnerId.setProviderId(providerId);
		return providerPartnerId;
	}

	/**
	 * Common method to pull Provider details from the database
	 * @param partnerId
	 * @param providerId
	 * @return KoopidProvider representing provider details
	 */
	public KoopidProvider getKoopidProvider(String partnerId, String providerId) {
		//ProviderPartnerId providerPartnerId = getProviderPartnerId(partnerId, providerId);

		// TO_DO find provider from the Cache object
		//return koopidConfigRepository.findByProviderPartnerId(providerPartnerId);

		// New changes for cache
		CacheManager<JSONObject> jsonCacheManager = redisUtil.getCacheObject();
		JSONObject koopidJson = jsonCacheManager.hget(providerId, Constants.PROVIDER_FIELD);
		KoopidProvider koopidProvider = new KoopidProvider();
		
		// If its not in the cache take from database
		if(null == koopidJson){
		
			ProviderPartnerId providerPartnerId = getProviderPartnerId(partnerId, providerId);
			koopidProvider = koopidConfigRepository.findByProviderPartnerId(providerPartnerId);
		}
		else
		{
			koopidProvider = (KoopidProvider) helper.jsonStrToObject(koopidJson.toString(), koopidProvider);			
		}
		
		return koopidProvider;
	}

	/**
	 * Based on partner/provider seek for the koopid config and transform it to
	 * an Object
	 * @param partnerId
	 * @param providerId
	 * @return ConfigObjects
	 */
	public ConfigObjects getProviderConfigObjects(String partnerId, String providerId) {
		KoopidProvider koopidProvider = getKoopidProvider(partnerId, providerId);
		ConfigObjects configObjects = new ConfigObjects();
		return (ConfigObjects) helper.jsonStrToObject(koopidProvider.getConfigObjects(), configObjects);
	}

	public boolean ifRouteExists(String routingContext) {
		return routeRepository.existsById(routingContext);
	}

	public void koopidMsgDeliveryUpdate(MessageContext messageContext, Route route) {
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

			koopidService.sendMsgDeliveredUpdateToCustomer(messageContextUpdate, messageContext.getProviderId(),
					route.getPartnerId());
		}
	}

	public String getKoopidURL(ConfigObjects configObjects, String endPoint) {
		return configObjects.getPartnerConfig().getApis().get(endPoint) + PARTNER_ID_AS_STRING
				+ configObjects.getPartnerConfig().getPartnerId() + PROVIDER_ID_AS_STRING
				+ configObjects.getProviderConfig().getProviderId() + API_KEY_AS_STRING
				+ configObjects.getPartnerConfig().getApiKey();
	}

	public SendMessage getSendMessage(String msgText, Long timeStamp) {
		SendMessage msg = new SendMessage();
		msg.setText(msgText);
		msg.setTs(timeStamp);
		msg.setAck(false);
		msg.setMediatype("text");
		msg.setType("chat");
		return msg;
	}

	public void logIncomingReq(String routingContext, CommonsRequestLoggingFilter filter, String message) {
		filter.setBeforeMessagePrefix("[" + routingContext + "] " + message + "::Request - ");
		filter.setAfterMessagePrefix("[" + routingContext + "] " + message + "::Request - ");
	}
	
	/**
	 * Method to validate authentication key and expiry of a key
	 * @param authKey
	 * @param partnerId
	 * @param providerId
	 * @return
	 * @throws ForbiddenAccessException
	 */
	public boolean authenticate(final String []authKey, final String partnerId, final String providerId) throws ForbiddenAccessException {
		if(authKey == null || (authKey.length > 0 && authKey[0] == null)) {
			throw new ForbiddenAccessException(Constants.PERMISSION_DENIED);
		}
		log.debug("Auth key is not null");
		KoopidProvider koopidProvider = getKoopidProvider(partnerId, providerId);
		if(koopidProvider.getAuthKey().equals(authKey[0]) && System.currentTimeMillis() < koopidProvider.getAuthKeyExpiration() ) {
			log.debug("Auth key and expiration verified partner id {} provider id {} ", partnerId, providerId);
			return true;
		}
		throw new ForbiddenAccessException(Constants.PERMISSION_DENIED);
	}
	/**
	 * Convert json string to Agent Skillset Object
	 * @param agentSkillDetails
	 * @return agentSkillset
	 */
	public AgentSkillset getAgentSkillset(String agentSkillDetails) {
		AgentSkillset agentSkillset = new AgentSkillset();
		agentSkillset = (AgentSkillset) helper.jsonStrToObject(agentSkillDetails, agentSkillset);
		return agentSkillset;
	}
	/**
	 * Look up to see if routing address channel exists within the busiess Id
	 * @param channel
	 * @param businessIds
	 * @return BusinessId - if match found; otherwise null
	 */
	public BusinessId IsCommChannelMatched(String channel, List<BusinessId> businessIds) {
		if(businessIds != null && !businessIds.isEmpty())
		{
			BusinessId businessId = businessIds.stream()
					.filter(business -> channel.equals(business.getChanneltype()))
					.findAny()
					.orElse(null);
			return businessId;
		}
		return null;
	}

	/**
	 * Aggregate customer details like name, email, phone
	 * @param channel
	 * @param customerContext
	 * @return Map<String, String>
	 */
	public Map<String, String> getCustomerContextDetails(String channel, CustomerContext customerContext) {
		BusinessId businessId = IsCommChannelMatched(channel, customerContext.getBusinessId());
		Customer customer = customerContext.getCustomer();
		String name = businessId == null ? customer.getName() : businessId.getName();
		
		/**
		 * businessId may or may not contain email id; if it does not contain email field 
		 * or does not carry value, will set the email from customer object
		 * 
		 */
		String customerEmail = customer.getEmail();
		String email = businessId == null ? customerEmail : businessId.getEmail();
		if(businessId != null && email == null)
			email = customerEmail;
		String phone = businessId == null ? customer.getPhone() : businessId.getPhone();

		Map<String, String> custDetails = new HashMap<>();
		custDetails.put(CUST_NAME, name);
		custDetails.put(CUST_EMAIL, email);
		custDetails.put(CUST_PHONE, phone);

		return custDetails;
		
		
	}	
}
