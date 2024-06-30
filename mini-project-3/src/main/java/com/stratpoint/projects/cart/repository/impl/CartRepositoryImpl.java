package com.stratpoint.projects.cart.repository.impl;

import com.stratpoint.projects.cart.models.Cart;
import com.stratpoint.projects.cart.models.Product;
import com.stratpoint.projects.cart.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements the CartRepository interface and provides an implementation for the methods defined in it.
 * It uses a Cart object to store the cart details.
 */
public class CartRepositoryImpl implements CartRepository {

    /**
     * Logger instance for logging messages within the CartRepositoryImpl class.
     */
    private final Logger logger = LoggerFactory.getLogger(CartRepositoryImpl.class);

    /**
     * The cart object that stores the cart details.
     */
    private final Cart cart;

    /**
     * Constructs a new instance of CartRepositoryImpl.
     * Initializes the cart object with a new instance of Cart.
     */
    public CartRepositoryImpl() {
        this.cart = new Cart();
    }

    /**
     * Adds a product to the cart.
     *
     * @param product The product to be added to the cart.
     * @return true if the product was successfully added to the cart, false otherwise.
     */
    @Override
    public boolean addItem(Product product) {
        if (product == null) {
            logger.error("Cannot add null product to cart");
            return false;
        }
        return cart.addItem(product);
    }

    /**
     * Removes a product from the cart.
     *
     * @param product The product to be removed from the cart.
     * @return true if the product was successfully removed from the cart, false otherwise.
     */
    @Override
    public boolean removeItem(Product product) {
        if (product == null) {
            logger.error("Cannot remove null product from cart");
            return false;
        }

        return cart.removeItem(product);
    }

    /**
     * Returns the cart object.
     *
     * @return The cart object.
     */
    @Override
    public Cart getCart() {
        return cart;
    }
}
