
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
@JsonPropertyOrder({ "method", "displayName", "pagePushURL", "pagePushDestination", "timestamp" })
public class PushPageMessageNotificationBody {

	@JsonProperty("method")
	private String method;
	@JsonProperty("displayName")
	private String displayName;
	@JsonProperty("pagePushURL")
	private String pagePushURL;
	@JsonProperty("pagePushDestination")
	private String pagePushDestination;
	@JsonProperty("timestamp")
	private Long timestamp;
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

	@JsonProperty("displayName")
	public String getDisplayName() {
		return displayName;
	}

	@JsonProperty("displayName")
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@JsonProperty("pagePushURL")
	public String getPagePushURL() {
		return pagePushURL;
	}

	@JsonProperty("pagePushURL")
	public void setPagePushURL(String pagePushURL) {
		this.pagePushURL = pagePushURL;
	}

	@JsonProperty("pagePushDestination")
	public String getPagePushDestination() {
		return pagePushDestination;
	}

	@JsonProperty("pagePushDestination")
	public void setPagePushDestination(String pagePushDestination) {
		this.pagePushDestination = pagePushDestination;
	}

	@JsonProperty("timestamp")
	public Long getTimestamp() {
		return timestamp;
	}

	@JsonProperty("timestamp")
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
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
		return new ToStringBuilder(this).append("method", method).append("displayName", displayName)
				.append("pagePushURL", pagePushURL).append("pagePushDestination", pagePushDestination)
				.append("timestamp", timestamp).append("additionalProperties", additionalProperties).toString();
	}

}
