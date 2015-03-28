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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.View;

import com.hvn.velocity.domain.Category;
import com.hvn.velocity.domain.Customer;
import com.hvn.velocity.domain.CustomerOrder;
import com.hvn.velocity.domain.Product;
import com.hvn.velocity.service.CategoryService;
import com.hvn.velocity.service.CustomerService;
import com.hvn.velocity.service.OrderService;
import com.hvn.velocity.service.OrderedProductService;
import com.hvn.velocity.service.ProductService;
import com.hvn.velocity.session.Cart;
import com.hvn.velocity.util.RegionHashMap;

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
		Mockito.reset(mockCart);
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
				.andExpect(view().name("front_store/home"));
	}
	
	/**
	 * Test {@link FrontStoreController#category(Byte, ModelMap)}
	 */
	@Test
	public void showCategoryProducts() throws Exception {
		/** given */
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
		
		/** when & then */
		mockMvc.perform(get("/category").param("id", categoryId.toString()))
				.andExpect(status().isOk())
				.andExpect(model().attribute("productList", productList))
				.andExpect(model().attribute("categoryList", categoryList))
				.andExpect(view().name("front_store/category"));
		Mockito.verify(mockProductService).getByCategoryId(categoryId);
	}

	/**
	 * Test {@link FrontStoreController#cart(ModelMap)}
	 */
	@Test
	public void showCart() throws Exception {
		// given
		Map<Product, Integer> itemMap = new HashMap<Product, Integer>();
		double subTotal = 0;
		Integer numOfItems = 0;
		Mockito.when(mockCart.getItems()).thenReturn(itemMap);
		Mockito.when(mockCart.calculateSubTotal()).thenReturn(subTotal);
		Mockito.when(mockCart.sumQuantity()).thenReturn(numOfItems);
		
		// when & then
		mockMvc.perform(get("/cart"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("itemMap", itemMap))
				.andExpect(model().attribute("subTotal", subTotal))
				.andExpect(model().attribute("numOfItems", numOfItems))
				.andExpect(view().name("front_store/cart"));
	}
	
	/**
	 * Test {@link FrontStoreController#addToCart(Integer, String)}
	 */
	@Test
	public void addToCart() throws Exception {
		// given
		Integer productId = 1;
		Product product = new Product();
		Mockito.when(mockProductService.getById(productId)).thenReturn(product);
		
		// when & then
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
		// given
		Integer productId = 1;
		Integer quantity = 10;
		
		// when & then
		mockMvc.perform(post("/updateCart")
				.param("id", productId.toString())
				.param("quantity", quantity.toString()))
				.andExpect(status().isOk())
				.andExpect(view().name("redirect:/cart"));
		Mockito.verify(mockCart).updateItem(productId, quantity);
	}
	
	/**
	 * Test {@link FrontStoreController#checkout(ModelMap)}
	 */
	@Test
	public void checkoutEmptyCart() throws Exception {
		// given
		double subTotal = 0;
		Mockito.when(mockCart.calculateSubTotal()).thenReturn(subTotal);
		
		// when & then
		mockMvc.perform(get("/checkout"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("subTotal", subTotal))
				.andExpect(view().name("front_store/checkout"));
	}
	
	@Test
	public void checkoutFullCart() throws Exception {
		// given
		double subTotal = 3.05;
		double total = subTotal + 3;
		Map<String, String> regions = new RegionHashMap().getCityRegion();
		Mockito.when(mockCart.calculateSubTotal()).thenReturn(subTotal);
		Mockito.when(mockCart.calculateTotal(subTotal)).thenReturn(total);
		
		// when & then
		mockMvc.perform(get("/checkout"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("subTotal", subTotal))
				.andExpect(model().attribute("total", total))
				.andExpect(model().attributeExists("customer"))
				.andExpect(model().attribute("regions", regions))
				.andExpect(view().name("front_store/checkout"));
		Mockito.verify(mockCart).calculateTotal(subTotal);
	}

	/**
	 * Test {@link FrontStoreController#purchaseGuest(Customer, ModelMap)}
	 */
	@Test
	public void purchaseGuest() throws Exception {
		/** given */
		// prepare data
		Integer customerId = 1;
		double subTotal = 1.15;
		double total = subTotal + 3;		
		Integer orderId = 1;
		CustomerOrder order = new CustomerOrder();
		Map<Product, Integer> itemMap = new HashMap<Product, Integer>();
		// mockito
		Mockito.when(mockCustomerService.save(Mockito.any(Customer.class))).thenReturn(customerId);
		Mockito.when(mockCart.calculateSubTotal()).thenReturn(subTotal);
		Mockito.when(mockCart.calculateTotal(subTotal)).thenReturn(total);
		Mockito.when(mockOrderService.save(customerId, total)).thenReturn(orderId);
		Mockito.when(mockOrderService.getById(orderId)).thenReturn(order);
		Mockito.when(mockCart.getItems()).thenReturn(itemMap);
		
		/** when & then */
		mockMvc.perform(post("/purchaseGuest").sessionAttr("customer", new Customer()))
				.andExpect(status().isOk())
				.andExpect(model().attribute("subTotal", subTotal))
				.andExpect(model().attribute("total", total))
				.andExpect(model().attribute("order", order))
				.andExpect(model().attributeExists("customer"))
				.andExpect(model().attributeExists("itemMap"))
				.andExpect(view().name("front_store/confirmation"));
		Mockito.verify(mockCustomerService).save(Mockito.any(Customer.class));
		Mockito.verify(mockCart).calculateSubTotal();
		Mockito.verify(mockCart).calculateTotal(subTotal);
		Mockito.verify(mockOrderService).save(customerId, total);
		Mockito.verify(mockOrderService).getById(orderId);
		Mockito.verify(mockCart).getItems();
		Mockito.verify(mockOrderedProductService).save(order, itemMap);
		Mockito.verify(mockCart).clear();
	}
	
	/**
	 * Test {@link FrontStoreController#purchaseMember(String, ModelMap)}
	 */
	@Test
	public void purchaseMember() throws Exception {
		/** given */
		// prepare data
		String email = "abc@co.uk";
		Customer customer = new Customer();
		customer.setId(1);
		double subTotal = 1.15;
		double total = subTotal + 3;		
		Integer orderId = 1;
		CustomerOrder order = new CustomerOrder();
		Map<Product, Integer> itemMap = new HashMap<Product, Integer>();
		// mockito
		Mockito.when(mockCustomerService.getByEmail(email)).thenReturn(customer);
		Mockito.when(mockCart.calculateSubTotal()).thenReturn(subTotal);
		Mockito.when(mockCart.calculateTotal(subTotal)).thenReturn(total);
		Mockito.when(mockOrderService.save(customer.getId(), total)).thenReturn(orderId);
		Mockito.when(mockOrderService.getById(orderId)).thenReturn(order);
		Mockito.when(mockCart.getItems()).thenReturn(itemMap);
		
		/** when & then */
		mockMvc.perform(post("/purchaseMember").param("email", email))
				.andExpect(status().isOk())
				.andExpect(model().attribute("subTotal", subTotal))
				.andExpect(model().attribute("total", total))
				.andExpect(model().attribute("order", order))
				.andExpect(model().attribute("customer", customer))
				.andExpect(model().attributeExists("itemMap"))
				.andExpect(view().name("front_store/confirmation"));
		Mockito.verify(mockCustomerService).getByEmail(email);
		Mockito.verify(mockCart).calculateSubTotal();
		Mockito.verify(mockCart).calculateTotal(subTotal);
		Mockito.verify(mockOrderService).save(customer.getId(), total);
		Mockito.verify(mockOrderService).getById(orderId);
		Mockito.verify(mockCart).getItems();
		Mockito.verify(mockOrderedProductService).save(order, itemMap);
		Mockito.verify(mockCart).clear();
	}
	
	/**
	 * Test {@link FrontStoreController#validateMember(String)}
	 */
	@Test
	public void validateMemberTrue() throws Exception {
		// given
		String email = "abc@co.uk";
		Mockito.when(mockCustomerService.getByEmail(email)).thenAnswer(
				new Answer<Customer>() {
					public Customer answer(InvocationOnMock invocation) {
						return new Customer();
					}
				});
		
		// when & then
		mockMvc.perform(post("/validateMember").param("email", email))				
				.andExpect(status().isOk())
				.andExpect(content().string("true"))
				.andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
		Mockito.verify(mockCustomerService).getByEmail(email);
	}
	
	@Test
	public void validateMemberFalse() throws Exception {
		// given
		String email = "abc@co.uk";
		Mockito.when(mockCustomerService.getByEmail(email)).thenReturn(null);
		
		// when & then
		mockMvc.perform(post("/validateMember").param("email", email))
				.andExpect(status().isOk())
				.andExpect(content().string("false"))
				.andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
		Mockito.verify(mockCustomerService).getByEmail(email);
	}
	
	/**
	 * Test {@link FrontStoreController#getCartSize()}
	 */
	@Test
	public void getCartSize() throws Exception {
		// given
		Integer quantity = 1;
		Mockito.when(mockCart.sumQuantity()).thenReturn(quantity);
		
		// when & then
		mockMvc.perform(get("/getCartSize"))				
				.andExpect(status().isOk())
				.andExpect(content().string(quantity.toString()))
				.andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
		Mockito.verify(mockCart).sumQuantity();
	}
	
}
