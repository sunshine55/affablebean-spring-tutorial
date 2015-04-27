package com.hvn.velocity.repository;

import com.hvn.velocity.domain.Customer;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface  CustomerDao extends Repository<Customer, Integer> {
	
	List<Customer> findAll();

	Customer findOne(Integer id);

	List<Customer> findByEmail(String email);
	
	Customer save(Customer customer);

}
