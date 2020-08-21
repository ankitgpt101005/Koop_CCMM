package com.koopid.ccmm.intercepter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.koopid.ccmm.intercepter.auth.GatewayAuthenticatorInterceptor;
import com.koopid.ccmm.intercepter.validator.PathParamsValidator;
import com.koopid.ccmm.utility.Constants;

@Configuration
public class KoopidAACCGatewayInterceptorConfig implements WebMvcConfigurer {

	private static final Logger log = LoggerFactory.getLogger(KoopidAACCGatewayInterceptorConfig.class);
	@Autowired
	private PathParamsValidator pathParamsValidator;
	@Autowired
	private GatewayAuthenticatorInterceptor gatewayAuthenticatorInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(pathParamsValidator).excludePathPatterns(Constants.ERROR_PATH_PATTERN,
				Constants.CONFIG_PATH_PATTERN);
		registry.addInterceptor(gatewayAuthenticatorInterceptor).excludePathPatterns(Constants.ERROR_PATH_PATTERN,
				Constants.CONFIG_PATH_PATTERN);
		log.debug("Register Intercepter");
	}
}
