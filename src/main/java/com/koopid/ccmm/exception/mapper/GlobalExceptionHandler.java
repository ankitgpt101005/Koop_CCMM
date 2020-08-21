package com.koopid.ccmm.exception.mapper;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.koopid.ccmm.exception.AppException;
import com.koopid.ccmm.utility.Constants;
import com.koopid.ccmm.utility.StringUtils;

import javassist.NotFoundException;


@ControllerAdvice
public class GlobalExceptionHandler
{
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(AppException.class)
	public @ResponseBody ErrorResponse appExceptionHandler(AppException exception,
			HttpServletResponse response)
	{
		response.setStatus(exception.getHttpErrorCode().getStatusCode());
		String msgException = exception.getMessage();

		if (StringUtils.isNullOrEmpty(msgException))
		{
			msgException = Constants.APP_EXCEPTION;
		}

		log.debug(msgException, exception);
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setReason(msgException);

		return errorResponse;
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public @ResponseBody ErrorResponse notFoundExceptionHandler(NotFoundException exception)
	{
		log.debug(Constants.NOT_FOUND_EXCEPTION, exception);
		// Here we can get the specific message from an exception
		// but the generic message looks good which will not expose 
		// invalid entity to the end user  
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setReason(Constants.NOT_FOUND_EXCEPTION);
		return errorResponse;
	}
	
}
