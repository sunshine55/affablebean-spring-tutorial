package com.hvn.velocity.service;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.hvn.velocity.domain.CustomerOrder;
import com.hvn.velocity.repository.OrderDao;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTests {

	/** {@link OrderService} instance under test */
	@InjectMocks
	private OrderService service;
	
	/**
	 * Mock objects declaration
	 */
	@Mock private OrderDao mockOrderDao;
	
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
		Mockito.reset(mockOrderDao);
	}

	/**
	 * Test {@link OrderService#save(Integer, double)}
	 */
	@Test
	public void save() {
		// given
		Integer customerId = 1;
		double total = 4.05;
		BigDecimal amount = new BigDecimal(total);
		Date dateProcessed = new Date();
		int refNum = new Random().nextInt(999999999);
		Mockito.when(mockOrderDao.save(customerId, amount, dateProcessed, refNum)).thenReturn(0);
		// when
		Integer expectedId = service.save(customerId, total);
		// then
		assertThat(expectedId).isEqualTo(0);
	}

	/**
	 * Test {@link OrderService#getAll()}
	 */
	@Test
	public void getAll() {
		// given
		List<CustomerOrder> orderList = Arrays.asList(new CustomerOrder());
		Mockito.when(mockOrderDao.findAll()).thenReturn(orderList);
		// when
		List<CustomerOrder> expectedList = service.getAll();
		// then
		assertThat(expectedList).isEqualTo(orderList);
		Mockito.verify(mockOrderDao).findAll();
	}

	/**
	 * Test {@link OrderService#getById(Integer)}
	 */
	@Test
	public void getById() {
		// given
		CustomerOrder order = new CustomerOrder();
		Integer orderId = 1;
		Mockito.when(mockOrderDao.findById(orderId)).thenReturn(order);
		// when
		CustomerOrder expectedCustomer = service.getById(orderId);
		// then
		assertThat(expectedCustomer).isEqualTo(order);
		Mockito.verify(mockOrderDao).findById(orderId);
	}

}
