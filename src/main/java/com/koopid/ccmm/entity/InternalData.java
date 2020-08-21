package com.koopid.ccmm.entity;

public class InternalData {

	private String domain;

	private String idPrefix;

	private String apiKey;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getIdPrefix() {
		return idPrefix;
	}

	public void setIdPrefix(String idPrefix) {
		this.idPrefix = idPrefix;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public String toString() {
		return "InternalData [domain=" + domain + ", idPrefix=" + idPrefix + ", apiKey=" + apiKey + "]";
	}

}
