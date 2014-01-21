package com.hvn.velocity.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hvn.velocity.domain.Customer;

@Repository
public class CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Integer save(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		return (Integer) session.save(customer);
	}
	
	public List<Customer> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Customer").list();
	}

	public Customer findById(Integer id) {
		return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
	}

	public Customer findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.createCriteria(Customer.class)
				.add(Restrictions.eq("email", email))
				.setMaxResults(1)
				.uniqueResult();
		return customer;
	}

}
