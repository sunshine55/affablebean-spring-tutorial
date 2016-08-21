package com.hvn.tutorial.affablebean.persistence;

import com.hvn.tutorial.affablebean.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Byte> {
}
