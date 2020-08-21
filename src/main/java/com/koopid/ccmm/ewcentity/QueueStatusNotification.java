
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
@JsonPropertyOrder({ "apiVersion", "type", "body" })
public class QueueStatusNotification {

	@JsonProperty("apiVersion")
	private String apiVersion;
	@JsonProperty("type")
	private String type;
	@JsonProperty("body")
	private QueueStatusNotificationBody body;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("apiVersion")
	public String getApiVersion() {
		return apiVersion;
	}

	@JsonProperty("apiVersion")
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("body")
	public QueueStatusNotificationBody getBody() {
		return body;
	}

	@JsonProperty("body")
	public void setBody(QueueStatusNotificationBody body) {
		this.body = body;
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
		return new ToStringBuilder(this).append("apiVersion", apiVersion).append("type", type).append("body", body)
				.append("additionalProperties", additionalProperties).toString();
	}

}
