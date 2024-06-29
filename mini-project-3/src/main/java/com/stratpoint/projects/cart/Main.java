package com.stratpoint.projects.cart;

import com.stratpoint.projects.cart.models.Cart;
import com.stratpoint.projects.cart.models.CartItem;
import com.stratpoint.projects.cart.models.Product;
import com.stratpoint.projects.cart.services.CartService;
import com.stratpoint.projects.cart.services.ProductService;
import com.stratpoint.projects.cart.services.impl.CartServiceImpl;
import com.stratpoint.projects.cart.services.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args )
    {
        Logger logger = LoggerFactory.getLogger(Main.class);
        ProductService productService = new ProductServiceImpl();
        CartService cartService = new CartServiceImpl(productService);
        Scanner sc = new Scanner(System.in);

        while (true) {
            printInitialInstructions();
            System.out.print("Enter your choice: ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("q")) { break; }

            switch (input) {
                case "1":
                    printProductList(productService);
                    break;
                case "2":
                    printCart(cartService);
                    break;
                case "3":
                    addToCart(logger, sc, productService, cartService);
                    break;
                case "4":
                    removeFromCart(logger, sc, productService, cartService);
                    break;
                case "5":
                    checkOutCart(cartService);
                    break;
                default:
                    logger.warn("Invalid user input");
                    break;
            }
        }
    }

    private static void printInitialInstructions() {
        System.out.println("\n[1] Show list of products");
        System.out.println("[2] Show shopping cart");
        System.out.println("[3] Add a product to the cart");
        System.out.println("[4] Remove an item from the cart");
        System.out.println("[5] Proceed to cart checkout");
        System.out.println("[q] Quit program");
    }

    private static void printCart(CartService cartService) {
        Cart cart = cartService.getCart();
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        }
        else {
            System.out.println("\n------ CART ITEMS ------");
            for (CartItem item : cart.getCartItems()) {
                System.out.println(item.toString());
            }
            System.out.println("Total Items: " + cart.getTotalQuantity());
            System.out.println("Total Price: " + cart.getTotalPrice());
        }
    }

    private static void printProductList(ProductService productService) {
        System.out.println("\n------ PRODUCT LIST ------");
        for (Product product : productService.getAllProducts()) {
            System.out.println(product.toString());
        }
    }

    private static void addToCart(Logger logger, Scanner sc, ProductService productService, CartService cartService) {
        System.out.print("Enter product ID to add: ");
        try {
            int productId = sc.nextInt();
            Product product = productService.getProductById(productId);

            if (product != null) {
                boolean success = cartService.addToCart(product);
                if (success) {
                    System.out.println(product.getProductName() + " added to cart");
                }
                else {
                    System.out.println("Failed to add " + product.getProductName() + " to cart" );
                }
            }
            else {
                System.out.println("Product not found");
                logger.warn("Product with ID {} not found", productId);
            }
        }
        catch (InputMismatchException e) {
            logger.error("Error while adding to cart: invalid user input");
        }
        finally {
            sc.nextLine();
        }
    }

    private static void removeFromCart(Logger logger, Scanner sc, ProductService productService, CartService cartService) {
        System.out.print("Enter product ID to remove: ");
        try {
            int productId = sc.nextInt();
            Product product = productService.getProductById(productId);

            if (product != null) {
                if (cartService.removeFromCart(product)) {
                    System.out.println("Product " + product.getProductName() + " removed from cart");
                }
                else {
                    System.out.println("Failed to remove product " + product.getProductName() + " from cart" );
                }
            }
            else {
                System.out.println("Product not found");
                logger.warn("Product with ID {} not found", productId);
            }
        }
        catch (InputMismatchException e) {
            logger.error("Error while removing from cart: invalid user input");
        }
        finally {
            sc.nextLine();
        }
    }

    private static void checkOutCart(CartService cartService) {
        Cart cart = cartService.getCart();
        if (cart.isEmpty()) {
            System.out.println("Cannot checkout! Cart is empty");
        }
        else {
            System.out.println("\n------ CHECKING OUT ------");
            for (CartItem item : cart.getCartItems()) {
                System.out.println(item.toString());
            }
            System.out.println("Total Items: " + cart.getTotalQuantity());
            System.out.println("Total Price: " + cart.getTotalPrice());
            cartService.checkOut();
            System.out.println("Cart checked out. Thank you for purchasing!");
        }

    }

}
