package com.sunshine55.tutorial.afbb.webflux.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sunshine55.tutorial.afbb.webflux.dto.ItemDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "category")
@Getter @Setter
@EqualsAndHashCode
public class CategoryEntity {
    @Id private String id;

    private String name, description;

    private List<ItemDTO> items;
}
