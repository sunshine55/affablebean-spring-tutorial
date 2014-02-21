package com.hvn.velocity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hvn.velocity.domain.Customer;
import com.hvn.velocity.repository.CustomerDao;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	public Integer save(Customer customer) {
		return customerDao.save(customer);
	}
	
	public List<Customer> getAll() {
		return customerDao.findAll();
	}
	
	public Customer getById(Integer id) {
		return customerDao.findById(id);
	}
	
	public Customer getByEmail(String email) {
		return customerDao.findByEmail(email);
	}
	
}
