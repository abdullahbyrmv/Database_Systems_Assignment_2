package dao;

import entity.Book;

import java.util.List;

public interface BookInterface {
    public boolean addBook(Book book);

    public List<Book> getAllBooks();

    public boolean updateBook(Book book);

    public boolean deleteBook(int book_id);

    public Book getBookById(int book_id);

    public List<Book> getAuthorInformation();

    public List<Book> getAuthorInformationById(int book_id);

    public List<Book> getOrderInformation();

    public List<Book> getOrderInformationById(int book_id);

    public List<Book> getWholeBookInformation();

    public List<Book> getWholeBookInformationById(int book_id);
}
