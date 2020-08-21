package com.koopid.ccmm.utility;

public class EwcConstants {

	private EwcConstants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String REQUEST_CHAT = "{\"apiVersion\":\"1.0\",\"type\":\"request\",\"body\":"
			+ "{\"method\":\"requestChat\"," + "\"guid\":654321," + "\"authenticationKey\":\"bgc714f138g2236823hchc\","
			+ "\"deviceType\":\"Mozilla/5.0 (Windows NT 6.1; WOW64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.130 Safari/537.36\","
			+ "\"requestTranscript\":false,"
			+ "\"intrinsics\":{\"email\":\"test@test.com\",\"name\":\"John\",\"country\":\"+353\",\"area\":\"091\",\"phoneNumber\":\"733277\",\"skillset\":\"WC_Default_Skillset\","
			+ "\"customFields\":[{\"title\":\"address\",\"value\":\"Springfield\"},{\"title\":\"locale\",\"value\":\"en-GB\"}]}}}";

}
