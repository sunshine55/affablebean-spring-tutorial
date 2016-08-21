package com.hvn.tutorial.affablebean.persistence;

import com.hvn.tutorial.affablebean.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<CustomerOrder, Integer> {
}
