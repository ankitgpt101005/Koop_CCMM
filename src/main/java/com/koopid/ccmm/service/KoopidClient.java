package com.koopid.ccmm.service;

import com.koopid.ccmm.entity.AgentContext;
import com.koopid.ccmm.entity.ConversationContext;
import com.koopid.ccmm.entity.CustomerContext;
import com.koopid.ccmm.entity.KoopidProviderConfig;
import com.koopid.ccmm.entity.MessageContext;

public interface KoopidClient {

	AgentContext routing(CustomerContext customerContext, String partnerId, String providerId);

	void msgrecv(MessageContext messageContext);

	void conversation(ConversationContext conversationContext, String providerId, String partnerId);
}