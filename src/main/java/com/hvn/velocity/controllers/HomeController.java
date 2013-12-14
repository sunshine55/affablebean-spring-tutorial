package com.hvn.velocity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("Redirect to home view");				
		return "home";
	}
	
	@RequestMapping(value = "/confirmation", method = RequestMethod.GET)
	public String confirmation() {
		logger.info("Redirect to confirmation view");				
		return "confirmation";
	}

}
