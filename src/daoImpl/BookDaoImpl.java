package daoImpl;

import abstractDao.AbstractDao;
import dao.BookInterface;
import entity.Book;

import java.util.List;

public class BookDaoImpl extends AbstractDao implements BookInterface {

    @Override
    public boolean addBook(Book book) {
        return false;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public boolean updateBook(Book book) {
        return false;
    }

    @Override
    public boolean deleteBook(int book_id) {
        return false;
    }

    @Override
    public Book getBookById(int book_id) {
        return null;
    }

    @Override
    public List<Book> getWholeBookInformation() {
        return null;
    }

    @Override
    public Book getWholeBookInformationById() {
        return null;
    }
}
