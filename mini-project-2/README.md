# Mini Project 2: Library Management System

A library management system implemented in Java, utilizing Maven for project management. 
The program allows users to manage a library of books, including adding new books (Physical, Electronic, or Audio), 
removing books by their ID, and searching for books based on various criteria such as title, author, genre, and ISBN.

## Features

- **Add Books:** Add new books to the library. Books are categorized as Physical, Electronic, or Audio.
- **Remove Books:** Remove books from the library using their ID.
- **Search Books:** Search for books using a string query that matches against fields like title, author, genre, and ISBN.

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/kevvvvin/mini-projects-2.git
   ```
2. Navigate into the project repository:
  ```sh
  cd mini-projects-2
  ```
3. Build and Run the project using Maven:
  ```sh
  mvn clean install
  mvn exec:java
  ```
## How to Use
### Program Instructions 
```
  [1] Add a book which will ask you for the details of the book to add including: title, author, genre, ISBN, type of book, and pages/length in minutes.  
  [2] Remove a book which will ask you for the ID of the book to remove.  
  [3] Search for a book which will ask you for a search query and display a list of the results and return the number of results.  
  [d] Display the next page of the list of books  
  [a] Display the previous page of the list of books  
  [q] Exit the program
```
   
