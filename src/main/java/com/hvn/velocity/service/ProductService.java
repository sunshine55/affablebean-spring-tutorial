package com.hvn.velocity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hvn.velocity.domain.Product;
import com.hvn.velocity.repository.ProductDao;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	public List<Product> listByCategoryId(Byte id) {
		return productDao.findByCategoryId(id);
	}
	
	public Product getById(Integer id) {
		return productDao.findById(id);
	}
	
}
