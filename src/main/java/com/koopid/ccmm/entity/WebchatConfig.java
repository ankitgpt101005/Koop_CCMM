package com.koopid.ccmm.entity;

import java.util.List;
import java.util.Map;

public class WebchatConfig {

	private String webServiceUrl;
	
	private String webServiceEndpoint;
	
	private String webSocketEndpoint;

	private Map<String, List<String>> skillset;

	private long maxAgentWaitTime;

	private KeepAlive keepalive;

	private Reconnect reconnect;

	private AgentIdle agentIdle;

	private CustomerIdle customerIdle;


	public String getWebServiceUrl() {
		return webServiceUrl;
	}

	public void setWebServiceUrl(String webServiceUrl) {
		this.webServiceUrl = webServiceUrl;
	}

	public String getWebServiceEndpoint() {
		return webServiceEndpoint;
	}

	public void setWebServiceEndpoint(String webServiceEndpoint) {
		this.webServiceEndpoint = webServiceEndpoint;
	}

	public String getWebSocketEndpoint() {
		return webSocketEndpoint;
	}

	public void setWebSocketEndpoint(String webSocketEndpoint) {
		this.webSocketEndpoint = webSocketEndpoint;
	}

	public Map<String, List<String>> getSkillset() {
		return skillset;
	}

	public void setSkillset(Map<String, List<String>> skillset) {
		this.skillset = skillset;
	}

	public long getMaxAgentWaitTime() {
		return maxAgentWaitTime;
	}

	public void setMaxAgentWaitTime(long maxAgentWaitTime) {
		this.maxAgentWaitTime = maxAgentWaitTime;
	}

	public KeepAlive getkeepalive() {
		return keepalive;
	}

	public void setkeepalive(KeepAlive keepAlive) {
		this.keepalive = keepAlive;
	}

	public Reconnect getReconnect() {
		return reconnect;
	}

	public void setReconnect(Reconnect reconnect) {
		this.reconnect = reconnect;
	}

	public AgentIdle getAgentIdle() {
		return agentIdle;
	}

	public void setAgentIdle(AgentIdle agentIdle) {
		this.agentIdle = agentIdle;
	}

	public CustomerIdle getCustomerIdle() {
		return customerIdle;
	}

	public void setCustomerIdle(CustomerIdle customerIdle) {
		this.customerIdle = customerIdle;
	}

	@Override
	public String toString() {
		return "WebchatConfig [webServiceUrl=" + webServiceUrl + ", webServiceEndpoint=" + webServiceEndpoint+ ", webSocketEndpoint=" + webSocketEndpoint+ ", skillset=" + skillset + ", maxAgentWaitTime=" + maxAgentWaitTime
				+ ", keepAlive=" + keepalive + ", reconnect=" + reconnect + ", agentIdle=" + agentIdle
				+ ", customerIdle=" + customerIdle + "]";
	}

}
