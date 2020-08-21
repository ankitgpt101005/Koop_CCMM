package com.koopid.ccmm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koopid.ccmm.entity.ConversationTimestamp;

public interface ConversationTimestampRepository extends JpaRepository<ConversationTimestamp, String> {

	ConversationTimestamp findByContext(String context);

	ConversationTimestamp save(ConversationTimestamp customer);

}
