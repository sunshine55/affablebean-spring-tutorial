package com.hvn.velocity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hvn.velocity.services.CustomerService;

@Controller
@RequestMapping("/admin")
public class AdminConsoleController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String loginConsole() {
		return "admin_login";
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String customerConsole(ModelMap mm) {
		mm.put("customerList", customerService.listAll());
		return "admin_customer";
	}
}
