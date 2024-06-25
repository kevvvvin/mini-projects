package com.stratpoint.projects;

import java.util.List;
import java.util.ArrayList;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
        System.out.println(book.getTitle() + " has been added to the library!");
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        System.out.println(book.getTitle() + " has been removed from the library!");
    }

    public void searchBook(Book book) {
        System.out.println("TODO: Search for a book");
    }

    public List<Book> getAvailableBooks() {
        return this.books;
    }
}
