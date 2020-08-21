package com.koopid.ccmm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koopid.ccmm.entity.KoopidAgents;

public interface KoopidAgentsRepository extends JpaRepository<KoopidAgents, String> {

	KoopidAgents findBypartnerId(String context);

	KoopidAgents save(KoopidAgents koopidAgents);

}
