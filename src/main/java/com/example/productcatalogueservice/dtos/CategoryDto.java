package com.example.productcatalogueservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDto {
    private long id;
    private String name;
    private String description;
}
