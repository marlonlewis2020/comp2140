package Authentication;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Connection.DBConnect;

public class Authentication {
    Connection conn = DBConnect.conn;
    String sql = "select role from users where username=? and password=?";
    PreparedStatement verify;

    public Authentication(String user, String pw) {
        try {
            verify = conn.prepareStatement(sql);
            verify.setString(1, user);
            verify.setString(2, pw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String authenticate() {
        String result = "";
        try {
            result = verify.executeQuery().getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
