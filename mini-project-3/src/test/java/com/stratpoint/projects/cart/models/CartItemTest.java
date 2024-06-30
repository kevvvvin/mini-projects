package com.stratpoint.projects.cart.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class CartItemTest {

    private CartItem cartItem;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field idField = Product.class.getDeclaredField("ID");
        idField.setAccessible(true);
        idField.set(null, 1);

        product1 = new Product("Test Product1", 10, "Test Product Description1", 10);
        product2 = new Product("Test Product2", 2, "Test Product Description2", 2);
        cartItem = new CartItem(product1, 5);
    }

    @Test
    public void testGetProductSuccess() {
        assertEquals(product1, cartItem.getProduct());
    }

    @Test
    public void testGetProductFail() {
        assertNotEquals(product2, cartItem.getProduct());
    }

    @Test
    public void testGetItemQuantitySuccess() {
        assertEquals(5, cartItem.getItemQuantity());
    }

    @Test
    public void testGetItemQuantityFail() {
        assertNotEquals(1, cartItem.getItemQuantity());
    }

    @Test
    public void testDecrementQuantity() {
        cartItem.decrementQuantity();
        assertEquals(4, cartItem.getItemQuantity());
    }

    @Test
    public void testIncrementQuantity() {
        cartItem.incrementQuantity();
        assertEquals(6, cartItem.getItemQuantity());
    }

}
