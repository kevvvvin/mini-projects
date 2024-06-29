package com.stratpoint.projects.cart.repository.impl;

import com.stratpoint.projects.cart.models.Product;
import com.stratpoint.projects.cart.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public ProductRepositoryImpl() {
        this.addDummyData();
    }

    @Override
    public List<Product> getAllProducts() {
        // return new ArrayList<>(this.products);
        return this.products;
    }

    @Override
    public Product getProductById(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void addDummyData() {
        this.products.add(new Product("Product 1", 42.5, "Description 1", 10));
        this.products.add(new Product("Product 2", 12.5, "Description 2", 50));
        this.products.add(new Product("Product 3", 19.9, "Description 3", 30));
        this.products.add(new Product("Product 4", 25, "Description 4", 40));
        this.products.add(new Product("Product 5", 59.99, "Description 5", 3));
    }
}
