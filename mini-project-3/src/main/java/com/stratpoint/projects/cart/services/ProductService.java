package com.stratpoint.projects.cart.services;

import com.stratpoint.projects.cart.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);
}
