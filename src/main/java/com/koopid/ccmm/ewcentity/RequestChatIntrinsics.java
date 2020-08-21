
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
@JsonPropertyOrder({ "email", "name", "country", "area", "phoneNumber", "skillset", "customFields" })
public class RequestChatIntrinsics {

	@JsonProperty("email")
	private String email;
	@JsonProperty("name")
	private String name;
	@JsonProperty("country")
	private String country;
	@JsonProperty("area")
	private String area;
	@JsonProperty("phoneNumber")
	private String phoneNumber;
	@JsonProperty("skillset")
	private String skillset;
	@JsonProperty("customFields")
	private List<RequestChatCustomField> customFields = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("area")
	public String getArea() {
		return area;
	}

	@JsonProperty("area")
	public void setArea(String area) {
		this.area = area;
	}

	@JsonProperty("phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("skillset")
	public String getSkillset() {
		return skillset;
	}

	@JsonProperty("skillset")
	public void setSkillset(String skillset) {
		this.skillset = skillset;
	}

	@JsonProperty("customFields")
	public List<RequestChatCustomField> getCustomFields() {
		return customFields;
	}

	@JsonProperty("customFields")
	public void setCustomFields(List<RequestChatCustomField> customFields) {
		this.customFields = customFields;
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
		return new ToStringBuilder(this).append("email", email).append("name", name).append("country", country)
				.append("area", area).append("phoneNumber", phoneNumber).append("skillset", skillset)
				.append("customFields", customFields).append("additionalProperties", additionalProperties).toString();
	}

}
