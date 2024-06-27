package com.stratpoint.projects.library.observers;

import java.util.List;
import com.stratpoint.projects.library.book_types.Book;

/**
 * The LibraryObserver interface defines the contract for classes that observe changes in a library's collection of books.
 * Implementing classes must provide an update method to receive updates on the list of books.
 */
public interface LibraryObserver {

    /**
     * Update method called when the list of books in the library changes.
     *
     * @param books The updated list of books in the library.
     */
    void update(List<Book> books);
}