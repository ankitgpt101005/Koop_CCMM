package com.koopid.ccmm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koopid.ccmm.entity.AgentQueued;

public interface AgentQueuedRepository extends JpaRepository<AgentQueued, String> {

	AgentQueued findByContext(String context);

	AgentQueued save(AgentQueued agentQueued);

}
