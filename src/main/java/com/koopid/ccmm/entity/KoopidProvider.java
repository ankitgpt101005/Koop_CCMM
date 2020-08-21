package com.koopid.ccmm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "koopid_provider")
@DynamicUpdate
public class KoopidProvider {
	@EmbeddedId
	private ProviderPartnerId providerPartnerId;
	@Column(name = "api_key")
	private String apiKey;
	@Column(name = "domain")
	private String domain;
	@Column(name = "config_objects", columnDefinition="TEXT")	
	private String configObjects;
	@Column(name = "prefix")
	private String prefix;
	@Column(name = "webchat_type")
	private String webchatType;
	@Column(name = "auth_key")
	private String authKey;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "auth_key_expiration")
	private Long authKeyExpiration;
	
	public ProviderPartnerId getProviderPartnerId() {
		return providerPartnerId;
	}

	public void setProviderPartnerId(ProviderPartnerId providerPartnerId) {
		this.providerPartnerId = providerPartnerId;
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

	public String getConfigObjects() {
		return configObjects;
	}

	public void setConfigObjects(String configObjects) {
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

	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getAuthKeyExpiration() {
		return authKeyExpiration;
	}

	public void setAuthKeyExpiration(Long authKeyExpiration) {
		this.authKeyExpiration = authKeyExpiration;
	}

	@Override
	public int hashCode() {
        return new HashCodeBuilder()
        		.append(this.providerPartnerId.getPartnerId())
        		.append(this.providerPartnerId.getProviderId())
        		.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
        if (obj instanceof KoopidProvider == false)
        {
        	return false;
        }
        if (this == obj)
        {
        	return true;
        }
        final KoopidProvider otherProvider = (KoopidProvider) obj;

        return new EqualsBuilder()
        		.append(this.providerPartnerId.getPartnerId(), otherProvider.providerPartnerId.getPartnerId())
        		.append(this.providerPartnerId.getProviderId(), otherProvider.providerPartnerId.getProviderId())
        		.isEquals();
	}

	@Override
	public String toString() {
		return "KoopidProvider [providerPartnerId=" + providerPartnerId + ", apiKey=" + apiKey + ", domain=" + domain
				+ ", configObjects=" + configObjects + ", prefix=" + prefix + ", webchatType=" + webchatType 
				+ ", isActive =" + isActive + ", authKeyExpiration =" + authKeyExpiration + "]";
	}
}