package com.stratpoint.projects.library.ui;

import com.stratpoint.projects.library.book_types.Book;
import com.stratpoint.projects.library.observers.LibraryObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * This class represents the display of a library. It implements the LibraryObserver interface to receive updates on changes
 * in the library's collection of books. It provides methods to print the current page of books.
 */
public class LibraryDisplay implements LibraryObserver {

    /**
     * Logger instance for logging purposes.
     */
    private static final Logger logger = LoggerFactory.getLogger(LibraryDisplay.class);

    /**
     * List of books in the library.
     */
    private List<Book> books;

    /**
     * Current page number.
     */
    private int currentPage;

    /**
     * Index of the first item on the current page.
     */
    private int firstItemIdx = 0;

    /**
     * Number of books to display per page.
     */
    private static final int PAGE_SIZE = 5;

    /**
     * Constructor for LibraryDisplay class.
     * Initializes the list of books and sets the current page to 1.
     *
     * @param books The list of books in the library
     */
    public LibraryDisplay(List<Book> books) {
        this.books = books;
        this.currentPage = 1;
    }

    /**
     * Displays the books in the current page of the library and the current page number.
     * The books are displayed in a formatted table with columns for ID, Title, Author, Genre, ISBN, Type, and Pages or Length.
     */
    public void printCurrentPage() {
        // Define the spacing between columns
        String gapLeft = "| ";
        String gapRight = " ";

        // Print the header row for the table
        this.printPageHeader();

        // Check if there are any books in the library
        if (this.books.isEmpty()) {
            logger.warn("There are no books in the library");
            return;
        }

        // Calculate the last book to display on the current page
        int lastItem = Math.min(this.currentPage * PAGE_SIZE, this.books.size());

        // Loop through the books on the current page and display them
        for (int i=this.firstItemIdx; i < lastItem; i++) {
            Book book = this.books.get(i);
            System.out.print(i+1 + gapRight);
            System.out.print(gapLeft + book.getTitle() + gapRight);
            System.out.print(gapLeft + book.getAuthor() + gapRight);
            System.out.print(gapLeft + book.getGenre() + gapRight);
            System.out.print(gapLeft + book.getISBN() + gapRight);
            System.out.print(gapLeft + book.getType() + gapRight);

            String length = book.getPagesOrLength() + (book.getType().equalsIgnoreCase("Audio") ? " minutes" : " pages");
            System.out.print(gapLeft + length + gapRight + "\n");
        }
    }

    /**
     * Moves to the next page of books in the library.
     * Increments the page number and updates the index of the first item on the page if the current page is not the last page.
     * If the current page is already the last page, it logs a warning message.
     */
    public void nextPage() {
        // Check if the current page is the last page
        if (this.firstItemIdx + PAGE_SIZE >= this.books.size()) {
            // Log a warning message if the current page is already the last page
            logger.warn("Failed to change the page. Already at the last page");
        } else {
            // Increment the page number
            this.currentPage += 1;

            // Update the index of the first item on the page
            this.firstItemIdx += PAGE_SIZE;

            // Log the page change event
            this.logPageChange();
        }
    }

    /**
     * Moves to the previous page of books in the library.
     * Decrements the page number and updates the index of the first item on the page if the current page is not the first page.
     * If the current page is already the first page, it logs a warning message.
     */
    public void prevPage() {
        // Check if the current page is the first page
        if (this.currentPage == 1) {
            // Log a warning message if the current page is already the first page
            logger.warn("Failed to change the page. Already at the first page");
            return;
        }

        // Decrement the page number
        this.currentPage -= 1;

        // Update the index of the first item on the page
        this.firstItemIdx -= PAGE_SIZE;

        this.logPageChange();
    }

    /**
     * This method prints the header row for the table.
     * It includes the column names for ID, Title, Author, Genre, ISBN, Type, and Pages or Length in Minutes.
     */
    private void printPageHeader() {
        // Define the spacing between columns
        String gapLeft = "| ";
        String gapRight = " ";

        // Print the header row for the table
        System.out.print("\nID ");
        System.out.print(gapLeft + "TITLE" + gapRight);
        System.out.print(gapLeft + "AUTHOR" + gapRight);
        System.out.print(gapLeft + "GENRE" + gapRight);
        System.out.print(gapLeft + "ISBN" + gapRight);
        System.out.print(gapLeft + "TYPE" + gapRight);
        System.out.print(gapLeft + "PAGES OR LENGTH IN MINUTES" + gapRight);
        System.out.print(gapLeft + "Page " + this.currentPage + gapRight + "\n");
    }

    /**
     * This method displays the instructions for the library.
     * It prints a list of commands that the user can use to interact with the library.
     */
    public void printInstructions() {
        // Print the instructions for adding a book to the library
        System.out.println("\n[1] Add a book to the library");
        System.out.println("[2] Remove a book from the library");
        System.out.println("[3] Search for a book within the library");
        System.out.println("[d] Go to next page of library");
        System.out.println("[a] Go to previous page of library");
        System.out.println("[q] Exit program");
    }

    /**
     * Logs the page change event.
     * This method logs the page number to the logger when the user navigates to a new page.
     */
    private void logPageChange() {
        // Use the logger to log the page change event
        logger.info("Moved to page {}.", this.currentPage);
    }

    @Override
    public void update(List<Book> books) {
        this.books = books;
    }
}
