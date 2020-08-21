package com.koopid.ccmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "koopid_meta")
public class KoopidMeta {

	@Id
	@Column(name = "context")
	private String context;

	@Column(name = "sender_name")
	private String senderName;

	@Column(name = "path")
	private String path;

	@Column(name = "sender")
	private String sender;

	@Column(name = "tz")
	private String tz;

	@Column(name = "ack")
	private boolean ack;

	@Column(name = "text")
	private String text;

	@Column(name = "id")
	private String id;

	@Column(name = "type")
	private String type;

	@Column(name = "mediatype")
	private String mediatype;

	@Column(name = "ts")
	private Long ts;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName.toLowerCase();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public boolean isAck() {
		return ack;
	}

	public void setAck(boolean ack) {
		this.ack = ack;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "KoopidMetaData [context=" + context + ", senderName=" + senderName + ", path=" + path + ", sender="
				+ sender + ", tz=" + tz + ", ack=" + ack + ", text=" + text + ", id=" + id + ", type=" + type
				+ ", mediatype=" + mediatype + ", ts=" + ts + "]";
	}

}
