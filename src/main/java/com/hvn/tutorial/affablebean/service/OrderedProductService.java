package com.hvn.tutorial.affablebean.service;

import com.hvn.tutorial.affablebean.domain.CustomerOrder;
import com.hvn.tutorial.affablebean.domain.Product;
import com.hvn.tutorial.affablebean.domain.OrderedProduct;
import com.hvn.tutorial.affablebean.domain.OrderedProductId;
import com.hvn.tutorial.affablebean.repository.OrderedProductDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class OrderedProductService {

	@Resource
	private OrderedProductDao orderedProductDao;
	
	public void save(CustomerOrder order, Map<Product, Integer> items) {
		Set<Product> keys = items.keySet();
		Set<OrderedProduct> set = new HashSet<OrderedProduct>(0);
		for (Product key : keys) {
			int productId = key.getId();
			int orderId = order.getId();
			OrderedProductId orderedProductId = new OrderedProductId(orderId, productId);
			OrderedProduct orderedProduct = new OrderedProduct(orderedProductId, key, order, items.get(key).shortValue());
			set.add(orderedProduct);
		}
		orderedProductDao.save(set);
	}
	
}
