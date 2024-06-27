package com.stratpoint.projects.library.library_management;

import com.stratpoint.projects.library.book_types.AudioBook;
import com.stratpoint.projects.library.book_types.Book;
import com.stratpoint.projects.library.book_types.ElectronicBook;
import com.stratpoint.projects.library.book_types.PhysicalBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The Library class represents a collection of books. It provides methods to manage
 * the books in the library, such as adding, removing, and searching for books.
 */
public class Library {
    /**
     * Logger instance for logging purposes.
     */
    private final Logger logger = LoggerFactory.getLogger(Library.class);

    /**
     * List of books in the library.
     */
    private final List<Book> books;

    /**
     * Flag indicating if the library is currently open.
     */
    public boolean isOpen;

    /**
     * Default constructor for the Library class.
     * Initializes the books list, and sets the library to open,
     * adds dummy data to the books list.
     */
    public Library() {
        this.books = new ArrayList<>();
        this.isOpen = true;
        this.addDummyData();
    }

    /**
     * Retrieves the list of books in the library.
     *
     * @return The list of books in the library.
     */
    public List<Book> getBooks() {
        return this.books;
    }

    /**
     * Adds a new book to the library if it is not already present.
     *
     * @param  newBook  the book to be added to the library
     */
    public void addBook(Book newBook) {
        if (newBook != null) {
            // Check if book is in the library
            for (Book book: this.books) {
                if (book.getISBN().equals(newBook.getISBN())) {
                    logger.warn("This book is already in the library");
                    return;
                }
            }

            this.books.add(newBook);
            logger.info("{} - {} by {} has been added to the library",
                    newBook.getTitle(), newBook.getISBN(), newBook.getAuthor());
        }
        else {
            logger.warn("An error occurred while adding a new book");
        }
    }

    /**
     * This method removes a book from the library based on the provided book ID.
     */
    public void removeBook(int id) {
        try {
            Book book = this.books.get(id-1);

            // Remove the book from the library at the specified index
            this.books.remove(id-1);

            logger.info("{} - {} has been removed from the library", book.getTitle(), book.getISBN());
        }
        catch (IndexOutOfBoundsException e) {
            // Print an error message if an exception occurs during the removal process
            logger.error("An error occurred while removing a book: {}", e.toString());
        }
    }

    /**
     * Searches for books in the library based on the provided query.
     * It checks if the book's title, author, genre, or ISBN contains the search query.
     * Displays the details of the books that match the search query.
     *
     * @param query The search query to look for in the books.
     * @return The number of books found that match the search query.
     */
    public int searchBook(String query) {
        // Log the search query
        logger.info("Searching from query: {}", query);

        // Initialize variables for formatting
        String gapLeft = "| ";
        String gapRight = " ";
        int foundBooks = 0;

        // Display search results
        System.out.println("\nSEARCH RESULTS:");

        // Iterate through the books in the library
        for (Book book : this.books) {
            // Check if the book matches the search query
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                    book.getGenre().toLowerCase().contains(query.toLowerCase()) ||
                    book.getISBN().equals(query)) {
                // Display book details
                System.out.print(gapLeft + book.getTitle() + gapRight);
                System.out.print(gapLeft + book.getAuthor() + gapRight);
                System.out.print(gapLeft + book.getGenre() + gapRight);
                System.out.print(gapLeft + book.getISBN() + gapRight);
                System.out.print(gapLeft + book.getType() + gapRight);

                // Format the length of the book
                String length = book.getPagesOrLength() + (book.getType().equalsIgnoreCase("Audio") ? " minutes" : " pages");
                System.out.print(gapLeft + length + gapRight + "\n");

                // Increment the count of found books
                foundBooks++;
            }
        }

        // Log the number of books found
        logger.info("{} books found", foundBooks);

        return foundBooks;
    }

    /**
     * This method prompts the user for book details and creates a new Book object based on the user's input.
     *
     * @param sc Scanner object to read user input.
     * @return A new Book object with the user's input details, or null if the user input is invalid.
     */
    public Book getBookDetailsFromUser(Scanner sc) {
        logger.info("Adding a book: getting book details from user");
        // Prompt the user for book details and validate the input
        String title = this.validateBookDetails(sc, "title");
        String author = this.validateBookDetails(sc, "author");
        String genre = this.validateBookDetails(sc, "genre");
        String ISBN = this.validateBookDetails(sc, "ISBN");
        String type = this.validateBookDetails(sc, "type");
        int length = Integer.parseInt(this.validateBookDetails(sc, "length"));
        logger.info("Obtained valid book details from user inputs");

        // Create a new Book object based on the user's input
        Book book = null;
        if (type.equalsIgnoreCase("physical")) {
            book = new PhysicalBook(title, author, genre, ISBN, length);
            logger.info("Creating new PhysicalBook from user inputs");
        }
        else if (type.equalsIgnoreCase("electronic")) {
            book = new ElectronicBook(title, author, genre, ISBN, length);
            logger.info("Creating new ElectronicBook from user inputs");
        }
        else if (type.equalsIgnoreCase("audio")) {
            book = new AudioBook(title, author, genre, ISBN, length);
            logger.info("Creating new AudioBook from user inputs");
        }

        // Return the new Book object or null if the user input is invalid
        if (book == null) {
            logger.warn("Failed to create a book object, returning null");
        }
        return book;
    }

    /**
     * This method prompts the user to input a book ID for removal.
     *
     * @param sc The Scanner object used to read user input.
     * @return The book ID entered by the user or -1 if an error occurs.
     */
    public int getBookIdFromUser(Scanner sc) {
        // Log information about the process of removing a book
        logger.info("Removing a book: getting book ID to remove from user");

        // Prompt the user to enter a book ID to remove
        System.out.print("Enter a book ID to remove: ");

        try {
            // Attempt to read an integer from user input
            int id = sc.nextInt();
            sc.nextLine(); // Consume the newline character after reading the integer
            return id;
        } catch (InputMismatchException e) {
            // Handle the case where a non-integer input is provided
            sc.nextLine(); // Clear the input buffer
            logger.error("Book ID must be a valid integer: {}", e.toString());
        }

        return -1; // Return -1 if an error occurs during the input process
    }

    /**
     * This method prompts the user to enter a search query for books.
     *
     * @param sc The Scanner object used to read user input.
     * @return The search query entered by the user.
     */
    public String getSearchQueryFromUser(Scanner sc) {
        // Log information about the process of searching for a book
        logger.info("Searching for a book: getting query from user");

        // Prompt the user to enter a search query
        System.out.print("Enter search query: ");

        // Read the search query from the user
        return sc.nextLine();
    }

    /**
     * This method handles user input validation for book details.
     *
     * @param sc Scanner object to read user input.
     * @param inputType The type of input to validate.
     * @return The validated user input.
     * @throws IllegalArgumentException If the inputType is not recognized.
     */
    protected String validateBookDetails(Scanner sc, String inputType) {
        // Define regex patterns for validating different input types
        String TITLE_REGEX = "^[\\p{L}0-9\\s',.:;&()-]+$";
        String AUTHOR_REGEX = "^[a-zA-Z][a-zA-Z\\s\\-.',]*$";
        String GENRE_REGEX = "^[a-zA-Z][a-zA-Z\\s\\-',]*$";
        String ISBN_REGEX = "^(?:\\d{9}[0-9X]|\\d{13})$";

        // Prompt user for input based on inputType
        System.out.print("Enter " + inputType);
        if (inputType.equalsIgnoreCase("type"))
            System.out.print(" (physical, electronic, audio)");

        System.out.print(": ");

        while (true) {
            String input = sc.nextLine();

            // Validate title input
            if (inputType.equalsIgnoreCase("title")) {
                if (Pattern.matches(TITLE_REGEX, input)) {
                    logger.info("User entered valid title input: {}", input);
                    return input;
                } else {
                    logger.warn("User entered invalid title input: {}", input);
                }
            }

            // Validate author input
            if (inputType.equalsIgnoreCase("author")) {
                if (Pattern.matches(AUTHOR_REGEX, input)) {
                    logger.info("User entered valid author input: {}", input);
                    return input;
                } else {
                    logger.warn("User entered invalid author input: {}", input);
                }
            }

            // Validate genre input
            if (inputType.equalsIgnoreCase("genre")) {
                if (Pattern.matches(GENRE_REGEX, input)) {
                    logger.info("User entered valid genre input: {}", input);
                    return input;
                } else {
                    logger.warn("User entered invalid genre input: {}", input);
                }
            }

            // Validate ISBN input
            if (inputType.equalsIgnoreCase("ISBN")) {
                if (Pattern.matches(ISBN_REGEX, input)) {
                    logger.info("User entered valid ISBN input: {}", input);
                    return input;
                } else {
                    logger.warn("User entered invalid ISBN input: {}", input);
                }
            }

            // Validate type input
            if (inputType.equalsIgnoreCase("type")) {
                if (input.equalsIgnoreCase("physical") ||
                        input.equalsIgnoreCase("electronic") ||
                        input.equalsIgnoreCase("audio")) {
                    logger.info("User entered valid type input: {}", input);
                    return input;
                } else {
                    logger.warn("User entered invalid type input: {}", input);
                }
            }

            // Validate length input
            if (inputType.equalsIgnoreCase("length")) {
                try {
                    int length = Integer.parseInt(input);
                    if (length > 0) {
                        logger.info("User entered valid length input: {}", input);
                        return input;
                    }
                } catch (Exception e) {
                    logger.error("User entered invalid length input. Could not parse to int: {}", input);
                }
            }

            // Prompt user for valid input
            System.out.print("Invalid " + inputType + "! Try again: ");
        }
    }

    /**
     * Adds dummy data to the library.
     * This method adds a set of predefined audio, physical, and electronic books to the library.
     */
    private void addDummyData() {
        // Dummy Data for Audio Books
        this.books.add(new AudioBook("Becoming", "Michelle Obama", "Memoir", "9781524763138", 1140));
        this.books.add(new AudioBook("The Hobbit", "J.R.R. Tolkien", "Fantasy", "9780261102217", 680));
        this.books.add(new AudioBook("Educated", "Tara Westover", "Memoir", "9780399590504", 690));
        this.books.add(new AudioBook("Where the Crawdads Sing", "Delia Owens", "Fiction", "9780735219090", 760));

        // Dummy Data for Physical Books
        this.books.add(new PhysicalBook("To Kill a Mockingbird", "Harper Lee", "Fiction", "9780060935467", 336));
        this.books.add(new PhysicalBook("1984", "George Orwell", "Dystopian", "9780451524935", 328));
        this.books.add(new PhysicalBook("Pride and Prejudice", "Jane Austen", "Romance", "9781503290563", 279));
        this.books.add(new PhysicalBook("The Catcher in the Rye", "J.D. Salinger", "Fiction", "9780316769488", 277));
        this.books.add(new PhysicalBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", "9780743273565", 180));

        // Dummy Data for Electronic Books
        this.books.add(new ElectronicBook("The Subtle Art of Not Giving a F*ck", "Mark Manson", "Self-help", "9780062457714", 224));
        this.books.add(new ElectronicBook("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "History", "9780062316110", 498));
        this.books.add(new ElectronicBook("The Handmaid's Tale", "Margaret Atwood", "Dystopian", "9780385490818", 311));
        this.books.add(new ElectronicBook("The Road", "Cormac McCarthy", "Post-apocalyptic", "9780307267450", 287));
        this.books.add(new ElectronicBook("A Game of Thrones", "George R.R. Martin", "Fantasy", "9780553386790", 835));
        this.books.add(new ElectronicBook("Life of Pi", "Yann Martel", "Adventure", "9780156027328", 319));
    }

    /**
     * Toggles the state of the library between open and closed.
     * If the library is currently open, it will be closed.
     */
    public void toggle() {
        logger.info("Exiting the program");
        this.isOpen = !this.isOpen;
    }


    /**
     * This method checks if a given book is present in the library's collection of books.
     *
     * @param book The book to check for.
     * @return True if the book is present in the library, false otherwise.
     */
    public boolean containsBook(Book book) {
        // Check if the given book is present in the library's collection of books.
        return this.books.contains(book);
    }
}
