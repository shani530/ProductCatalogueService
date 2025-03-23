package com.example.productcatalogueservice.service;

import com.example.productcatalogueservice.dtos.ProductDto;
import com.example.productcatalogueservice.models.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    public List<Product> getAllProducts();
    public Product replaceProduct(Long id , Product product);


    Product getProductById(Long id);

    void deleteProduct(Long id);

    void updateProduct(Long id, Product product);


}
