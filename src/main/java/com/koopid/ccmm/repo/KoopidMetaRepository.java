package com.koopid.ccmm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koopid.ccmm.entity.KoopidMeta;

public interface KoopidMetaRepository extends JpaRepository<KoopidMeta, String> {

	KoopidMeta findByContext(String context);

	KoopidMeta save(KoopidMeta koopidMeta);

}
