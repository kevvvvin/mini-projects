package com.stratpoint.projects.book_types;

/**
 * Represents an audiobook.
 */
public class AudioBook extends Book {
    /**
     * The type of the book (e.g., Audio).
     */
    private static final String TYPE = "Audio";

    /**
     * The length of the audiobook in minutes.
     */
    private final int lengthInMinutes;

    /**
     * Constructor for creating an AudioBook object.
     *
     * @param title The title of the audiobook.
     * @param author The author of the audiobook.
     * @param genre The genre of the audiobook.
     * @param isbn The ISBN of the audiobook.
     * @param length The length of the audiobook in minutes.
     */
    public AudioBook(String title, String author, String genre, String isbn, int length) {
        super(title, author, genre, isbn);
        this.lengthInMinutes = length;
    }

    /**
     * Get the type of the audiobook.
     *
     * @return The type of the audiobook.
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Get the length of the audiobook in minutes.
     *
     * @return The length of the audiobook in minutes.
     */
    @Override
    public int getPagesOrLength() {
        return this.lengthInMinutes;
    }
}