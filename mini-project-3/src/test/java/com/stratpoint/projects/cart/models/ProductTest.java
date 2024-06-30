package com.stratpoint.projects.cart.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() throws IllegalAccessException, NoSuchFieldException {
        // Reset static unique ID variable to 1
        Field idField = Product.class.getDeclaredField("ID");
        idField.setAccessible(true);
        idField.set(null, 1);

        product1 = new Product("Test Product", 10, "Description", 2);
        product2 = new Product("Test Product 2", 5, "Description 2", 5);
    }

    @Test
    public void testGetId() {
        assertEquals(1, product1.getId());
        assertEquals(2, product2.getId());
    }

    @Test
    public void testGetProductName() {
        assertEquals("Test Product", product1.getProductName());
        assertEquals("Test Product 2", product2.getProductName());
    }

    @Test
    public void testGetProductPrice() {
        assertEquals(10, product1.getProductPrice());
        assertEquals(5, product2.getProductPrice());
    }

    @Test
    public void testGetProductDescription() {
        assertEquals("Description", product1.getProductDescription());
        assertEquals("Description 2", product2.getProductDescription());
    }

    @Test
    public void testGetProductStock() {
        assertEquals(2, product1.getProductStock());
        assertEquals(5, product2.getProductStock());
    }

    @Test
    public void testReduceStockSuccess() {
        product1.reduceStock(2);
        product2.reduceStock(1);
        assertEquals(0, product1.getProductStock());
        assertEquals(4, product2.getProductStock());
    }

    @Test
    public void testReduceStockFail() {
        product1.reduceStock(3);
        product2.reduceStock(6);
        assertNotEquals(-1, product1.getProductStock());
        assertEquals(5, product2.getProductStock());
    }

}
