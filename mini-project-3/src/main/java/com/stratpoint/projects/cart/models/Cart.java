package com.stratpoint.projects.cart.models;

import java.util.ArrayList;
import java.util.List;

public class Cart{
    private List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public List<Product> getCartItems() {
        return cartItems;
    }
}
