package com.koopid.ccmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ewc_meta")
public class EwcMeta {

	@Id
	@Column(name = "context")
	private String context;

	@Column(name = "auth_key")
	private String authKey;

	@Column(name = "guid")
	private Integer guid;

	@Column(name = "agent_id")
	private String agentId;

	@Column(name = "chat_id")
	private String chatId;

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public Integer getGuid() {
		return guid;
	}

	public void setGuid(Integer guid) {
		this.guid = guid;
	}

	@Override
	public String toString() {
		return "EwcMeta [context=" + context + ", authKey=" + authKey + ", guid=" + guid + ", agentId=" + agentId
				+ ", chatId=" + chatId + "]";
	}

}
