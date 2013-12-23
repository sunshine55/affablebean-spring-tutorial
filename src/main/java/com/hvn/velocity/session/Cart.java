package com.hvn.velocity.session;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hvn.velocity.entities.Product;

public class Cart {

	private Map<Product, Integer> products = new HashMap<Product, Integer>();

	public Map<Product, Integer> getProducts() {
		return Collections.unmodifiableMap(this.products);
	}

	public void addProduct(Product product) {
		if (!products.isEmpty()) {
			Set<Product> keys = products.keySet();
			int i = 0;
			for (Product key : keys) {
				if (key.getId() == product.getId()) {
					int quantity = products.get(key);
					quantity++;
					products.put(key, quantity);
					break;
				}
				i++;
				if (i >= keys.size()) {
					products.put(product, 1);
				}
			}
		} else {
			products.put(product, 1);
		}
	}

	public void clear() {
		this.products.clear();
	}

	public void updateProduct(Integer id, Integer qty) {
		Set<Product> keys = products.keySet();
		for (Product key : keys) {
			if (key.getId() == id) {
				if (qty <= 0)
					products.remove(key);
				else
					products.put(key, qty);
				break;
			}
		}
	}

	public double calculateSubTotal() {
		double sum = 0;
		if (products.size() != 0) {
			Set<Entry<Product, Integer>> entries = products.entrySet();
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
	
}
