package com.stratpoint.projects.booktypes;

public class Book {
    private String title;
    private String author;
    private String genre;
    private String ISBN;

    public Book(String title, String author, String genre, String ISBN) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getGenre() { return this.genre; }

    public String getISBN() {
        return this.ISBN;
    }

}
