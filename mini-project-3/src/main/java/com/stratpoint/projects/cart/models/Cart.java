package com.stratpoint.projects.cart.models;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cart{

    private final Logger logger = LoggerFactory.getLogger(Cart.class);
    private List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public boolean addItem(Product product) {
        if (product.getProductStock() <= 0) {
            logger.warn("Cannot add a sold out product to cart");
            return false;
        }

        CartItem existingItem = getCartItem(product.getId());
        if (existingItem != null) {
            if (existingItem.getProduct().getProductStock() - existingItem.getItemQuantity() > 0) {
                existingItem.incrementQuantity();
            }
            else {
                logger.warn("Cannot add more than the product's stock quantity to cart");
                return false;
            }
        }
        else {
            cartItems.add(new CartItem(product, 1));
        }

        logger.info("Successfully added product {} to cart", product.getProductName());
        return true;
    }

    public boolean removeItem(Product product) {
        CartItem existingItem = getCartItem(product.getId());
        if (existingItem != null) {
            if (existingItem.getItemQuantity() > 1) {
                existingItem.decrementQuantity();
                logger.info("Successfully decremented item quantity of {}", product.getProductName());
                return true;
            }
            else {
                cartItems.remove(existingItem);
                logger.info("Removed product {} from the cart", product.getProductName());
                return true;
            }
        }
        logger.warn("Could not remove product {} from cart", product.getProductName());
        return false;
    }

    private CartItem getCartItem(int productId) {
        for (CartItem item: cartItems) {
            if (item.getProduct().getId() == productId) {
                return item;
            }
        }
        return null;
    }

    public int getTotalQuantity() {
        return cartItems.stream()
                .mapToInt(CartItem:: getItemQuantity)
                .sum();
    }

    public double getTotalPrice() {
        return cartItems.stream()
                .mapToDouble((item) -> item.getProduct().getProductPrice())
                .sum();
    }

    public boolean isEmpty() {
        logger.info("Checking if cart is empty");
        return cartItems.isEmpty();
    }

    public void clear() {
        cartItems.clear();
        logger.info("Successfully cleared the cart");
    }
}
