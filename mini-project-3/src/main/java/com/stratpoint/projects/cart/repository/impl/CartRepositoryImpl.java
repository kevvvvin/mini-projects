package com.stratpoint.projects.cart.repository.impl;

import com.stratpoint.projects.cart.models.Cart;
import com.stratpoint.projects.cart.models.Product;
import com.stratpoint.projects.cart.repository.CartRepository;

public class CartRepositoryImpl implements CartRepository {
    private final Cart cart;

    public CartRepositoryImpl() {
        this.cart = new Cart();
    }

    @Override
    public void addItem(Product product) {
        cart.addItem(product);
    }

    @Override
    public void removeItem(Product product) {
        cart.removeItem(product);
    }

    @Override
    public Cart getCart() {
        return cart;
    }
}
