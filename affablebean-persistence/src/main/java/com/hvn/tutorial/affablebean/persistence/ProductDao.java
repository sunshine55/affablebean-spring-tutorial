package com.hvn.tutorial.affablebean.persistence;

import com.hvn.tutorial.affablebean.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	
	List<Product> findByCategoryId(Byte id);

}
