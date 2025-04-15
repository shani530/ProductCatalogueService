package com.example.productcatalogueservice.service;

import com.example.productcatalogueservice.dtos.ProductDto;
import com.example.productcatalogueservice.models.Product;
import com.example.productcatalogueservice.repository.ProductCatalogueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Product getProductById(Long id) {

        Optional<Product> optionalProduct= productCatalogueRepo.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productCatalogueRepo.deleteById(id);
    }

    @Override
    public void updateProduct(Long id, Product product) {
       Optional<Product> optionalProduct = productCatalogueRepo.findById(id);
         if(optionalProduct.isPresent()){
              Product product1 = optionalProduct.get();
              product1.setName(product.getName());
              product1.setPrice(product.getPrice());
              product1.setImageUrl(product.getImageUrl());
              productCatalogueRepo.save(product1);
         }
    }

    @Override
    public Product createProduct(Product product) {
        productCatalogueRepo.save(product);
        return product;
    }


}

