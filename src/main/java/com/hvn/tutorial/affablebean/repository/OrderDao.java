package com.hvn.tutorial.affablebean.repository;

import com.hvn.tutorial.affablebean.domain.CustomerOrder;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface OrderDao extends Repository<CustomerOrder, Integer> {
		
	List<CustomerOrder> findAll();
	
	CustomerOrder save(CustomerOrder order);
	
}
