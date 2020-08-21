package com.koopid.ccmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route {

	@Id
	@Column(name = "context")
	private String context;

	@Column(name = "provider_id")
	private String providerId;

	@Column(name = "partner_id")
	private String partnerId;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_polling_started")
	private boolean isPolling;

	@Column(name = "skill")
	private String skill;

	@Column(name = "type")
	private String type;

	public String getSkill() {
		return skill;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public boolean isPolling() {
		return isPolling;
	}

	public void setPolling(boolean isPolling) {
		this.isPolling = isPolling;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	public String toString() {
		return "Route [context=" + context + ", providerId=" + providerId + ", partnerId=" + partnerId + ", isActive="
				+ isActive + ", isPolling=" + isPolling + ", skill=" + skill + ", type=" + type + "]";
	}

}
