package com.example.productcatalogueservice.dtos;

import com.example.productcatalogueservice.models.BaseModel;
import com.example.productcatalogueservice.models.Category;
import com.example.productcatalogueservice.models.State;
import com.example.productcatalogueservice.models.Type;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductDto  {
    private long id;
    private String name;
    private String imageUrl;

    private String description;

    private double price;
    private Category category;

    private Date createdAt;
    private Date lastUpdatedAt;
    private State state;
    private Type type;


}
