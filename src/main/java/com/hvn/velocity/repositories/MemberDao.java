package com.hvn.velocity.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hvn.velocity.entities.Member;

@Repository
public class MemberDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Member findByUsername(String username) {
		Member member = (Member) sessionFactory.getCurrentSession()
				.createCriteria(Member.class)
				.add(Restrictions.eq("username", username))
				.uniqueResult(); // note: we can add many Restrictions, refer Hibernate querying
		return member;
	}
}
