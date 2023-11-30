package daoImpl;

import abstractDao.AbstractDao;
import dao.BookInterface;
import entity.Author;
import entity.Book;
import entity.Customer;
import entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl extends AbstractDao implements BookInterface {

    private Book joinAuthors(ResultSet res) throws SQLException {
        int book_id = res.getInt("book_id");
        String title = res.getString("title");
        String genre = res.getString("genre");
        int stock = res.getInt("stock");
        String author_name = res.getString("author_name");
        String author_surname = res.getString("author_surname");
        int author_id = res.getInt("author_id");

        System.out.println("author_id = " + author_id + ", book_id = " + book_id + ", title = " + title + ", genre = " + genre
                + ", stock = " + stock + ", author_name = " + author_name + ", author_surname = " + author_surname);

        Author author = new Author(author_id, author_name, author_surname);

        return new Book(book_id, title, genre, stock, author);
    }

    private Book joinAuthorsOrders(ResultSet res) throws SQLException {
        int book_id = res.getInt("book_id");
        String title = res.getString("title");
        String genre = res.getString("genre");
        int stock = res.getInt("stock");
        int order_id = res.getInt("order_id");
        Date order_date = res.getDate("order_date");
        int customer_id = res.getInt("customer_id");
        String customer_name = res.getString("customer_name");
        String customer_surname = res.getString("customer_surname");
        String customer_address = res.getString("address");
        String customer_email = res.getString("email");
        int number_of_ordered_books = res.getInt("number_of_ordered_books");

        System.out.println("customer_id = " + customer_id + ", order_id = " + order_id + ", book_id = " + book_id
                + ", title = " + title + ", genre = " + genre + ", stock = " + stock + ", number_of_ordered_books = " + number_of_ordered_books
                + ", order_date = " + order_date + ", customer_name = " + customer_name + ", customer_surname = " + customer_surname
                + ", address = " + customer_address + ", email = " + customer_email);

        Customer customer = new Customer(customer_id, customer_name, customer_surname, customer_address, customer_email);

        Order order = new Order(order_id, customer_id, order_date, customer);

        return new Book(book_id, title, genre, stock, order);
    }

    private Book joinAllTables(ResultSet res) throws SQLException {
        int book_id = res.getInt("book_id");
        String title = res.getString("title");
        String genre = res.getString("genre");
        int stock = res.getInt("stock");
        int order_id = res.getInt("order_id");
        Date order_date = res.getDate("order_date");
        int customer_id = res.getInt("customer_id");
        String customer_name = res.getString("customer_name");
        String customer_surname = res.getString("customer_surname");
        String customer_address = res.getString("address");
        String customer_email = res.getString("email");
        String author_name = res.getString("author_name");
        String author_surname = res.getString("author_surname");
        int author_id = res.getInt("author_id");
        int number_of_ordered_books = res.getInt("number_of_ordered_books");

        System.out.println("customer_id = " + customer_id + ", order_id = " + order_id + ", book_id = " + book_id
                + ", author_id = " + author_id + ", title = " + title + ", genre = " + genre + ", stock = " + stock
                + ", author_name = " + author_name + ", author_surname = " + author_surname + ", number_of_ordered_books = " + number_of_ordered_books
                + ", order_date = " + order_date + ", customer_name = " + customer_name + ", customer_surname = " + customer_surname
                + ", address = " + customer_address + ", email = " + customer_email);

        Author author = new Author(author_id, author_name, author_surname);
        Customer customer = new Customer(customer_id, customer_name, customer_surname, customer_address, customer_email);
        Order order = new Order(order_id, customer_id, order_date, customer);

        return new Book(book_id, title, genre, stock, author, order);
    }

    @Override
    public boolean addBook(Book book) {
        try (Connection connection = connect();) {
            PreparedStatement st = connection.prepareStatement("INSERT INTO book (book_id,title,genre,stock) VALUES (?,?,?,?)");
            st.setInt(1, book.getBook_id());
            st.setString(2, book.getTitle());
            st.setString(3, book.getGenre());
            st.setInt(4, book.getStock());
            System.out.println("Query executing: INSERT INTO book (book_id,title,genre,stock) VALUES ("
                    + book.getBook_id() + "," + book.getTitle() + "," + book.getGenre() + "," + book.getStock() + ")");
            st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        System.out.println("Book Inserted successfully");
        return true;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM book");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int book_id = res.getInt("book_id");
                String title = res.getString("title");
                String genre = res.getString("genre");
                int stock = res.getInt("stock");
                System.out.println("book_id = " + book_id + ", title = " + title + ", genre = " + genre + ", stock = " + stock);
                books.add(new Book(book_id, title, genre, stock));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return books;
    }

    @Override
    public boolean updateBook(Book book) {
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("UPDATE book SET title=?, genre=?, stock=? WHERE book_id=?");
            st.setString(1, book.getTitle());
            st.setString(2, book.getGenre());
            st.setInt(3, book.getStock());
            st.setInt(4, book.getBook_id());
            System.out.println("Updated book with book_id = " + book.getBook_id());
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBook(int book_id) {
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            int number_of_affected_rows = st.executeUpdate("DELETE FROM book WHERE book_id = " + book_id);

            if (number_of_affected_rows == 0) {
                System.out.println("No book with book_id = " + book_id + " exists.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        System.out.println("Deleted book with book_id = " + book_id);
        return true;
    }

    @Override
    public Book getBookById(int book_id) {
        Book book = null;
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM book WHERE book_id = " + book_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id = res.getInt("book_id");
                String title = res.getString("title");
                String genre = res.getString("genre");
                int stock = res.getInt("stock");
                System.out.println("book_id = " + book_id + ", title = " + title + ", genre = " + genre + ", stock = " + stock);
                book = new Book(id, title, genre, stock);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        if (book == null) {
            System.out.println("No such book found");
        }
        return book;
    }

    @Override
    public List<Book> getAuthorInformation() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM (book NATURAL JOIN book_detail) JOIN author USING(author_id)");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                Book book = joinAuthors(res);
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> getAuthorInformationById(int book_id) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM (book NATURAL JOIN book_detail) JOIN author USING(author_id) WHERE book_id = " + book_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                Book book = joinAuthors(res);
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> getOrderInformation() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM ((book NATURAL JOIN order_detail) \n" +
                    "JOIN orders USING(order_id)) JOIN customer USING(customer_id)");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                Book book = joinAuthorsOrders(res);
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> getOrderInformationById(int book_id) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM ((book NATURAL JOIN order_detail) \n" +
                    "JOIN orders USING(order_id)) JOIN customer USING(customer_id) WHERE book_id = " + book_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                Book book = joinAuthorsOrders(res);
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> getWholeBookInformation() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM ((((book NATURAL JOIN book_detail) JOIN author USING(author_id))\n" +
                    " JOIN order_detail USING(book_id)) JOIN orders USING(order_id)) JOIN customer USING(customer_id)");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                Book book = joinAllTables(res);
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> getWholeBookInformationById(int book_id) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM ((((book NATURAL JOIN book_detail) JOIN author USING(author_id))\n" +
                    " JOIN order_detail USING(book_id)) JOIN orders USING(order_id)) JOIN customer USING(customer_id) where book_id = " + book_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                Book book = joinAllTables(res);
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return books;
    }
}
