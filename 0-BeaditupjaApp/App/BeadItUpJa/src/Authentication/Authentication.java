package Authentication;

/**
 * BeadItUpJa Project
 * @version 1.0
 * @author Marlon Lewis
 * 
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import ClassInterface.Operations;

public class Authentication implements Operations{
    private PreparedStatement verify;
    
    private int uid = 0;
    private String user;
    private int pw;
    
    private ArrayList<String> menu;
    private String request = "";
    private ResultSet result;

    //constructor sets the roles and the user id
    public Authentication(String user, String pw){
        this.user = user;
        this.pw = pw.hashCode();
        try{
            authenticate();
        }
        catch(SQLException e){
        }
    }

    public void authenticate() throws SQLException{
        result = sendRequest("query",String.format("select * from 'roles' r join 'users' u on r.id where u.uname=%s and u.pword=%d", user, pw));
        ResultSet roles = verify.executeQuery();
        uid = roles.getInt("id");
        getMenu(roles);
    }

    public ArrayList<String> getMenu(ResultSet roles) throws SQLException{
        for (int i=0;i<roles.getFetchSize(); i++){
            if(roles.getString(i+1)=="True"){
                menu.add(menu_list[i]);
            }
        }
        return menu;
    }

    public ResultSet sendRequest(String type,String request){
        try{
            PreparedStatement p = conn.prepareStatement(request);
            if(type=="query"){
                result = p.executeQuery();
            }else{
                p.executeUpdate();
            }
        }catch(SQLException e){}
        return result;
    }

    public String getRequest() {
        return request;
    }

    public ResultSet getResult() {
        return result;
    }

    public int getUid() {
        return uid;
    }

    public int getPwd() {
        return pw;
    }

    public String getUser() {
        return user;
    }

    public String[] getMenu_list() {
        return menu_list;
    }

    public Connection getDbConn() {
        return conn;
    }
}
