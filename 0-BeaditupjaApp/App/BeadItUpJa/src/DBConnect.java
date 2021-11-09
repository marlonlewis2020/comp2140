import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnect
 */
public class DBConnect {

    static final String DB = "jdbc:mysql://localhost/beaditupja";
    static final String USER = "admin";
    static final String PWD = "admin";
    Connection conn = null;

    {
        try {
            conn = DriverManager.getConnection(DB, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}