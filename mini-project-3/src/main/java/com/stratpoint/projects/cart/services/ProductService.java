package com.stratpoint.projects.cart.services;

import com.stratpoint.projects.cart.models.Product;

import java.util.List;

/**
 * This interface defines the contract for a service that provides operations related to products.
 */
public interface ProductService {

    /**
     * Retrieves all products from the service.
     *
     * @return a list of all products
     */
    List<Product> getAllProducts();

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the product with the specified ID, or null if not found
     */
    Product getProductById(int id);
}