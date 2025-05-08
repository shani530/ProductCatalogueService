package com.example.productcatalogueservice.service;

import com.example.productcatalogueservice.dtos.ProductDto;
import com.example.productcatalogueservice.dtos.Role;
import com.example.productcatalogueservice.dtos.UserDto;
import com.example.productcatalogueservice.models.Product;
import com.example.productcatalogueservice.models.Type;
import com.example.productcatalogueservice.repository.ProductCatalogueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductCatalogueImpl implements ProductService{

    @Autowired
    ProductCatalogueRepo productCatalogueRepo;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    RestTemplate restTemplate;

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

    @Override
    public Product getProductBasedOnUserRole(Long userId, Long productId) {
        // call userAuthentication service to get user by id
        Product product = getProductById(productId);
if (product.getType() == Type.PRIVATE) {
    // Call userAuthentication service to check if the user is an admin
    String url = "http://userservice/user/{userId}";

    // Check if the user is an admin
    ResponseEntity<UserDto> response = restTemplate.getForEntity(url,
            UserDto.class, userId);
    UserDto userDto = response.getBody();
    if (userDto == null) {
        return null;
    }
    if (userDto.getRole().equals(Role.ADMIN)) {
        return product;
    } else {
        return null;
    }
}
        else return product;


    }


}

