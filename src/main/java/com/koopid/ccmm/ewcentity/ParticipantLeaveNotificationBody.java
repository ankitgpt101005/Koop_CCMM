
package com.koopid.ccmm.ewcentity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "method", "agentId", "endChatFlag", "numberOfParticipants", "participants" })
public class ParticipantLeaveNotificationBody {

	@JsonProperty("method")
	private String method;
	@JsonProperty("agentId")
	private String agentId;
	@JsonProperty("endChatFlag")
	private Boolean endChatFlag;
	@JsonProperty("numberOfParticipants")
	private Integer numberOfParticipants;
	@JsonProperty("participants")
	private List<Participant> participants = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("method")
	public String getMethod() {
		return method;
	}

	@JsonProperty("method")
	public void setMethod(String method) {
		this.method = method;
	}

	@JsonProperty("agentId")
	public String getAgentId() {
		return agentId;
	}

	@JsonProperty("agentId")
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	@JsonProperty("endChatFlag")
	public Boolean getEndChatFlag() {
		return endChatFlag;
	}

	@JsonProperty("endChatFlag")
	public void setEndChatFlag(Boolean endChatFlag) {
		this.endChatFlag = endChatFlag;
	}

	@JsonProperty("numberOfParticipants")
	public Integer getNumberOfParticipants() {
		return numberOfParticipants;
	}

	@JsonProperty("numberOfParticipants")
	public void setNumberOfParticipants(Integer numberOfParticipants) {
		this.numberOfParticipants = numberOfParticipants;
	}

	@JsonProperty("participants")
	public List<Participant> getParticipants() {
		return participants;
	}

	@JsonProperty("participants")
	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("method", method).append("agentId", agentId)
				.append("endChatFlag", endChatFlag).append("numberOfParticipants", numberOfParticipants)
				.append("participants", participants).append("additionalProperties", additionalProperties).toString();
	}

}
