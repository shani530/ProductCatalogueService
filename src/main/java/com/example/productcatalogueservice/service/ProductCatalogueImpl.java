package com.example.productcatalogueservice.service;

import com.example.productcatalogueservice.dtos.ProductDto;
import com.example.productcatalogueservice.models.Product;
import com.example.productcatalogueservice.repository.ProductCatalogueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductCatalogueImpl implements ProductService{

    @Autowired
    ProductCatalogueRepo productCatalogueRepo;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Product> getAllProducts() {
        return productCatalogueRepo.findAll();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    // implement get method using redis template

    public Product getProductById(Long id) {
        String key = "product:" + id;
        // fix the issue of getting null value in id , createdAt and lastUpdatedAt field in product
        // when using redis template

        Product product = (Product) redisTemplate.opsForValue().get(key);

        //product.setId(id);
        if (product == null) {
            Optional<Product> optionalProduct = productCatalogueRepo.findById(id);
            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
                redisTemplate.opsForValue().set(key, product);
            }
        }
        return product;
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
        // save in redis cache
        String key = "product:" + product.getId();
        redisTemplate.opsForValue().set(key, product);
        // save in database
        productCatalogueRepo.save(product);
        return product;
    }


}

