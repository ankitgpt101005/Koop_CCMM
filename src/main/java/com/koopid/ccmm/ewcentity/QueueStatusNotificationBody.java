
package com.koopid.ccmm.ewcentity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "method", "positionInQueue", "estimatedWaitTime" })
public class QueueStatusNotificationBody {

	@JsonProperty("method")
	private String method;
	@JsonProperty("positionInQueue")
	private Integer positionInQueue;
	@JsonProperty("estimatedWaitTime")
	private Integer estimatedWaitTime;
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

	@JsonProperty("positionInQueue")
	public Integer getPositionInQueue() {
		return positionInQueue;
	}

	@JsonProperty("positionInQueue")
	public void setPositionInQueue(Integer positionInQueue) {
		this.positionInQueue = positionInQueue;
	}

	@JsonProperty("estimatedWaitTime")
	public Integer getEstimatedWaitTime() {
		return estimatedWaitTime;
	}

	@JsonProperty("estimatedWaitTime")
	public void setEstimatedWaitTime(Integer estimatedWaitTime) {
		this.estimatedWaitTime = estimatedWaitTime;
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
		return new ToStringBuilder(this).append("method", method).append("positionInQueue", positionInQueue)
				.append("estimatedWaitTime", estimatedWaitTime).append("additionalProperties", additionalProperties)
				.toString();
	}

}
