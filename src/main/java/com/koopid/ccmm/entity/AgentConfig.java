package com.koopid.ccmm.entity;

public class AgentConfig {

	private String idPrefix;

	private String domain;

	private String defaultPhone;

	private String defaultPassword;

	private String notifyAgentJoin;

	private boolean sendAgentName;

	public String getIdPrefix() {
		return idPrefix;
	}

	public void setIdPrefix(String idPrefix) {
		this.idPrefix = idPrefix;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDefaultPhone() {
		return defaultPhone;
	}

	public void setDefaultPhone(String defaultPhone) {
		this.defaultPhone = defaultPhone;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	public String getNotifyAgentJoin() {
		return notifyAgentJoin;
	}

	public void setNotifyAgentJoin(String notifyAgentJoing) {
		this.notifyAgentJoin = notifyAgentJoing;
	}

	public boolean isSendAgentName() {
		return sendAgentName;
	}

	public void setSendAgentName(boolean sendAgentName) {
		this.sendAgentName = sendAgentName;
	}

	@Override
	public String toString() {
		return "AgentConfig [idPrefix=" + idPrefix + ", domain=" + domain + ", defaultPhone=" + defaultPhone
				+ ", defaultPassword=" + defaultPassword + ", notifyAgentJoin=" + notifyAgentJoin + ", sendAgentName="
				+ sendAgentName + "]";
	}

}
