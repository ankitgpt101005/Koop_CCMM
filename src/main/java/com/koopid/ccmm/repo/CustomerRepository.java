package com.koopid.ccmm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koopid.ccmm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

	Customer findByContext(String context);

	Customer save(Customer customer);

}
