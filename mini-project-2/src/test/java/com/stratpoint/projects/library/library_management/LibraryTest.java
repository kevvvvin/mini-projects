package com.stratpoint.projects.library.library_management;

import com.stratpoint.projects.library.book_types.Book;
import com.stratpoint.projects.library.book_types.ElectronicBook;
import com.stratpoint.projects.library.book_types.PhysicalBook;
import com.stratpoint.projects.library.book_types.AudioBook;


import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class LibraryTest {
    private Library library;
    private InputStream originalInput;
    private ByteArrayInputStream input;

    @Before
    public void setUp() {
        originalInput = System.in;

        library = new Library();
        library.addBook(new PhysicalBook("Physical Book 1", "Author One", "Fiction", "1234567890", 243));
        library.addBook(new PhysicalBook("Physical Book 2", "abc", "def", "1234567809", 100));
        library.addBook(new ElectronicBook("Electronic Book 1", "Author Two", "Non-fiction", "0987654321", 12));
        library.addBook(new ElectronicBook("Electronic Book 2", "def", "abc", "2134567890", 112));
        library.addBook(new AudioBook("Audio Book 1", "Author Three", "Horror", "4321567890", 20));
        library.addBook(new AudioBook("Audio Book 2", "zxc", "qwe", "1324567890", 2000));
    }

    @After
    public void tearDown() {
        library = null;
        System.setIn(originalInput);
    }

    @Test
    public void testAddBook() {
        Book book = new PhysicalBook("Physical Book 2", "Author Four", "Mystery", "9876054321", 100);
        library.addBook(book);

        assertTrue(library.containsBook(book));
    }

    @Test
    public void testRemoveBook() {
        Book book = library.getBooks().get(0);
        library.removeBook(1);

        assertFalse(library.containsBook(book));
    }

    @Test
    public void testSearchBook() {
        String query = "Audio";
        int resultsCount = library.searchBook(query);
        assertEquals(2, resultsCount);

        query = "abc";
        resultsCount = library.searchBook(query);
        assertEquals(2, resultsCount);

        query = "book";
        resultsCount = library.searchBook(query);
        assertEquals(6, resultsCount);

        query = "1234567890";
        resultsCount = library.searchBook(query);
        assertEquals(1, resultsCount);

        query = "Horror";
        resultsCount = library.searchBook(query);
        assertEquals(1, resultsCount);
    }

    @Test
    public void testValidateBookDetails_Title_Valid() {
        provideInput("Valid Title");
        Scanner sc = new Scanner(System.in);

        String validatedTitle = library.validateBookDetails(sc, "title");
        assertEquals("Valid Title", validatedTitle);
    }

    @Test(timeout = 5000)
    public void testValidateBookDetails_Title_InvalidToValid() {
        provideInput("<>Invalid Title\nValid Title\n");
        Scanner sc = new Scanner(System.in);

        String validatedTitle = library.validateBookDetails(sc, "title");
        assertEquals("Valid Title", validatedTitle);
    }

    @Test
    public void testValidateBookDetails_Author_Valid() {
        provideInput("Valid Author\n");
        Scanner sc = new Scanner(System.in);

        String validatedAuthor = library.validateBookDetails(sc, "author");
        assertEquals("Valid Author", validatedAuthor);
    }

    @Test(timeout = 5000)
    public void testValidateBookDetails_Author_InvalidToValid() {
        provideInput("123Author\nValid Author\n");
        Scanner sc = new Scanner(System.in);

        String validatedAuthor = library.validateBookDetails(sc, "author");
        assertEquals("Valid Author", validatedAuthor);
    }


    @Test
    public void testValidateBookDetails_Genre_Valid() {
        provideInput("Valid Genre\n");
        Scanner sc = new Scanner(System.in);

        String validatedGenre = library.validateBookDetails(sc, "genre");
        assertEquals("Valid Genre", validatedGenre);
    }

    @Test(timeout = 5000)
    public void testValidateBookDetails_Genre_InvalidToValid() {
        provideInput("123Genre--\nValid Genre\n");
        Scanner sc = new Scanner(System.in);

        String validatedGenre = library.validateBookDetails(sc, "genre");
        assertEquals("Valid Genre", validatedGenre);
    }

    @Test
    public void testValidateBookDetails_ISBN_Valid() {
        provideInput("1234567890\n");
        Scanner sc = new Scanner(System.in);

        String validatedISBN = library.validateBookDetails(sc, "ISBN");
        assertEquals("1234567890", validatedISBN);
    }

    @Test(timeout = 5000)
    public void testValidateBookDetails_ISBN_InvalidToValid() {
        provideInput("1234\n1234567890\n");
        Scanner sc = new Scanner(System.in);

        String validated = library.validateBookDetails(sc, "ISBN");
        assertEquals("1234567890", validated);
    }

    @Test
    public void testValidateBookDetails_Type_Valid() {
        provideInput("physical\n");
        Scanner sc = new Scanner(System.in);

        String validatedType = library.validateBookDetails(sc, "type");
        assertEquals("physical", validatedType);
    }

    @Test(timeout = 5000)
    public void testValidateBookDetails_Type_InvalidToValid() {
        provideInput("invalidType\nphysical\n");
        Scanner sc = new Scanner(System.in);

        String validated = library.validateBookDetails(sc, "type");
        assertEquals("physical", validated);
    }

    @Test
    public void testValidateBookDetails_Length_Valid() {
        provideInput("300\n");
        Scanner sc = new Scanner(System.in);

        String validatedLength = library.validateBookDetails(sc, "length");
        assertEquals("300", validatedLength);
    }

    @Test(timeout = 5000)
    public void testValidateBookDetails_Length_InvalidToValid() {
        provideInput("-1\n400\n");
        Scanner sc = new Scanner(System.in);

        String validated = library.validateBookDetails(sc, "length");
        assertEquals("400", validated);
    }

    private void provideInput(String data) {
        input = new ByteArrayInputStream(data.getBytes());
        System.setIn(input);
    }

}
