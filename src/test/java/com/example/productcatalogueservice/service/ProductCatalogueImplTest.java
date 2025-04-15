package com.example.productcatalogueservice.service;

import com.example.productcatalogueservice.models.Product;
import com.example.productcatalogueservice.repository.ProductCatalogueRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductCatalogueImplTest {

    @Mock
    private ProductCatalogueRepo productCatalogueRepo;

    @InjectMocks
    private ProductCatalogueImpl productCatalogueImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        // Arrange
        Product product1 = new Product(1L, "Product1",  100.0, "url1" );
        Product product2 = new Product(2L, "Product2",   200.0, "url2");
        when(productCatalogueRepo.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Act
        List<Product> products = productCatalogueImpl.getAllProducts();

        // Assert
        assertNotNull(products);
        assertEquals(2, products.size());
        verify(productCatalogueRepo, times(1)).findAll();
    }

    @Test
    void getProductById() {
        // Arrange
        Product product = new Product(1L, "Product1",  100.0, "url1");
        when(productCatalogueRepo.findById(1L)).thenReturn(Optional.of(product));

        // Act
        Product result = productCatalogueImpl.getProductById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Product1", result.getName());
        verify(productCatalogueRepo, times(1)).findById(1L);
    }

    @Test
    void deleteProduct() {
        // Act
        productCatalogueImpl.deleteProduct(1L);

        // Assert
        verify(productCatalogueRepo, times(1)).deleteById(1L);
    }

    @Test
    void updateProduct() {
        // Arrange
        Product existingProduct = new Product(1L, "OldName", 100.0, "oldUrl");
        Product updatedProduct = new Product(1L, "NewName", 150.0, "newUrl");
        when(productCatalogueRepo.findById(1L)).thenReturn(Optional.of(existingProduct));

        // Act
        productCatalogueImpl.updateProduct(1L, updatedProduct);

        // Assert
        verify(productCatalogueRepo, times(1)).findById(1L);
        verify(productCatalogueRepo, times(1)).save(existingProduct);
        assertEquals("NewName", existingProduct.getName());
        assertEquals(150.0, existingProduct.getPrice());
        assertEquals("newUrl", existingProduct.getImageUrl());
    }

    @Test
    void replaceProduct() {
        // Since the `replaceProduct` method is not implemented, you can test for `null` or add a placeholder test.
        Product result = productCatalogueImpl.replaceProduct(1L, new Product());
        assertNull(result);
    }
}