package com.koopid.ccmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ccmm_meta")
public class CcmmMeta {

	@Id
	@Column(name = "context")
	private String context;

	@Column(name = "session_key")
	private String sessionkey;

	@Column(name = "contact_id")
	private Long contactID;

	@Column(name = "cust_id")
	private Long custID;

	@Column(name = "chat_id")
	private String chatId;

	@Column(name = "agent_id")
	private String agentId;

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getSessionkey() {
		return sessionkey;
	}

	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}

	public Long getContactID() {
		return contactID;
	}

	public void setContactID(Long contactID) {
		this.contactID = contactID;
	}

	public Long getCustID() {
		return custID;
	}

	public void setCustID(Long custID) {
		this.custID = custID;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "CcmmMeta [context=" + context + ", sessionkey=" + sessionkey + ", contactID=" + contactID + ", custID="
				+ custID + ", chatId=" + chatId + ", agentId=" + agentId + "]";
	}

}
