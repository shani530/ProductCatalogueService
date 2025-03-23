package com.example.productcatalogueservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
public class Product extends BaseModel{
    private String name;
    private String imageUrl;

    private String description;

    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private State state;


    public Product(long l, String product1, String description1, double v) {
        super();
    }

    public Product() {

    }

    public Product(long l, String s, String s1, double v, String image1, Object o) {
        super();
    }
}

