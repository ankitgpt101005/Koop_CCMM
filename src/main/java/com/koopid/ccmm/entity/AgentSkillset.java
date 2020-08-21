package com.koopid.ccmm.entity;

import java.util.List;

public class AgentSkillset {
	private String skillset;
	private List<CustomFields> customFields;

	public String getSkillset() {
		return skillset;
	}
	public void setSkillset(String skillset) {
		this.skillset = skillset;
	}
	public List<CustomFields> getCustomFields() {
		return customFields;
	}
	public void setCustomFields(List<CustomFields> customFields) {
		this.customFields = customFields;
	}

	@Override
	public String toString() {
		return "{skillset=" + skillset + ", customFields=" + customFields + "}";
	}	
}
