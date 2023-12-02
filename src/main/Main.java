package main;

import dao.*;
import entity.*;
import metaData.MetaDataMethods;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static BookInterface bookTable = Context.instanceBookDao();
    static AuthorInterface authorTable = Context.instanceAuthorDao();
    static BookDetailInterface bookDetailTable = Context.instanceBookDetailDao();
    static OrderInterface orderTable = Context.instanceOrderDao();
    static CustomerInterface customerTable = Context.instanceCustomerDao();
    static OrderDetailInterface orderDetailTable = Context.instanceOrderDetailDao();

    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to JDBC Application");
        userInterface();
    }

    public static void userInterface() throws SQLException {

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
                    7.Show table names and columns
                    8.Show column details
                    9.Show primary keys
                    10.Show foreign keys
                    11.Close application""");
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
                    MetaDataMethods.displayTableNamesandColumns();
                    userInterface();
                case 8:
                    MetaDataMethods.displayColumnDetails();
                    userInterface();
                case 9:
                    MetaDataMethods.displayPrimaryKeys();
                    userInterface();
                case 10:
                    MetaDataMethods.displayForeignKeys();
                    userInterface();
                case 11:
                    endSession();
            }
        }
    }

    public static void bookOperations() throws SQLException {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("""
                1.Insert book
                2.Get all books
                3.Get book by book_id
                4.Update book
                5.Delete book
                6.Get whole book information
                7.Get whole book information by book_id
                8.Join book with authors
                9.Join book with authors by using specific book_id
                10.Go to main menu""");
        int choice = input.nextInt();
        System.out.println();
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
                System.out.println();
                bookTable.addBook(new Book(book_id, title, genre, stock));
                System.out.println();
                bookOperations();
            case 2:
                System.out.println();
                bookTable.getAllBooks();
                System.out.println();
                bookOperations();
            case 3:
                System.out.print("Please enter the id of book: ");
                int book_id2 = input.nextInt();
                System.out.println();
                bookTable.getBookById(book_id2);
                System.out.println();
                bookOperations();
            case 4:
                System.out.println();
                List<Book> books = bookTable.getAllBooks();
                if (books.size() == 0) {
                    bookOperations();
                }
                System.out.println();
                System.out.print("Please enter the id of book you would like to update: ");
                int book_id3 = input.nextInt();
                input.nextLine();
                System.out.println();
                Book book = bookTable.getBookById(book_id3);
                if (book == null) {
                    System.out.println();
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
                System.out.println();
                bookTable.updateBook(book);
                System.out.println();
                bookOperations();
            case 5:
                System.out.print("Please enter the id of book you would like to delete: ");
                int book_id4 = input.nextInt();
                System.out.println();
                bookTable.deleteBook(book_id4);
                System.out.println();
                bookOperations();
            case 6:
                System.out.println();
                bookTable.getWholeBookInformation();
                System.out.println();
                bookOperations();
            case 7:
                System.out.print("Please enter the id of book you would like to get whole information: ");
                int book_id5 = input.nextInt();
                System.out.println();
                bookTable.getWholeBookInformationById(book_id5);
                System.out.println();
                bookOperations();
            case 8:
                System.out.println();
                bookTable.getAuthorInformation();
                System.out.println();
                bookOperations();
            case 9:
                System.out.print("Please enter the id of book you would like to join with authors: ");
                int book_id6 = input.nextInt();
                System.out.println();
                bookTable.getAuthorInformationById(book_id6);
                System.out.println();
                bookOperations();
            case 10:
                userInterface();
        }
    }

    public static void authorOperations() throws SQLException {
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
        System.out.println();
        switch (choice) {
            case 1:
                System.out.print("Enter author id: ");
                int author_id = input.nextInt();
                input.nextLine();
                System.out.print("Enter author_name: ");
                String author_name = input.nextLine();
                System.out.print("Enter author_surname: ");
                String author_surname = input.nextLine();
                System.out.println();
                authorTable.addAuthor(new Author(author_id, author_name, author_surname));
                System.out.println();
                authorOperations();
            case 2:
                System.out.println();
                authorTable.getAllAuthors();
                System.out.println();
                authorOperations();
            case 3:
                System.out.print("Please enter the id of author: ");
                int author_id2 = input.nextInt();
                System.out.println();
                authorTable.getAuthorById(author_id2);
                System.out.println();
                authorOperations();
            case 4:
                System.out.println();
                List<Author> authors = authorTable.getAllAuthors();
                if (authors.size() == 0) {
                    authorOperations();
                }
                System.out.println();
                System.out.print("Please enter the id of author you would like to update: ");
                int author_id3 = input.nextInt();
                input.nextLine();
                System.out.println();
                Author author = authorTable.getAuthorById(author_id3);
                if (author == null) {
                    System.out.println();
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
                System.out.println();
                authorTable.updateAuthor(author);
                System.out.println();
                authorOperations();
            case 5:
                System.out.print("Please enter the id of author you would like to delete: ");
                int author_id4 = input.nextInt();
                System.out.println();
                authorTable.deleteAuthor(author_id4);
                System.out.println();
                authorOperations();
            case 6:
                userInterface();
        }
    }

    public static void customerOperations() throws SQLException {
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
        System.out.println();
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
                System.out.println();
                System.out.println();
                customerTable.addCustomer(new Customer(customer_id, customer_name, customer_surname, address, email));
                System.out.println();
                customerOperations();
            case 2:
                System.out.println();
                customerTable.getAllCustomers();
                System.out.println();
                customerOperations();
            case 3:
                System.out.print("Please enter the id of customer: ");
                int customer_id2 = input.nextInt();
                System.out.println();
                customerTable.getCustomerById(customer_id2);
                System.out.println();
                customerOperations();
            case 4:
                System.out.println();
                List<Customer> customers = customerTable.getAllCustomers();
                if (customers.size() == 0) {
                    customerOperations();
                }
                System.out.println();
                System.out.print("Please enter the id of customer you would like to update: ");
                int customer_id3 = input.nextInt();
                input.nextLine();
                Customer customer = customerTable.getCustomerById(customer_id3);
                if (customer == null) {
                    System.out.println();
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
                System.out.println();
                customerTable.updateCustomer(customer);
                System.out.println();
                customerOperations();
            case 5:
                System.out.print("Please enter the id of customer you would like to delete: ");
                int customer_id4 = input.nextInt();
                System.out.println();
                customerTable.deleteCustomer(customer_id4);
                System.out.println();
                customerOperations();
            case 6:
                userInterface();
        }
    }

    public static void orderOperations() throws SQLException {
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
        System.out.println();
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
                System.out.println();
                orderTable.addOrder(new Order(order_id, customer_id, Date.valueOf(order_date)));
                System.out.println();
                orderOperations();
            case 2:
                System.out.println();
                orderTable.getAllOrders();
                System.out.println();
                orderOperations();
            case 3:
                System.out.print("Please enter the id of order: ");
                int order_id2 = input.nextInt();
                System.out.println();
                orderTable.getOrderById(order_id2);
                System.out.println();
                orderOperations();
            case 4:
                System.out.println();
                List<Order> orders = orderTable.getAllOrders();
                if (orders.size() == 0) {
                    orderOperations();
                }
                System.out.println();
                System.out.print("Please enter the id of order you would like to update: ");
                int order_id3 = input.nextInt();
                input.nextLine();
                System.out.println();
                Order order = orderTable.getOrderById(order_id3);
                if (order == null) {
                    System.out.println();
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
                System.out.println();
                orderTable.updateOrder(order);
                System.out.println();
                orderOperations();
            case 5:
                System.out.print("Please enter the id of order you would like to delete: ");
                int order_id4 = input.nextInt();
                System.out.println();
                orderTable.deleteOrder(order_id4);
                System.out.println();
                orderOperations();
            case 6:
                userInterface();
        }
    }

    public static void book_detailOperations() throws SQLException {
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
        System.out.println();
        switch (choice) {
            case 1:
                System.out.print("Enter book_id: ");
                int book_id = input.nextInt();
                System.out.print("Enter author_id: ");
                int author_id = input.nextInt();
                System.out.println();
                bookDetailTable.addBookDetail(new BookDetail(book_id, author_id));
                System.out.println();
                book_detailOperations();
            case 2:
                System.out.println();
                bookDetailTable.getAllBookDetails();
                System.out.println();
                book_detailOperations();
            case 3:
                System.out.print("Please enter the id of book: ");
                int book_id2 = input.nextInt();
                System.out.println();
                bookDetailTable.getBookDetailByBookId(book_id2);
                System.out.println();
                book_detailOperations();
            case 4:
                System.out.println();
                List<BookDetail> bookDetailList = bookDetailTable.getAllBookDetails();
                if (bookDetailList.size() == 0) {
                    book_detailOperations();
                }
                System.out.println();
                System.out.print("Please enter the id of book you would like to update: ");
                int book_id3 = input.nextInt();
                System.out.print("Please enter the id of author you would like to update: ");
                int author_id3 = input.nextInt();
                BookDetail bookDetail = bookDetailTable.getBookDetailByIdAndAuthorId(book_id3, author_id3);
                if (bookDetail == null) {
                    System.out.println();
                    book_detailOperations();
                }
                System.out.println("Please enter new values to attributes you would like to update." + "Note: If you do not want to update any attribute just press enter and skip it.");
                System.out.print("Update author_id: ");
                int author_id2 = input.nextInt();
                bookDetail.setAuthor_id(author_id2);
                System.out.println();
                bookDetailTable.updateBookDetail(bookDetail);
                System.out.println();
                book_detailOperations();
            case 5:
                System.out.print("Please enter the id of book you would like to delete: ");
                int book_id4 = input.nextInt();
                System.out.print("Please enter the id of author you would like to delete: ");
                int author_id4 = input.nextInt();
                System.out.println();
                bookDetailTable.deleteBookDetail(book_id4, author_id4);
                System.out.println();
                book_detailOperations();
            case 6:
                userInterface();
        }
    }

    public static void order_detailOperations() throws SQLException {
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
        System.out.println();
        switch (choice) {
            case 1:
                System.out.print("Enter order_id: ");
                int order_id = input.nextInt();
                System.out.print("Enter book_id: ");
                int book_id = input.nextInt();
                System.out.print("Enter the number of ordered books: ");
                int number_of_ordered_books = input.nextInt();
                System.out.println();
                orderDetailTable.addOrderDetail(new OrderDetail(order_id, book_id, number_of_ordered_books));
                System.out.println();
                order_detailOperations();
            case 2:
                System.out.println();
                orderDetailTable.getAllOrderDetails();
                System.out.println();
                order_detailOperations();
            case 3:
                System.out.print("Please enter the id of order: ");
                int order_id2 = input.nextInt();
                System.out.println();
                orderDetailTable.getOrderDetailByOrderId(order_id2);
                System.out.println();
                order_detailOperations();
            case 4:
                System.out.println();
                List<OrderDetail> orderDetailList = orderDetailTable.getAllOrderDetails();
                if (orderDetailList.size() == 0) {
                    order_detailOperations();
                }
                System.out.println();
                System.out.print("Please enter the id of order you would like to update: ");
                int order_id3 = input.nextInt();
                System.out.print("Please enter the id of book you would like to update: ");
                int book_id2 = input.nextInt();
                OrderDetail orderDetail = orderDetailTable.getOrderDetailByOrderIdAndBookId(order_id3, book_id2);
                if (orderDetail == null) {
                    order_detailOperations();
                    System.out.println();
                }
                System.out.println("Please enter new values to attributes you would like to update." + "Note: If you do not want to update any attribute just press enter and skip it.");
                System.out.print("Update book_id: ");
                int book_id3 = input.nextInt();
                System.out.print("Update number of ordered books: ");
                int number_of_ordered_books2 = input.nextInt();
                orderDetail.setBook_id(book_id3);
                orderDetail.setNumber_of_ordered_books(number_of_ordered_books2);
                System.out.println();
                orderDetailTable.updateOrderDetail(orderDetail);
                System.out.println();
                order_detailOperations();
            case 5:
                System.out.print("Please enter the id of order you would like to delete: ");
                int order_id4 = input.nextInt();
                System.out.print("Please enter the id of book you would like to delete: ");
                int book_id4 = input.nextInt();
                System.out.println();
                orderDetailTable.deleteOrderDetail(order_id4, book_id4);
                System.out.println();
                order_detailOperations();
            case 6:
                userInterface();
        }
    }

    public static void endSession() throws SQLException {
        System.out.println("Please confirm to close application by typing 'Yes'. Otherwise type anything to stay in application");
        String confirmation = input.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            System.exit(0);
        } else {
            userInterface();
        }
    }
}
