package com.koopid.ccmm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koopid.ccmm.entity.EwcMeta;

public interface EwcMetaRepository extends JpaRepository<EwcMeta, String> {

	EwcMeta findByContext(String context);

	EwcMeta save(EwcMeta ewcMeta);

}
