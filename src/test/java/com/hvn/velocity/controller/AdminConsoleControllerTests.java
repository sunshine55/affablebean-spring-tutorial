package com.hvn.velocity.controller;

import com.hvn.velocity.domain.Customer;
import com.hvn.velocity.domain.CustomerOrder;
import com.hvn.velocity.domain.Member;
import com.hvn.velocity.service.CustomerService;
import com.hvn.velocity.service.MemberService;
import com.hvn.velocity.service.OrderService;
import org.junit.After;
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

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminConsoleControllerTests {

	/** {@link AdminConsoleController} instance under test */
	@InjectMocks
	private AdminConsoleController controller;

	/**
	 * Mock objects declaration
	 */
	@Mock private CustomerService mockCustomerService;
	@Mock private MemberService mockMemberService;
	@Mock private OrderService mockOrderService;
	@Mock private View mockView;
	private MockMvc mockMvc;

	/**
	 * Method to perform setup work for each test.
	 */
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(mockView).build();
	}
	
	/**
	 * Method to perform tear down work for each test.
	 */
	@After
	public void teardown() throws Exception {
		Mockito.reset(mockCustomerService);
		Mockito.reset(mockMemberService);
		Mockito.reset(mockOrderService);
	}
	
	/**
	 * Test {@link AdminConsoleController#loginConsole(ModelMap)}
	 */
	@Test
	public void loginFail() throws Exception {
		mockMvc.perform(get("/admin/login").param("error", "true"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", "Login Failed!"))
				.andExpect(view().name("admin_console/login"));
	}

	@Test
	public void loginSuccess() throws Exception {
		mockMvc.perform(get("/admin/login").param("error", "false"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", false))
				.andExpect(view().name("admin_console/login"));
	}

	/**
	 * Test {@link AdminConsoleController#customerConsole(ModelMap)}
	 */
	@Test
	public void showCustomers() throws Exception {
		List<Customer> customerList = Arrays.asList(new Customer());
		Mockito.when(mockCustomerService.getAll()).thenReturn(customerList);
		mockMvc.perform(get("/admin"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("customerList", customerList))
				.andExpect(view().name("admin_console/customer"));
	}

	/**
	 * Test {@link AdminConsoleController#memberConsole(ModelMap)}
	 */
	@Test
	public void showMembers() throws Exception {
		List<Member> memberList = Arrays.asList(new Member());
		Mockito.when(mockMemberService.getAll()).thenReturn(memberList);
		mockMvc.perform(get("/admin/member"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("memberList", memberList))
				.andExpect(view().name("admin_console/member"));
	}
	
	/**
	 * Test {@link AdminConsoleController#orderConsole(ModelMap)}
	 */
	@Test
	public void showOrders() throws Exception {
		List<CustomerOrder> orderList = Arrays.asList(new CustomerOrder());
		Mockito.when(mockOrderService.getAll()).thenReturn(orderList);
		mockMvc.perform(get("/admin/order"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("orderList", orderList))
				.andExpect(view().name("admin_console/order"));
	}
	
}
