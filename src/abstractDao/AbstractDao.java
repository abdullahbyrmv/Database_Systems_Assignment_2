package abstractDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDao {
    public static Connection connect() {
        String url = "jdbc:postgresql://localhost/Assignment2";
        String user = "postgres";
        String password = "legend";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
