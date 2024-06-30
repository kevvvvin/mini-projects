package com.stratpoint.projects.cart.repository.impl;

import com.stratpoint.projects.cart.models.Cart;
import com.stratpoint.projects.cart.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class CartRepositoryImplTest {

    private CartRepositoryImpl cartRepository;
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
        cartRepository = new CartRepositoryImpl();
    }

    @AfterEach
    public void tearDown() {
        cartRepository.getCart().clear();
    }

    @Test
    public void testAddItemSuccess() {
        boolean added = cartRepository.addItem(product1);
        assertTrue(added);
        assertEquals(1, cartRepository.getCart().getCartItems().size());
        assertEquals(1, cartRepository.getCart().getTotalQuantity());
        assertEquals(10, cartRepository.getCart().getTotalPrice());
    }

    @Test
    public void testAddItemFail() {
        boolean added = cartRepository.addItem(null);
        assertFalse(added);
        assertEquals(0, cartRepository.getCart().getCartItems().size());
        assertEquals(0, cartRepository.getCart().getTotalQuantity());
        assertEquals(0, cartRepository.getCart().getTotalPrice());
    }

    @Test
    public void testRemoveItemSuccess() {
        cartRepository.addItem(product1);
        boolean removed = cartRepository.removeItem(product1);
        assertTrue(removed);
        assertEquals(0, cartRepository.getCart().getCartItems().size());
        assertEquals(0, cartRepository.getCart().getTotalQuantity());
        assertEquals(0, cartRepository.getCart().getTotalPrice());
    }

    @Test
    public void testRemoveItemFail() {
        cartRepository.addItem(product1);
        boolean removed = cartRepository.removeItem(product2);
        assertFalse(removed);
        assertNotEquals(0, cartRepository.getCart().getCartItems().size());
        assertNotEquals(0, cartRepository.getCart().getTotalQuantity());
        assertNotEquals(0, cartRepository.getCart().getTotalPrice());
    }

    @Test
    public void testGetCart() {
        Cart cart = cartRepository.getCart();
        cart.addItem(product1);
        assertNotNull(cart);
        assertEquals(1, cart.getCartItems().size());
        assertEquals(1, cart.getTotalQuantity());
        assertEquals(10, cart.getTotalPrice());
    }


}
