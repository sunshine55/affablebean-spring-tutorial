package com.hvn.velocity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hvn.velocity.services.CustomerService;

@Controller
@RequestMapping("/admin")
public class AdminConsoleController {
	
	@Autowired
	private CustomerService customerService;
	
	/*
	 * Auth process
	 */
	@RequestMapping(value = "/login", method= RequestMethod.GET )
	public String loginConsole(@RequestParam(required = false) String message, ModelMap mm) {
		mm.put("message", message);
		return "admin_login";
	}

	@RequestMapping(value = "/login/failure")
	public String loginFailure() {
		String message = "Login Failure!";
		return "redirect:/admin/login?message=" + message;
	}
	
	@RequestMapping(value = "/logout/success")
	public String logoutSuccess() {
		String message = "Logout Success!";
		return "redirect:/admin/login?message=" + message;
	}
	
	/*
	 * Login succeeded, inside AdminConsole
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String customerConsole(ModelMap mm) {
		mm.put("customerList", customerService.listAll());
		return "admin_customer";
	}
	
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public String memberConsole() {
		return "admin_member";
	}
}
