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
    private static int users = 0;

    /**
     * function sets up the database connection for other classes to use
     */
    public DBConnect() {
        users++;
        System.out.println("[CONNECTING] active user connections: "+users);
        boolean x = conn==null;
        boolean c;

        if(!x){
            try {
                c = conn.isClosed();
            } catch (Exception e) {
                e.printStackTrace();
                c = false;
            }
        }
        else {
            c = false;
        }

        if(c || x){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(DB, USER, PWD);
            } catch (SQLException e) {
                // e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // returns connection
    protected Connection dbconnection() {
        return conn;
    }

    public void close(){
        users--;
        System.out.println("[CONNECTING] active user connections: "+users);
        if(users<1){
            try{
                conn.close();
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.println("Could not close connection");
            }
        }
    }

}
