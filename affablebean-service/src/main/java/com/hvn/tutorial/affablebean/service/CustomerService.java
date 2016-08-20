package com.hvn.tutorial.affablebean.service;

import com.hvn.tutorial.affablebean.persistence.CustomerDao;
import com.hvn.tutorial.affablebean.domain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CustomerService {

	@Resource
	private CustomerDao customerDao;
	
	public Integer save(Customer customer) {
		Customer persisted = customerDao.save(customer);
		return persisted.getId();
	}
	
	public List<Customer> getAll() {
		return customerDao.findAll();
	}
	
	public Customer getById(Integer id) {
		return customerDao.findOne(id);
	}
	
	public Customer getByEmail(String email) {
		return customerDao.findByEmail(email);
	}
	
}
