package com.hvn.velocity.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CategoryDaoTests {

	/** {@link CategoryDao} instance under test */
	@InjectMocks
	private CategoryDao dao;
	
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
		final Query query = Mockito.mock(Query.class);
		final Session session = Mockito.mock(Session.class);
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
		Mockito.when(session.createQuery("from " + CategoryDao.class.getName())).thenReturn(query);
		// exercise
		dao.findAll();
		// verify
		Mockito.verify(sessionFactory.getCurrentSession()).createQuery("from Category");
		Mockito.verify(query).list();
		Mockito.verifyNoMoreInteractions(query);
	}

}
