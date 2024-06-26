package com.stratpoint.projects;

public class Book {
    private static int nextID = 1;
    private final int ID;
    private String title;
    private String author;
    private String genre;
    private String ISBN;

    public Book(String title, String author, String genre, String ISBN) {
        this.ID = nextID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        nextID++;
    }

    public int getID() { return this.ID; }

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
