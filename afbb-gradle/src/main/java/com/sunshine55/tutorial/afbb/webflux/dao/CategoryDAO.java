package com.sunshine55.tutorial.afbb.webflux.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.sunshine55.tutorial.afbb.webflux.entity.CategoryEntity;

@Repository
public interface CategoryDAO extends ReactiveMongoRepository<CategoryEntity, String> {
}
