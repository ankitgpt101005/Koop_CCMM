package com.koopid.ccmm.utility;

public class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String SCAN_PACKAGES = "com.koopid.ccmm.wsdl";
	public static final String START = "start";
	public static final String END = "end";
	public static final String EMPTY = "";
	public static final String TYPING = "typing";
	public static final String DEFAULT_SKILL = "WC_Skill2";
	public static final String EWC_DEVICE_TYPE = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36";

	public static final String ROUTING = "Routing";
	public static final String SEND_MESSAGE = "SendMessage";
	public static final String WHITELIST = "Whitelist";
	public static final String CALLBACKS = "Callbacks";
	public static final String CONVERSATION = "Conversation";

	public static final String PROVIDER_ID_AS_STRING = "&providerId=";
	public static final String PARTNER_ID_AS_STRING = "?partnerId=";
	public static final String API_KEY_AS_STRING = "&apiKey=";

	public static final String PROVIDER_ID = "providerId";
	public static final String PARTNER_ID = "partnerId";
	public static final String AUTH_KEY = "authKey";
	public static final String PROVIDER_SUBSCRIPTION_EXPIRED = "Provider subscription is expired. Please contact account administrator";
	public static final String INVALID_URI_PARAM = "Invalid value provided for the URI parameter '%s'";
	public static final String ERROR_PATH_PATTERN = "/error";
	public static final String CONFIG_PATH_PATTERN = "/aacc_chat_interface/api/config/**";

	// Exception Messages
	public static final String APP_EXCEPTION = "Failed to process request";
	public static final String NOT_FOUND_EXCEPTION = "The server has not found anything matching the Request-URI";
	public static final String PARTNER_PROVIDER_NOT_FOUND = "Requested Partner - '%s' Provider - %s not found";
	public static final String PERMISSION_DENIED = "Permission denied";

	// CCMM SOAP APIs
	public static final String CI_WEB_COMMS_WS = "CIWebCommsWs.asmx";
	public static final String CI_UTILITY_WS = "CIUtilityWs.asmx";
	public static final String CI_CUSTOMER_WS = "CICustomerWs.asmx";
	public static final String CI_CONTACT_WS = "CIContactWs.asmx";
	public static final String CI_SKILLSET_WS = "CISkillsetWs.asmx";

	public static final int REPEAT_INTERVAL = 3000;
	public static final int POLLDURATION = 3000;
	public static final int CHAT_EXPIRY = 1;

	public static final String CHAT_MESSAGE_FROM_AGENT = "CHAT_MESSAGE_FROM_AGENT";
	public static final String AGENT_LEFT_MESSAGE = "Looks like agent has left the chat or the session is no more active";
	public static final String CHAT_EXPIRY_MSG = "Closing chat due to idle time threshold";
	public static final String CONTACT_ID_NOT_RECOGNISED = "Contact ID not recognised: possibly waiting on an agent to accept contact (errorcode: 2831; Cache: 8756).";
	public static final String INVALID_SESSION_KEY = "Access Denied - Validation of session key failed";
	public static final String SESSION_DISCONNECT_BY_CUSTOMER = "Chat closed by customer";
	public static final String WELCOME_MESSAGE = "[Agent] Welcome to the Avaya Contact Center. How can I help you?";

	// EWC
	public static final String DELIVERED = "delivered";
	public static final String CHAT_NOT_STARTED = "Invalid message: Chat not started";
	public static final String INVALID_SESSION = "Unable to reconnect, session key is invalid or expired";
	
	// Customer Context
	public static final String CUST_NAME = "name";
	public static final String CUST_EMAIL = "email";
	public static final String CUST_PHONE = "phone";
	
	// Redis constants
	public static final String PROVIDER_FIELD = "provider";
}
