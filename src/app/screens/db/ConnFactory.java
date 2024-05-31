package app.screens.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        return DriverManager.getConnection("jdbc:mysql://" + servidor+":" + porta + "/" + database + "?user="+ usuario + "&password=" + senha);
    }

    public static void disconnect(Connection conn) throws SQLException {
        conn.close();
    }
}
