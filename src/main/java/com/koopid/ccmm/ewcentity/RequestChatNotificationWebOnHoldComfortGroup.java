
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
@JsonPropertyOrder({ "groupName", "delay", "numberOfMessages", "messages" })
public class RequestChatNotificationWebOnHoldComfortGroup {

	@JsonProperty("groupName")
	private String groupName;
	@JsonProperty("delay")
	private Integer delay;
	@JsonProperty("numberOfMessages")
	private Integer numberOfMessages;
	@JsonProperty("messages")
	private List<RequestChatNotificationMessage> requestChatNotificationMessages = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("groupName")
	public String getGroupName() {
		return groupName;
	}

	@JsonProperty("groupName")
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@JsonProperty("delay")
	public Integer getDelay() {
		return delay;
	}

	@JsonProperty("delay")
	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	@JsonProperty("numberOfMessages")
	public Integer getNumberOfMessages() {
		return numberOfMessages;
	}

	@JsonProperty("numberOfMessages")
	public void setNumberOfMessages(Integer numberOfMessages) {
		this.numberOfMessages = numberOfMessages;
	}

	@JsonProperty("messages")
	public List<RequestChatNotificationMessage> getMessages() {
		return requestChatNotificationMessages;
	}

	@JsonProperty("messages")
	public void setMessages(List<RequestChatNotificationMessage> requestChatNotificationMessages) {
		this.requestChatNotificationMessages = requestChatNotificationMessages;
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
		return new ToStringBuilder(this).append("groupName", groupName).append("delay", delay)
				.append("numberOfMessages", numberOfMessages).append("messages", requestChatNotificationMessages)
				.append("additionalProperties", additionalProperties).toString();
	}

}
