package com.koopid.ccmm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.koopid.ccmm.entity.Route;

public interface RouteRepository extends JpaRepository<Route, String> {

	Route findByContext(String context);

	Route findByIsActive(Boolean isActive);
	
	//Route findByIsRouteExistAndType(@Param("is_active") Boolean isActive, @Param("type") String type);
	
	List<Route> findByIsActiveAndType(@Param("is_active") Boolean isActive, @Param("type") String type);

	Route save(Route route);

}
