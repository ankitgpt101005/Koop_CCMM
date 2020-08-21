package com.koopid.ccmm.entity;

public class KeepAlive {

	private long interval;

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	@Override
	public String toString() {
		return "KeepAlive [interval=" + interval + "]";
	}

}
