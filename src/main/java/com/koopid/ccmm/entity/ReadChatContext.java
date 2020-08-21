package com.koopid.ccmm.entity;

import com.koopid.ccmm.wsdl.ci_webcomm.CIChatMessageType;

/**
 * 
 * @author GS-2145
 *
 */
public class ReadChatContext {

	private String agentId;

	private String chatId;

	private String providerId;

	private String cmd;

	private String routingContext;

	private String sessionkey;

	private Long contactID;

	private Long custID;

	private CIChatMessageType ciChatMessageType;

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

	public CIChatMessageType getCiChatMessageType() {
		return ciChatMessageType;
	}

	public void setCiChatMessageType(CIChatMessageType ciChatMessageType) {
		this.ciChatMessageType = ciChatMessageType;
	}

	@Override
	public String toString() {
		return "ConversationContext [agentId=" + agentId + ", chatId=" + chatId + ", providerId=" + providerId
				+ ", cmd=" + cmd + ", routingContext=" + routingContext + ", sessionkey=" + sessionkey + ", contactID="
				+ contactID + ", custID=" + custID + "]";
	}

}
