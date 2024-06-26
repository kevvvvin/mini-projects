package com.stratpoint.projects.booktypes;

/**
 * Represents an audiobook.
 */
public class AudioBook extends Book{
    /**
     * The type of the book (e.g., Audio).
     */
    private String type;

    /**
     * The length of the audiobook in minutes.
     */
    private int lengthInMinutes;

    /**
     * Constructor for creating an AudioBook object.
     *
     * @param title The title of the audiobook.
     * @param author The author of the audiobook.
     * @param genre The genre of the audiobook.
     * @param ISBN The ISBN of the audiobook.
     * @param length The length of the audiobook in minutes.
     */
    public AudioBook(String title, String author, String genre, String ISBN, int length ) {
        super(title, author, genre, ISBN);
        this.type = "Audio";
        this.lengthInMinutes = length;
    }

    /**
     * Get the type of the audiobook.
     *
     * @return The type of the audiobook.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the length of the audiobook in minutes.
     *
     * @return The length of the audiobook in minutes.
     */
    public int getLength() {
        return this.lengthInMinutes;
    }
}