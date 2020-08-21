package com.koopid.ccmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "koopid_agents")
public class KoopidAgents {

	@Id
	@Column(name = "partner_id")
	private String partnerId;

	@Column(name = "provider_id")
	private String providerId;

	@Column(name = "agent_id")
	private String agentId;

	@Column(name = "status")
	private String status;

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

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "KoopidAgents [partnerId=" + partnerId + ", providerId=" + providerId + ", agentId=" + agentId
				+ ", status=" + status + "]";
	}

}
