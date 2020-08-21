package com.koopid.ccmm.exception;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import com.koopid.ccmm.utility.Constants;

public class ProviderSubscriptionExpiredException extends AppException
{
	private static final long serialVersionUID = 1L;

	public ProviderSubscriptionExpiredException(String messsage)
	{
		super(messsage);
	}

	public ProviderSubscriptionExpiredException()
	{
		super(getErrorMessage());
	}

	@Override
	public StatusType getHttpErrorCode()
	{
		return Status.PAYMENT_REQUIRED;
	}

	public static String getErrorMessage()
	{
		return String.format(Constants.PROVIDER_SUBSCRIPTION_EXPIRED);
	}
}
