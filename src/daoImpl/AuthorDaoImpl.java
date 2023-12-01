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
            System.out.println("Query executing: INSERT INTO author (author_id,author_name,author_surname) VALUES ("
                    + author.getAuthor_id() + "," + author.getAuthor_name() + "," + author.getAuthor_surname() + ")");
            st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        System.out.println("Author inserted successfully!");
        return true;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM author ORDER BY author_id");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int author_id = res.getInt("author_id");
                String author_name = res.getString("author_name");
                String author_surname = res.getString("author_surname");
                System.out.println("author_id = " + author_id + ", author_name = " + author_name + ", author_surname = " + author_surname);
                authors.add(new Author(author_id, author_name, author_surname));
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
            System.out.println("Updated author with author_id = " + author.getAuthor_id());
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
            int number_of_affected_rows = st.executeUpdate("DELETE FROM author WHERE author_id = " + author_id);

            if (number_of_affected_rows == 0) {
                System.out.println("No author with author_id = " + author_id + " exists.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        System.out.println("Deleted author with author_id = " + author_id);
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
                String author_name = res.getString("author_name");
                String author_surname = res.getString("author_surname");
                System.out.println("author_id = " + author_id + ", author_name = " + author_name + ", author_surname = " + author_surname);
                author = new Author(id, author_name, author_surname);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        if (author == null) {
            System.out.println("No such author found");
        }
        return author;
    }
}
