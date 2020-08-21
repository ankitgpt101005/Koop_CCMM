package com.koopid.ccmm.controller;

import javax.validation.Valid;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.koopid.ccmm.config.RequestLoggingFilterConfig;
import com.koopid.ccmm.entity.AgentContext;
import com.koopid.ccmm.entity.Code;
import com.koopid.ccmm.entity.ConversationContext;
import com.koopid.ccmm.entity.CustomerContext;
import com.koopid.ccmm.entity.KoopidProviderConfig;
import com.koopid.ccmm.entity.MessageContext;
import com.koopid.ccmm.service.CcmmService;
import com.koopid.ccmm.service.EwcService;
import com.koopid.ccmm.service.KoopidService;
import com.koopid.ccmm.utility.CommonUtility;

import javassist.NotFoundException;

@RestController
public class KoopidController {

	private static final Logger log = LoggerFactory.getLogger(KoopidController.class);

	@Autowired
	private KoopidService koopidService;
	@Autowired
	private CcmmService ccmmService;
	@Autowired
	private EwcService ewcService;
	@Autowired
	private CommonUtility commonUtility;
	@Autowired
	private RequestLoggingFilterConfig logFilter;

	/**
	 * When koopid end client clicks on ESCALATE control comes here.
	 * 1. Log the request 
	 * 2. Then we check that agent is available or not
	 * @param customerContext
	 * @param partnerId
	 * @param providerId
	 * @param authKey
	 * @param webChatType
	 * @return
	 */
	@PostMapping(path = "/aacc_chat_interface/api/routing/{partnerId}/{providerId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AgentContext> routing(@RequestBody @Valid CustomerContext customerContext,
			@PathVariable("partnerId") String partnerId, @PathVariable("providerId") String providerId,
			@RequestParam(value = "authKey") String authKey,
			@RequestParam(value = "webChatType", required = false) String webChatType) {
		
		// Using following code to log the request details
		CommonsRequestLoggingFilter filter = logFilter.logFilter();
		commonUtility.logIncomingReq(customerContext.getRoutingContext(), filter, "HandleRoute");
		
		// Earlier there was seperate controller for ccmm and ews so Thiru and Ashish Kucheria decided that 
		// It will be in the single controller and depending on type we will redirect request.		
		AgentContext agentContext = new AgentContext();
	
		log.debug("Routing context details are :" + customerContext.toString());
		
		// Earlier there was seperate controller for ccmm and ews so Thiru and Ashish Kucheria decided that 
		// It will be in the single controller and depending on type we will redirect request.		
		if ("ewc".equals(webChatType))
			agentContext = ewcService.routing(customerContext, partnerId, providerId);
		else
			agentContext = ccmmService.routing(customerContext, partnerId, providerId);
		
		return new ResponseEntity<>(agentContext, HttpStatus.OK);
	}

	@PostMapping(path = "aacc_chat_interface/api/msgrecv/{partnerId}/{providerId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Code> msgrecv(@RequestBody @Valid MessageContext messageContext,
			@PathVariable("partnerId") int partnerId, @PathVariable("providerId") String providerId,
			@RequestParam(value = "authKey") String authKey,
			@RequestParam(value = "webChatType", required = false) String webChatType) {
		CommonsRequestLoggingFilter filter = logFilter.logFilter();
		commonUtility.logIncomingReq(messageContext.getRoutingContext(), filter, "HandleMessage");
		if ("ewc".equals(webChatType))
			ewcService.msgrecv(messageContext);
		else
			ccmmService.msgrecv(messageContext);
		Code code = new Code();
		code.setCode("success");
		return new ResponseEntity<>(code, HttpStatus.OK);
	}

	@PostMapping(path = "/aacc_chat_interface/api/conversation/{partnerId}/{providerId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Code> conversation(@RequestBody @Valid ConversationContext conversationContext,
			@PathVariable("partnerId") String partnerId, @PathVariable("providerId") String providerId,
			@RequestParam(value = "authKey") String authKey,
			@RequestParam(value = "webChatType", required = false) String webChatType) {
		CommonsRequestLoggingFilter filter = logFilter.logFilter();
		commonUtility.logIncomingReq(conversationContext.getRoutingContext(), filter, "HandleConversation");

		if ("ewc".equals(webChatType))
			ewcService.conversation(conversationContext, providerId, partnerId);
		else
			ccmmService.conversation(conversationContext, providerId, partnerId);

		Code code = new Code();
		code.setCode("success");
		return new ResponseEntity<>(code, HttpStatus.OK);
	}

	@PostMapping(path = "/aacc_chat_interface/api/config/{partnerId}/{providerId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Code> config(@RequestBody @Valid KoopidProviderConfig providerConfig,
			@PathVariable("partnerId") String partnerId, @PathVariable("providerId") String providerId,
			@RequestParam(value = "authKey") String authKey
			) throws NotFoundException, JSONException {
		CommonsRequestLoggingFilter filter = logFilter.logFilter();
		commonUtility.logIncomingReq(partnerId+" "+providerId, filter, "config");
		ccmmService.updateProviderConfig(providerConfig, partnerId, providerId);

		Code code = new Code();
		code.setCode("success");
		return new ResponseEntity<>(code, HttpStatus.OK);
	}

	@GetMapping(path = "/aacc_chat_interface/api/config/{partnerId}/{providerId}", produces = "application/json")
	public ResponseEntity<String> getConfig(@PathVariable("partnerId") String partnerId, @PathVariable("providerId") String providerId,
			@RequestParam(value = "authKey") String authKey
			) throws NotFoundException {
		CommonsRequestLoggingFilter filter = logFilter.logFilter();
		commonUtility.logIncomingReq(partnerId+"-"+providerId, filter, "getConfig");

		String providerConfig = ccmmService.getConfig(partnerId, providerId);
		log.debug("[{}] Provider Config Object:: {} ", partnerId+"-"+providerId, providerConfig);

		return new ResponseEntity<>(providerConfig, HttpStatus.OK);
	}
}
