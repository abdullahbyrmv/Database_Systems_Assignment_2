package main;

import dao.*;
import entity.*;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static BookInterface bookTable = Context.instanceBookDao();
    static AuthorInterface authorTable = Context.instanceAuthorDao();
    static BookDetailInterface bookDetailTable = Context.instanceBookDetailDao();
    static OrderInterface orderTable = Context.instanceOrderDao();
    static CustomerInterface customerTable = Context.instanceCustomerDao();
    static OrderDetailInterface orderDetailTable = Context.instanceOrderDetailDao();

    public static void main(String[] args) {
        System.out.println("Welcome to JDBC Application");
        userInterface();
    }

    public static void userInterface() {

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
                6.Get whole book information
                7.Join book with authors
                8.Go to main menu""");
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
            case 7:
                bookTable.getAuthorInformation();
            case 8:
                userInterface();
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
                6.Go to main menu
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
            case 6:
                userInterface();
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
                6.Go to main menu
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
            case 6:
                userInterface();
        }
    }

    public static void orderOperations() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("""
                1.Insert order
                2.Get all orders
                3.Get order by order_id
                4.Update order
                5.Delete order
                6.Go to main menu
                """);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter order_id: ");
                int order_id = input.nextInt();
                input.nextLine();
                System.out.print("Enter customer_id: ");
                int customer_id = input.nextInt();
                input.nextLine();
                System.out.print("Enter order_date: ");
                String order_date = input.nextLine();
                orderTable.addOrder(new Order(order_id, customer_id, Date.valueOf(order_date)));
                orderOperations();
            case 2:
                orderTable.getAllOrders();
                orderOperations();
            case 3:
                System.out.print("Please enter the id of order: ");
                int order_id2 = input.nextInt();
                orderTable.getOrderById(order_id2);
                orderOperations();
            case 4:
                System.out.print("Please enter the id of order you would like to update: ");
                int order_id3 = input.nextInt();
                input.nextLine();
                Order order = orderTable.getOrderById(order_id3);
                if (order == null) {
                    orderOperations();
                }
                System.out.println("Please enter new values to attributes you would like to update." + "Note: If you do not want to update any attribute just press enter and skip it.");
                System.out.print("Update customer_id: ");
                int customer_id2 = input.nextInt();
                input.nextLine();
                System.out.print("Update order_date: ");
                String order_date2 = input.nextLine();
                if (order_date2.trim().length() < 1) {
                    order_date2 = String.valueOf(order.getOrder_date());
                }
                order.setCustomer_id(customer_id2);
                order.setOrder_date(Date.valueOf(order_date2));
                orderTable.updateOrder(order);
                orderOperations();
            case 5:
                System.out.print("Please enter the id of order you would like to delete: ");
                int order_id4 = input.nextInt();
                orderTable.deleteOrder(order_id4);
                orderOperations();
            case 6:
                userInterface();
        }
    }

    public static void book_detailOperations() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("""
                1.Insert book detail
                2.Get all book details
                3.Get book detail by book_id
                4.Update book detail
                5.Delete book detail
                6.Go to main menu
                """);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter book_id: ");
                int book_id = input.nextInt();
                System.out.print("Enter author_id: ");
                int author_id = input.nextInt();
                bookDetailTable.addBookDetail(new BookDetail(book_id, author_id));
                book_detailOperations();
            case 2:
                bookDetailTable.getAllBookDetails();
                book_detailOperations();
            case 3:
                System.out.print("Please enter the id of book: ");
                int book_id2 = input.nextInt();
                bookDetailTable.getBookDetailById(book_id2);
                book_detailOperations();
            case 4:
                System.out.print("Please enter the id of book you would like to update: ");
                int book_id3 = input.nextInt();
                input.nextLine();
                BookDetail bookDetail = bookDetailTable.getBookDetailById(book_id3);
                if (bookDetail == null) {
                    book_detailOperations();
                }
                System.out.println("Please enter new values to attributes you would like to update." +
                        "Note: If you do not want to update any attribute just press enter and skip it.");
                System.out.print("Update author_id: ");
                int author_id2 = input.nextInt();
                bookDetail.setAuthor_id(author_id2);
                bookDetailTable.updateBookDetail(bookDetail);
                book_detailOperations();
            case 5:
                System.out.print("Please enter the id of book you would like to delete: ");
                int book_id4 = input.nextInt();
                System.out.print("Please enter the id of author you would like to delete: ");
                int author_id3 = input.nextInt();
                bookDetailTable.deleteBookDetail(book_id4, author_id3);
                book_detailOperations();
            case 6:
                userInterface();
        }
    }

    public static void order_detailOperations() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("""
                1.Insert order detail
                2.Get all order details
                3.Get order detail by order_id
                4.Update order detail
                5.Delete order detail
                6.Go to main menu
                """);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter order_id: ");
                int order_id = input.nextInt();
                System.out.print("Enter book_id: ");
                int book_id = input.nextInt();
                System.out.print("Enter the number of ordered books: ");
                int number_of_ordered_books = input.nextInt();
                orderDetailTable.addOrderDetail(new OrderDetail(order_id, book_id, number_of_ordered_books));
                order_detailOperations();
            case 2:
                orderDetailTable.getAllOrderDetails();
                order_detailOperations();
            case 3:
                System.out.print("Please enter the id of order: ");
                int order_id2 = input.nextInt();
                orderDetailTable.getOrderDetailById(order_id2);
                order_detailOperations();
            case 4:
                System.out.print("Please enter the id of order you would like to update: ");
                int order_id3 = input.nextInt();
                OrderDetail orderDetail = orderDetailTable.getOrderDetailById(order_id3);
                if (orderDetail == null) {
                    order_detailOperations();
                }
                System.out.println("Please enter new values to attributes you would like to update." +
                        "Note: If you do not want to update any attribute just press enter and skip it.");
                System.out.print("Update book_id: ");
                int book_id2 = input.nextInt();
                System.out.print("Update number of ordered books: ");
                int number_of_ordered_books2 = input.nextInt();
                orderDetail.setBook_id(book_id2);
                orderDetail.setNumber_of_ordered_books(number_of_ordered_books2);
                orderDetailTable.updateOrderDetail(orderDetail);
                order_detailOperations();
            case 5:
                System.out.print("Please enter the id of order you would like to delete: ");
                int order_id4 = input.nextInt();
                System.out.print("Please enter the id of book you would like to delete: ");
                int book_id3 = input.nextInt();
                orderDetailTable.deleteOrderDetail(order_id4, book_id3);
                order_detailOperations();
            case 6:
                userInterface();
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
