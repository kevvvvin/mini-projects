package com.stratpoint.projects.cart.repository;

import com.stratpoint.projects.cart.models.Cart;
import com.stratpoint.projects.cart.models.Product;

public interface CartRepository {

    boolean addItem(Product product);
    boolean removeItem(Product product);
    Cart getCart();
}
