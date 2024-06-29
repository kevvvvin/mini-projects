package com.stratpoint.projects.cart.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Product {
    private final Logger logger = LoggerFactory.getLogger(Product.class);
    private static int ID = 1;

    private final int id;
    private final String productName;
    private final double productPrice;
    private final String productDescription;
    private int productStock;

    public Product(String productName, double productPrice, String productDescription, int productStock) {
        this.id = ID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productStock = productStock;
        ID++;
    }

    public int getId() {
        return this.id;
    }

    public String getProductName() {
        return this.productName;
    }

    public double getProductPrice() {
        return this.productPrice;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public int getProductStock() {
        return this.productStock;
    }

    public void reduceStock(int quantity) {
        this.productStock -= quantity;
        logger.info("Reduced {} stock to quantity {}", this.productName, this.productStock);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", productName='" + productName +
                ", productPrice=" + productPrice +
                ", productDescription='" + productDescription +
                ", productStock=" + productStock;
    }
}
