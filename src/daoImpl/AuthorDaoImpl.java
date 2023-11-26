package daoImpl;

import abstractDao.AbstractDao;
import dao.AuthorInterface;
import entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl extends AbstractDao implements AuthorInterface {

    @Override
    public boolean addAuthor(Author author) {
        try (Connection connection = connect();) {
            PreparedStatement st = connection.prepareStatement("INSERT INTO author (author_id,author_name,author_surname) VALUES (?,?,?)");
            st.setInt(1, author.getAuthor_id());
            st.setString(2, author.getAuthor_name());
            st.setString(3, author.getAuthor_surname());
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM author");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int author_id = res.getInt("author_id");
                String first_name = res.getString("author_name");
                String last_name = res.getString("author_surname");
                authors.add(new Author(author_id, first_name, last_name));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return authors;
    }

    @Override
    public boolean updateAuthor(Author author) {
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("UPDATE author SET author_name=?, author_surname=? WHERE author_id=?");
            st.setString(1, author.getAuthor_name());
            st.setString(2, author.getAuthor_surname());
            st.setInt(3, author.getAuthor_id());
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAuthor(int author_id) {
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("DELETE FROM author WHERE author_id = " + author_id);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Author getAuthorById(int author_id) {
        Author author = null;
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM author WHERE author_id = " + author_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id = res.getInt("author_id");
                String first_name = res.getString("author_name");
                String last_name = res.getString("author_surname");
                author = new Author(id, first_name, last_name);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return author;
    }
}
