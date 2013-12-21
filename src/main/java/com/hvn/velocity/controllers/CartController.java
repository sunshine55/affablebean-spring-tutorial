package com.hvn.velocity.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hvn.velocity.entities.Product;
import com.hvn.velocity.services.ProductService;
import com.hvn.velocity.session.Cart;

@Controller
public class CartController {

	@Autowired
	private Cart cart;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cart(ModelMap mm) {
		Map<Product, Integer> itemMap = cart.getProducts();
		double subTotal = cart.calculateSubTotal();
		mm.put("itemMap", itemMap);
		mm.put("subTotal", subTotal);
		return "cart";
	}

	@RequestMapping(value = "/addToCart", method = RequestMethod.GET)
	public String addToCart(@RequestParam("id") Integer id,
			@RequestHeader("referer") String referer) {
		Product product = productService.getById(id);
		cart.addProduct(product);
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/clearCart", method = RequestMethod.GET)
	public String clearCart() {
		cart.clear();
		return "redirect:/cart";
	}

	@RequestMapping(value = "/updateCart", method = RequestMethod.POST)
	public String updateCart(@RequestParam("id") Integer id,
			@RequestParam("quantity") Integer quantity) {
		cart.updateProduct(id, quantity);
		return "redirect:/cart";
	}

}
