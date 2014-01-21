package com.hvn.velocity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hvn.velocity.domain.Category;
import com.hvn.velocity.repository.CategoryDao;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> listAll() {
		return categoryDao.findAll();
	}
	
}
