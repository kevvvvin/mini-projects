package com.stratpoint.projects.cart.services;

import com.stratpoint.projects.cart.models.Cart;
import com.stratpoint.projects.cart.models.Product;

public interface CartService {
    boolean addToCart(Product product);
    boolean removeFromCart(Product product);
    Cart getCart();
    void checkOut();
}
