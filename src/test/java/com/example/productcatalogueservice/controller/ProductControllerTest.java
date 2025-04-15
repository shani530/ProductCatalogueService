package com.example.productcatalogueservice.controller;

import com.example.productcatalogueservice.dtos.ProductDto;
import com.example.productcatalogueservice.models.Product;
import com.example.productcatalogueservice.service.ProductService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;



@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Autowired
    ProductController productController;

    @Test
    public void testGetAllProducts() throws Exception {
        // Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product1");
        product1.setDescription("Description1");
        product1.setPrice(100.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product2");
        product2.setDescription("Description2");
        product2.setPrice(200.0);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);


        when(productService.getAllProducts()).thenReturn(productList);

        // Act
        List<ProductDto> productDtoList = productController.getAllProducts();

        // Verify the service call
        assertNotNull(productDtoList);
        assertEquals(2, productDtoList.size());
        assertEquals("Product1", productDtoList.get(0).getName());
        assertEquals("Product2", productDtoList.get(1).getName());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void TestGetProductDetailsById_WithValidProductId_RunSuccessfully() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Product Name");
        product.setDescription("Product Description");
        product.setPrice(100.0);
        when(productService.getProductById(productId)).thenReturn(product);

        ProductDto productDto = productController.getProducts(productId);

        assertNotNull(productDto);
        assertEquals("Product Name", productDto.getName());
        assertEquals("Product Description", productDto.getDescription());
        assertEquals(100.0, productDto.getPrice());
    }





}

