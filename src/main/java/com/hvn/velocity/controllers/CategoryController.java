package com.hvn.velocity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hvn.velocity.services.CategoryService;
import com.hvn.velocity.services.ProductService;

@Controller
public class CategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String category(@RequestParam("id") Byte id, ModelMap mm) {
		logger.info("Redirect to category " + id);
		mm.put("productList", productService.listByCategoryId(id));
		mm.put("categoryList", categoryService.listAll());
		mm.put("id", id);
		return "category";
	}

}
