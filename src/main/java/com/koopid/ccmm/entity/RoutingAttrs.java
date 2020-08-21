package com.koopid.ccmm.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoutingAttrs {
	@JsonProperty("agent.skill")
	private String agentSkill;
	private String channel;
	private String topic;

	public String getAgentSkill() {
		return agentSkill;
	}
	public void setAgentSkill(String agentSkill) {
		this.agentSkill = agentSkill;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "RoutingAttrs [agentSkill=" + agentSkill + ", channel=" + channel + ", topic=" + topic + "]";
	}
	
}
