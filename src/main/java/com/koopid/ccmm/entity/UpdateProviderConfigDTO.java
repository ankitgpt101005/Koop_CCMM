package com.koopid.ccmm.entity;

public class UpdateProviderConfigDTO {
	
	private String partnerId;
	private String providerId;
	private ConfigObjects configObjects;

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

	public ConfigObjects getConfigObjects() {
		return configObjects;
	}

	public void setConfigObjects(ConfigObjects configObjects) {
		this.configObjects = configObjects;
	}

}
