
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
@JsonPropertyOrder({ "method", "guid", "authenticationKey", "deviceType", "requestTranscript", "intrinsics" })
public class RequestChatBody {

	@JsonProperty("method")
	private String method;
	@JsonProperty("guid")
	private Integer guid;
	@JsonProperty("authenticationKey")
	private String authenticationKey;
	@JsonProperty("deviceType")
	private String deviceType;
	@JsonProperty("requestTranscript")
	private Boolean requestTranscript;
	@JsonProperty("intrinsics")
	private RequestChatIntrinsics intrinsics;
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

	@JsonProperty("deviceType")
	public String getDeviceType() {
		return deviceType;
	}

	@JsonProperty("deviceType")
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@JsonProperty("requestTranscript")
	public Boolean getRequestTranscript() {
		return requestTranscript;
	}

	@JsonProperty("requestTranscript")
	public void setRequestTranscript(Boolean requestTranscript) {
		this.requestTranscript = requestTranscript;
	}

	@JsonProperty("intrinsics")
	public RequestChatIntrinsics getIntrinsics() {
		return intrinsics;
	}

	@JsonProperty("intrinsics")
	public void setIntrinsics(RequestChatIntrinsics intrinsics) {
		this.intrinsics = intrinsics;
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
				.append("authenticationKey", authenticationKey).append("deviceType", deviceType)
				.append("requestTranscript", requestTranscript).append("intrinsics", intrinsics)
				.append("additionalProperties", additionalProperties).toString();
	}

}
