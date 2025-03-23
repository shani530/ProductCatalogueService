package com.example.productcatalogueservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String name;
    private String imageUrl;

    private String description;

    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private State state;
}

