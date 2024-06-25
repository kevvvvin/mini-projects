package com.stratpoint.projects;

public class AudioBook extends Book{
    private String type;
    private double lengthInMinutes;

    public AudioBook(String title, String author, String genre, String ISBN, double length ) {
        super(title, author, genre, ISBN);
        this.type = "audio";
        this.lengthInMinutes = length;
    }
}
