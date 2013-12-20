package com.hvn.velocity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hvn.velocity.services.CategoryService;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap mm) {
		mm.put("categoryList", categoryService.listAll());
		return "home";
	}
	
	@RequestMapping(value = "/confirmation", method = RequestMethod.GET)
	public String confirmation() {				
		return "confirmation";
	}

}
