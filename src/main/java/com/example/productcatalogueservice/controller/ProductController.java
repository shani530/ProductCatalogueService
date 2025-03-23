package com.example.productcatalogueservice.controller;


import com.example.productcatalogueservice.dtos.ProductDto;
import com.example.productcatalogueservice.models.Product;
import com.example.productcatalogueservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import static com.example.productcatalogueservice.models.State.ACTIVE;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping("products")
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
    public ProductDto postProduct( @RequestBody ProductDto productDto){

        return productDto;
    }
    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        //ProductDto productDtoNew = new ProductDto();
/*        productDto.setId(id);
        productDto.setName("Apple 16");
        productDto.setPrice(3000);
        productDto.setImageUrl("slash/get/image");*/
        Product product = convertProductDTOToProduct(productDto);
        productService.replaceProduct(id , product);
        return productDto;
    }
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id){

        return "Deleted successfully";
    }
    @PatchMapping("/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDTO){
        productDTO.setPrice(2000);
        return productDTO;
    }
    public ProductDto convertProductToProductDTO(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(productDto.getId());
        productDto.setCategory(product.getCategory());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(productDto.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        return productDto;
    }
    public Product convertProductDTOToProduct(ProductDto productDto){
        Product product= new Product();
        product.setId(productDto.getId());
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        return product;
    }
}
