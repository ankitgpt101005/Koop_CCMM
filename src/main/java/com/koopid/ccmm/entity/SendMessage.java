package com.koopid.ccmm.entity;

public class SendMessage {

	private boolean ack;

	private String type;

	private String mediatype;

	private Long ts;

	private String text;

	public boolean isAck() {
		return ack;
	}

	public void setAck(boolean ack) {
		this.ack = ack;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMediatype() {
		return mediatype;
	}

	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}

	public Long getTs() {
		return ts;
	}

	public void setTs(Long ts) {
		this.ts = ts;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "SendMessage [ack=" + ack + ", type=" + type + ", mediatype=" + mediatype + ", ts=" + ts + ", text="
				+ text + "]";
	}

}
