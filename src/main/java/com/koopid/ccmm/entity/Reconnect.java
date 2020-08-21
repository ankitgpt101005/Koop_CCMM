package com.koopid.ccmm.entity;

public class Reconnect {

	private int maxAttempt;

	private long interval;

	private String message;

	public int getMaxAttempt() {
		return maxAttempt;
	}

	public void setMaxAttempt(int maxAttempt) {
		this.maxAttempt = maxAttempt;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Reconnect [maxAttempt=" + maxAttempt + ", interval=" + interval + ", message=" + message + "]";
	}

}
