package com.stratpoint.projects;

import com.stratpoint.projects.booktypes.AudioBook;
import com.stratpoint.projects.booktypes.Book;
import com.stratpoint.projects.booktypes.ElectronicBook;
import com.stratpoint.projects.booktypes.PhysicalBook;
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
    Logger logger = LoggerFactory.getLogger(Library.class);

    /**
     * List of books in the library.
     */
    private final List<Book> books;

    /**
     * Current page number.
     */
    private int page;

    /**
     * Index of the first item on the current page.
     */
    private int firstItemIdx = 0;

    /**
     * Flag indicating if the library is currently open.
     */
    public boolean isOpen;

    /**
     * Default constructor for the Library class.
     * Initializes the books list, sets the initial page number to 1,
     * adds dummy data to the books list, and sets the library to open.
     */
    public Library() {
        this.books = new ArrayList<>();
        this.page = 1;
        this.isOpen = true;
        this.addDummyData();
    }


    /**
     * Adds a new book to the library if it is not already present.
     *
     * @param  newBook  the book to be added to the library
     */
    private void addBook(Book newBook) {
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
     *
     * @param sc Scanner object to read user input.
     */
    private void removeBook(Scanner sc) {
        try {
            // Prompt user to enter the book ID to remove
            System.out.print("Enter a book ID to remove: ");

            // Read the book ID from user input
            int idx = sc.nextInt();

            Book book = this.books.get(idx-1);

            // Remove the book from the library at the specified index
            this.books.remove(idx-1);

            sc.nextLine();
            logger.info("{} - {} has been removed from the library", book.getTitle(), book.getISBN());
        }
        catch (InputMismatchException | IndexOutOfBoundsException e) {
            // Print an error message if an exception occurs during the removal process
            logger.error("An error occurred while removing a book: {}", e.toString());
            sc.nextLine();
        }
    }

    /**
     * This method allows the user to search for books in the library based on a search query.
     *
     * @param sc Scanner object to read user input.
     */
    private void searchBook(Scanner sc) {
        while (true) {
            // Prompt user to enter the search query
            System.out.print("Enter search query: ");
            String query = sc.nextLine();

            String gapLeft = "| ";
            String gapRight = " ";
            int foundBooks = 0;

            this.displayPageHeader();
            System.out.print("\n");

            for (Book book : this.books) {
                // Check if the book's title, author, genre, or ISBN contains the search query
                if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                        book.getGenre().toLowerCase().contains(query.toLowerCase()) ||
                        book.getISBN().equals(query)) {
                    // Display the book details if it matches the search query
                    System.out.print(gapLeft + book.getTitle() + gapRight);
                    System.out.print(gapLeft + book.getAuthor() + gapRight);
                    System.out.print(gapLeft + book.getGenre() + gapRight);
                    System.out.print(gapLeft + book.getISBN() + gapRight);

                    if (book instanceof AudioBook) {
                        AudioBook audioBook = (AudioBook) book;
                        System.out.print(gapLeft + audioBook.getType() + gapRight);
                        System.out.print(gapLeft + audioBook.getLength() + " minutes" + gapRight + "\n");
                    }
                    else if (book instanceof ElectronicBook) {
                        ElectronicBook electronicBook = (ElectronicBook) book;
                        System.out.print(gapLeft + electronicBook.getType() + gapRight);
                        System.out.print(gapLeft + electronicBook.getPages() + " pages" + gapRight + "\n");
                    }
                    else if (book instanceof PhysicalBook) {
                        PhysicalBook physicalBook = (PhysicalBook) book;
                        System.out.print(gapLeft + physicalBook.getType() + gapRight);
                        System.out.print(gapLeft + physicalBook.getPages() + " pages" + gapRight + "\n");
                    }

                    foundBooks++;
                }
            }
            logger.info("{} books found", foundBooks);

            // Ask the user if they want to continue searching
            System.out.print("\nContinue searching? (y / Y): ");
            String cont = sc.nextLine();

            // Break out of the loop if the user does not want to continue searching
            if (!cont.equalsIgnoreCase("y")) {
                logger.info("No longer searching for books");
                break;
            }
            logger.info("Searching for another book");
        }
    }

    /**
     * Displays the books in the current page of the library and the current page number.
     * The books are displayed in a formatted table with columns for ID, Title, Author, Genre, and ISBN.
     */
    public void displayCurrentPage() {
        // Define the spacing between columns
        String gapLeft = "| ";
        String gapRight = " ";

        // Print the header row for the table
        this.displayPageHeader();
        System.out.print(gapLeft + "Page " + this.page + gapRight + "\n");

        // Check if there are any books in the library
        if (this.books.isEmpty()) {
            logger.warn("There are no books in the library");
            return;
        }

        // Calculate the last book to display on the current page
        int lastItem = Math.min(this.page * 5, this.books.size());

        // Loop through the books on the current page and display them
        for (int i=this.firstItemIdx; i < lastItem; i++) {
            Book book = this.books.get(i);
            System.out.print(i+1 + gapRight);
            System.out.print(gapLeft + book.getTitle() + gapRight);
            System.out.print(gapLeft + book.getAuthor() + gapRight);
            System.out.print(gapLeft + book.getGenre() + gapRight);
            System.out.print(gapLeft + book.getISBN() + gapRight);

            if (book instanceof AudioBook) {
                AudioBook audioBook = (AudioBook) book;
                System.out.print(gapLeft + audioBook.getType() + gapRight);
                System.out.print(gapLeft + audioBook.getLength() + " minutes" + gapRight + "\n");
            }
            else if (book instanceof ElectronicBook) {
                ElectronicBook electronicBook = (ElectronicBook) book;
                System.out.print(gapLeft + electronicBook.getType() + gapRight);
                System.out.print(gapLeft + electronicBook.getPages() + " pages" + gapRight + "\n");
            }
            else if (book instanceof PhysicalBook) {
                PhysicalBook physicalBook = (PhysicalBook) book;
                System.out.print(gapLeft + physicalBook.getType() + gapRight);
                System.out.print(gapLeft + physicalBook.getPages() + " pages" + gapRight + "\n");
            }
        }
    }


    /**
     * Increments the page number and updates the index of the first item on the page if the current page is not the last page.
     * If the current page is already the last page, it logs a warning message.
     */
    private void nextPage() {
        // Check if the current page is the last page
        if (this.firstItemIdx + 5 >= this.books.size()) {
            // Log a warning message if the current page is already the last page
            logger.warn("Failed to change the page. Already at the last page");
        } else {
            // Increment the page number
            this.page += 1;

            // Update the index of the first item on the page
            this.firstItemIdx += 5;

            // Log the page change event
            this.logPageChange();
        }
    }


    /**
     * Decrements the page number and updates the index of the first item on the page if the current page is not the first page.
     * If the current page is already the first page, it prints a message and does nothing.
     */
    private void prevPage() {
        // Check if the current page is the first page
        if (this.page == 1) {
            // Log a warning message if the current page is already the first page
            logger.warn("Failed to change the page. Already at the first page");
            return;
        }

        // Decrement the page number
        this.page -= 1;

        // Update the index of the first item on the page
        this.firstItemIdx -= 5;

        this.logPageChange();
    }

    /**
     * Handles the user inputs for library commands.
     *
     * @param sc Scanner object to read user input
     */
    public void handleLibraryCommands(Scanner sc) {
        // Get user input
        String input = sc.nextLine();

        // Switch statement to handle different input cases
        switch (input) {
            case "1":
                // Add a new book to the library
                logger.info("Adding a new book to the library");
                Book book = this.getBookDetails(sc);
                this.addBook(book);
                break;
            case "2":
                // Remove a book from the library
                logger.info("Removing a book from the library");
                this.removeBook(sc);
                break;
            case "3":
                // Search for a book in the library
                logger.info("Searching for a book in the library");
                this.searchBook(sc);
                break;
            case "d", "D":
                // Go to the next page
                this.nextPage();
                break;
            case "a", "A":
                // Go to the previous page
                this.prevPage();
                break;
            case "q", "Q":
                // Exit the program
                logger.info("Exiting the program");
                this.isOpen = false;
                break;
        }
    }

    /**
     * This method prompts the user for book details and creates a new Book object based on the user's input.
     *
     * @param sc Scanner object to read user input.
     * @return A new Book object with the user's input details, or null if the user input is invalid.
     */
    private Book getBookDetails(Scanner sc) {
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
     * This method handles user input validation for book details.
     *
     * @param sc Scanner object to read user input.
     * @param inputType The type of input to validate.
     * @return The validated user input.
     * @throws IllegalArgumentException If the inputType is not recognized.
     */
    private String validateBookDetails(Scanner sc, String inputType) {
        // Define regex patterns for validating different input types
        String TITLE_REGEX = "^[\\p{L}0-9\\s',.:;&()-]+$";
        String AUTHOR_REGEX = "^[a-zA-Z][a-zA-Z\\s\\-.',]*$";
        String GENRE_REGEX = "^[a-zA-Z][a-zA-Z\\s\\-',]*$";
        String ISBN_REGEX = "^(?:\\d{9}[0-9X]|\\d{13})$";

        // Prompt user for input based on inputType
        System.out.print("Enter " + inputType + ": ");

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
     * This method displays the instructions for the library.
     * It prints a list of commands that the user can use to interact with the library.
     */
    public void displayInstructions() {
        // Print the instructions for adding a book to the library
        System.out.println("\n[1] Add a book to the library");

        // Print the instructions for removing a book from the library
        System.out.println("[2] Remove a book from the library");

        // Print the instructions for searching for a book within the library
        System.out.println("[3] Search for a book within the library");

        // Print the instructions for going to the next page of the library
        System.out.println("[d] Go to next page of library");

        // Print the instructions for going to the previous page of the library
        System.out.println("[a] Go to previous page of library");

        // Print the instructions for exiting the program
        System.out.println("[q] Exit program");
    }

    /**
     * This method prints the header row for the table.
     * It includes the column names for ID, Title, Author, Genre, ISBN, Type, and Pages or Length in Minutes.
     */
    private void displayPageHeader() {
        // Define the spacing between columns
        String gapLeft = "| ";
        String gapRight = " ";

        // Print the header row for the table
        System.out.print("\nID ");
        System.out.print(gapLeft + "TITLE" + gapRight);
        System.out.print(gapLeft + "AUTHOR" + gapRight);
        System.out.print(gapLeft + "GENRE" + gapRight);
        System.out.print(gapLeft + "ISBN" + gapRight);
        System.out.print(gapLeft + "TYPE" + gapRight);
        System.out.print(gapLeft + "PAGES OR LENGTH IN MINUTES" + gapRight);
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
     * Logs the page change event.
     * This method logs the page number to the logger when the user navigates to a new page.
     */
    private void logPageChange() {
        // Use the logger to log the page change event
        logger.info("Moved to page {}.", this.page);
    }
}
