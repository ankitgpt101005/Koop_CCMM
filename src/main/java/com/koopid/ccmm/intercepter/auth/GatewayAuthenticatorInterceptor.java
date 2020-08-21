package com.koopid.ccmm.intercepter.auth;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.koopid.ccmm.exception.InvalidPathParamValueException;
import com.koopid.ccmm.utility.CommonUtility;
import com.koopid.ccmm.utility.Constants;

@Component
public class GatewayAuthenticatorInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = LoggerFactory.getLogger(GatewayAuthenticatorInterceptor.class);
	@Autowired
	private CommonUtility commonUtility;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!(handler instanceof HandlerMethod))
		{
			return true;
		}

		final String partnerId = getId(request, Constants.PARTNER_ID);
		log.info(" Partner Id *** "+partnerId);
		if (partnerId == null || partnerId.isEmpty())
		{
			log.debug("Resource does not contain partnerId in path");
			throw new InvalidPathParamValueException(Constants.PARTNER_ID);
		}

		final String providerId = getId(request, Constants.PROVIDER_ID);
		log.info(" providerId *** "+providerId);
		if (providerId == null || providerId.isEmpty())
		{
			log.debug("Resource does not contain providerId in path");
			throw new InvalidPathParamValueException(Constants.PROVIDER_ID);
		}
		return commonUtility.authenticate(request.getParameterValues(Constants.AUTH_KEY), partnerId, providerId);
	}

	@SuppressWarnings("unchecked")
	private String getId(HttpServletRequest request, String param)
	{
		Map<String, String> variables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		return variables.get(param);
	}
}
