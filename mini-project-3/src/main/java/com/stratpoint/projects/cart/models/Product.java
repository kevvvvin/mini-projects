package com.stratpoint.projects.cart.models;

public class Product {
    private static int ID = 1;

    private int id;
    private String productName;
    private double productPrice;
    private String productDescription;
    private int productQuantity;

    public Product(String productName, double productPrice, String productDescription, int productQuantity) {
        this.id = ID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productQuantity = productQuantity;
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

    public int getProductQuantity() {
        return this.productQuantity;
    }

    public void setProductQuantity(int newQuantity) {
        this.productQuantity = newQuantity;
    }
}
