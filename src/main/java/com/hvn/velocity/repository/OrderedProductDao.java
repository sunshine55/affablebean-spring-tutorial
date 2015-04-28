package com.hvn.velocity.repository;

import com.hvn.velocity.domain.OrderedProduct;
import com.hvn.velocity.domain.OrderedProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductDao extends JpaRepository<OrderedProduct, OrderedProductId> {
	
}
