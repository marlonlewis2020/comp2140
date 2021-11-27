package Authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * DBConnect
 */
public class DBConnect {

    static final String DB = "jdbc:mysql://localhost/beaditupja";
    static final String USER = "root";
    static final String PWD = "";
    public static Connection conn;

    public DBConnect() {
        try {
            conn = DriverManager.getConnection(DB, USER, PWD);
        } catch (SQLException e) {
            conn = null;
        }
    }

    // checks all inventory levels each time an edit is made to the database
    public String notification() {
        return "";
    }

    // returns connection
    public static Connection dbconnection() {
        return conn;
    }

    public void save(String table, HashMap<String, String> dict) {

    }
}
