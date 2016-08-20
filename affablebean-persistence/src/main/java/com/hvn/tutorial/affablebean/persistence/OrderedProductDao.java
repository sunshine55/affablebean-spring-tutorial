package com.hvn.tutorial.affablebean.persistence;

import com.hvn.tutorial.affablebean.domain.OrderedProduct;
import com.hvn.tutorial.affablebean.domain.OrderedProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductDao extends JpaRepository<OrderedProduct, OrderedProductId> {
	
}
