package metaData;

import abstractDao.AbstractDao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaDataMethods extends AbstractDao {
    public static void displayTableNamesandColumns() throws SQLException {
        Connection connection = connect();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            System.out.println("Table name: " + tableName);
            System.out.println("Columns: ");
            displayColumns(tableName);
            System.out.println();
        }
    }

    public static void displayColumns(String tableName) throws SQLException {
        Connection connection = connect();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet columns = metaData.getColumns(null, null, tableName, null);
        while (columns.next()) {
            String columnName = columns.getString("COLUMN_NAME");
            System.out.println(columnName);
        }
    }

    public static void displayColumnDetails() throws SQLException {
        Connection connection = connect();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            System.out.println("Table name: " + tableName);
            System.out.println("Column names and types: ");
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");
                System.out.println(columnName + " " + columnType);
            }
            System.out.println();
        }
    }

    public static void displayPrimaryKeys() throws SQLException {
        Connection connection = connect();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
            System.out.println("Table name: " + tableName);
            System.out.println("Primary key(s): ");
            while (primaryKeys.next()) {
                String primaryKeyColumnName = primaryKeys.getString("COLUMN_NAME");
                System.out.println(primaryKeyColumnName);
            }
            System.out.println();
        }
    }

    public static void displayForeignKeys() throws SQLException {
        Connection connection = connect();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
            System.out.println("Table name: " + tableName);
            System.out.println("Foreign key(s): ");
            while (foreignKeys.next()) {
                String foreign_key_column = foreignKeys.getString("FKCOLUMN_NAME");
                String referenced_table_name = foreignKeys.getString("PKTABLE_NAME");
                String referenced_column_name = foreignKeys.getString("PKCOLUMN_NAME");
                System.out.print(foreign_key_column + " references " + referenced_table_name + "(" + referenced_column_name + ")");
                System.out.println();
            }
            System.out.println();
        }
    }
}
