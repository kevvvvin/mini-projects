package com.stratpoint.projects.booktypes;

/**
 * Represents an electronic book.
 */
public class ElectronicBook extends Book {
    /**
     * The type of book.
     */
    private String type;

    /**
     * The number of pages in the book.
     */
    private int pages;

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
        super(title, author, genre, ISBN);
        this.type = "Electronic";
        this.pages = pages;
    }


    /**
     * Gets the book type.
     *
     * @return The type of the book.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the number of pages of the book.
     *
     * @return The number of pages of the book.
     */
    public int getPages() {
        return this.pages;
    }
}