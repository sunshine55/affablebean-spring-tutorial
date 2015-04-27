package com.hvn.velocity.repository;

import com.hvn.velocity.domain.OrderedProduct;
import com.hvn.velocity.domain.OrderedProductId;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface OrderedProductDao extends Repository<OrderedProduct, OrderedProductId> {
	
	Set<OrderedProduct> save(Set<OrderedProduct> orderedProducts);
	
}
