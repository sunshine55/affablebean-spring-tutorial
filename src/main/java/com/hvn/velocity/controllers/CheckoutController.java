package com.hvn.velocity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CheckoutController {
	
	private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);
	
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout() {
		logger.info("Redirect to checkout view");				
		return "checkout";
	}

}
