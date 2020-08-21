package com.koopid.ccmm.entity;

import java.util.List;

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 *
 */
public class CustomerContext {

	private String role;

	@NotBlank
	private String providerId;

	private String customerContext;

	@NotBlank
	private String routingContext;

	private Customer customer;

	private RoutingAttrs routingAttrs;
		
	private String skillset;

	private List<BusinessId> businessId;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getCustomerContext() {
		return customerContext;
	}

	public void setCustomerContext(String customerContext) {
		this.customerContext = customerContext;
	}

	public String getRoutingContext() {
		return routingContext;
	}

	public void setRoutingContext(String routingContext) {
		this.routingContext = routingContext;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public RoutingAttrs getRoutingAttrs() {
		return routingAttrs;
	}

	public void setRoutingAttrs(RoutingAttrs routingAttrs) {
		this.routingAttrs = routingAttrs;
	}

	public List<BusinessId> getBusinessId() {
		return businessId;
	}

	public void setBusinessIdList(List<BusinessId> businessId) {
		this.businessId = businessId;
	}

	public String getSkillset() {
		return skillset;
	}

	public void setSkillset(String skillset) {
		this.skillset = skillset;
	}

	@Override
	public String toString() {
		return "CustomerContext [role=" + role + ", providerId=" + providerId + ", customerContext=" + customerContext
				+ ", routingContext=" + routingContext + ", customer=" + customer 
				+ ", routingAttrs=" + routingAttrs + ", businessId=" + businessId + ", skillset=" + skillset + "]";
	}

}
