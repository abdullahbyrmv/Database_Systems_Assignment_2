import java.sql.*;

public class Connectivity {
    public static void main(String[] args) {
        deleteAuthor(6);
    }

    public static Connection connect() {
        String url = "jdbc:postgresql://x/x";
        String user = "x";
        String password = "x";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Database successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static boolean insertAuthor(int author_id,String author_name) {
        try (Connection a = connect()) {
            PreparedStatement st = a.prepareStatement("INSERT INTO authors(author_id,author_name) VALUES (?,?)");
            st.setInt(1,author_id);
            st.setString(2,author_name);
            System.out.println("Author inserted successfully!");
            return st.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAuthor(int author_id) {
        try (Connection a = connect()) {
            Statement st = a.createStatement();
            st.execute("DELETE FROM authors where author_id = " + author_id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        System.out.println("Author deleted successfully with id=" + author_id);
        return true;
    }
}
