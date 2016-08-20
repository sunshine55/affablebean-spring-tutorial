package com.hvn.tutorial.affablebean.persistence;

import com.hvn.tutorial.affablebean.domain.Member;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MemberDao extends Repository<Member, Integer> {
	
	List<Member> findAll();
	
	List<Member> findByUsername(String username);
	
}
