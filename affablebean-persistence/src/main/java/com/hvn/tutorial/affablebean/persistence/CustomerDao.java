package com.hvn.tutorial.affablebean.persistence;

import com.hvn.tutorial.affablebean.domain.Customer;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CustomerDao extends Repository<Customer, Integer> {
	
	List<Customer> findAll();

	Customer findOne(Integer id);

	Customer findByEmail(String email);
	
	Customer save(Customer customer);

}
