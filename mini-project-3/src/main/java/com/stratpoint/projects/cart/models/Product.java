package com.stratpoint.projects.cart.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents a product with its attributes and methods.
 */
public class Product {

    /**
     * Logger instance for logging messages within the Product class.
     */
    private final Logger logger = LoggerFactory.getLogger(Product.class);

    /**
     * This static variable stores the next ID that will be assigned to a new Product instance.
     * It is initialized with 1, and incremented each time a new Product is created.
     */
    private static int ID = 1;

    /**
     * The unique identifier of the product.
     */
    private final int id;

    /**
     * The name of the product.
     */
    private final String productName;

    /**
     * The price of the product.
     */
    private final double productPrice;

    /**
     * The description of the product.
     */
    private final String productDescription;

    /**
     * The stock of the product.
     */
    private int productStock;

    /**
     * Constructs a new Product object.
     *
     * @param productName         The name of the product.
     * @param productPrice        The price of the product.
     * @param productDescription  The description of the product.
     * @param productStock        The stock of the product.
     */
    public Product(String productName, double productPrice, String productDescription, int productStock) {
        this.id = ID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productStock = productStock;
        ID++;
    }

    /**
     * Returns the ID of the product.
     *
     * @return the ID of the product
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the name of the product.
     *
     * @return the name of the product
     */
    public String getProductName() {
        return this.productName;
    }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product
     */
    public double getProductPrice() {
        return this.productPrice;
    }

    /**
     * Returns the description of the product.
     *
     * @return the description of the product
     */
    public String getProductDescription() {
        return this.productDescription;
    }

    /**
     * Returns the description of the product.
     *
     * @return the description of the product
     */
    public int getProductStock() {
        return this.productStock;
    }

    /**
     * Reduces the stock of the product by the specified quantity.
     * If the current stock is less than the specified quantity, a warning log message is logged and the method returns.
     * Otherwise, the stock is reduced and an info log message is logged.
     *
     * @param quantity the quantity to reduce the stock by
     */
    public void reduceStock(int quantity) {
        if (this.productStock < quantity) {
            logger.warn("Cannot reduce {} stock to quantity {}", this.productName, this.productStock - quantity);
            return;
        }
        this.productStock -= quantity;
        logger.info("Reduced {} stock to quantity {}", this.productName, this.productStock);
    }

    /**
     * Returns a string representation of the product.
     *
     * @return a string with product details
     */
    @Override
    public String toString() {
        return "id=" + id +
                ", productName='" + productName +
                ", productPrice=" + productPrice +
                ", productDescription='" + productDescription +
                ", productStock=" + productStock;
    }
}
