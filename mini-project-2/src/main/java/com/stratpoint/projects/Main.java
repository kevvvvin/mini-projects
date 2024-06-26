package com.stratpoint.projects;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        System.out.println("==================== LIBRARY ====================");
        library.displayInstructions();

        Scanner sc = new Scanner(System.in);
        while (library.isOpen) {
            library.displayCurrentPage();
            System.out.print("\nEnter a command: ");
            library.handleLibraryCommands(sc);
        }

        sc.close();
    }
}
