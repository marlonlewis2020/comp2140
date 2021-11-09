package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * DBConnect
 */
public class DBConnect {

    static final String DB = "jdbc:mysql://localhost/beaditupja";
    static final String USER = "admin";
    static final String PWD = "admin";
    public static Connection conn;

    public DBConnect() {
        try {
            conn = DriverManager.getConnection(DB, USER, PWD);
        } catch (SQLException e) {
            conn = null;
        }
    }

    public Connection dbconnection() {
        return conn;
    }

    public void save(String table, HashMap<String, String> dict) {

    }
}
