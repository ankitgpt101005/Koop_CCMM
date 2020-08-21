package com.koopid.ccmm.entity;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author GS-2145
 *
 */
public class Agent {

	@NotNull
	private String name;

	@NotNull
	private String email;

	@NotNull
	private String phone;

	@NotNull
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", email=" + email + ", phone=" + phone + ", password=" + password + "]";
	}

}
