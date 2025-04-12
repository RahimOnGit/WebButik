package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private static String url = "jdbc:sqlite:webbutiken.db";
    private Connection connection;
    private DatabaseConnection() {


    }
    public Connection getConnection() throws SQLException {
        try
        {
            connection = DriverManager.getConnection(url);

        }
        catch (SQLException e)
        {
            System.out.println("failed to open the database "+e.getMessage());
        }
        return connection;
    }
    public void closeConnection() throws SQLException {
        try { connection.close(); } catch (SQLException e) {
            System.out.println("failed to close the database "+e.getMessage());
        }
    }

}
