package com.hvn.tutorial.affablebean.service;

import com.hvn.tutorial.affablebean.domain.Category;
import com.hvn.tutorial.affablebean.repository.CategoryDao;
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
public class CategoryServiceTests {
	
	/** {@link CategoryService} instance under test */
	@InjectMocks
	private CategoryService service;
	
	/**
	 * Mock objects declaration
	 */
	@Mock private CategoryDao mockCategoryDao;

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
		Mockito.reset(mockCategoryDao);
	}

	/**
	 * Test {@link CategoryService#getAll()}
	 */
	@Test
	public void getAll() {
		// given
		List<Category> categoryList = Arrays.asList(new Category());
		Mockito.when(mockCategoryDao.findAll()).thenReturn(categoryList);
		// when
		List<Category> expectedList = service.getAll();
		// then
		assertThat(expectedList).isEqualTo(categoryList);
		Mockito.verify(mockCategoryDao).findAll();
	}

}
