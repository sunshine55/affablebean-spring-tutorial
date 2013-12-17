package com.hvn.velocity.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<Product, Integer> products = new HashMap<Product, Integer>();

    public Map<Product, Integer> getProducts() {
        return Collections.unmodifiableMap(this.products);
    }

    public void addProduct(Product product) {
        if (this.products.containsKey(product)) {
            int quantity = this.products.get(product);
            quantity++;
            this.products.put(product, quantity);
        } else {
            this.products.put(product, 1);
        }
    }
    
    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public void clear() {
        this.products.clear();
    }
    
}
