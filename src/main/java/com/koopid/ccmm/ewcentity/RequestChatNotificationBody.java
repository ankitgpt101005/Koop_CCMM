
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
@JsonPropertyOrder({ "method", "guid", "authenticationKey", "webOnHoldComfortGroups", "webOnHoldURLs" })
public class RequestChatNotificationBody {

	@JsonProperty("method")
	private String method;
	@JsonProperty("guid")
	private Integer guid;
	@JsonProperty("authenticationKey")
	private String authenticationKey;
	@JsonProperty("webOnHoldComfortGroups")
	private List<RequestChatNotificationWebOnHoldComfortGroup> webOnHoldComfortGroups = null;
	@JsonProperty("webOnHoldURLs")
	private List<RequestChatNotificationWebOnHoldURL> webOnHoldURLs = null;
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

	@JsonProperty("guid")
	public Integer getGuid() {
		return guid;
	}

	@JsonProperty("guid")
	public void setGuid(Integer guid) {
		this.guid = guid;
	}

	@JsonProperty("authenticationKey")
	public String getAuthenticationKey() {
		return authenticationKey;
	}

	@JsonProperty("authenticationKey")
	public void setAuthenticationKey(String authenticationKey) {
		this.authenticationKey = authenticationKey;
	}

	@JsonProperty("webOnHoldComfortGroups")
	public List<RequestChatNotificationWebOnHoldComfortGroup> getWebOnHoldComfortGroups() {
		return webOnHoldComfortGroups;
	}

	@JsonProperty("webOnHoldComfortGroups")
	public void setWebOnHoldComfortGroups(List<RequestChatNotificationWebOnHoldComfortGroup> webOnHoldComfortGroups) {
		this.webOnHoldComfortGroups = webOnHoldComfortGroups;
	}

	@JsonProperty("webOnHoldURLs")
	public List<RequestChatNotificationWebOnHoldURL> getWebOnHoldURLs() {
		return webOnHoldURLs;
	}

	@JsonProperty("webOnHoldURLs")
	public void setWebOnHoldURLs(List<RequestChatNotificationWebOnHoldURL> webOnHoldURLs) {
		this.webOnHoldURLs = webOnHoldURLs;
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
		return new ToStringBuilder(this).append("method", method).append("guid", guid)
				.append("authenticationKey", authenticationKey).append("webOnHoldComfortGroups", webOnHoldComfortGroups)
				.append("webOnHoldURLs", webOnHoldURLs).append("additionalProperties", additionalProperties).toString();
	}

}
