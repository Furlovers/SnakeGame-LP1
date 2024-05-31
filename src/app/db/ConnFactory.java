package app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// connects with the database
public class ConnFactory {

    static {
        try{ 
            Class.forName("com.mysql.jdbc.Driver");
        } catch( ClassNotFoundException e ) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String servidor = "localhost";
        String porta = "3306";
        String database = "TUTORIAL";
        String usuario = "root";
        String senha =  "admin";

        // Connect to the MySQL server without specifying a database
        Connection conn = DriverManager.getConnection("jdbc:mysql://" + servidor + ":" + porta + "?user=" + usuario + "&password=" + senha);
        
        // Create the database if it does not exist
        createDatabaseIfNotExists(conn, database);
        
        // Close the initial connection
        conn.close();

        return DriverManager.getConnection("jdbc:mysql://" + servidor+":" + porta + "/" + database + "?user="+ usuario + "&password=" + senha);
    }

    private static void createDatabaseIfNotExists(Connection conn, String database) throws SQLException {
        String sqlCreateDB = "CREATE DATABASE IF NOT EXISTS " + database;
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlCreateDB);
        }
    }

    public static void disconnect(Connection conn) throws SQLException {
        conn.close();
    }
}
