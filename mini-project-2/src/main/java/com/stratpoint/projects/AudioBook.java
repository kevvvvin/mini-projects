package com.stratpoint.projects;

public class AudioBook extends Book{
    private String type;
    private int lengthInMinutes;

    public AudioBook(String title, String author, String genre, String ISBN, int length ) {
        super(title, author, genre, ISBN);
        this.type = "Audio";
        this.lengthInMinutes = length;
    }
}
