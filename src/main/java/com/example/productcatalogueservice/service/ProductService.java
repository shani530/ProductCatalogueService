package com.example.productcatalogueservice.service;

import com.example.productcatalogueservice.dtos.ProductDto;
import com.example.productcatalogueservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    public List<Product> getAllProducts();
    public Product replaceProduct(Long id , Product product);


    Product getProductById(Long id);
}
