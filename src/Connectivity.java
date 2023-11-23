import java.sql.*;

public class Connectivity {
    public static void main(String[] args) throws SQLException {

    }

    public static Connection connect() {
        String url = "jdbc:postgresql://hostname/databaseName";
        String user = "username";
        String password = "password";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Database successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void displayTableNames() throws SQLException {
        Connection connection = connect();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[] { "TABLE" });
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            System.out.print(tableName + " || ");
        }
    }

    public static void displayColumns() throws SQLException {
        Connection connection = connect();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet columns = metaData.getColumns(null, null, "table_name", null);
        while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            String columnType = columns.getString("TYPE_NAME");
            System.out.print(columnName + " " + columnType);
            System.out.println();
        }
    }
}
