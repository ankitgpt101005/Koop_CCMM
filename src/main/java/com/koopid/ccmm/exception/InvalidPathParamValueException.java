package com.koopid.ccmm.exception;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import com.koopid.ccmm.utility.Constants;

public class InvalidPathParamValueException extends AppException {
	private static final long serialVersionUID = 1L;

	public InvalidPathParamValueException(String messsage)
	{
		super(messsage);
	}


	@Override
	public StatusType getHttpErrorCode()
	{
		return Status.BAD_REQUEST;
	}

	public static String getErrorMessage(String fieldName)
	{
		return String.format(Constants.INVALID_URI_PARAM, fieldName);
	}
}
