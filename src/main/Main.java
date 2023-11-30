package main;

import dao.*;
import entity.Author;
import entity.Book;
import entity.Customer;

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
                    customerOperations();
                case 4:
                    orderOperations();
                case 5:
                    book_detailOperations();
                case 6:
                    order_detailOperations();
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
                System.out.println("Please enter new values to attributes you would like to update." + "Note: If you do not want to update any attribute just press enter and skip it.");
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
                System.out.println("Please enter new values to attributes you would like to update." + "Note: If you do not want to update any attribute just press enter and skip it.");
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

    public static void customerOperations() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("""
                1.Insert customer
                2.Get all customers
                3.Get customer by customer_id
                4.Update customer
                5.Delete customer
                """);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter customer id: ");
                int customer_id = input.nextInt();
                input.nextLine();
                System.out.print("Enter customer_name: ");
                String customer_name = input.nextLine();
                System.out.print("Enter customer_surname: ");
                String customer_surname = input.nextLine();
                System.out.print("Enter customer address: ");
                String address = input.nextLine();
                System.out.print("Enter customer email: ");
                String email = input.nextLine();
                customerTable.addCustomer(new Customer(customer_id, customer_name, customer_surname, address, email));
                customerOperations();
            case 2:
                customerTable.getAllCustomers();
                customerOperations();
            case 3:
                System.out.print("Please enter the id of customer: ");
                int customer_id2 = input.nextInt();
                customerTable.getCustomerById(customer_id2);
                customerOperations();
            case 4:
                System.out.print("Please enter the id of customer you would like to update: ");
                int customer_id3 = input.nextInt();
                input.nextLine();
                Customer customer = customerTable.getCustomerById(customer_id3);
                if (customer == null) {
                    customerOperations();
                }
                System.out.println("Please enter new values to attributes you would like to update." + "Note: If you do not want to update any attribute just press enter and skip it.");
                System.out.print("Update customer_name: ");
                String customer_name2 = input.nextLine();
                if (customer_name2.trim().length() < 1) {
                    customer_name2 = customer.getCustomer_name();
                }
                System.out.print("Update customer_surname: ");
                String customer_surname2 = input.nextLine();
                if (customer_surname2.trim().length() < 1) {
                    customer_surname2 = customer.getCustomer_surname();
                }
                System.out.print("Update customer address: ");
                String address2 = input.nextLine();
                if (address2.trim().length() < 1) {
                    address2 = customer.getAddress();
                }
                System.out.print("Update customer email: ");
                String email2 = input.nextLine();
                if (email2.trim().length() < 1) {
                    email2 = customer.getEmail();
                }
                customer.setCustomer_name(customer_name2);
                customer.setCustomer_surname(customer_surname2);
                customer.setAddress(address2);
                customer.setEmail(email2);
                customerTable.updateCustomer(customer);
                customerOperations();
            case 5:
                System.out.print("Please enter the id of customer you would like to delete: ");
                int customer_id4 = input.nextInt();
                customerTable.deleteCustomer(customer_id4);
                customerOperations();
        }
    }

    public static void orderOperations() {

    }

    public static void book_detailOperations() {

    }

    public static void order_detailOperations() {

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
