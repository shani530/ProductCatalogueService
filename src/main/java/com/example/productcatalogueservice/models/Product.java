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


    public Product() {

    }
    public Product(long id, String name,  String description, String imageUrl, double price) {
        super();
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    // Constructor 2: Matching (long id, String name, double price, String imageUrl)
    public Product(long id, String name, double price, String imageUrl) {
        super();
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }


}

