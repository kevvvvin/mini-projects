package com.stratpoint.projects;

public class ElectronicBook extends Book{
    private String type;
    private int pages;

    public ElectronicBook(String title, String author, String genre, String ISBN, int pages) {
        super(title, author, genre, ISBN);
        this.type = "electronic";
        this.pages = pages;

    }
}
