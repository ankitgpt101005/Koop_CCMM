package com.koopid.ccmm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.koopid.ccmm.ewcentity.CloseConversation;
import com.koopid.ccmm.utility.EwcUtility;
import com.koopid.ccmm.utility.Helper;

@Configuration
@EnableScheduling
public class EwcConfig {

	@Autowired
	private EwcUtility ewcUtility;

	private String pingReqJson;

	@Bean
	public void initData() {
		Helper helper = new Helper();
		CloseConversation pingReq = ewcUtility.getCloseConversation("ping");
		pingReqJson = helper.objectToJson(pingReq);
	}

	@Scheduled(fixedDelay = 50000)
	public void sendPing() {
		ewcUtility.sendPingRequest(pingReqJson);
	}

}
