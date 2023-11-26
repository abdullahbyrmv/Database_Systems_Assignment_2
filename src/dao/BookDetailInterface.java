package dao;

import entity.BookDetail;

import java.util.List;

public interface BookDetailInterface {
    public boolean addBookDetail(BookDetail bookDetail);

    public List<BookDetail> getAllBookDetails();

    public boolean updateBookDetail(BookDetail bookDetail);

    public boolean deleteBookDetail(int book_id, int author_id);

    public BookDetail getBookDetailById(int book_id);
}
