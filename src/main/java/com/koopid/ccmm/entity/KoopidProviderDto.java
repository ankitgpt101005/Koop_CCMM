package com.koopid.ccmm.entity;

import org.json.JSONObject;

public class KoopidProviderDto {

	String partnerId;
	String providerId;
	String apiKey;
	String domain;
	ConfigObjects configObjects;
	String prefix;
	String webchatType;
	String authKey;
	boolean isActive;
	Long authKeyExpiration;
	
		
	public KoopidProviderDto()
	{
		super();
	}


	public String getPartnerId() {
		return partnerId;
	}


	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}


	public String getProviderId() {
		return providerId;
	}


	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}


	public String getApiKey() {
		return apiKey;
	}


	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public ConfigObjects getConfigObjects() {
		return configObjects;
	}


	public void setConfigObjects(ConfigObjects configObjects) {
		this.configObjects = configObjects;
	}


	public String getPrefix() {
		return prefix;
	}


	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}


	public String getWebchatType() {
		return webchatType;
	}


	public void setWebchatType(String webchatType) {
		this.webchatType = webchatType;
	}


	public String getAuthKey() {
		return authKey;
	}


	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}


	public boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Long getAuthKeyExpiration() {
		return authKeyExpiration;
	}


	public void setAuthKeyExpiration(Long authKeyExpiration) {
		this.authKeyExpiration = authKeyExpiration;
	}
		
	
}
