package com.koopid.ccmm.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koopid.basesvcs.koopidcache.CacheManager;
import com.koopid.ccmm.entity.KoopidProvider;
import com.koopid.ccmm.entity.KoopidProviderDto;
import com.koopid.ccmm.entity.PartnerProviderDTO;
import com.koopid.ccmm.entity.UpdateProviderConfigDTO;
import com.koopid.ccmm.exception.ApiError;
import com.koopid.ccmm.redis.RedisUtility;
import com.koopid.ccmm.entity.ProviderPartnerId;
import com.koopid.ccmm.repo.KoopidConfigRepository;
import com.koopid.ccmm.service.CcmmService;
import com.koopid.ccmm.service.KoopidService;
import com.koopid.ccmm.utility.Constants;
import com.koopid.ccmm.utility.Helper;

import javassist.NotFoundException;

@RestController
public class ProviderController {

	private static final Logger log = LoggerFactory.getLogger(ProviderController.class);

	@Autowired
	private KoopidConfigRepository koopidConfigRepository;	

	@Autowired
	RedisUtility redisUtil;
	
	@Autowired
	private Helper helper;
	
	@Autowired
	private KoopidService koopidService;
	
	@PostMapping(path = "/aacc_chat_interface/api/config/insertProvider", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> insertProvider(@RequestBody KoopidProviderDto koopidProviderDto) throws NotFoundException, JSONException {
	
		// Convert config details to the string as we have stringtype for config in the provider table
		String configDetails = helper.objectToJson(koopidProviderDto.getConfigObjects());
		
		//JSONObject jsonConfig = new JSONObject(configDetails);
				
		// Create provider object 
		KoopidProvider koopidProvider = new KoopidProvider();
		
		// Set all the provider parameters 
		// 1. Form composite key object		
		ProviderPartnerId providerPartnerId = new ProviderPartnerId();
		providerPartnerId.setPartnerId(koopidProviderDto.getPartnerId());
		providerPartnerId.setProviderId(koopidProviderDto.getProviderId());
		koopidProvider.setProviderPartnerId(providerPartnerId);
		
		// Find provider details
		KoopidProvider checkKoopidProvider = koopidConfigRepository.findByProviderPartnerId(providerPartnerId);
		
		// Add defensive check here that if provider not found ignore that 
		if(null == checkKoopidProvider){

			// 2. Set other parameters 	
			koopidProvider.setApiKey(koopidProviderDto.getApiKey());
			koopidProvider.setActive(koopidProviderDto.getIsActive());
			koopidProvider.setAuthKey(koopidProviderDto.getAuthKey());
			koopidProvider.setAuthKeyExpiration(koopidProviderDto.getAuthKeyExpiration());
			koopidProvider.setConfigObjects(configDetails);
			koopidProvider.setDomain(koopidProviderDto.getDomain());
			koopidProvider.setPrefix(koopidProviderDto.getPrefix());
			koopidProvider.setWebchatType(koopidProviderDto.getWebchatType());

			// Add provider object in the database
			koopidConfigRepository.save(koopidProvider);		
			log.info("Provider added successfully in the database" + koopidProvider.toString());
			
			// Put provider details in the Redis cache		
			// Convert provider to JSON object as redis expecting JSON 
			JSONObject providerJSON = new JSONObject(helper.objectToJson(koopidProvider));		
			redisUtil.addCacheObject(koopidProviderDto.getProviderId(), Constants.PROVIDER_FIELD, providerJSON);		
			log.info("Provider added in the Redis cache");
			
			// Following code is for verification of the object is inserted in the REDIS or not
			// Get connection object 
			
	/*		CacheManager<JSONObject> jsonCacheManager = redisUtil.getCacheObject();
			JSONObject koopidJson = jsonCacheManager.hget(koopidProviderDto.getProviderId(), Constants.PROVIDER_FIELD);
					
			KoopidProvider koopidProvider1 = new KoopidProvider();
			koopidProvider1 = (KoopidProvider) helper.jsonStrToObject(koopidJson.toString(), koopidProvider);
			log.info(" Get object from the cache " + koopidProvider1.getApiKey());
	*/
			
			// Register new provider to the Koopid
			koopidService.callbackRegistration(koopidProvider);
			koopidService.messageWhitelisting(koopidProvider);
			
			// return new provider object with response
			return new ResponseEntity<>(koopidProvider, HttpStatus.CREATED);

		}
		else
		{
			return new ResponseEntity<>(new ApiError(HttpStatus.SEE_OTHER, "Provider already exist") , HttpStatus.OK);
		}
		

	}


	@PostMapping(path = "/aacc_chat_interface/api/config/updateProvider", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateProvider(@RequestBody UpdateProviderConfigDTO updateProviderConfig) throws NotFoundException, JSONException {

		// Create partner provider object to find provider setails 
		ProviderPartnerId providerPartnerObj = new ProviderPartnerId();
		providerPartnerObj.setPartnerId(updateProviderConfig.getPartnerId());
		providerPartnerObj.setProviderId(updateProviderConfig.getProviderId());
		
		// Find provider details
		KoopidProvider koopidProvider = koopidConfigRepository.findByProviderPartnerId(providerPartnerObj);

		// TODO :- Add defensive check here that if provider not found ignore that 
		if(null != koopidProvider){

			// Convert configuration object to the string
			String configDetails = helper.objectToJson(updateProviderConfig.getConfigObjects());

			// Update configuration
			koopidProvider.setConfigObjects(configDetails);

			// Save provider details
			koopidConfigRepository.save(koopidProvider);
			log.info("Provider updated successfully in the database" + koopidProvider.toString());

			// Update object in the Redis cache as well 
			// 1. remove object
			// 2. Insert again			
			redisUtil.delCacheObject(updateProviderConfig.getProviderId(),Constants.PROVIDER_FIELD);

			// Convert provider to JSON object as redis expecting JSON  and add it
			JSONObject providerJSON = new JSONObject(helper.objectToJson(koopidProvider));		
			redisUtil.addCacheObject(updateProviderConfig.getProviderId(), Constants.PROVIDER_FIELD, providerJSON);
			
			log.info("Provider updated in the Redis cache");			

			// Again Register new updated details to the Koopid
			koopidService.callbackRegistration(koopidProvider);
			koopidService.messageWhitelisting(koopidProvider);

			// Following code is for testing that actualy object in the cache or not
			
/*			CacheManager<JSONObject> jsonCacheManager = redisUtil.getCacheObject();
			JSONObject koopidJson = jsonCacheManager.hget(updateProviderConfig.getProviderId(), Constants.PROVIDER_FIELD);

			KoopidProvider koopidProvider1 = new KoopidProvider();
			koopidProvider1 = (KoopidProvider) helper.jsonStrToObject(koopidJson.toString(), koopidProvider);
			log.info(" Get object from the cache " + koopidProvider1.getConfigObjects().toString());
*/			
			
		}
		else
		{
			log.error("Provider details not found");
			return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, "Provider details not found"), HttpStatus.NOT_FOUND);
			
		}
		
		// Return updated object
		return new ResponseEntity<>(koopidProvider, HttpStatus.OK);

	}

	@PostMapping(path = "/aacc_chat_interface/api/config/deleteProvider", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deleteProvider(@RequestBody PartnerProviderDTO partnerProviderDTO) throws NotFoundException {

		log.info("Deleting provider  providerId" + partnerProviderDTO.toString());
		String statusMessage = "Provider deleted successfully";
		HttpStatus respStatus = HttpStatus.NOT_FOUND; 
		
		// Form provider object 		
		ProviderPartnerId providerDetails = new ProviderPartnerId();
		providerDetails.setPartnerId(partnerProviderDTO.getPartnerId());
		providerDetails.setProviderId(partnerProviderDTO.getProviderId());		
	
		// Find provider details
		KoopidProvider koopidProvider = koopidConfigRepository.findByProviderPartnerId(providerDetails);

		if(null != koopidProvider){
			
			// Delete provider object
			koopidConfigRepository.delete(koopidProvider);	

			// Update object in the Redis cache as well 
			// 1. remove object			
			redisUtil.delCacheObject(partnerProviderDTO.getProviderId(),Constants.PROVIDER_FIELD);
			
			respStatus = HttpStatus.OK;
			
			log.info("Provider " + partnerProviderDTO.getProviderId() + " successfully deleted");

		}
		else {
			log.error("Provider does not exist");
			statusMessage = "Provider does not exist";
		}

		// return message
		return new ResponseEntity<>(new ApiError(respStatus, statusMessage), HttpStatus.OK);

	}


}
