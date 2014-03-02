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
		/*// arrange
		Integer customerId = 1;
		double total = 4.05;
		BigDecimal amount = new BigDecimal(total);
		Date dateProcessed = new Date();
		Mockito.when(mockOrderDao.save(customerId, amount, dateProcessed, (int) Mockito.anyInt())).thenReturn(0);
		// exercise
		Integer expectedId = service.save(customerId, total);
		// verify
		assertThat(expectedId).isEqualTo(0);*/
	}

	/**
	 * Test {@link OrderService#getAll()}
	 */
	@Test
	public void getAll() {
		// arrange
		List<CustomerOrder> orderList = Arrays.asList(new CustomerOrder());
		Mockito.when(mockOrderDao.findAll()).thenReturn(orderList);
		// exercise
		List<CustomerOrder> expectedList = service.getAll();
		// verify
		assertThat(expectedList).isEqualTo(orderList);
		Mockito.verify(mockOrderDao).findAll();
	}

	/**
	 * Test {@link OrderService#getById(Integer)}
	 */
	@Test
	public void getById() {
		// arrange
		CustomerOrder order = new CustomerOrder();
		Integer orderId = 1;
		Mockito.when(mockOrderDao.findById(orderId)).thenReturn(order);
		// exercise
		CustomerOrder expectedCustomer = service.getById(orderId);
		// verify
		assertThat(expectedCustomer).isEqualTo(order);
		Mockito.verify(mockOrderDao).findById(orderId);
	}

}
