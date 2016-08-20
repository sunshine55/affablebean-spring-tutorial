package com.hvn.tutorial.affablebean.service;

import com.hvn.tutorial.affablebean.domain.CustomerOrder;
import com.hvn.tutorial.affablebean.persistence.CustomerDao;
import com.hvn.tutorial.affablebean.persistence.OrderDao;
import com.hvn.tutorial.affablebean.domain.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTests {

	/** {@link OrderService} instance under test */
	@InjectMocks
	private OrderService service;
	
	/**
	 * Mock objects declaration
	 */
	@Mock private OrderDao mockOrderDao;
	@Mock private CustomerDao mockCustomerDao;
	
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
		Customer customer = new Customer();
		Mockito.when(mockCustomerDao.findOne(customerId)).thenReturn(customer);

		double total = 4.05;
		CustomerOrder order = new CustomerOrder();
		order.setId(1);
		Mockito.when(mockOrderDao.save(Matchers.any(CustomerOrder.class))).thenReturn(order);
		// when
		CustomerOrder expectedOrder = service.save(customerId, total);
		// then
		assertThat(expectedOrder.getId()).isEqualTo(order.getId());
		Mockito.verify(mockCustomerDao).findOne(customerId);
		Mockito.verify(mockOrderDao).save(Matchers.any(CustomerOrder.class));
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

}
