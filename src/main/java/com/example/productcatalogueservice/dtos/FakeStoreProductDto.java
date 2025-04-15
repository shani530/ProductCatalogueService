package com.example.productcatalogueservice.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FakeStoreProductDto {
    Long id;
    String title;
    String description;
    Double price;
    String category;
    String image;

    public FakeStoreProductDto(Long id, String title, String description, Double price, String category, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public FakeStoreProductDto() {

    }
}
