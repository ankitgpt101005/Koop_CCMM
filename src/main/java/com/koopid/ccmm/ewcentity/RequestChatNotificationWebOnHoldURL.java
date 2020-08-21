
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
@JsonPropertyOrder({ "tag", "description", "holdTime", "sequence", "urls" })
public class RequestChatNotificationWebOnHoldURL {

	@JsonProperty("tag")
	private String tag;
	@JsonProperty("description")
	private String description;
	@JsonProperty("holdTime")
	private Integer holdTime;
	@JsonProperty("sequence")
	private Integer sequence;
	@JsonProperty("urls")
	private List<RequestChatNotificationUrl> urls = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("tag")
	public String getTag() {
		return tag;
	}

	@JsonProperty("tag")
	public void setTag(String tag) {
		this.tag = tag;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("holdTime")
	public Integer getHoldTime() {
		return holdTime;
	}

	@JsonProperty("holdTime")
	public void setHoldTime(Integer holdTime) {
		this.holdTime = holdTime;
	}

	@JsonProperty("sequence")
	public Integer getSequence() {
		return sequence;
	}

	@JsonProperty("sequence")
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@JsonProperty("urls")
	public List<RequestChatNotificationUrl> getUrls() {
		return urls;
	}

	@JsonProperty("urls")
	public void setUrls(List<RequestChatNotificationUrl> urls) {
		this.urls = urls;
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
		return new ToStringBuilder(this).append("tag", tag).append("description", description)
				.append("holdTime", holdTime).append("sequence", sequence).append("urls", urls)
				.append("additionalProperties", additionalProperties).toString();
	}

}
