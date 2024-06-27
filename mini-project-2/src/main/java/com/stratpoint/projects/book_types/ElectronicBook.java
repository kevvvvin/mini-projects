package com.stratpoint.projects.book_types;

/**
 * Represents an electronic book.
 * An electronic book is a type of book that is stored electronically.
 * It has a type, title, author, genre, ISBN, and number of pages.
 */
public class ElectronicBook extends Book {
    /**
     * The type of the book (e.g., Electronic).
     */
    private static final String type = "Electronic";

    /**
     * The number of pages in the book.
     */
    private final int pages;

    /**
     * Constructs an electronic book with the specified title, author, genre, ISBN, and number of pages.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param genre The genre of the book.
     * @param ISBN The ISBN of the book.
     * @param pages The number of pages in the book.
     */
    public ElectronicBook(String title, String author, String genre, String ISBN, int pages) {
        super(title, author, genre, ISBN); // Call the parent class constructor
        this.pages = pages;
    }

    /**
     * Gets the book type.
     *
     * @return The type of the book.
     */
    @Override
    public String getType() {
        return type; // Return the type of the book
    }

    /**
     * Gets the number of pages of the book.
     *
     * @return The number of pages of the book.
     */
    @Override
    public int getPagesOrLength() {
        return this.pages; // Return the number of pages in the book
    }
}