package abstractDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDao {
    public static Connection connect() {
        String url = "jdbc:postgresql://hostname:portnumber/Database_name";
        String user = "x";
        String password = "x";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
