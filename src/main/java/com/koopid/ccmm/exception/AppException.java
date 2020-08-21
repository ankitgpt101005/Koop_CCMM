package com.koopid.ccmm.exception;

import javax.ws.rs.core.Response.StatusType;

public abstract class AppException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	public AppException()
	{

	}

	public AppException(String message)
	{
		super(message);
	}

	public abstract StatusType getHttpErrorCode();
}
