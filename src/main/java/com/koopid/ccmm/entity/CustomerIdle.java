package com.koopid.ccmm.entity;

public class CustomerIdle {

	private long time;

	private String message;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String meassage) {
		this.message = meassage;
	}

	@Override
	public String toString() {
		return "CustomerIdle [time=" + time + ", meassage=" + message + "]";
	}

}
