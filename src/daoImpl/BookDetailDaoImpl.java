package daoImpl;

import abstractDao.AbstractDao;
import dao.BookDetailInterface;
import entity.BookDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDetailDaoImpl extends AbstractDao implements BookDetailInterface {

    @Override
    public boolean addBookDetail(BookDetail bookDetail) {
        try (Connection connection = connect();) {
            PreparedStatement st = connection.prepareStatement("INSERT INTO book_detail (book_id,author_id) VALUES (?,?)");
            st.setInt(1, bookDetail.getBook_id());
            st.setInt(2, bookDetail.getAuthor_id());
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<BookDetail> getAllBookDetails() {
        List<BookDetail> bookDetailList = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM book_detail");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int book_id = res.getInt("book_id");
                int author_id = res.getInt("author_id");
                bookDetailList.add(new BookDetail(book_id, author_id));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return bookDetailList;
    }

    @Override
    public boolean updateBookDetail(BookDetail bookDetail) {
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("UPDATE book_detail SET author_id=? WHERE book_id=?");
            st.setInt(1, bookDetail.getAuthor_id());
            st.setInt(2, bookDetail.getBook_id());
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBookDetail(int book_id, int author_id) {
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("DELETE FROM book_detail WHERE book_id = " + book_id + " AND author_id" + author_id);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public BookDetail getBookDetailById(int book_id) {
        BookDetail bookDetail = null;
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM book_detail WHERE book_id = " + book_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id_book = res.getInt("book_id");
                int id_author = res.getInt("author_id");
                bookDetail = new BookDetail(id_book, id_author);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return bookDetail;
    }
}
