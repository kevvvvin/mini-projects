package com.stratpoint.projects.cart.services.impl;

import com.stratpoint.projects.cart.models.Product;
import com.stratpoint.projects.cart.repository.ProductRepository;
import com.stratpoint.projects.cart.repository.impl.ProductRepositoryImpl;
import com.stratpoint.projects.cart.services.ProductService;

import java.util.List;

/**
 * This class implements the ProductService interface and provides product-related functionality.
 */
public class ProductServiceImpl implements ProductService {

    /**
     * The ProductRepository instance used to interact with the product repository.
     */
    private final ProductRepository productRepository;

    /**
     * Constructs a new ProductServiceImpl instance.
     * Initializes the productRepository field with a new instance of ProductRepositoryImpl.
     */
    public ProductServiceImpl() {
        this.productRepository = new ProductRepositoryImpl();
    }

    /**
     * Retrieves all the products from the repository.
     *
     * @return A list of all the products obtained from the product repository.
     */
    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.getAllProducts();
    }

    /**
     * Retrieves a product by its ID.
     * @param id The ID of the product to retrieve.
     * @return The product with the specified ID, or null if not found.
     */
    @Override
    public Product getProductById(int id) {
        return this.productRepository.getProductById(id);
    }
}
