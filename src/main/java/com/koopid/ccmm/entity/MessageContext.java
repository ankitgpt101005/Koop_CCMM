package com.koopid.ccmm.entity;

import javax.validation.constraints.NotBlank;

public class MessageContext {

	private Message msg;

	@NotBlank
	private String agentId;

	@NotBlank
	private String chatId;

	@NotBlank
	private String providerId;

	@NotBlank
	private String routingContext;

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

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

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getRoutingContext() {
		return routingContext;
	}

	public void setRoutingContext(String routingContext) {
		this.routingContext = routingContext;
	}

	@Override
	public String toString() {
		return "MessageContext [msg=" + msg + ", agentId=" + agentId + ", chatId=" + chatId + ", providerId="
				+ providerId + ", routingContext=" + routingContext + "]";
	}

}
