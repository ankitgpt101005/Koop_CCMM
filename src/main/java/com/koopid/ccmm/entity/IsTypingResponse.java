package com.koopid.ccmm.entity;

public class IsTypingResponse {

	String type;
	String sender;
	String senderName;
	String ts;
	String tz;
	String mediaType;
	boolean flag;

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getSenderName() {
		return senderName;
	}
	
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	public String getTs() {
		return ts;
	}
	
	public void setTs(String ts) {
		this.ts = ts;
	}
	
	public String getTz() {
		return tz;
	}
	
	public void setTz(String tz) {
		this.tz = tz;
	}
	
	public String getMediaType() {
		return mediaType;
	}
	
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "IsTypingResponse [type=" + type + ", sender=" + sender + ", senderName=" + senderName + ", ts=" + ts
				+ ", tz=" + tz + ", mediaType=" + mediaType + ", flag=" + flag + "]";
	}

}
