package com.koopid.ccmm.entity;

import java.util.List;

/**
 *
 * @author GS-2145
 *
 */
public class ProviderConfig {
	private String providerId;
	private List<Callback> callbacks;

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public List<Callback> getCallbacks() {
		return callbacks;
	}

	public void setCallbacks(List<Callback> callbacks) {
		this.callbacks = callbacks;
	}

	@Override
	public String toString() {
		return "ProviderConfig [providerId=" + providerId + ", callbacks=" + callbacks + "]";
	}
}