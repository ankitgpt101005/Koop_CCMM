package com.koopid.ccmm.entity;

/**
 * 
 * @author GS-2145
 *
 */
public class AgentContext {

	private String providerId;

	private String state;

	private String routingContext;

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

	@Override
	public String toString() {
		return "AgentContext [providerId=" + providerId + ", state=" + state + ", routingContext=" + routingContext
				+ "]";
	}

}
