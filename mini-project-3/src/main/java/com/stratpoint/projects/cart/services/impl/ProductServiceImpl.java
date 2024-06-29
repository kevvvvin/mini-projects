package com.stratpoint.projects.cart.services.impl;

import com.stratpoint.projects.cart.models.Product;
import com.stratpoint.projects.cart.repository.ProductRepository;
import com.stratpoint.projects.cart.repository.impl.ProductRepositoryImpl;
import com.stratpoint.projects.cart.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl() {
        this.productRepository = new ProductRepositoryImpl();
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.getAllProducts();
    }

    @Override
    public Product getProductById(int id) {
        return this.productRepository.getProductById(id);
    }
}
