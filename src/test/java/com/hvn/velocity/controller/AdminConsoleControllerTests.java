package com.hvn.velocity.controller;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.View;

import com.hvn.velocity.domain.Customer;
import com.hvn.velocity.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
public class AdminConsoleControllerTests {

	/** {@link AdminConsoleController} instance under test */
	@InjectMocks
	private AdminConsoleController controller;

	/**
	 * Mock objects declaration
	 */
	@Mock private CustomerService mockCustomerService;
	@Mock private View mockView;
	private MockMvc mockMvc;

	/**
	 * Method to perform setup work for each test.
	 */
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(mockCustomerService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(mockView).build();
	}

	/**
	 * Test {@link AdminConsoleController#customerConsole(ModelMap)}
	 */
	@Test
	public void showCustomers() throws Exception {
		// initialize
		List<Customer> customerList = Arrays.asList(new Customer());
		// setup expectation
		Mockito.when(mockCustomerService.getAll()).thenReturn(customerList);
		// exercise & verify
		mockMvc.perform(get("/admin"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("customerList", customerList))
				.andExpect(view().name("views/admin/customer"));
	}

}
