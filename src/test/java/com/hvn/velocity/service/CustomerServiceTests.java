package com.hvn.velocity.service;

import com.hvn.velocity.domain.Customer;
import com.hvn.velocity.repository.CustomerDao;
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
public class CustomerServiceTests {

	/** {@link CustomerService} instance under test */
	@InjectMocks
	private CustomerService service;
	
	/**
	 * Mock objects declaration
	 */
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
		Mockito.reset(mockCustomerDao);
	}

	/**
	 * Test {@link CustomerService#save(Customer)}
	 */
	@Test
	public void save() {
		// given
		Customer customer = new Customer();
		customer.setId(1);
		Mockito.when(mockCustomerDao.save(customer)).thenReturn(customer);
		// when
		Integer expectedId = service.save(customer);
		// then
		assertThat(expectedId).isEqualTo(1);
		Mockito.verify(mockCustomerDao).save(customer);
	}

	/**
	 * Test {@link CustomerService#getAll()}
	 */
	@Test
	public void getAll() {
		// given
		List<Customer> customerList = Arrays.asList(new Customer());
		Mockito.when(mockCustomerDao.findAll()).thenReturn(customerList);
		// when
		List<Customer> expectedList = service.getAll();
		// then
		assertThat(expectedList).isEqualTo(customerList);
		Mockito.verify(mockCustomerDao).findAll();
	}

	/**
	 * Test {@link CustomerService#getById(Integer)}
	 */
	@Test
	public void getById() {
		// given
		Customer customer = new Customer();
		Integer customerId = 1;
		Mockito.when(mockCustomerDao.findOne(customerId)).thenReturn(customer);
		// when
		Customer expectedCustomer = service.getById(customerId);
		// then
		assertThat(expectedCustomer).isEqualTo(customer);
		Mockito.verify(mockCustomerDao).findOne(customerId);
	}

	/**
	 * Test {@link CustomerService#getByEmail(String)}
	 */
	@Test
	public void getByEmail() {
		// given
		Customer customer = new Customer();
		customer.setId(1);
		String email = "abc@co.uk";
		Mockito.when(mockCustomerDao.findByEmail(email)).thenReturn(customer);
		// when
		Customer expectedCustomer = service.getByEmail(email);
		// then
		assertThat(expectedCustomer.getId()).isEqualTo(customer.getId());
		Mockito.verify(mockCustomerDao).findByEmail(email);
	}

}
