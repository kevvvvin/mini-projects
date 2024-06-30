package com.stratpoint.projects.cart.repository.impl;

import com.stratpoint.projects.cart.models.Product;
import com.stratpoint.projects.cart.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryImplTest {

    private ProductRepository productRepository;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() throws IllegalAccessException, NoSuchFieldException {
        // Reset static unique ID variable to 1
        Field idField = Product.class.getDeclaredField("ID");
        idField.setAccessible(true);
        idField.set(null, 1);

        productRepository = new ProductRepositoryImpl();
    }

    @AfterEach
    public void tearDown() {
        productRepository.getAllProducts().clear();
    }

    @Test
    public void testGetAllProducts() {
        assertEquals(5, productRepository.getAllProducts().size());
    }

    @Test
    public void testGetProductById() {
        assertEquals("Product 1", productRepository.getProductById(1).getProductName());
        assertEquals("Product 2", productRepository.getProductById(2).getProductName());
        assertEquals("Product 3", productRepository.getProductById(3).getProductName());
        assertEquals("Product 4", productRepository.getProductById(4).getProductName());
        assertEquals("Product 5", productRepository.getProductById(5).getProductName());
        assertNotNull(productRepository.getProductById(1));
        assertNull(productRepository.getProductById(10));
    }


}
