package com.hvn.tutorial.affablebean.repository;

import com.hvn.tutorial.affablebean.domain.Product;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ProductDao extends Repository<Product, Integer> {
	
	List<Product> findByCategoryId(Byte id);
	
	Product findOne(Integer id);
	
}
