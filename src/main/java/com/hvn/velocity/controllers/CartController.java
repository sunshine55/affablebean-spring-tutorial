package com.hvn.velocity.controllers;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hvn.velocity.entities.Cart;
import com.hvn.velocity.entities.Product;
import com.hvn.velocity.services.ProductService;

@Controller
public class CartController {
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private Cart cart;
	
	@Autowired
	private ProductService productService;
		
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart(ModelMap mm) {
		logger.info("Redirect to cart view");
		Map<Product, Integer> itemMap = cart.getProducts();
		double subTotal = 0;		
		if (itemMap.size() != 0) {			
			Set<Entry<Product, Integer>> entries = itemMap.entrySet();
	        for(Entry<Product, Integer> entry : entries) {
	        	subTotal += entry.getKey().getPrice().doubleValue() * entry.getValue();
	        }	        
		}
		mm.put("itemMap", itemMap);
		mm.put("subTotal", subTotal);
		return "cart";
	}
	
	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	public String addToCart(@RequestParam("id") Integer id, @RequestHeader("referer") String referer) {
		logger.info("Add to cart");
		Product product = productService.getById(id);
		cart.addProduct(product);
		return "redirect:"+referer;
	}
	
	@RequestMapping(value = "/clearCart", method = RequestMethod.GET)
	public String clearCart() {
		logger.info("Clear cart");
		cart.clear();
		return "redirect:/cart";
	}
	
	@RequestMapping(value = "/updateCart", method = RequestMethod.POST)
	public String updateCart(@ModelAttribute("id") Integer id, @ModelAttribute("quantity") Integer quantity) {
		logger.info("Update cart");
		Product product = productService.getById(id);
		cart.updateProduct(product, quantity);
		return "redirect:/cart";
	}
	
}
