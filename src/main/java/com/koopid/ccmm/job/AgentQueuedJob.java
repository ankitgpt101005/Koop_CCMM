package com.koopid.ccmm.job;

import static com.koopid.ccmm.utility.Constants.INVALID_SESSION_KEY;

import java.util.List;

import javax.persistence.OrderBy;

import org.json.JSONObject;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.koopid.basesvcs.koopidcache.CacheManager;
import com.koopid.ccmm.config.CollectionConfig;
import com.koopid.ccmm.entity.Agent;
import com.koopid.ccmm.entity.AgentQueued;
import com.koopid.ccmm.entity.CcmmMeta;
import com.koopid.ccmm.entity.ConfigObjects;
import com.koopid.ccmm.entity.KoopidProvider;
import com.koopid.ccmm.entity.MessageContext;
import com.koopid.ccmm.entity.ProviderPartnerId;
import com.koopid.ccmm.entity.Route;
import com.koopid.ccmm.entity.RouteResponse;
import com.koopid.ccmm.entity.WebchatConfig;
import com.koopid.ccmm.redis.RedisUtility;
import com.koopid.ccmm.repo.AgentQueuedRepository;
import com.koopid.ccmm.repo.CcmmMetaRepository;
import com.koopid.ccmm.repo.KoopidConfigRepository;
import com.koopid.ccmm.repo.RouteRepository;
import com.koopid.ccmm.service.KoopidService;
import com.koopid.ccmm.service.SessionKeyClient;
import com.koopid.ccmm.utility.CcmmUtility;
import com.koopid.ccmm.utility.CommonUtility;
import com.koopid.ccmm.utility.Constants;
import com.koopid.ccmm.utility.Helper;
import com.koopid.ccmm.wsdl.ci_utility.GetTotalQueuedToSkillset;
import com.koopid.ccmm.wsdl.ci_utility.GetTotalQueuedToSkillsetResponse;
import com.koopid.ccmm.wsdl.ci_webcomm.ReadChatMessageResponse;

public class AgentQueuedJob extends QuartzJobBean {

	private static final Logger log = LoggerFactory.getLogger(AgentQueuedJob.class);

	@Autowired
	private AgentQueuedRepository agentQueuedRepository;
	@Autowired
	private CcmmUtility ccmmUtility;
	@Autowired
	private CcmmMetaRepository ccmmMetaRepository;
	@Autowired
	private KoopidService koopidService;
	@Autowired
	private RouteRepository routeRepository;
	@Autowired
	private KoopidConfigRepository koopidConfigRepository;
	@Autowired
	private SessionKeyClient sessionKeyClient;
	@Autowired
	private CommonUtility commonUtility;
	@Autowired
	private Helper helper;
	@Autowired
	RedisUtility redisUtil;

	@OrderBy("queued ASC")
	List<AgentQueued> listQueued;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		listQueued = agentQueuedRepository.findAll();
		if (!listQueued.isEmpty()) {
			
			// Comment here what exactly we are doing
			listQueued.parallelStream().forEach(agentQueued -> {

				if (!CollectionConfig.getRouteQueuedList().contains(agentQueued.getContext())) {
					CcmmMeta ccmmMeta = ccmmMetaRepository.findByContext(agentQueued.getContext());
					Route route = routeRepository.findByContext(agentQueued.getContext());
					//ProviderPartnerId providerPartnerId = commonUtility.getProviderPartnerId(route.getPartnerId(),route.getProviderId());
					
					// Take object from the cache instead of database commenting following line and adding new code
						//KoopidProvider koopidProvider = koopidConfigRepository.findByProviderPartnerId(providerPartnerId);
					
					// This is new code to fetch details from cache
					CacheManager<JSONObject> jsonCacheManager = redisUtil.getCacheObject();
					JSONObject koopidJson = jsonCacheManager.hget(route.getProviderId(), Constants.PROVIDER_FIELD);
					KoopidProvider koopidProvider = new KoopidProvider();
					koopidProvider = (KoopidProvider) helper.jsonStrToObject(koopidJson.toString(), koopidProvider);

					processQueue(agentQueued, ccmmMeta, route, koopidProvider);
				}
			});
		}
	}

	public void processQueue(AgentQueued agentQueued, CcmmMeta ccmmMeta, Route route, KoopidProvider koopidProvider) {
		try {
			ConfigObjects configObjects = new ConfigObjects();
			configObjects = (ConfigObjects) helper.jsonStrToObject(koopidProvider.getConfigObjects(), configObjects);
			if (agentQueued.getQueued() == 0) {
				hasAgentAcceptedChat(agentQueued, ccmmMeta, route, koopidProvider, configObjects);
			} else {
				GetTotalQueuedToSkillset getTotalQueuedToSkillset = ccmmUtility.getAgentPollBody(agentQueued, ccmmMeta);
				log.debug("[{}] ccmmGetAgentQueue:", route.getContext());

				WebchatConfig webchatConfig = configObjects.getWebchatConfig();
				
				GetTotalQueuedToSkillsetResponse getTotalQueuedToSkillsetResponse = sessionKeyClient
						.getTotalQueuedToSkillset(getTotalQueuedToSkillset, webchatConfig.getWebServiceUrl(),
								webchatConfig.getWebServiceEndpoint());
				AgentQueued agentQueue = new AgentQueued();
				agentQueue.setContext(ccmmMeta.getContext());

				Long queuePosition = getTotalQueuedToSkillsetResponse.getGetTotalQueuedToSkillsetResult();
				agentQueue.setQueued(queuePosition);
				agentQueuedRepository.saveAndFlush(agentQueue);

				if (queuePosition == 0) {
					hasAgentAcceptedChat(agentQueued, ccmmMeta, route, koopidProvider, configObjects);
				}

			}
		} catch (NullPointerException nx) {
			// Every time while reading for latest message, if there is no message from
			// agent it goes in exception with null in message.
		} catch (Exception ex) {

			if (ex.getMessage().contains(INVALID_SESSION_KEY)) {
				agentQueuedRepository.delete(agentQueued);
				CollectionConfig.getRouteQueuedList().remove(agentQueued.getContext());
				log.debug("[{}] {}  ", agentQueued.getContext(), INVALID_SESSION_KEY);
			}
		}
	}

	public void hasAgentAcceptedChat(AgentQueued agentQueued, CcmmMeta ccmmMeta, Route route,
			KoopidProvider koopidProvider, ConfigObjects configObjects) {
		CollectionConfig.getRouteQueuedList().add(ccmmMeta.getContext());
		WebchatConfig webchatConfig = configObjects.getWebchatConfig();
		ReadChatMessageResponse readChatMessageResponse = ccmmUtility.readChatMessageUtility(ccmmMeta.getContext(),
				ccmmMeta.getContactID(), ccmmMeta.getSessionkey(), webchatConfig.getWebServiceUrl(),
				webchatConfig.getWebServiceEndpoint());
		if (readChatMessageResponse != null
				&& readChatMessageResponse.getReadChatMessageResult().getListOfChatMessages() != null) {
			MessageContext messageContext = new MessageContext();
			messageContext.setRoutingContext(ccmmMeta.getContext());
			RouteResponse routeResponse = new RouteResponse();
			routeResponse.setProviderId(route.getProviderId());
			routeResponse.setRoutingContext(ccmmMeta.getContext());
			routeResponse.setState("route");
			Agent agent = new Agent();
			agent.setName(koopidProvider.getPrefix());
			ccmmMeta.setAgentId(ccmmUtility.getAgentIdReadContact(ccmmMeta.getContext()));
			ccmmMetaRepository.save(ccmmMeta);
			agent.setEmail(ccmmMeta.getAgentId());
			agent.setPassword(configObjects.getAgentConfig().getDefaultPassword());
			agent.setPhone(configObjects.getAgentConfig().getDefaultPhone());
			routeResponse.setAgent(agent);
			agentQueuedRepository.delete(agentQueued);
			koopidService.routingToKoopid(routeResponse, route.getPartnerId());
		}
		CollectionConfig.getRouteQueuedList().remove(ccmmMeta.getContext());
	}
}