package com.hvn.velocity.repositories;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hvn.velocity.entities.Category;

@Repository
public class CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Category> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Category").list();
	}
	
}
