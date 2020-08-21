package com.koopid.ccmm.websocket.client;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.websocket.ClientEndpointConfig;

import org.springframework.beans.factory.annotation.Value;

public class ClientConfigurator extends ClientEndpointConfig.Configurator {

	ClientEndpointConfig.Configurator configurator = new ClientEndpointConfig.Configurator() {

		@Value("${ewc_host}")
		private String ewcHost;

		@Value("${ewc_origin}")
		private String ewcOrigin;

		@Value("${ewc_override_host}")
		private Boolean ewcOverride;

		@Override
		public void beforeRequest(Map<String, List<String>> headers) {
			if (Boolean.TRUE.equals(ewcOverride)) {
				headers.put("Host", Arrays.asList(ewcHost));
				headers.put("Origin", Arrays.asList(ewcOrigin));
			}
		}
	};
}
