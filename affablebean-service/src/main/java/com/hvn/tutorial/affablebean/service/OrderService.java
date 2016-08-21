package com.hvn.tutorial.affablebean.service;

import com.hvn.tutorial.affablebean.domain.CustomerOrder;
import com.hvn.tutorial.affablebean.persistence.CustomerDao;
import com.hvn.tutorial.affablebean.persistence.OrderDao;
import com.hvn.tutorial.affablebean.domain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class OrderService {

	@Resource
	private OrderDao orderDao;

	@Resource
	private CustomerDao customerDao;

	public CustomerOrder save(Integer customerId, double total) {
		Customer customer = customerDao.findOne(customerId);
		BigDecimal amount = new BigDecimal(total);
		Date dateProcessed = new Date();
		Random random = new Random();
		int refNum = random.nextInt(999999999);

		CustomerOrder order = new CustomerOrder(customer, amount, dateProcessed, refNum);

		return orderDao.save(order);
	}
	
	public List<CustomerOrder> getAll() {
		return orderDao.findAll();
	}
	
}
