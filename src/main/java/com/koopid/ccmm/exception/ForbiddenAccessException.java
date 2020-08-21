package com.koopid.ccmm.exception;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

public class ForbiddenAccessException  extends AppException {
	private static final long serialVersionUID = 1L;

	public ForbiddenAccessException(String message)
	{
		super(message);
	}

	@Override
	public StatusType getHttpErrorCode()
	{
		return Status.FORBIDDEN;
	}
}
