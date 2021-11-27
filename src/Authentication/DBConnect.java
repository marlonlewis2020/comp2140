package Authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnect
 */
public class DBConnect {

    private static final String DB = "jdbc:mysql://localhost/beaditupja";
    private static final String USER = "root";
    private static final String PWD = "";
    private static Connection conn;

    public DBConnect() {
        try {
            conn = DriverManager.getConnection(DB, USER, PWD);
        } catch (SQLException e) {
            conn = null;
        }
    }

    // returns connection
    protected static Connection dbconnection() {
        return conn;
    }

}
