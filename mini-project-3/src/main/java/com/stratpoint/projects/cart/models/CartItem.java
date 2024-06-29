package com.stratpoint.projects.cart.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartItem {
    private final Logger logger = LoggerFactory.getLogger(CartItem.class);

    private final Product product;
    private int itemQuantity;

    public CartItem(Product product, int itemQuantity) {
        this.product = product;
        this.itemQuantity = itemQuantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getItemQuantity() {
        return this.itemQuantity;
    }

    public void setItemQuantity(int newQuantity) {
        this.itemQuantity = newQuantity;
    }

    public void decrementQuantity() {
        if (this.itemQuantity > 0) {
            this.itemQuantity--;
            logger.info("Successfully decremented item quantity.");
        }
        else {
            logger.warn("Cannot decrement quantity below 0.");
        }
    }
    public void incrementQuantity() {
        if (this.itemQuantity < this.product.getProductStock())
            this.itemQuantity++;
        else {
            logger.warn("Cannot increment item quantity above product stock quantity.");
        }
    }
}
