package com.stratpoint.projects.booktypes;

/**
 * Represents a physical book.
 */
public class PhysicalBook extends Book {
    /**
     * The type of book.
     */
    private String type;

    /**
     * The number of pages in the book.
     */
    private int pages;

    /**
     * Constructs a new PhysicalBook object.
     *
     * @param title   The title of the book.
     * @param author  The author of the book.
     * @param genre   The genre of the book.
     * @param ISBN    The ISBN of the book.
     * @param pages   The number of pages in the book.
     */
    public PhysicalBook(String title, String author, String genre, String ISBN, int pages) {
        super(title, author, genre, ISBN);
        this.type = "Physical";
        this.pages = pages;
    }

    /**
     * Gets the type of the book.
     *
     * @return The type of the book.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the number of pages in the book.
     *
     * @return The number of pages in the book.
     */
    public int getPages() {
        return this.pages;
    }
}
