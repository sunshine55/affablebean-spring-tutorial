package com.hvn.velocity.repository;

import com.hvn.velocity.domain.CustomerOrder;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface OrderDao extends Repository<CustomerOrder, Integer> {
		
	List<CustomerOrder> findAll();
	
	CustomerOrder save(CustomerOrder order);
	
}
