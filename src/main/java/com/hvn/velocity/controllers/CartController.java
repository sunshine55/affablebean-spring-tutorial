package com.hvn.velocity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
		
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart() {
		logger.info("Redirect to cart view");				
		return "cart";
	}
		
}
