package main;

import dao.*;
import entity.Author;
import entity.Book;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    LocalDate myDate = LocalDate.of(2023, 11, 26);
    Date date = Date.valueOf(myDate);

    static Scanner input = new Scanner(System.in);

    static BookInterface bookTable = Context.instanceBookDao();
    static AuthorInterface authorTable = Context.instanceAuthorDao();
    static BookDetailInterface bookDetailTable = Context.instanceBookDetailDao();
    static OrderInterface orderTable = Context.instanceOrderDao();
    static CustomerInterface customerTable = Context.instanceCustomerDao();
    static OrderDetailInterface orderDetailTable = Context.instanceOrderDetailDao();

    public static void main(String[] args) {
        userInterface();
    }

    public static void userInterface() {
        System.out.println("Welcome to JDBC Application");
        while (true) {
            System.out.println();
            System.out.println("In which table would you like to operate?");
            System.out.println("""
                    1.book table
                    2.author table
                    3.customer table
                    4.order table
                    5.book_detail table
                    6.order_detail table
                    7.Close application""");

            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    bookOperations();
                case 2:
                    authorOperations();
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    endSession();
            }
        }
    }

    public static void bookOperations() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("""
                1.Insert book
                2.Get all books
                3.Get book by book_id
                4.Update book
                5.Delete book
                6.Get whole book information""");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter book id: ");
                int book_id = input.nextInt();
                input.nextLine();
                System.out.print("Enter title: ");
                String title = input.nextLine();
                System.out.print("Enter genre: ");
                String genre = input.nextLine();
                System.out.print("Enter stock: ");
                int stock = input.nextInt();
                bookTable.addBook(new Book(book_id, title, genre, stock));
                bookOperations();
            case 2:
                bookTable.getAllBooks();
                bookOperations();
            case 3:
                System.out.print("Please enter the id of book: ");
                int book_id2 = input.nextInt();
                bookTable.getBookById(book_id2);
                bookOperations();
            case 4:
                System.out.print("Please enter the id of book you would like to update: ");
                int book_id3 = input.nextInt();
                input.nextLine();
                Book book = bookTable.getBookById(book_id3);
                if (book == null) {
                    bookOperations();
                }
                System.out.println("Please enter new values to attributes you would like to update." +
                        "Note: If you do not want to update any attribute just press enter and skip it.");
                System.out.print("Update title: ");
                String title2 = input.nextLine();
                if (title2.trim().length() < 1) {
                    title2 = book.getTitle();
                }
                System.out.print("Update genre: ");
                String genre2 = input.nextLine();
                if (genre2.trim().length() < 1) {
                    genre2 = book.getGenre();
                }
                System.out.print("Update stock: ");
                int stock2 = input.nextInt();
                book.setTitle(title2);
                book.setGenre(genre2);
                book.setStock(stock2);
                bookTable.updateBook(book);
                bookOperations();
            case 5:
                System.out.print("Please enter the id of book you would like to delete: ");
                int book_id4 = input.nextInt();
                bookTable.deleteBook(book_id4);
                bookOperations();
            case 6:
                bookTable.getWholeBookInformation();
                bookOperations();
        }
    }

    public static void authorOperations() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("""
                1.Insert author
                2.Get all authors
                3.Get author by author_id
                4.Update author
                5.Delete author
                """);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter author id: ");
                int author_id = input.nextInt();
                input.nextLine();
                System.out.print("Enter author_name: ");
                String author_name = input.nextLine();
                System.out.print("Enter author_surname: ");
                String author_surname = input.nextLine();
                authorTable.addAuthor(new Author(author_id, author_name, author_surname));
                authorOperations();
            case 2:
                authorTable.getAllAuthors();
                authorOperations();
            case 3:
                System.out.print("Please enter the id of author: ");
                int author_id2 = input.nextInt();
                authorTable.getAuthorById(author_id2);
                authorOperations();
            case 4:
                System.out.print("Please enter the id of author you would like to update: ");
                int author_id3 = input.nextInt();
                input.nextLine();
                Author author = authorTable.getAuthorById(author_id3);
                if (author == null) {
                    authorOperations();
                }
                System.out.println("Please enter new values to attributes you would like to update." +
                        "Note: If you do not want to update any attribute just press enter and skip it.");
                System.out.print("Update author_name: ");
                String author_name2 = input.nextLine();
                if (author_name2.trim().length() < 1) {
                    author_name2 = author.getAuthor_name();
                }
                System.out.print("Update author_surname: ");
                String author_surname2 = input.nextLine();
                if (author_surname2.trim().length() < 1) {
                    author_surname2 = author.getAuthor_surname();
                }
                author.setAuthor_name(author_name2);
                author.setAuthor_surname(author_surname2);
                authorTable.updateAuthor(author);
                authorOperations();
            case 5:
                System.out.print("Please enter the id of author you would like to delete: ");
                int author_id4 = input.nextInt();
                authorTable.deleteAuthor(author_id4);
                authorOperations();
        }
    }

    public static void endSession() {
        System.out.println("Please confirm to close application by typing 'Yes'. Otherwise type anything to stay in application");
        String confirmation = input.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            System.exit(0);
        } else {
            userInterface();
        }
    }
}
