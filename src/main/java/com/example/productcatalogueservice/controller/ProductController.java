package com.example.productcatalogueservice.controller;


import com.example.productcatalogueservice.dtos.ProductDto;
import com.example.productcatalogueservice.models.Product;
import com.example.productcatalogueservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping("/products")
      public List<ProductDto> getAllProducts(){
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = productService.getAllProducts();
        for (Product product :  productList){
            productDtoList.add(convertProductToProductDTO(product));
        }
        return productDtoList;
    }
    @GetMapping("/products/{id}")
    public ProductDto getProducts(@PathVariable Long id){
        if(id < 0) {
            throw new IllegalArgumentException("Please pass productId greater than 0");
        }
        Product product = productService.getProductById(id);
        if(product == null) return null;
        return convertProductToProductDTO(product);

    }
    @PostMapping("/products")
    public ProductDto postProduct( @RequestBody ProductDto productDto) throws Exception {
        Product product = convertProductDTOToProduct(productDto);
        Product pdtResponse = productService.createProduct(product);
        return convertProductToProductDTO(pdtResponse);
    }
    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        Product product = convertProductDTOToProduct(productDto);
        productService.replaceProduct(id , product);
        return productDto;
    }
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
    @PatchMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody Product product){
        productService.updateProduct(id, product);
        return convertProductToProductDTO(product);
    }
    public ProductDto convertProductToProductDTO(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setCategory(product.getCategory());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        return productDto;
    }
    public Product convertProductDTOToProduct(ProductDto productDto){
        Product product= new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        return product;
    }
}
