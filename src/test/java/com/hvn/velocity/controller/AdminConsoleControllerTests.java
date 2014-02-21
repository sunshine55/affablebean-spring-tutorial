package com.hvn.velocity.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import com.hvn.velocity.service.CustomerService;
import com.hvn.velocity.service.MemberService;
import com.hvn.velocity.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class AdminConsoleControllerTests {

	/** {@link AdminConsoleController} instance under test */
	private AdminConsoleController controller;
	
	/**
	 * Mock objects declaration
	 */
	@Mock private CustomerService customerService;
	@Mock private MemberService memberService;
	@Mock private OrderService orderService;
	
	/**
	 * Instances passed to test
	 */
	private ModelMap mm;
	private boolean error;

	/**
     * Method to perform setup work for each test.
     */
	@Before
	public void setup() {
		controller = new AdminConsoleController();
		mm = new ModelMap();
	}

	/**
	 * Test the {@link AdminConsoleController#loginConsole(boolean, ModelMap)}
	 */
	@Test
	public void loginFail() {
		// Given
		error = true;
		// When
		String rs = controller.loginConsole(error, mm);
		// Then
		assertThat(rs).isEqualTo("views/admin/login");
		assertThat(mm).containsEntry("message", "Login Failed!");
	}

	@Test
	public void loginSuccess() {
		// Given
		error = false;
		// When
		String rs = controller.loginConsole(error, mm);
		// Then
		assertThat(rs).isEqualTo("views/admin/login");
		assertThat(mm).containsEntry("message", false);
	}

}
