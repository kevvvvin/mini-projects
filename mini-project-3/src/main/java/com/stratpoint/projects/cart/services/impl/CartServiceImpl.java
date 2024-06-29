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

public class CartServiceImpl implements CartService {
    private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartServiceImpl(ProductService productService) {
        this.cartRepository = new CartRepositoryImpl();
        this.productService = productService;
    }

    @Override
    public boolean addToCart(Product product) {
        if (product.getProductStock() > 0) {
            return this.cartRepository.addItem(product);
        }
        return false;
    }

    @Override
    public boolean removeFromCart(Product product) {
        return this.cartRepository.removeItem(product);
    }

    @Override
    public Cart getCart() {
        return cartRepository.getCart();
    }

    @Override
    public void checkOut() {
        Cart cart = this.getCart();

        if (cart.isEmpty()) {
            logger.warn("Cannot check out an empty cart");
            return;
        }

        for (CartItem item: cart.getCartItems()) {
            Product product = productService.getProductById(item.getProduct().getId());
            product.reduceStock(item.getItemQuantity());
        }
        cart.clear();
        logger.info("The cart has been checked out");
    }
}
