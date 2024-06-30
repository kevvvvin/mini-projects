# Mini Project 3: E-commerce Cart System

A basic shopping cart system implemented in Java, utilizing Maven for project management.
The program allows users to view a list of products to add to the cart, view their cart and the total number of items
and price, add items to the cart, remove items from the cart, and checkout the items in the cart.

## Features

- **View Product List:** View the list of products and the details of each product including product name, price, description, and available stock.
- **View Shopping Cart:** View the current state of the shopping cart ang the details of each cart item including product name, total item price, description, and item quantity.
- **Add Items to Cart:** Add a product to the cart if there is available stock.
- **Remove Items from Cart:** Remove an item from the cart (decrements the quantity if multiple of same item is in the cart).
- **Checkout the Cart:** Purchase the items in the cart. Displays the cart and total price and quantity of items before clearing the cart.

## Installation

1. Clone the repository:
```sh
git clone https://github.com/kevvvvin/mini-projects.git
```
2. Navigate into the project repository:
```sh
cd mini-project-3
```
3. Build and Run the project using Maven:
```sh
mvn clean install
mvn exec:java
```
## How to Use
### Program Instructions
```
[1] Show list of products
[2] Show shopping cart
[3] Add a product to the cart based on product ID
[4] Remove an item from the cart based on product ID
[5] Proceed to cart checkout
[q] Quit program
```
   
