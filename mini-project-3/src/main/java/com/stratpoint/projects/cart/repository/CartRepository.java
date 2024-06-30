package com.stratpoint.projects.cart.repository;

import com.stratpoint.projects.cart.models.Cart;
import com.stratpoint.projects.cart.models.Product;

/**
 * Repository interface for managing shopping cart data.
 */
public interface CartRepository {

    /**
     * Adds a product to the cart.
     *
     * @param product The product to be added to the cart.
     * @return true if the product was successfully added to the cart, false otherwise.
     */
    boolean addItem(Product product);

    /**
     * Removes a product from the cart.
     *
     * @param product The product to be removed from the cart.
     * @return true if the product was successfully removed from the cart, false otherwise.
     */
    boolean removeItem(Product product);

    /**
     * Retrieves the shopping cart.
     *
     * @return the shopping cart
     */
    Cart getCart();
}