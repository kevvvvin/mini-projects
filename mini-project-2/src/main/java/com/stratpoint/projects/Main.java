package com.stratpoint.projects;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        AddDummyData(library);
        for (Book book : library.getAvailableBooks()) {
            System.out.println(book.getTitle());
        }


    }

    public void PrintInstructions() {
        System.out.println("[1] Add a book to the library");
        System.out.println("[2] Remove a book from the library");
        System.out.println("[3] Search for a book within the library");
    }

    public static void AddDummyData(Library lib) {
        // Dummy Data for Audio Books
        lib.addBook(new AudioBook("Becoming", "Michelle Obama", "Memoir", "9781524763138", 1140));
        lib.addBook(new AudioBook("The Hobbit", "J.R.R. Tolkien", "Fantasy", "9780261102217", 680));
        lib.addBook(new AudioBook("Educated", "Tara Westover", "Memoir", "9780399590504", 690));
        lib.addBook(new AudioBook("Where the Crawdads Sing", "Delia Owens", "Fiction", "9780735219090", 760));
        lib.addBook(new AudioBook("The Silent Patient", "Alex Michaelides", "Thriller", "9781250301697", 530));
        lib.addBook(new AudioBook("Atomic Habits", "James Clear", "Self-help", "9780735211292", 320));
        lib.addBook(new AudioBook("Becoming Supernatural", "Dr. Joe Dispenza", "Self-help", "9781401953119", 620));
        lib.addBook(new AudioBook("The Night Circus", "Erin Morgenstern", "Fantasy", "9780385534635", 780));
        lib.addBook(new AudioBook("Born a Crime", "Trevor Noah", "Memoir", "9780399588198", 660));
        lib.addBook(new AudioBook("Dune", "Frank Herbert", "Science Fiction", "9780441013593", 1260));

        // Dummy Data for Physical Books
        lib.addBook(new PhysicalBook("To Kill a Mockingbird", "Harper Lee", "Fiction", "9780060935467", 336));
        lib.addBook(new PhysicalBook("1984", "George Orwell", "Dystopian", "9780451524935", 328));
        lib.addBook(new PhysicalBook("Pride and Prejudice", "Jane Austen", "Romance", "9781503290563", 279));
        lib.addBook(new PhysicalBook("The Catcher in the Rye", "J.D. Salinger", "Fiction", "9780316769488", 277));
        lib.addBook(new PhysicalBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", "9780743273565", 180));
        lib.addBook(new PhysicalBook("Moby Dick", "Herman Melville", "Adventure", "9781503280786", 720));
        lib.addBook(new PhysicalBook("War and Peace", "Leo Tolstoy", "Historical", "9780199232765", 1296));
        lib.addBook(new PhysicalBook("Crime and Punishment", "Fyodor Dostoevsky", "Psychological", "9780143107637", 720));
        lib.addBook(new PhysicalBook("The Odyssey", "Homer", "Epic", "9780140268867", 541));
        lib.addBook(new PhysicalBook("Les Misérables", "Victor Hugo", "Historical", "9780451419439", 1488));

        // Dummy Data for Electronic Books
        lib.addBook(new ElectronicBook("The Subtle Art of Not Giving a F*ck", "Mark Manson", "Self-help", "9780062457714", 224));
        lib.addBook(new ElectronicBook("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "History", "9780062316110", 498));
        lib.addBook(new ElectronicBook("The Handmaid's Tale", "Margaret Atwood", "Dystopian", "9780385490818", 311));
        lib.addBook(new ElectronicBook("The Road", "Cormac McCarthy", "Post-apocalyptic", "9780307267450", 287));
        lib.addBook(new ElectronicBook("A Game of Thrones", "George R.R. Martin", "Fantasy", "9780553386790", 835));
        lib.addBook(new ElectronicBook("Life of Pi", "Yann Martel", "Adventure", "9780156027328", 319));
        lib.addBook(new ElectronicBook("The Girl on the Train", "Paula Hawkins", "Thriller", "9781594634024", 336));
        lib.addBook(new ElectronicBook("The Goldfinch", "Donna Tartt", "Fiction", "9780316055439", 784));
        lib.addBook(new ElectronicBook("Gone Girl", "Gillian Flynn", "Thriller", "9780307588371", 432));
        lib.addBook(new ElectronicBook("The Martian", "Andy Weir", "Science Fiction", "9780804139021", 369));
    }
}
