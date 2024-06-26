package com.stratpoint.projects;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Library {
    private final List<Book> books;
    private int page;
    private int firstItemIdx = 0;
    public boolean isOpen;

    public Library() {
        this.books = new ArrayList<>();
        this.page = 1;
        this.AddDummyData();
        this.isOpen = true;
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
                    System.out.println("\nThis book is already in the library!");
                    return;
                }
            }

            this.books.add(newBook);
            System.out.println("\n" + newBook.getTitle() + " - " + newBook.getISBN() +
                " by " + newBook.getAuthor() + " has been added to the library!");
        }
        else {
            System.out.println("An error occurred while adding a new book!");
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
            sc.nextLine();

            Book book = this.books.get(idx-1);

            // Remove the book from the library at the specified index
            this.books.remove(idx-1);

            // Uncomment the following line if you want to print a message upon successful removal
            System.out.println("\n" + book.getTitle() + " has been removed from the library!");
        } catch (Exception e) {
            // Print an error message if an exception occurs during the removal process
            System.out.println("An error occurred while removing a book!");
        }
    }

    private void searchBook(Scanner sc) {
        while (true) {
            System.out.print("Enter search query: ");
            String query = sc.nextLine();

            String gapLeft = "| ";
            String gapRight = " ";

            for (Book book : this.books) {
                if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                    book.getGenre().toLowerCase().contains(query.toLowerCase()) ||
                    book.getISBN().equals(query)) {
                    System.out.print(gapLeft + book.getTitle() + gapRight);
                    System.out.print(gapLeft + book.getAuthor() + gapRight);
                    System.out.print(gapLeft + book.getGenre() + gapRight);
                    System.out.print(gapLeft + book.getISBN() + gapRight + "\n");
                }
            }

            System.out.print("\nContinue searching? (y / Y): ");
            String cont = sc.nextLine();

            if (!cont.equalsIgnoreCase("y"))
                break;
        }
    }

    // Display the books in the current page of the library and the current page
    public void displayCurrentPage() {
        // TODO: fix formatting
        String gapLeft = "| ";
        String gapRight = " ";

        System.out.print("\nID ");
        System.out.print(gapLeft + "TITLE" + gapRight);
        System.out.print(gapLeft + "AUTHOR" + gapRight);
        System.out.print(gapLeft + "GENRE" + gapRight);
        System.out.print(gapLeft + "ISBN" + gapRight);
        System.out.print(gapLeft + "Page " + this.page + gapRight + "\n");


        if (this.books.isEmpty()) {
            System.out.println("There are no books in the library!");
            return;
        }

        int lastItem = Math.min(this.page * 5, this.books.size());

        for (int i=this.firstItemIdx; i < lastItem; i++) {
            Book book = this.books.get(i);
            System.out.print(i+1 + gapRight);
            System.out.print(gapLeft + book.getTitle() + gapRight);
            System.out.print(gapLeft + book.getAuthor() + gapRight);
            System.out.print(gapLeft + book.getGenre() + gapRight);
            System.out.print(gapLeft + book.getISBN() + gapRight + "\n");
        }
    }

    // Display next page of books if possible
    private void nextPage() {
        if (this.firstItemIdx + 5 >= this.books.size() ) {
            System.out.println("\nYou are already at the last page!");
            return;
        }
        this.page += 1;
        this.firstItemIdx += 5;
    }


    // Display previous page of books if possible
    private void prevPage() {
        if (this.page == 1) {
            System.out.println("\nYou are already at the first page!");
            return;
        }
        this.page -= 1;
        this.firstItemIdx -= 5;
    }


    // Handle user inputs
    public void handleLibraryCommands(Scanner sc) {
        String input = sc.nextLine();

        switch (input) {
            case "1":
                Book book = this.getBookDetails(sc);
                this.addBook(book);
                break;
            case "2":
                this.removeBook(sc);
                break;
            case "3":
                this.searchBook(sc);
                break;
            case "d", "D":
                this.nextPage();
                break;
            case "a", "A":
                this.prevPage();
                break;
            case "q", "Q":
                this.isOpen = false;
                break;
        }
    }

    private Book getBookDetails(Scanner sc) {
        String title = this.validateBookDetails(sc, "title");
        String author = this.validateBookDetails(sc, "author");
        String genre = this.validateBookDetails(sc, "genre");
        String ISBN = this.validateBookDetails(sc, "ISBN");
        String type = this.validateBookDetails(sc, "type");
        int length = Integer.parseInt(this.validateBookDetails(sc, "length"));

        if (type.equalsIgnoreCase("physical")) {
            return new PhysicalBook(title, author, genre, ISBN, length);
        }
        else if (type.equalsIgnoreCase("electronic")) {
            return new ElectronicBook(title, author, genre, ISBN, length);
        }
        else if (type.equalsIgnoreCase("audio")) {
            return new AudioBook(title, author, genre, ISBN, length);
        }

        return null;
    }

    // Handle user input validation
    private String validateBookDetails(Scanner sc, String inputType) {
        String TITLE_REGEX = "^[\\w\\s\\-',:.!?()]+$";
        String AUTHOR_REGEX = "^[a-zA-Z\\s.\\-']+$";
        String GENRE_REGEX = "^[a-zA-Z\\s.\\-']+$";
        String ISBN_REGEX = "^(?:\\d{9}[0-9X]|\\d{13})$";

        if (inputType.equalsIgnoreCase("type"))
            System.out.print("Enter a book type (physical, electronic, audio): ");
        else if (inputType.equalsIgnoreCase("length"))
            System.out.print("Enter pages or length of book: ");
        else
            System.out.print("Enter " + inputType + ": ");


        while (true) {
            String input = sc.nextLine();

            // Validate title input
            if (inputType.equalsIgnoreCase("title")) {
                if (Pattern.matches(TITLE_REGEX, input)) {
                    return input;
                }
            }
            // Validate author input
            if (inputType.equalsIgnoreCase("author")) {
                if (Pattern.matches(AUTHOR_REGEX, input)) {
                    return input;
                }
            }
            // Validate genre input
            if (inputType.equalsIgnoreCase("genre")) {
                if (Pattern.matches(GENRE_REGEX, input)) { return input; }
            }
            // Validate genre input
            if (inputType.equalsIgnoreCase("ISBN")) {
                if (Pattern.matches(ISBN_REGEX, input)) { return input; }
            }

            if (inputType.equalsIgnoreCase("type")) {
                if (input.equalsIgnoreCase("physical") ||
                    input.equalsIgnoreCase("electronic") ||
                    input.equalsIgnoreCase("audio")) {
                    return input;
                }
            }

            if (inputType.equalsIgnoreCase("length")) {
                try {
                    int length = Integer.parseInt(input);
                    if (length > 0)
                        return input;
                } catch (Exception e) {
                    // todo:  do stuff
                }
            }

            System.out.print("Invalid " + inputType + "! Try again: ");
        }
    }

    public void displayInstructions() {
        System.out.println("\n[1] Add a book to the library");
        System.out.println("[2] Remove a book from the library");
        System.out.println("[3] Search for a book within the library");
        System.out.println("[d] Go to next page of library");
        System.out.println("[a] Go to previous page of library");
        System.out.println("[q] Exit program");
    }

    // Test data
    private void AddDummyData() {
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
}
