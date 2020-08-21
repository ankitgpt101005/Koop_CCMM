package com.koopid.ccmm.entity;


public class PartnerProviderDTO {

	String partnerId;
	String providerId;

	PartnerProviderDTO()
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

	@Override
	public String toString() {
		return "PartnerProviderDTO [partnerId=" + partnerId + ", providerId=" + providerId + "]";
	}

}
