package com.stratpoint.projects.cart.repository.impl;

import com.stratpoint.projects.cart.models.Product;
import com.stratpoint.projects.cart.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the ProductRepository interface and provides methods
 * to interact with a list of Product objects.
 */
public class ProductRepositoryImpl implements ProductRepository {

    /**
     * The list of products.
     */
    private final List<Product> products = new ArrayList<>();

    /**
     * Constructor that initializes the products list with dummy data.
     */
    public ProductRepositoryImpl() {
        this.addDummyData();
    }


    /**
     * Retrieves all the products from the repository.
     *
     * @return A list of all the products in the repository.
     */
    @Override
    public List<Product> getAllProducts() {
        return this.products;
    }

    /**
     * Retrieves a product by its ID.
     * @param id The ID of the product to retrieve.
     * @return The product with the specified ID, or null if not found.
     */
    @Override
    public Product getProductById(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds dummy data to the products list.
     */
    private void addDummyData() {
        this.products.add(new Product("Product 1", 42.5, "Description 1", 10));
        this.products.add(new Product("Product 2", 12.5, "Description 2", 50));
        this.products.add(new Product("Product 3", 19.9, "Description 3", 30));
        this.products.add(new Product("Product 4", 25, "Description 4", 40));
        this.products.add(new Product("Product 5", 59.99, "Description 5", 3));
    }
}
