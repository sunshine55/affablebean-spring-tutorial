package com.hvn.velocity.repository;

import com.hvn.velocity.domain.Category;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CategoryDao extends Repository<Category, Byte> {

	List<Category> findAll();
	
}
