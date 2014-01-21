package com.hvn.velocity.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hvn.velocity.domain.Customer;
import com.hvn.velocity.domain.CustomerOrder;

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
	
	public List<CustomerOrder> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from CustomerOrder").list();
	}
	
}
