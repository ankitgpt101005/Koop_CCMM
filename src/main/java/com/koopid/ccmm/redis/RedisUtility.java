package com.koopid.ccmm.redis;

import java.beans.AppletInitializer;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koopid.basesvcs.koopidcache.CacheManager;
import com.koopid.basesvcs.koopidcache.codec.JSONObjectCodec;
import com.koopid.ccmm.entity.KoopidProvider;
import com.koopid.ccmm.repo.KoopidConfigRepository;
import com.koopid.ccmm.utility.Constants;
import com.koopid.ccmm.utility.Helper;


@Component
public class RedisUtility implements ApplicationRunner{

	@Value("${redis.host}")
	private String redisHost;

	@Value("${redis.port}")
	private String redisPort;
	
	private final String serviceName = "ccmmconn";
	
	static CacheManager<JSONObject> jsonCacheManager;
	
	@Autowired
	KoopidConfigRepository koopidConfigRepository;
	
	@Autowired
	private Helper helper;

	private static final Logger log = LoggerFactory.getLogger(RedisUtility.class);

	/**
	 * This function will run at the start of application 
	 * 1] It will create connection with the Redis
	 * 2] It will put all the existing providers from DB in the Redis Cache 
	 */
	public void run(ApplicationArguments args) throws Exception {

		log.info("Initiallizing redis. Redis Host :  " + redisHost + " Redis Port" + redisPort);
			
		// Create connection with the Redis Srever
		jsonCacheManager = new CacheManager<JSONObject>(serviceName, redisHost, Integer.parseInt(redisPort), new JSONObjectCodec());
				
		// Now we need to fetch all the existing providers and we need to put that in the Redis Cache 		
		List<KoopidProvider> koopidProviderList = koopidConfigRepository.findAll();
		
		// Iterate on each provider and put it in the cache
		for (KoopidProvider koopidProvider : koopidProviderList) {
			
			log.info(" Adding provider in the cache " + koopidProvider.getProviderPartnerId().getProviderId());
			// Convert object to JSON
			JSONObject providerJSON = new JSONObject(helper.objectToJson(koopidProvider));		
			// Add provider in the cache
			addCacheObject(koopidProvider.getProviderPartnerId().getProviderId(), Constants.PROVIDER_FIELD, providerJSON);		
			
		}

	}
		
	/**
	 * This method will return cache object
	 * which we have created on the application startup 
 	 * key :- unique key like provider or customerId
	 * field :- specific field .

	 * @return return Cache manager object
	 */
	
	public CacheManager<JSONObject> getCacheObject()
	{
		return jsonCacheManager;
	}
	
	/**
	 * This method will add JSON object in the redis cache 
	 * key :- unique key like provider or customerId
	 * field :- specific field 
	 * object :- Json object that we need to store.
	 * @return return Cache manager object
	 */
	
	public CacheManager<JSONObject> addCacheObject(String key,String field, JSONObject object)
	{
		// Set new key in the 
		jsonCacheManager.hset(key, field, object);
		
		// return cache manager object
		return jsonCacheManager;
	}
	
	/**
	 * This method will remove JSON object in the redis cache 
	 * key :- unique key like provider or customerId
	 * field :- specific field .
	 * its like key-->field -->data structure
	 * @return return Cache manager object
	 */
	
	public void delCacheObject(String key,String field)
	{
		// delete field from the cache 
		jsonCacheManager.hdel(key, field);
	}

}
