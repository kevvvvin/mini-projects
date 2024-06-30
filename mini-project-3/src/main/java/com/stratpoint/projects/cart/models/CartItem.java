package com.stratpoint.projects.cart.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class representing an item in a shopping cart.
 */
public class CartItem {

    /**
     * Logger instance for logging messages within the CartItem class.
     */
    private final Logger logger = LoggerFactory.getLogger(CartItem.class);

    /**
     * The product associated with this cart item.
     */
    private final Product product;

    /**
     * The quantity of the product in this cart item.
     */
    private int itemQuantity;

    /**
     * Constructs a new CartItem with the given product and quantity.
     *
     * @param product The product to associate with this cart item.
     * @param itemQuantity The quantity of the product in this cart item.
     */
    public CartItem(Product product, int itemQuantity) {
        this.product = product;
        this.itemQuantity = itemQuantity;
    }

    /**
     * Returns the product associated with this cart item.
     *
     * @return The product associated with this cart item.
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Returns the quantity of the item in the cart.
     *
     * @return The quantity of the item in the cart.
     */
    public int getItemQuantity() {
        return this.itemQuantity;
    }

    /**
     * Decrements the quantity of the item in the cart.
     * If the quantity is already 0, a warning message is logged.
     */
    public void decrementQuantity() {
        if (this.itemQuantity > 0) {
            this.itemQuantity--;
        }
        else {
            logger.warn("Cannot decrement quantity below 0.");
        }
    }

    /**
     * Increments the quantity of the item in the cart.
     * If the quantity is already equal to or greater than the product stock quantity,
     * a warning message is logged.
     */
    public void incrementQuantity() {
        if (this.itemQuantity < this.product.getProductStock())
            this.itemQuantity++;
        else {
            logger.warn("Cannot increment item quantity above product stock quantity.");
        }
    }

    /**
     * Returns a string representation of the CartItem.
     *
     * @return a string with cart item details
     */
    @Override
    public String toString() {
        return "id=" + this.product.getId() +
                ", productName=" + this.product.getProductName() +
                ", itemPrice=" + this.product.getProductPrice() * this.itemQuantity +
                ", productDescription=" + this.product.getProductDescription() +
                ", itemQuantity=" + this.itemQuantity;
    }
}
