package com.stratpoint.projects.cart.models;

public class CartItem {
    private Product product;
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
}
