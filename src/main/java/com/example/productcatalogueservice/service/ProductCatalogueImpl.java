package com.example.productcatalogueservice.service;

import com.example.productcatalogueservice.models.Product;
import com.example.productcatalogueservice.repository.ProductCatalogueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductCatalogueImpl implements ProductService{

    @Autowired
    ProductCatalogueRepo productCatalogueRepo;
    @Override
    public List<Product> getAllProducts() {
        return productCatalogueRepo.findAll();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
