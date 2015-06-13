package com.hvn.tutorial.affablebean.session;

import com.hvn.tutorial.affablebean.domain.Product;
import com.hvn.tutorial.affablebean.service.Cart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTests {
	
	private Cart cart;
	private ArrayList<Product> testData;
	
	@Before
	public void setup() throws Exception {
		Product prod1 = new Product(null, "Product_1", new BigDecimal("2.5"), null);
		prod1.setId(1);
		Product prod2 = new Product(null, "Product_2", new BigDecimal("3"), null);
		prod2.setId(2);
		
		testData = new ArrayList<Product>();
		testData.add(prod1);
		testData.add(prod2);
		
		cart = new Cart();
		for (int i = 0; i < 2; i++) {
			cart.addItem(testData.get(0));
		}
		cart.addItem(testData.get(1));
	}
	
	@After
	public void teardown() throws Exception {
		cart = null;
		testData.clear();
	}

	@Test
	public void testGetItems() {
		assertThat(cart.getItems()).hasSize(2);
	}

	@Test
	public void testAddItems() {
		assertThat(cart.getItems()).containsKeys(testData.get(0), testData.get(1));
		assertThat(cart.getItems().get(testData.get(0))).isEqualTo(2);
		assertThat(cart.getItems().get(testData.get(1))).isEqualTo(1);
	}

	@Test
	public void testClear() {
		cart.clear();
		assertThat(cart.getItems()).hasSize(0);
	}

	@Test
	public void testUpdateItem() {
		cart.updateItem(2, 2);
		assertThat(cart.getItems()).containsKeys(testData.get(0), testData.get(1));
		assertThat(cart.getItems().get(testData.get(0))).isEqualTo(2);
		assertThat(cart.getItems().get(testData.get(1))).isEqualTo(2);
	}

	@Test
	public void testCalculateSubTotal() {
		assertThat(cart.calculateSubTotal()).isEqualTo(8.00);
	}

	@Test
	public void testCalculateTotal() {
		assertThat(cart.calculateTotal(8.00)).isEqualTo(11.00);
	}

	@Test
	public void testSumQuantity() {
		assertThat(cart.sumQuantity()).isEqualTo(3);
	}

}
