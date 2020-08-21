package com.koopid.ccmm.entity;

public class RouteResponse {

	private String providerId;

	private String state;

	private String routingContext;

	private Agent agent;

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRoutingContext() {
		return routingContext;
	}

	public void setRoutingContext(String routingContext) {
		this.routingContext = routingContext;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "RouteResponse [providerId=" + providerId + ", state=" + state + ", routingContext=" + routingContext
				+ ", agent=" + agent + "]";
	}

}
