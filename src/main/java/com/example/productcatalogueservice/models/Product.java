package com.example.productcatalogueservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {
    private String name;
    private String imageUrl;

    private String description;

    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private State state;
    private Type type;



}