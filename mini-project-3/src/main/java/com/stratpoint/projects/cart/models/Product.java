package com.stratpoint.projects.cart.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Product {
    private final Logger logger = LoggerFactory.getLogger(Product.class);
    private static int ID = 1;

    private int id;
    private String productName;
    private double productPrice;
    private String productDescription;
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

    public void incrementStock() {
        this.productStock++;
    }

    public void decrementStock() {
        if (this.productStock > 0) {
            this.productStock--;
        }
        else {
            logger.warn("Cannot decrement product stock quantity below 0.");
        }
    }
}
