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

/**
 * Main class acting as the controller for the shopping cart application
 */
public class Main
{

    /**
     * Main method to start the application
     * @param args the command line arguments
     */
    public static void main( String[] args )
    {
        // Logger instance for logging messages within the Main class.
        Logger logger = LoggerFactory.getLogger(Main.class);

        // Initialize the product service with its implementation
        ProductService productService = new ProductServiceImpl();

        // Initialize the cart service with its implementation using the product service
        CartService cartService = new CartServiceImpl(productService);

        // Initialize the scanner to read user input from the console
        Scanner sc = new Scanner(System.in);

        while (true) {
            printInstructions();
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

    /**
     * Prints the instructions for the user.
     * Instructions include options to show product list, shopping cart, add a product to the cart,
     * remove an item from the cart, proceed to cart checkout, and quit the program.
     */
    private static void printInstructions() {
        System.out.println("\n[1] Show list of products");
        System.out.println("[2] Show shopping cart");
        System.out.println("[3] Add a product to the cart");
        System.out.println("[4] Remove an item from the cart");
        System.out.println("[5] Proceed to cart checkout");
        System.out.println("[q] Quit program");
    }

    /**
     * Prints the contents of the cart.
     * If the cart is empty, a message is printed accordingly.
     * Otherwise, the cart items are printed, along with the total quantity and total price.
     *
     * @param cartService the cart service used to retrieve the cart
     */
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

    /**
     * Prints the list of products to the console.
     *
     * @param productService The product service used to retrieve the list of products.
     */
    private static void printProductList(ProductService productService) {
        System.out.println("\n------ PRODUCT LIST ------");
        for (Product product : productService.getAllProducts()) {
            System.out.println(product.toString());
        }
    }

    /**
     * Adds a product to the cart.
     * It prompts the user to enter the ID of the product they want to add.
     * If the product exists and is successfully added to the cart, a success message is printed.
     * If the product does not exist or is sold out, an error message is printed.
     * If the user enters invalid input, an error message is logged.
     *
     * @param logger The logger instance.
     * @param sc The scanner instance.
     * @param productService The product service instance.
     * @param cartService The cart service instance.
     */
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

    /**
     * This method removes a product from the user's cart.
     * It prompts the user to enter the product ID they want to remove.
     * If the product is found in the cart and successfully removed, a success message is printed.
     * If the product is not found in the cart, an error message is printed.
     * If the user enters invalid input, an error message is logged.
     *
     * @param logger The logger instance for logging messages.
     * @param sc The scanner instance for reading user input.
     * @param productService The product service instance for retrieving product details.
     * @param cartService The cart service instance for managing the user's cart.
     */
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

    /**
     * Checks out the items in the cart.
     * If the cart is not empty, it displays the items, total quantity, total price,
     * checks out the cart, and prints a thank-you message.
     *
     * @param cartService the cart service used to handle the cart operations
     */
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
