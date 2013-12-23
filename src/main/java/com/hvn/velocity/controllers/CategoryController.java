package com.hvn.velocity.controllers;

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
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap mm) {
		mm.put("categoryList", categoryService.listAll());
		return "home";
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String category(@RequestParam("id") Byte id, ModelMap mm) {
		mm.put("productList", productService.listByCategoryId(id));
		mm.put("categoryList", categoryService.listAll());
		mm.put("id", id);
		return "category";
	}

}
