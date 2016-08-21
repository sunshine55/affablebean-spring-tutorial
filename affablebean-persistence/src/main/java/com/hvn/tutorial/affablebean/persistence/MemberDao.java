package com.hvn.tutorial.affablebean.persistence;

import com.hvn.tutorial.affablebean.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDao extends JpaRepository<Member, Integer> {
	
	List<Member> findByUsername(String username);
	
}
