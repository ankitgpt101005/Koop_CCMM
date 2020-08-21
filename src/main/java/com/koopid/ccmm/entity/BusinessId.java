package com.koopid.ccmm.entity;

public class BusinessId {
	private String phone;
	private String name;
	private String email;
	private String channeltype;

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChanneltype() {
		return channeltype;
	}
	public void setChanneltype(String channelType) {
		this.channeltype = channelType;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "{phone=" + phone + ", name=" + name + ", email=" + email + ", channeltype=" + channeltype + "}";
	}	
}
