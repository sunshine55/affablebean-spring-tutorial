package com.hvn.tutorial.affablebean.persistence;

import com.hvn.tutorial.affablebean.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

	Customer findByEmail(String email);

}
