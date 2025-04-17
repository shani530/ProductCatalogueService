package com.example.productcatalogueservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Data
@Entity
public class Product extends BaseModel implements Serializable {
    private String name;
    private String imageUrl;

    private String description;

    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private State state;


    public Product() {
        super();
        this.setCreatedAt(new java.util.Date());
        this.setState(State.ACTIVE);
        this.setLastUpdatedAt(new java.util.Date());
    }
    public Product(long id, String name,  String description, String imageUrl, double price) {
        super();
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.setCreatedAt(new java.util.Date());
        this.setState(State.ACTIVE);
        this.setLastUpdatedAt(new java.util.Date());
    }

    // Constructor 2: Matching (long id, String name, double price, String imageUrl)
    public Product(long id, String name, double price, String imageUrl) {
        super();
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.setCreatedAt(new java.util.Date());
        this.setState(State.ACTIVE);
        this.setLastUpdatedAt(new java.util.Date());
    }


}

