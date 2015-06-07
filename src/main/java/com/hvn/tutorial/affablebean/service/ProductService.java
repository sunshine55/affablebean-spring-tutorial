package com.hvn.tutorial.affablebean.service;

import com.hvn.tutorial.affablebean.domain.Product;
import com.hvn.tutorial.affablebean.repository.ProductDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class ProductService {

	@Resource
	private ProductDao productDao;
	
	public List<Product> getByCategoryId(Byte id) {
		return productDao.findByCategoryId(id);
	}
	
	public Product getById(Integer id) {
		return productDao.findOne(id);
	}
	
}
