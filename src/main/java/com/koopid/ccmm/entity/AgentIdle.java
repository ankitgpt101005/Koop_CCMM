package com.koopid.ccmm.entity;

public class AgentIdle {

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

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "AgentIdle [time=" + time + ", message=" + message + "]";
	}

}
