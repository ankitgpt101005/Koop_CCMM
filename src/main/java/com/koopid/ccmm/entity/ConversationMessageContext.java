package com.koopid.ccmm.entity;

public class ConversationMessageContext {

	private SendMessage msg;

	private String agentId;

	private String chatId;

	private String providerId;

	private String routingContext;

	public SendMessage getMsg() {
		return msg;
	}

	public void setMsg(SendMessage msg) {
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
		return "ConversationMessageContext [msg=" + msg + ", agentId=" + agentId + ", chatId=" + chatId
				+ ", providerId=" + providerId + ", routingContext=" + routingContext + "]";
	}

}
