package com.hvn.velocity.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.View;

import com.hvn.velocity.domain.Category;
import com.hvn.velocity.domain.Product;
import com.hvn.velocity.service.CategoryService;
import com.hvn.velocity.service.CustomerService;
import com.hvn.velocity.service.ProductService;
import com.hvn.velocity.service.OrderService;
import com.hvn.velocity.service.OrderedProductService;
import com.hvn.velocity.session.Cart;

@RunWith(MockitoJUnitRunner.class)
public class FrontStoreControllerTests {

	/** {@link FrontStoreController} instance under test */
	@InjectMocks
	private FrontStoreController controller;
	
	/**
	 * Mock objects declaration
	 */
	@Mock private Cart mockCart;
	@Mock private CategoryService mockCategoryService;
	@Mock private ProductService mockProductService;
	@Mock private CustomerService mockCustomerService;
	@Mock private OrderedProductService mockOrderedProductService;
	@Mock private OrderService mockOrderService;
	@Mock private View mockView;
	private MockMvc mockMvc;
	
	/**
	 * Method to perform setup work for each test.
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(mockView).build();
	}

	/**
	 * Method to perform tear down work for each test.
	 */
	@After
	public void tearDown() throws Exception {
		Mockito.reset(mockCategoryService);
		Mockito.reset(mockProductService);
		Mockito.reset(mockCustomerService);
		Mockito.reset(mockOrderedProductService);
		Mockito.reset(mockOrderService);
	}

	/**
	 * Test {@link FrontStoreController#home(ModelMap)}
	 */
	@Test
	public void showCategories() throws Exception {
		List<Category> categoryList = Arrays.asList(new Category());
		Mockito.when(mockCategoryService.getAll()).thenReturn(categoryList);
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("categoryList", categoryList))
				.andExpect(view().name("views/store/home"));
	}
	
	/**
	 * Test {@link FrontStoreController#category(Byte, ModelMap)}
	 */
	@Test
	public void showCategoryProducts() throws Exception {
		/** arrange */
		// category
		Byte categoryId = 3;
		String categoryName = "bakery";
		Set<Product> productSet = new HashSet<Product>();
		Category category = new Category(categoryName, productSet);
		category.setId(categoryId);
		List<Category> categoryList = Arrays.asList(category);
		// product
		List<Product> productList = Arrays.asList();
		productList.addAll(category.getProducts());
		// mockito
		Mockito.when(mockCategoryService.getAll()).thenReturn(categoryList);
		Mockito.when(mockProductService.getByCategoryId(categoryId)).thenReturn(productList);
		
		/** exercise & verify */
		mockMvc.perform(get("/category").param("id", categoryId.toString()))
				.andExpect(status().isOk())
				.andExpect(model().attribute("productList", productList))
				.andExpect(model().attribute("categoryList", categoryList))
				.andExpect(view().name("views/store/category"));
		Mockito.verify(mockProductService).getByCategoryId(categoryId);
	}

	/**
	 * Test {@link FrontStoreController#cart(ModelMap)}
	 */
	@Test
	public void showCart() throws Exception {
		Map<Product, Integer> itemMap = new HashMap<Product, Integer>();
		double subTotal = 0;
		Integer numOfItems = 0;
		Mockito.when(mockCart.getItems()).thenReturn(itemMap);
		Mockito.when(mockCart.calculateSubTotal()).thenReturn(subTotal);
		Mockito.when(mockCart.sumQuantity()).thenReturn(numOfItems);
		mockMvc.perform(get("/cart"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("itemMap", itemMap))
				.andExpect(model().attribute("subTotal", subTotal))
				.andExpect(model().attribute("numOfItems", numOfItems))
				.andExpect(view().name("views/store/cart"));
	}
	
	/**
	 * Test {@link FrontStoreController#addToCart(Integer, String)}
	 */
	@Test
	public void addToCart() throws Exception {
		Integer productId = 1;
		Product product = new Product();
		Mockito.when(mockProductService.getById(productId)).thenReturn(product);
		mockMvc.perform(get("/addToCart").param("id", productId.toString()))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
		Mockito.verify(mockProductService).getById(productId);
		Mockito.verify(mockCart).addItem(product);
		Mockito.verify(mockCart).sumQuantity();
	}
	
	/**
	 * Test {@link FrontStoreController#clearCart()}
	 */
	@Test
	public void clearCart() throws Exception {
		mockMvc.perform(get("/clearCart"))
				.andExpect(status().isOk())
				.andExpect(view().name("redirect:/cart"));
		Mockito.verify(mockCart).clear();
	}
	
	/**
	 * Test {@link FrontStoreController#updateCart(Integer, Integer)}
	 */
	@Test
	public void updateCart() throws Exception {
		Integer productId = 1;
		Integer quantity = 10;
		mockMvc.perform(post("/updateCart")
				.param("id", productId.toString())
				.param("quantity", quantity.toString()))
				.andExpect(status().isOk())
				.andExpect(view().name("redirect:/cart"));
		Mockito.verify(mockCart).updateItem(productId, quantity);
	}
	
}
