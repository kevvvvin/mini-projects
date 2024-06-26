package com.stratpoint.projects.library.book_types;

/**
 * This abstract class represents a Book with properties like title, author, genre, and ISBN.
 * It provides a common interface for all types of books.
 */
public abstract class Book {
    /**
     * The title of the book.
     */
    private final String title;

    /**
     * The author of the book.
     */
    private final String author;

    /**
     * The genre of the book.
     */
    private final String genre;

    /**
     * The ISBN of the book.
     */
    private final String ISBN;

    /**
     * Constructs a new Book with the given title, author, genre, and ISBN.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param genre The genre of the book.
     * @param ISBN The ISBN of the book.
     */
    public Book(String title, String author, String genre, String ISBN) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
    }

    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Gets the genre of the book.
     *
     * @return The genre of the book.
     */
    public String getGenre() {
        return this.genre;
    }

    /**
     * Gets the ISBN of the book.
     *
     * @return The ISBN of the book.
     */
    public String getISBN() {
        return this.ISBN;
    }

    /**
     * Gets the type of the book.
     *
     * @return The type of the book.
     */
    public abstract String getType();

    /**
     * Gets the number of pages or length of the book, depending on the type of book.
     *
     * @return The number of pages or length of the book.
     */
    public abstract int getPagesOrLength();
}