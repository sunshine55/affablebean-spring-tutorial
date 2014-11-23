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

import com.hvn.velocity.domain.Customer;
import com.hvn.velocity.repository.CustomerDao;

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
		// arrange
		Customer customer = new Customer();
		Integer customerId = 1;
		Mockito.when(mockCustomerDao.save(customer)).thenReturn(customerId);
		// exercise
		Integer expectedId = service.save(customer);
		// verify
		assertThat(expectedId).isEqualTo(customerId);
		Mockito.verify(mockCustomerDao).save(customer);
	}

	/**
	 * Test {@link CustomerService#getAll()}
	 */
	@Test
	public void getAll() {
		// arrange
		List<Customer> customerList = Arrays.asList(new Customer());
		Mockito.when(mockCustomerDao.findAll()).thenReturn(customerList);
		// exercise
		List<Customer> expectedList = service.getAll();
		// verify
		assertThat(expectedList).isEqualTo(customerList);
		Mockito.verify(mockCustomerDao).findAll();
	}

	/**
	 * Test {@link CustomerService#getById(Integer)}
	 */
	@Test
	public void getById() {
		// arrange
		Customer customer = new Customer();
		Integer customerId = 1;
		Mockito.when(mockCustomerDao.findById(customerId)).thenReturn(customer);
		// exercise
		Customer expectedCustomer = service.getById(customerId);
		// verify
		assertThat(expectedCustomer).isEqualTo(customer);
		Mockito.verify(mockCustomerDao).findById(customerId);
	}

	/**
	 * Test {@link CustomerService#getByEmail(String)}
	 */
	@Test
	public void getByEmail() {
		// arrange
		Customer customer = new Customer();
		String email = "abc@co.uk";
		Mockito.when(mockCustomerDao.findByEmail(email)).thenReturn(customer);
		// exercise
		Customer expectedCustomer = service.getByEmail(email);
		// verify
		assertThat(expectedCustomer).isEqualTo(customer);
		Mockito.verify(mockCustomerDao).findByEmail(email);
	}

}
