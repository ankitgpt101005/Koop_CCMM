package com.koopid.ccmm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ProviderPartnerId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "partner_id")
	private String partnerId;

	@Column(name = "provider_id")
	private String providerId;

	public ProviderPartnerId() {
		super();
	}

	public ProviderPartnerId(@NotNull String partnerId, @NotNull String providerId, String webchatType) {
		super();
		this.partnerId = partnerId;
		this.providerId = providerId;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partnerId == null) ? 0 : partnerId.hashCode());
		result = prime * result + ((providerId == null) ? 0 : providerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProviderPartnerId other = (ProviderPartnerId) obj;
		if (partnerId == null) {
			if (other.partnerId != null)
				return false;
		} else if (!partnerId.equals(other.partnerId))
			return false;
		if (providerId == null) {
			if (other.providerId != null)
				return false;
		} else if (!providerId.equals(other.providerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProviderPartnerId [partnerId=" + partnerId + ", providerId=" + providerId + "]";
	}

}
