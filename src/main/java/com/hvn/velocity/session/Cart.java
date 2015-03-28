package com.hvn.velocity.session;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.hvn.velocity.domain.Product;

@Component
public class Cart implements java.io.Serializable {
	
	private static final long serialVersionUID = 7810676090697245518L;
	
	private Map<Product, Integer> items = new HashMap<Product, Integer>(0);

	public Map<Product, Integer> getItems() {
		return Collections.unmodifiableMap(this.items);
	}

	public void addItem(Product product) {
		if (!items.isEmpty()) {
			Set<Product> keys = items.keySet();
			int i = 0;
			for (Product key : keys) {
				if (key.getId() == product.getId()) {
					int quantity = items.get(key);
					quantity++;
					items.put(key, quantity);
					break;
				}
				i++;
				if (i >= keys.size()) {
					items.put(product, 1);
				}
			}
		} else {
			items.put(product, 1);
		}
	}

	public void clear() {
		this.items.clear();
	}

	public void updateItem(Integer id, Integer qty) {
		Set<Product> keys = items.keySet();
		for (Product key : keys) {
			if (key.getId() == id) {
				if (qty <= 0)
					items.remove(key);
				else
					items.put(key, qty);
				break;
			}
		}
	}

	public double calculateSubTotal() {
		double sum = 0;
		if (items.size() != 0) {
			Set<Entry<Product, Integer>> entries = items.entrySet();
			for (Entry<Product, Integer> entry : entries) {
				sum += entry.getKey().getPrice().doubleValue()
						* entry.getValue();
			}
		}
		DecimalFormat form = new DecimalFormat("#.##");
		return Double.valueOf(form.format(sum));
	}
	
	public double calculateTotal(double subTotal) {
		DecimalFormat form = new DecimalFormat("#.##");
		return Double.valueOf(form.format(subTotal + 3));
	}
	
	public Integer sumQuantity() {
		Integer count = 0;
		Set<Product> keys = items.keySet();
		for (Product key : keys) {
			count += items.get(key);
		}
		return count;
	}
	
}
