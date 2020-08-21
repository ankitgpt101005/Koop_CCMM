package com.koopid.ccmm.entity;

/**
 * 
 * @author GS-2145
 *
 */
public class Callback {

	private String api;
	private String callback;

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	@Override
	public String toString() {
		return "{api=" + api + ", callback=" + callback + "}";
	}

}
