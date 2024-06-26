package com.stratpoint.projects;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Library {
    private List<Book> books;
    private int page;
    private int firstItemIdx = 0;
    public boolean isOpen;

    public Library() {
        this.books = new ArrayList<>();
        this.page = 1;
        this.AddDummyData();
        this.isOpen = true;
    }

    // Add a book to the library
    public void addBook(Scanner sc) {
        // todo: input handling loop if not valid
        System.out.print("Enter a title: ");
        String title = sc.nextLine();

        System.out.print("Enter an author: ");
        String author = sc.nextLine();

        System.out.print("Enter a genre: ");
        String genre = sc.nextLine();

        System.out.print("Enter an ISBN: ");
        String ISBN = sc.nextLine();

        System.out.print("Enter a type (physical/electronic/audio): ");
        String type = sc.nextLine();

        switch (type) {
            case "physical" -> {
                System.out.print("Enter number of pages: ");
                int pages = sc.nextInt();
                this.books.add(new PhysicalBook(title, author, genre, ISBN, pages));
            }
            case "electronic" -> {
                System.out.print("Enter number of pages: ");
                int pages = sc.nextInt();
                this.books.add(new ElectronicBook(title, author, genre, ISBN, pages));
            }
            case "audio" -> {
                System.out.print("Enter length of audiobook in minutes: ");
                double length = sc.nextDouble();
                this.books.add(new AudioBook(title, author, genre, ISBN, length));
            }
            default -> {
                System.out.println("\nSomething went wrong while trying to add a book to the library.");
                return;
            }
        }

        System.out.println("\n" + title + ": " + ISBN + " by " + author + " has been added to the library!");
    }

    // Remove a book from the library
    private void removeBook(Scanner sc) {
        try {
            System.out.print("Enter a book ID to remove: ");
            int ID = sc.nextInt();

            this.books.removeIf(book -> book.getID() == ID);
            // System.out.println(book.getTitle() + " has been removed from the library!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchBook(Scanner sc) {
        System.out.print("Enter search query: ");
        String query = sc.nextLine();
    }

    // Display the books in the current page of the library and the current page
    public void displayCurrentPage() {
        System.out.println("|   ID   ||   TITLE    ||   AUTHOR   ||   GENRE   ||   ISBN   ||   Page " + this.page + "   |");

        if (this.books.isEmpty()) {
            System.out.println("There are no books in the library!");
            return;
        }

        int lastItem = Math.min(this.page * 5, this.books.size());

        for (int i=this.firstItemIdx; i < lastItem; i++) {
            Book book = this.books.get(i);
            System.out.println(book.getID() + " | " + book.getTitle() + " | " + book.getAuthor() + " | "
                    + book.getGenre() + " | " + book.getISBN() + " | ");
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
                this.addBook(sc);
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
