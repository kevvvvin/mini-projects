package com.stratpoint.projects.cart.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private Cart cart;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field idField = Product.class.getDeclaredField("ID");
        idField.setAccessible(true);
        idField.set(null, 1);

        cart = new Cart();
        product1 = new Product("Product1", 5.99, "Description1", 10);
        product2 = new Product("Product2", 10.99, "Description2", 0);
    }

    @Test
    public void testAddItemSuccess() {
        boolean added = cart.addItem(product1);
        assertTrue(added);
        assertEquals(1, cart.getCartItems().size());
        assertEquals(1, cart.getTotalQuantity());
        assertEquals(5.99, cart.getTotalPrice());
    }

    @Test
    public void testAddItemFail() {
        boolean added = cart.addItem(product2);
        assertFalse(added);
        assertEquals(0, cart.getCartItems().size());
        assertEquals(0, cart.getTotalQuantity());
        assertEquals(0, cart.getTotalPrice());
    }

    @Test
    public void testRemoveItemSuccess() {
        cart.addItem(product1);
        boolean removed = cart.removeItem(product1);
        assertTrue(removed);
        assertEquals(0, cart.getCartItems().size());
        assertEquals(0, cart.getTotalQuantity());
        assertEquals(0, cart.getTotalPrice());
    }

    @Test
    public void testRemoveItemFail() {
        cart.addItem(product1);
        boolean removed = cart.removeItem(product2);
        assertFalse(removed);
        assertEquals(1, cart.getCartItems().size());
        assertEquals(1, cart.getTotalQuantity());
        assertEquals(5.99, cart.getTotalPrice());
    }

    @Test
    public void testGetTotalQuantity() {
        cart.addItem(product1);
        cart.addItem(product1);
        assertEquals(2, cart.getTotalQuantity());
    }

    @Test
    public void testGetTotalPrice() {
        cart.addItem(product1);
        cart.addItem(product1);
        cart.addItem(product1);
        assertEquals(product1.getProductPrice()*3, cart.getTotalPrice());
    }

    @Test
    public void testClear() {
        cart.addItem(product1);
        cart.clear();
        assertEquals(0, cart.getCartItems().size());
        assertTrue(cart.getCartItems().isEmpty());
    }

}
