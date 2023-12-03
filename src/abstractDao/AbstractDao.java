package abstractDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDao {
    public static Connection connect() {
        String url = "jdbc:postgresql://hostname:port_number/Database_Name";
        String user = "username";
        String password = "password";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
