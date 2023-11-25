package daoImpl;

import abstractDao.AbstractDao;
import dao.BookDetailInterface;
import entity.Author;
import entity.Book;
import entity.BookDetail;

import java.util.List;

public class BookDetailDaoImpl extends AbstractDao implements BookDetailInterface {

    @Override
    public boolean addBookDetail(BookDetail bookDetail) {
        return false;
    }

    @Override
    public List<BookDetail> getAllBookDetails() {
        return null;
    }

    @Override
    public boolean updateBookDetail(BookDetail bookDetail) {
        return false;
    }

    @Override
    public boolean deleteBookDetail(Book book_id, Author author_id) {
        return false;
    }

    @Override
    public BookDetail getBookDetailById(int book_id) {
        return null;
    }
}
