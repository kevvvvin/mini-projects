package com.stratpoint.projects.cart.repository;

import com.stratpoint.projects.cart.models.Product;

import java.util.List;

/**
 * Interface for a repository that handles Product objects.
 */
public interface ProductRepository {

    /**
     * Retrieves all products from the repository.
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