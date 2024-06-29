package com.stratpoint.projects.cart.repository;

import com.stratpoint.projects.cart.models.Cart;
import com.stratpoint.projects.cart.models.Product;

public interface CartRepository {

    void addItem(Product product);
    void removeItem(Product product);
    Cart getCart();
}
