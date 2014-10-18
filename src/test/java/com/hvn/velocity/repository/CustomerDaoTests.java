package com.hvn.velocity.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
public class CustomerDaoTests {

	/** {@link CustomerDao} instance under test */
	@InjectMocks
	private CustomerDao dao;
	
	/**
	 * Mock objects declaration
	 */
	@Mock private SessionFactory sessionFactory;
	
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
		Mockito.reset(sessionFactory);
	}

	@Test
	public void testFindAll() {
		// arrange
		List<Customer> customerList = Arrays.asList(new Customer());
		Mockito.when(sessionFactory.getCurrentSession().createQuery("from Customer").list()).thenReturn(customerList);
		// exercise
		List<Customer> expectedList = dao.findAll();
		// verify
		assertThat(expectedList).isEqualTo(customerList);
		Mockito.verify(sessionFactory).getCurrentSession().createQuery("from Customer").list();
	}

	@Test
	public void testFindById() {
		// arrange
		Integer id = 1;
		Customer customer = new Customer();
		Mockito.when(sessionFactory.getCurrentSession().get(Customer.class, id)).thenReturn(customer);
		// exercise
		Customer expectedCustomer = dao.findById(id);
		// verify
		assertThat(expectedCustomer).isEqualTo(customer);
		Mockito.verify(sessionFactory).getCurrentSession().get(Customer.class, id);
	}

	@Test
	public void testFindByEmail() {
		// arrange
		String email = "tester@local.dev";
		Customer customer = new Customer();
		Mockito.when(sessionFactory.getCurrentSession()
				.createCriteria(Customer.class)
				.add(Restrictions.eq("email", email))
				.setMaxResults(1)
				.uniqueResult()).thenReturn(customer);
		// exercise
		Customer expectedCustomer = dao.findByEmail(email);
		// verify
		assertThat(expectedCustomer).isEqualTo(customer);
		Mockito.verify(sessionFactory).getCurrentSession()
				.createCriteria(Customer.class)
				.add(Restrictions.eq("email", email)).setMaxResults(1)
				.uniqueResult();
	}

	@Test
	public void testSave() {
		// arrange
		Customer customer = new Customer();
		Integer id = 10;
		Mockito.when(sessionFactory.getCurrentSession().save(customer)).thenReturn(id);
		// exercise
		Integer expectedId = dao.save(customer);
		// verify
		assertThat(expectedId).isEqualTo(id);
		Mockito.verify(sessionFactory).getCurrentSession().save(customer);
	}

}
