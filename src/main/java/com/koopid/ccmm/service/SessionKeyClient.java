package com.koopid.ccmm.service;

import static com.koopid.ccmm.utility.Constants.CI_CONTACT_WS;
import static com.koopid.ccmm.utility.Constants.CI_CUSTOMER_WS;
import static com.koopid.ccmm.utility.Constants.CI_SKILLSET_WS;
import static com.koopid.ccmm.utility.Constants.CI_UTILITY_WS;
import static com.koopid.ccmm.utility.Constants.CI_WEB_COMMS_WS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.koopid.ccmm.wsdl.ci_contact.ReadContact;
import com.koopid.ccmm.wsdl.ci_contact.ReadContactResponse;
import com.koopid.ccmm.wsdl.ci_customer.RequestTextChat;
import com.koopid.ccmm.wsdl.ci_customer.RequestTextChatResponse;
import com.koopid.ccmm.wsdl.ci_skill_set.GetSkillsetByName;
import com.koopid.ccmm.wsdl.ci_skill_set.GetSkillsetByNameResponse;
import com.koopid.ccmm.wsdl.ci_utility.GetAndUpdateAnonymousCustomerID;
import com.koopid.ccmm.wsdl.ci_utility.GetAndUpdateAnonymousCustomerIDResponse;
import com.koopid.ccmm.wsdl.ci_utility.GetAnonymousSessionKey;
import com.koopid.ccmm.wsdl.ci_utility.GetAnonymousSessionKeyResponse;
import com.koopid.ccmm.wsdl.ci_utility.GetTotalQueued;
import com.koopid.ccmm.wsdl.ci_utility.GetTotalQueuedResponse;
import com.koopid.ccmm.wsdl.ci_utility.GetTotalQueuedToSkillset;
import com.koopid.ccmm.wsdl.ci_utility.GetTotalQueuedToSkillsetResponse;
import com.koopid.ccmm.wsdl.ci_webcomm.ReadChatMessage;
import com.koopid.ccmm.wsdl.ci_webcomm.ReadChatMessageResponse;
import com.koopid.ccmm.wsdl.ci_webcomm.UpdateAliveTimeAndUpdateIsTyping;
import com.koopid.ccmm.wsdl.ci_webcomm.UpdateAliveTimeAndUpdateIsTypingResponse;
import com.koopid.ccmm.wsdl.ci_webcomm.WriteChatMessage;
import com.koopid.ccmm.wsdl.ci_webcomm.WriteChatMessageResponse;

/**
 * 
 * @author GS-2145
 *
 */
public class SessionKeyClient extends WebServiceGatewaySupport {


	public GetAnonymousSessionKeyResponse getSessionKey(String ccmmWebserviceUrl, String ccmmWebserviceEndPoint) {
		GetAnonymousSessionKey getAnonymousSessionKey = new GetAnonymousSessionKey();
		return (GetAnonymousSessionKeyResponse) getWebServiceTemplate().marshalSendAndReceive(
				ccmmWebserviceUrl + CI_UTILITY_WS, getAnonymousSessionKey,
				new SoapActionCallback(ccmmWebserviceEndPoint + "GetAnonymousSessionKey"));
	}

	public GetTotalQueuedResponse getTotalQueued(GetTotalQueued getTotalQueued, String ccmmWebserviceUrl, String ccmmWebserviceEndPoint) {
		return (GetTotalQueuedResponse) getWebServiceTemplate().marshalSendAndReceive(ccmmWebserviceUrl + CI_UTILITY_WS,
				getTotalQueued, new SoapActionCallback(ccmmWebserviceEndPoint + "GetTotalQueued"));
	}

	public GetTotalQueuedToSkillsetResponse getTotalQueuedToSkillset(
			GetTotalQueuedToSkillset getTotalQueuedToSkillset, String ccmmWebserviceUrl, String ccmmWebserviceEndPoint) {
		return (GetTotalQueuedToSkillsetResponse) getWebServiceTemplate().marshalSendAndReceive(
				ccmmWebserviceUrl + CI_UTILITY_WS, getTotalQueuedToSkillset,
				new SoapActionCallback(ccmmWebserviceEndPoint + "GetTotalQueuedToSkillset"));
	}

	public GetAndUpdateAnonymousCustomerIDResponse getMyCustomerId(
			GetAndUpdateAnonymousCustomerID getAndUpdateAnonymousCustomerID,
			String ccmmWebserviceUrl, String ccmmWebserviceEndPoint) {
		return (GetAndUpdateAnonymousCustomerIDResponse) getWebServiceTemplate().marshalSendAndReceive(
				ccmmWebserviceUrl + CI_UTILITY_WS, getAndUpdateAnonymousCustomerID,
				new SoapActionCallback(ccmmWebserviceEndPoint + "GetAndUpdateAnonymousCustomerID"));
	}

	public RequestTextChatResponse requestTextChat(RequestTextChat requestTextChat, String ccmmWebserviceUrl, 
			String ccmmWebserviceEndPoint) {
		return (RequestTextChatResponse) getWebServiceTemplate().marshalSendAndReceive(
				ccmmWebserviceUrl + CI_CUSTOMER_WS, requestTextChat,
				new SoapActionCallback(ccmmWebserviceEndPoint + "RequestTextChat"));
	}

	public ReadChatMessageResponse getReadChatMessage(ReadChatMessage readChatMessage, String ccmmWebserviceUrl, 
			String ccmmWebserviceEndPoint) {
		return (ReadChatMessageResponse) getWebServiceTemplate().marshalSendAndReceive(
				ccmmWebserviceUrl + CI_WEB_COMMS_WS, readChatMessage,
				new SoapActionCallback(ccmmWebserviceEndPoint + "ReadChatMessage"));
	}

	public WriteChatMessageResponse getWriteChatMessage(WriteChatMessage writeChatMessage, String ccmmWebserviceUrl,
			String ccmmWebserviceEndPoint) {
		return (WriteChatMessageResponse) getWebServiceTemplate().marshalSendAndReceive(
				ccmmWebserviceUrl + CI_WEB_COMMS_WS, writeChatMessage,
				new SoapActionCallback(ccmmWebserviceEndPoint + "WriteChatMessage"));
	}

	public ReadContactResponse getReadContactResponse(ReadContact readContact, String ccmmWebserviceUrl,
			String ccmmWebserviceEndPoint) {
		return (ReadContactResponse) getWebServiceTemplate().marshalSendAndReceive(ccmmWebserviceUrl + CI_CONTACT_WS,
				readContact, new SoapActionCallback(ccmmWebserviceEndPoint + "ReadContact"));
	}

	public UpdateAliveTimeAndUpdateIsTypingResponse updateAliveTimeAndUpdateIsTyping(
			UpdateAliveTimeAndUpdateIsTyping updateAliveTimeAndUpdateIsTyping, String ccmmWebserviceUrl, 
			String ccmmWebserviceEndPoint) {
		return (UpdateAliveTimeAndUpdateIsTypingResponse) getWebServiceTemplate().marshalSendAndReceive(
				ccmmWebserviceUrl + CI_WEB_COMMS_WS, updateAliveTimeAndUpdateIsTyping,
				new SoapActionCallback(ccmmWebserviceEndPoint + "UpdateAliveTimeAndUpdateIsTyping"));
	}

	public GetSkillsetByNameResponse getSkillsetByNameResponse(GetSkillsetByName getSkillsetByName, String ccmmWebserviceUrl, String ccmmWebserviceEndPoint) {
		return (GetSkillsetByNameResponse) getWebServiceTemplate().marshalSendAndReceive(
				ccmmWebserviceUrl + CI_SKILLSET_WS, getSkillsetByName,
				new SoapActionCallback(ccmmWebserviceEndPoint + "GetSkillsetByName"));
	}
}
