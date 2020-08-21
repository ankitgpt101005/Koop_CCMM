package com.koopid.ccmm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koopid.ccmm.entity.KoopidProvider;
import com.koopid.ccmm.entity.ProviderPartnerId;

public interface KoopidConfigRepository extends JpaRepository<KoopidProvider, String> {

	KoopidProvider findByProviderPartnerId(ProviderPartnerId providerPartnerId);
	
	void deleteByProviderPartnerId(ProviderPartnerId providerPartnerId);
	
	List<KoopidProvider> findByIsActiveTrue();
	@Override
	<S extends KoopidProvider> S save(S koopidConfig);
}
