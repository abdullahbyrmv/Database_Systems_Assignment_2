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

    private Book getBook(ResultSet res) throws SQLException {
        int book_id = res.getInt("book_id");
        String title = res.getString("title");
        String genre = res.getString("genre");
        int stock = res.getInt("stock");
        String author_name = res.getString("author_name");
        String author_surname = res.getString("author_surname");
        int author_id = res.getInt("author_id");

        Author author = new Author(author_id, author_name, author_surname);

        return new Book(book_id, title, genre, stock, author);
    }

    private Book getBook2(ResultSet res) throws SQLException {
        int book_id = res.getInt("book_id");
        String title = res.getString("title");
        String genre = res.getString("genre");
        int stock = res.getInt("stock");
        int order_id = res.getInt("order_id");
        Date date = res.getDate("order_date");
        int customer_id = res.getInt("customer_id");
        String customer_name = res.getString("customer_name");
        String customer_surname = res.getString("customer_surname");
        String customer_address = res.getString("address");
        String customer_email = res.getString("email");

        Customer customer = new Customer(customer_id, customer_name, customer_surname, customer_address, customer_email);

        Order order = new Order(order_id, customer_id, date, customer);

        return new Book(book_id, title, genre, stock, order);
    }

    private Book getBook3(ResultSet res) throws SQLException {
        int book_id = res.getInt("book_id");
        String title = res.getString("title");
        String genre = res.getString("genre");
        int stock = res.getInt("stock");
        int order_id = res.getInt("order_id");
        Date date = res.getDate("order_date");
        int customer_id = res.getInt("customer_id");
        String customer_name = res.getString("customer_name");
        String customer_surname = res.getString("customer_surname");
        String customer_address = res.getString("address");
        String customer_email = res.getString("email");
        String author_name = res.getString("author_name");
        String author_surname = res.getString("author_surname");
        int author_id = res.getInt("author_id");

        Author author = new Author(author_id, author_name, author_surname);
        Customer customer = new Customer(customer_id, customer_name, customer_surname, customer_address, customer_email);
        Order order = new Order(order_id, customer_id, date, customer);

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
            System.out.println("Query executing: INSERT INTO book (book_id,title,genre,stock) VALUES (" + book.getBook_id() + "," + book.getTitle() + "," + book.getGenre() + "," + book.getStock() + ")");
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
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
            st.execute("DELETE FROM book WHERE book_id = " + book_id);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
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
                book = new Book(id, title, genre, stock);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
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
                Book book = getBook(res);
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
                Book book = getBook(res);
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
                Book book = getBook2(res);
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
            st.execute("SELECT * FROM ((book NATURAL JOIN order_detail) \\n\" +\n" +
                    "                    \"JOIN orders USING(order_id)) JOIN customer USING(customer_id) where book_id = " + book_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                Book book = getBook2(res);
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
                Book book = getBook3(res);
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
                Book book = getBook3(res);
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return books;
    }
}
