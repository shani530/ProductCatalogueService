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

    public FakeStoreProductDto(long l, String s, String s1, double v, String image1, String category1) {
    }

    public FakeStoreProductDto() {

    }
}
