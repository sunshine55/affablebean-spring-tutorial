package com.hvn.velocity.repositories;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hvn.velocity.entities.Customer;
import com.hvn.velocity.entities.CustomerOrder;

@Repository
public class OrderDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Integer save(Integer customerId, BigDecimal amount, Date dateProcessed, int refNum) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.get(Customer.class, customerId);
		CustomerOrder order = new CustomerOrder(customer, amount, dateProcessed, refNum);
		return (Integer) session.save(order);
	}
	
	public CustomerOrder findById(Integer id) {
		return (CustomerOrder) sessionFactory.getCurrentSession().get(CustomerOrder.class, id);
	}
	
}
