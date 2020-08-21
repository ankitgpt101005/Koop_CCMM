package com.koopid.ccmm.entity;

import java.util.List;

/**
 * 
 * @author GS-2145
 *
 */
public class MessageWhitelist {

	private String providerId;

	private List<String> entity;

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public List<String> getEntity() {
		return entity;
	}

	public void setEntity(List<String> entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "MessageWhitelist [ProviderId=" + providerId + ", entity=" + entity + "]";
	}

}
