package com.koopid.ccmm.entity;

public class KoopidProviderConfig
{
	
	private boolean isActive;

	
	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "KoopidProviderConfig [isActive =" + isActive + "]";
	}	
}
