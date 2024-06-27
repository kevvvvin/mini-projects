package com.stratpoint.projects.library;

import com.stratpoint.projects.library.book_types.Book;
import com.stratpoint.projects.library.library_management.Library;
import com.stratpoint.projects.library.ui.LibraryDisplay;

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

        // Create a new LibraryDisplay object to display the library
        LibraryDisplay display = new LibraryDisplay(library.getBooks());

        // Display the library instructions
        System.out.println("==================== LIBRARY ====================");
        display.printInstructions();

        // Create a new Scanner object to read user input
        Scanner sc = new Scanner(System.in);

        // Loop until the library is closed
        while (library.isOpen) {
            // Display the current page of the library
            display.printCurrentPage();

            // Prompt the user for a command
            System.out.print("\nEnter a command: ");
            String input = sc.nextLine();

            // Handle the user's input
            handleCommands(sc, input, library, display);

            // Pass any updates to the library display
            display.update(library.getBooks());
        }

        // Close the scanner object after library is closed
        sc.close();
    }

    /**
     * This method handles the user's input commands.
     *
     * @param sc The Scanner object used to read user input.
     * @param input The user's input command.
     * @param library The Library object representing the library.
     * @param display The LibraryDisplay object used to display the library.
     */
    public static void handleCommands(Scanner sc, String input, Library library, LibraryDisplay display) {
        // Switch statement to handle different input cases
        switch (input) {
            case "1":
                // Add a new book to the library
                Book book = library.getBookDetailsFromUser(sc);
                library.addBook(book);
                break;
            case "2":
                // Remove a book from the library
                int id = library.getBookIdFromUser(sc);
                library.removeBook(id);
                break;
            case "3":
                // Search for a book in the library
                String query = library.getSearchQueryFromUser(sc);
                library.searchBook(query);
                break;
            case "d", "D":
                // Go to the next page
                display.nextPage();
                break;
            case "a", "A":
                // Go to the previous page
                display.prevPage();
                break;
            case "q", "Q":
                // Exit the program
                library.toggle();
                break;
        }
    }
}
