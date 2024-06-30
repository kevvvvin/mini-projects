package com.stratpoint.projects.cart.services.impl;

import com.stratpoint.projects.cart.models.Product;
import com.stratpoint.projects.cart.services.CartService;
import com.stratpoint.projects.cart.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceImplTest {
    private CartService cartService;
    private ProductService productService;
    private Product product;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field idField = Product.class.getDeclaredField("ID");
        idField.setAccessible(true);
        idField.set(null, 1);

        product = new Product("Test Product", 10, "Description", 3);
        productService = new ProductServiceImpl();
        cartService = new CartServiceImpl(productService);
    }

    @Test
    public void testAddToCart() {
        boolean added = cartService.addToCart(product);
        assertTrue(added);
        assertEquals(1, cartService.getCart().getCartItems().size());
        assertEquals(1, cartService.getCart().getTotalQuantity());
        assertEquals(10, cartService.getCart().getTotalPrice());
    }

    @Test
    public void testRemoveFromCart() {
        cartService.addToCart(product);
        boolean removed = cartService.removeFromCart(product);
        assertTrue(removed);
        assertEquals(0, cartService.getCart().getCartItems().size());
        assertEquals(0, cartService.getCart().getTotalQuantity());
        assertEquals(0, cartService.getCart().getTotalPrice());
    }

    @Test
    public void testGetCart() {
        cartService.addToCart(product);
        assertEquals(1, cartService.getCart().getCartItems().size());
        assertEquals(1, cartService.getCart().getTotalQuantity());
        assertEquals(10, cartService.getCart().getTotalPrice());
    }

    @Test
    public void testCheckOutSuccess() {
        cartService.addToCart(productService.getProductById(1));
        cartService.checkOut();
        assertTrue(cartService.getCart().isEmpty());
        assertEquals(0, cartService.getCart().getCartItems().size());
        assertEquals(0, cartService.getCart().getTotalQuantity());
        assertEquals(0, cartService.getCart().getTotalPrice());
    }
}
