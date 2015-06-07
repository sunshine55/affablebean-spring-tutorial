package com.hvn.tutorial.affablebean.service;

import com.hvn.tutorial.affablebean.domain.Product;
import com.hvn.tutorial.affablebean.repository.ProductDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
		// given
		Byte categoryId = 1;
		List<Product> productList = Arrays.asList(new Product());
		Mockito.when(mockProductDao.findByCategoryId(categoryId)).thenReturn(productList);
		// when
		List<Product> expectedList = service.getByCategoryId(categoryId);
		// then
		assertThat(expectedList).isEqualTo(productList);
	}

	/**
	 * Test {@link ProductService#getById(Integer)}
	 */
	@Test
	public void getById() {
		// given
		Integer productId = 1;
		Product product = new Product();
		Mockito.when(mockProductDao.findOne(productId)).thenReturn(product);
		// when
		Product expectedProduct = service.getById(productId);
		// then
		assertThat(expectedProduct).isEqualTo(product);
	}

}
