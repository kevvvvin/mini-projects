package com.stratpoint.projects.cart.services.impl;

import com.stratpoint.projects.cart.models.Product;
import com.stratpoint.projects.cart.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceImplTest {
    private ProductService productService;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field idField = Product.class.getDeclaredField("ID");
        idField.setAccessible(true);
        idField.set(null, 1);

        productService = new ProductServiceImpl();
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertEquals(5, products.size());
    }

    @Test
    public void testGetProductById() {
        Product product = productService.getProductById(1);
        assertEquals("Product 1", product.getProductName());
    }

}
