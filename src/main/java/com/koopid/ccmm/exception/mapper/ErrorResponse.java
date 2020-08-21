package com.koopid.ccmm.exception.mapper;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse
{
	private String reason;
	private String code = "failure";

	private List<Error> errors = new ArrayList<>();
	
	public List<Error> getErrors()
	{
		return errors;
	}

	public void setErrors(List<Error> errors)
	{
		this.errors = errors;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
