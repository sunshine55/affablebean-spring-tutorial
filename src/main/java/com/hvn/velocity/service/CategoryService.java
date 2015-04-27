package com.hvn.velocity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hvn.velocity.domain.Category;
import com.hvn.velocity.repository.CategoryDao;

import javax.annotation.Resource;

@Service
@Transactional
public class CategoryService {

	@Resource
	private CategoryDao categoryDao;
	
	public List<Category> getAll() {
		return categoryDao.findAll();
	}
	
}
