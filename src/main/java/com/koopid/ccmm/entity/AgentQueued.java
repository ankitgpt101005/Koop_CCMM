package com.koopid.ccmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agent_queued")
public class AgentQueued {

	@Id
	@Column(name = "context")
	private String context;

	@Column(name = "queued")
	private Long queued;

	@Column(name = "skill_set_id")
	private Long skillSetId;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Long getQueued() {
		return queued;
	}

	public void setQueued(Long queued) {
		this.queued = queued;
	}

	public Long getSkillSetId() {
		return skillSetId;
	}

	public void setSkillSetId(Long skillSetId) {
		this.skillSetId = skillSetId;
	}

	@Override
	public String toString() {
		return "AgentQueued [context=" + context + ", queued=" + queued + ", skillSetId=" + skillSetId + "]";
	}

}
