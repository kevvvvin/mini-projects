package com.stratpoint.projects.cart.repository;

import com.stratpoint.projects.cart.models.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
    Product getProductById(int id);
}