package com.stratpoint.projects.booktypes;

public class PhysicalBook extends Book{
    private String type;
    private int pages;

    public PhysicalBook(String title, String author, String genre,  String ISBN, int pages) {
        super(title, author, genre, ISBN);
        this.type = "Physical";
        this.pages = pages;
    }
}
