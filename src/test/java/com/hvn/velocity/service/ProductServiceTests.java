package com.hvn.velocity.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.hvn.velocity.domain.Product;
import com.hvn.velocity.repository.ProductDao;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTests {

	/** {@link ProductService} instance under test */
	@InjectMocks
	private ProductService service;
	
	/**
	 * Mock objects declaration
	 */
	@Mock private ProductDao mockProductDao;
	
	/**
	 * Method to perform setup work for each test.
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Method to perform tear down work for each test.
	 */
	@After
	public void tearDown() throws Exception {
		Mockito.reset(mockProductDao);
	}

	/**
	 * Test {@link ProductService#getByCategoryId(Byte)}
	 */
	@Test
	public void getByCategoryId() {
		// arrange
		Byte categoryId = 1;
		List<Product> productList = Arrays.asList(new Product());
		Mockito.when(mockProductDao.findByCategoryId(categoryId)).thenReturn(productList);
		// exercise
		List<Product> expectedList = service.getByCategoryId(categoryId);
		// verify
		assertThat(expectedList).isEqualTo(productList);
	}

	/**
	 * Test {@link ProductService#getById(Integer)}
	 */
	@Test
	public void getById() {
		// arrange
		Integer productId = 1;
		Product product = new Product();
		Mockito.when(mockProductDao.findById(productId)).thenReturn(product);
		// exercise
		Product expectedProduct = service.getById(productId);
		// verify
		assertThat(expectedProduct).isEqualTo(product);
	}

}
