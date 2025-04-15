package com.example.productcatalogueservice.service;

import com.example.productcatalogueservice.dtos.FakeStoreProductDto;
import com.example.productcatalogueservice.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FakeStoreImplTest {

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FakeStoreImpl fakeStoreImpl;

 @BeforeEach
    void setUp() {

        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    public void testGetAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = {
                new FakeStoreProductDto(1L, "Product 1", "Description 1", 10.0, "image1", "category1"),
                new FakeStoreProductDto(2L, "Product 2", "Description 2", 20.0, "image2", "category2")
        };

        when(restTemplate.getForEntity(anyString(), eq(FakeStoreProductDto[].class)))
                .thenReturn(new ResponseEntity<>(fakeStoreProductDtos, HttpStatus.OK));

        List<Product> products = fakeStoreImpl.getAllProducts();

        assertEquals(2, products.size());
        assertEquals("Product 1", products.get(0).getName());
        assertEquals("Product 2", products.get(1).getName());
    }

    /*@Test
    public void testReplaceProduct() {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto(1L, "Product 1", "Description 1", 10.0, "image1", "category1");
        Product product = new Product(1L, "Product Name", "Product Description", null, 100.0);

        when(restTemplate.getForEntity(anyString(), eq(FakeStoreProductDto.class), anyLong()))
                .thenReturn(new ResponseEntity<>(fakeStoreProductDto, HttpStatus.OK));
        *//*when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.PUT),
                any(),
                eq(FakeStoreProductDto.class),
                anyLong()
        ))*//*
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.PUT),
                any(),
                eq(FakeStoreProductDto.class),
                anyLong()
        )).thenReturn(new ResponseEntity<>(fakeStoreProductDto, HttpStatus.OK));
        Product replacedProduct = fakeStoreImpl.replaceProduct(1L, product);

        assertEquals("Product Name", replacedProduct.getName());
        assertEquals("Product Description", replacedProduct.getDescription());
    }*/

    @Test
    public void testGetProductById() {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto(1L, "Product 1", "Description 1", 10.0, "image1", "category1");

        when(restTemplate.getForEntity(anyString(), eq(FakeStoreProductDto.class), anyLong()))
                .thenReturn(new ResponseEntity<>(fakeStoreProductDto, HttpStatus.OK));

        Product product = fakeStoreImpl.getProductById(1L);

        assertEquals("Product 1", product.getName());
        assertEquals("Description 1", product.getDescription());
    }
}