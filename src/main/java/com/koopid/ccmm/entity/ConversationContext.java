package com.koopid.ccmm.entity;

import javax.validation.constraints.NotBlank;

public class ConversationContext {

	@NotBlank
	private String agentId;

	@NotBlank
	private String chatId;

	@NotBlank
	private String providerId;

	@NotBlank
	private String cmd;

	@NotBlank
	private String routingContext;

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId.toLowerCase();
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

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getRoutingContext() {
		return routingContext;
	}

	public void setRoutingContext(String routingContext) {
		this.routingContext = routingContext;
	}

	@Override
	public String toString() {
		return "ConversationContext [agentId=" + agentId + ", chatId=" + chatId + ", providerId=" + providerId
				+ ", cmd=" + cmd + ", routingContext=" + routingContext + ", text=" + text + "]";
	}

}
