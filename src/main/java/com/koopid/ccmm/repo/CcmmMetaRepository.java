package com.koopid.ccmm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koopid.ccmm.entity.CcmmMeta;

public interface CcmmMetaRepository extends JpaRepository<CcmmMeta, String> {

	CcmmMeta findByContext(String context);

	CcmmMeta save(CcmmMeta ccmmMeta);

}
