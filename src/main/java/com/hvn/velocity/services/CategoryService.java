package com.hvn.velocity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hvn.velocity.entities.Category;
import com.hvn.velocity.repositories.CategoryDao;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Transactional
	public List<Category> listAll() {
		return categoryDao.findAll();
	}
	
}
