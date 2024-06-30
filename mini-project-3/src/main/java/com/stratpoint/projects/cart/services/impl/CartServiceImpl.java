package com.stratpoint.projects.cart.services.impl;

import com.stratpoint.projects.cart.models.Cart;
import com.stratpoint.projects.cart.models.CartItem;
import com.stratpoint.projects.cart.models.Product;
import com.stratpoint.projects.cart.repository.CartRepository;
import com.stratpoint.projects.cart.repository.impl.CartRepositoryImpl;
import com.stratpoint.projects.cart.services.CartService;
import com.stratpoint.projects.cart.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements the CartService interface and provides cart-related functionality.
 */
public class CartServiceImpl implements CartService {

    /**
     * Logger instance for logging messages within the CartServiceImpl class.
     */
    private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    /**
     * The repository for interacting with the cart data.
     */
    private final CartRepository cartRepository;

    /**
     * The service for managing product-related operations.
     */
    private final ProductService productService;

    /**
     * Constructs a new instance of the CartServiceImpl class.
     * Initializes the cart repository object with a new instance of CartRepositoryImpl,
     * initializes the product service object with the provided ProductService.
     *
     * @param productService The service for managing product-related operations.
     */
    public CartServiceImpl(ProductService productService) {
        this.cartRepository = new CartRepositoryImpl();
        this.productService = productService;
    }


    /**
     * Adds a product to the cart.
     *
     * @param product the product to add
     * @return true if the product was added successfully, false otherwise
     */
    @Override
    public boolean addToCart(Product product) {
        return this.cartRepository.addItem(product);
    }

    /**
     * Removes a product from the cart.
     *
     * @param product The product to be removed from the cart.
     * @return true if the product was successfully removed from the cart, false otherwise.
     */
    @Override
    public boolean removeFromCart(Product product) {
        return this.cartRepository.removeItem(product);
    }

    /**
     * Returns the cart object from the cart repository.
     *
     * @return The cart object.
     */
    @Override
    public Cart getCart() {
        return this.cartRepository.getCart();
    }

    /**
     * Performs the checkout process, reducing the product stock of items in the cart and clears the cart.
     */
    @Override
    public void checkOut() {
        Cart cart = this.getCart();

        if (cart.isEmpty()) {
            logger.warn("Cannot check out an empty cart");
            return;
        }

        for (CartItem item: cart.getCartItems()) {
            Product product = this.productService.getProductById(item.getProduct().getId());
            product.reduceStock(item.getItemQuantity());
        }
        cart.clear();
        logger.info("The cart has been checked out");
    }
}
