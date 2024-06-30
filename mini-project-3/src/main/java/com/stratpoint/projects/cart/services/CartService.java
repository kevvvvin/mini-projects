package com.stratpoint.projects.cart.services;

import com.stratpoint.projects.cart.models.Cart;
import com.stratpoint.projects.cart.models.Product;

/**
 * Represents a service for managing a shopping cart.
 */
public interface CartService {

    /**
     * Adds a product to the cart.
     *
     * @param product the product to add
     * @return true if the product was added successfully, false otherwise
     */
    boolean addToCart(Product product);

    /**
     * Removes a product from the cart.
     *
     * @param product the product to remove
     * @return true if the product was removed successfully, false otherwise
     */
    boolean removeFromCart(Product product);

    /**
     * Retrieves the shopping cart.
     *
     * @return the shopping cart
     */
    Cart getCart();

    /**
     * Checks out the shopping cart.
     */
    void checkOut();
}
