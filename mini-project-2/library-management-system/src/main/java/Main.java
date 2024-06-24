// import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Dummy Data
        Book bookOne = new Book("Title 1", "Author 1", "ISBN1");
        Book bookTwo = new Book("Title 2", "Author 2", "ISBN2");
        Book bookThree = new Book("Title 3", "Author 3", "ISBN3");
        Book bookFour = new Book("Title 4", "Author 4", "ISBN4");
        Book bookFive = new Book("Title 5", "Author 5", "ISBN5");
        library.addBook(bookOne);
        library.addBook(bookTwo);
        library.addBook(bookThree);
        library.addBook(bookFour);
        library.addBook(bookFive);


        System.out.println("Test");


    }

    public void PrintInstructions() {
        System.out.println("[1] Add a book to the library");
        System.out.println("[2] Remove a book from the library");
        System.out.println("[3] Search for a book within the library");
    }
}
