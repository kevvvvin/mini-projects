package com.stratpoint.projects.cart.models;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents a Cart object.
 */
public class Cart{

    /**
     * Logger instance for logging messages within the Cart class.
     */
    private final Logger logger = LoggerFactory.getLogger(Cart.class);

    /**
     * List containing the items in the cart.
     */
    private final List<CartItem> cartItems;

    /**
     * Constructor for creating a new Cart object.
     */
    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    /**
     * This method returns the list of CartItems in the Cart.
     * It provides access to the contents of the cart.
     *
     * @return List<CartItem> The list of CartItems in the Cart.
     */
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * Adds a product to the cart.
     *
     * @param product The product to be added to the cart.
     * @return true if the product was successfully added to the cart, false otherwise.
     */
    public boolean addItem(Product product) {
        if (product.getProductStock() < 1) {
            logger.warn("Failed to add product: {} is sold out", product.getProductName());
            return false;
        }

        CartItem existingItem = getCartItem(product.getId());

        if (existingItem != null) {
            if (existingItem.getProduct().getProductStock() - existingItem.getItemQuantity() > 0) {
                existingItem.incrementQuantity();
            }
            else {
                logger.warn("Cannot add more than {}'s stock quantity to cart", product.getProductName());
                return false;
            }
        }
        else {
            cartItems.add(new CartItem(product, 1));
        }

        logger.info("Successfully added {} to cart", product.getProductName());
        return true;
    }

    /**
     * Removes a product from the cart.
     *
     * @param product The product to be removed from the cart.
     * @return true if the product was successfully removed from the cart, false otherwise.
     */
    public boolean removeItem(Product product) {
        CartItem existingItem = getCartItem(product.getId());
        if (existingItem != null) {
            if (existingItem.getItemQuantity() > 1) {
                existingItem.decrementQuantity();
                logger.info("Successfully decremented item quantity of {}", product.getProductName());
            }
            else {
                cartItems.remove(existingItem);
                logger.info("Removed product {} from the cart", product.getProductName());
            }
            return true;
        }
        logger.warn("Could not remove {} from cart", product.getProductName());
        return false;
    }

    /**
     * Returns the CartItem with the specified productId, or null if no matching item is found.
     *
     * @param productId the ID of the product to search for
     * @return the CartItem with the specified productId, or null if no matching item is found
     */
    private CartItem getCartItem(int productId) {
        for (CartItem item: cartItems) {
            if (item.getProduct().getId() == productId) {
                logger.info("Found product {} in cart", item.getProduct().getProductName());
                return item;
            }
        }
        logger.warn("Could not find product with id {} in cart", productId);
        return null;
    }

    /**
     * This method calculates the total quantity of items in the cart.
     * It does so by iterating over each item in the cart, calling the getItemQuantity method on each item,
     * and summing up the results.
     *
     * @return the total quantity of items in the cart
     */
    public int getTotalQuantity() {
        return cartItems.stream()
                .mapToInt(CartItem:: getItemQuantity)
                .sum();
    }

    /**
     * Calculates the total price of all items in the cart.
     *
     * @return the total price of all items in the cart
     */
    public double getTotalPrice() {
        return cartItems.stream()
                .mapToDouble((item) -> item.getProduct().getProductPrice() * item.getItemQuantity())
                .sum();
    }

    /**
     * Checks if the cart is empty.
     *
     * @return true if the cart is empty, false otherwise
     */
    public boolean isEmpty() {
        logger.info("Checking if cart is empty");
        return cartItems.isEmpty();
    }

    /**
     * Clears all items from the cart.
     */
    public void clear() {
        cartItems.clear();
        logger.info("Successfully cleared the cart");
    }
}
