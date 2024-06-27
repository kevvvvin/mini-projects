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

/**
 * This class contains unit tests for the Library class.
 */
public class LibraryTest {
    /**
     * The library instance used for testing.
     */
    private Library library;

    /**
     * The original input stream before the tests.
     */
    private InputStream originalInput;

    /**
     * Sets up the test environment by initializing the library and adding some test books.
     * This method is called before each test case is executed.
     */
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

    /**
     * Tears down the test environment after each test.
     * This method resets the library instance and restores the original input stream.
     */
    @After
    public void tearDown() {
        library = null;
        System.setIn(originalInput);
    }

    /**
     * Test case for adding a book to the library.
     * This test verifies that a book can be added to the library and that it can be
     * confirmed that the book is present in the library.
     */
    @Test
    public void testAddBook() {
        Book book = new PhysicalBook("Physical Book 2", "Author Four", "Mystery", "9876054321", 100);

        library.addBook(book);
        assertTrue(library.containsBook(book));
    }

    /**
     * Test case for removing a book from the library.
     * This test verifies that a book can be successfully removed from the library.
     */
    @Test
    public void testRemoveBook() {
        Book book = library.getBooks().get(0);
        library.removeBook(1);

        assertFalse(library.containsBook(book));
    }

    /**
     * Test case for the searchBook method.
     * This test verifies the functionality of the searchBook method.
     * It tests various search queries and checks the number of results.
     */
    @Test
    public void testSearchBook() {
        // Test case: Search for books containing the word "Audio"
        String query = "Audio";
        int resultsCount = library.searchBook(query);
        // Expected: 2 books containing the word "Audio"
        assertEquals(2, resultsCount);

        // Test case: Search for books containing the word "abc"
        query = "abc";
        resultsCount = library.searchBook(query);
        // Expected: 2 books containing the word "abc"
        assertEquals(2, resultsCount);

        // Test case: Search for books containing the word "book"
        query = "book";
        resultsCount = library.searchBook(query);
        // Expected: 6 books containing the word "book"
        assertEquals(6, resultsCount);

        // Test case: Search for books with the ISBN "1234567890"
        query = "1234567890";
        resultsCount = library.searchBook(query);
        // Expected: 1 book with the ISBN "1234567890"
        assertEquals(1, resultsCount);

        // Test case: Search for books with the genre "Horror"
        query = "Horror";
        resultsCount = library.searchBook(query);
        // Expected: 1 book with the genre "Horror"
        assertEquals(1, resultsCount);
    }

    /**
     * This test case checks if the validateBookDetails method correctly validates a title.
     * It provides a valid title as input and expects the method to return the same title.
     */
    @Test
    public void testValidateBookDetails_Title_Valid() {
        // Provide a valid title as input
        provideInput("Valid Title");

        // Create a Scanner to read the input
        Scanner sc = new Scanner(System.in);

        // Call the validateBookDetails method with the Scanner and the title parameter
        String validatedTitle = library.validateBookDetails(sc, "title");

        // Assert that the returned title is the same as the input
        assertEquals("Valid Title", validatedTitle);
    }

    /**
     * This test case checks if the validateBookDetails method correctly validates a title.
     * It provides an invalid title as input and expects the method to return a valid title.
     */
    @Test(timeout = 5000)
    public void testValidateBookDetails_Title_InvalidToValid() {
        // Provide an invalid title as input
        provideInput("<>Invalid Title\nValid Title\n");

        // Create a Scanner to read the input
        Scanner sc = new Scanner(System.in);

        // Call the validateBookDetails method with the Scanner and the title parameter
        String validatedTitle = library.validateBookDetails(sc, "title");

        // Assert that the returned title is the expected valid title
        assertEquals("Valid Title", validatedTitle);
    }

    /**
     * This test case checks if the validateBookDetails method correctly validates an author.
     * It provides a valid author as input and expects the method to return the same author.
     */
    @Test
    public void testValidateBookDetails_Author_Valid() {
        // Provide a valid author as input
        provideInput("Valid Author\n");

        // Create a Scanner to read the input
        Scanner sc = new Scanner(System.in);

        // Call the validateBookDetails method with the Scanner and the author parameter
        String validatedAuthor = library.validateBookDetails(sc, "author");

        // Assert that the returned author is the same as the input
        assertEquals("Valid Author", validatedAuthor);
    }

    /**
     * This test case checks if the validateBookDetails method correctly validates an author.
     * It provides an invalid author as input and expects the method to return a valid author.
     */
    @Test(timeout = 5000)
    public void testValidateBookDetails_Author_InvalidToValid() {
        // Provide an invalid author as input
        provideInput("123Author\nValid Author\n");

        // Create a Scanner to read the input
        Scanner sc = new Scanner(System.in);

        // Call the validateBookDetails method with the Scanner and the author parameter
        String validatedAuthor = library.validateBookDetails(sc, "author");

        // Assert that the returned author is the expected valid author
        assertEquals("Valid Author", validatedAuthor);
    }


    /**
     * Test case for validating a valid genre in the validateBookDetails method.
     * It provides a valid genre as input and expects the method to return the same genre.
     */
    @Test
    public void testValidateBookDetails_Genre_Valid() {
        // Provide a valid genre as input
        provideInput("Valid Genre\n");

        // Create a Scanner to read the input
        Scanner sc = new Scanner(System.in);

        // Call the validateBookDetails method with the Scanner and the genre parameter
        String validatedGenre = library.validateBookDetails(sc, "genre");

        // Assert that the returned genre is the same as the input
        assertEquals("Valid Genre", validatedGenre);
    }

    /**
     * Test case for validating a valid genre in the validateBookDetails method.
     * It provides an invalid genre as input and expects the method to return a valid genre.
     */
    @Test(timeout = 5000)
    public void testValidateBookDetails_Genre_InvalidToValid() {
        // Provide an invalid genre as input
        provideInput("123Genre--\nValid Genre\n");

        // Create a Scanner to read the input
        Scanner sc = new Scanner(System.in);

        // Call the validateBookDetails method with the Scanner and the genre parameter
        String validatedGenre = library.validateBookDetails(sc, "genre");

        // Assert that the returned genre is the expected valid genre
        assertEquals("Valid Genre", validatedGenre);
    }

    /**
     * Test case for validating a valid ISBN in the validateBookDetails method.
     * It provides a valid ISBN as input and expects the method to return the same ISBN.
     */
    @Test
    public void testValidateBookDetails_ISBN_Valid() {
        provideInput("1234567890\n");
        Scanner sc = new Scanner(System.in);

        String validatedISBN = library.validateBookDetails(sc, "ISBN");
        assertEquals("1234567890", validatedISBN);
    }

    /**
     * Test case for validating an invalid ISBN to a valid ISBN in the validateBookDetails method.
     * It provides an invalid ISBN as input and expects the method to return a valid ISBN.
     */
    @Test(timeout = 5000)
    public void testValidateBookDetails_ISBN_InvalidToValid() {
        provideInput("1234\n1234567890\n");
        Scanner sc = new Scanner(System.in);

        String validated = library.validateBookDetails(sc, "ISBN");
        assertEquals("1234567890", validated);
    }

    /**
     * Test case for validating a valid book type in the validateBookDetails method.
     * It provides a valid book type as input and expects the method to return the same type.
     */
    @Test
    public void testValidateBookDetails_Type_Valid() {
        provideInput("physical\n");
        Scanner sc = new Scanner(System.in);

        String validatedType = library.validateBookDetails(sc, "type");
        assertEquals("physical", validatedType);
    }

    /**
     * Test case for validating an invalid book type to a valid type in the validateBookDetails method.
     * It provides an invalid book type as input and expects the method to return a valid type.
     */
    @Test(timeout = 5000)
    public void testValidateBookDetails_Type_InvalidToValid() {
        provideInput("invalidType\nphysical\n");
        Scanner sc = new Scanner(System.in);

        String validated = library.validateBookDetails(sc, "type");
        assertEquals("physical", validated);
    }

    /**
     * Test case for validating a valid book length in the validateBookDetails method.
     * It provides a valid book length as input and expects the method to return the same length.
     */
    @Test
    public void testValidateBookDetails_Length_Valid() {
        provideInput("300\n");
        Scanner sc = new Scanner(System.in);

        String validatedLength = library.validateBookDetails(sc, "length");
        assertEquals("300", validatedLength);
    }

    /**
     * Test case for validating an invalid book length to a valid length in the validateBookDetails method.
     * It provides an invalid book length as input and expects the method to return a valid length.
     */
    @Test(timeout = 5000)
    public void testValidateBookDetails_Length_InvalidToValid() {
        provideInput("-1\n400\n");
        Scanner sc = new Scanner(System.in);

        String validated = library.validateBookDetails(sc, "length");
        assertEquals("400", validated);
    }

    /**
     * Provides the given input to the standard input stream.
     *
     * @param input the input to provide
     */
    private void provideInput(String input) {
        // Set the input stream to read from a string
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

}
