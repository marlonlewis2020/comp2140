package Authentication;

import java.sql.*;

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
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB, USER, PWD);
        } catch (SQLException e) {
            // e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // returns connection
    protected Connection dbconnection() {
        return conn;
    }

    public void close(){
        try{
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Could not close connection");
        }
    }

}
