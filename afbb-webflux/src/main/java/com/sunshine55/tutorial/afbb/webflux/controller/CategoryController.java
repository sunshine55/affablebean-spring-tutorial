package com.sunshine55.tutorial.afbb.webflux.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sunshine55.tutorial.afbb.webflux.dao.CategoryDAO;
import com.sunshine55.tutorial.afbb.webflux.entity.CategoryEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryDAO categoryDAO;

    @GetMapping("/getAll")
    public Flux<CategoryEntity> getAll() {
        return categoryDAO.findAll();
    }
}
