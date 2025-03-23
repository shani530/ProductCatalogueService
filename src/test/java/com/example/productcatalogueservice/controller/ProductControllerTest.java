package com.example.productcatalogueservice.controller;

import com.example.productcatalogueservice.dtos.ProductDto;
import com.example.productcatalogueservice.models.Product;
import com.example.productcatalogueservice.service.ProductService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProductService productService;

    @Test
    public void testGetAllProducts() throws Exception {
        // Setup mock data
        Product product1 = new Product(1L, "Product1", "Description1", 100.0);
        Product product2 = new Product(2L, "Product2", "Description2", 200.0);
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        // Mock the service call
        when(productService.getAllProducts()).thenReturn(productList);

        // Perform the GET request and assert the results
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"id\":1,\"name\":\"Product1\",\"description\":\"Description1\",\"price\":100.0},{\"id\":2,\"name\":\"Product2\",\"description\":\"Description2\",\"price\":200.0}]"));

        // Verify the service call
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() throws Exception {
        Long productId = 1L;
        Product product = new Product(productId, "Product Name", "Product Description", 100.0);
        when(productService.getProductById(productId)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", productId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":1,\"name\":\"Product Name\",\"description\":\"Product Description\",\"price\":100.0}"));

        verify(productService, times(1)).getProductById(productId);
    }




}

