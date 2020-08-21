package com.koopid.ccmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conversation_timestamp")
public class ConversationTimestamp {

	@Id
	@Column(name = "context")
	private String context;

	@Column(name = "timestamp")
	private Long timestamp;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ConversationTimestamp [context=" + context + ", timestamp=" + timestamp + "]";
	}

}
