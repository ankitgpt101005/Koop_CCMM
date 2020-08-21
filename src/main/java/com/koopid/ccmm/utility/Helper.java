package com.koopid.ccmm.utility;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Helper {

	private static final Logger log = LoggerFactory.getLogger(Helper.class);

	public String objectToJson(Object chatObj) {

		ObjectMapper mapper = new ObjectMapper();

		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(chatObj);
			return jsonStr;
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return jsonStr;
	}

	public Object jsonStrToObject(String message, Object obj) {

		ObjectMapper mapper = new ObjectMapper();
		Object destObj = null;
		try {
			destObj = mapper.readValue(message, obj.getClass());
			return destObj;
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return destObj;
	}
}
