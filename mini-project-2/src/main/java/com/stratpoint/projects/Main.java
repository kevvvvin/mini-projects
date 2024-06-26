package com.stratpoint.projects;

import java.util.Scanner;

/**
 * This class represents the main entry point of the application.
 * It initializes a Library object and prompts the user for commands until the library is closed.
 */
public class Main {
    /**
     * The main method that runs the application.
     * It creates a new Library object, displays the library instructions, and prompts the user for commands until the library is closed.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Create a new Library object
        Library library = new Library();

        // Display the library instructions
        System.out.println("==================== LIBRARY ====================");
        library.displayInstructions();

        // Create a new Scanner object to read user input
        Scanner sc = new Scanner(System.in);

        // Loop until the library is closed
        while (library.isOpen) {
            // Display the current page of the library
            library.displayCurrentPage();

            // Prompt the user for a command
            System.out.print("\nEnter a command: ");

            // Handle the user's command
            library.handleLibraryCommands(sc);
        }

        // Close the scanner object after library is closed
        sc.close();
    }
}
