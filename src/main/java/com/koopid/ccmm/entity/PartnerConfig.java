package com.koopid.ccmm.entity;

import java.util.Map;

public class PartnerConfig {

	private String PartnerId;

	private String apiKey;

	private Map<String, String> apis;

	public String getPartnerId() {
		return PartnerId;
	}

	public void setPartnerId(String partnerId) {
		PartnerId = partnerId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Map<String, String> getApis() {
		return apis;
	}

	public void setApis(Map<String, String> apis) {
		this.apis = apis;
	}

	@Override
	public String toString() {
		return "PartnerConfig [PartnerId=" + PartnerId + ", apiKey=" + apiKey + ", apis=" + apis + "]";
	}

}
