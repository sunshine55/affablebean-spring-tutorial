package com.hvn.tutorial.affablebean.persistence;

import com.hvn.tutorial.affablebean.domain.Category;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CategoryDao extends Repository<Category, Byte> {

	List<Category> findAll();
	
}
