package Authentication;

import java.sql.Connection;

/**
 * BeadItUpJa Project
 * @version 1.0
 * @author Marlon Lewis
 * 
 */

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.ResultSet;
// import Bracelet;
// import Stock;
// import Order;

import ClassInterface.Operations;

public class Authentication implements Operations{
    private static DBConnect conn;
    private PreparedStatement ps;
    
    private String role = null;
    private String user = null;
    private int pw = 0;
    private ArrayList<String> userMenu = new ArrayList<String>();
    private String request = null;
    private String auth_message = "You are not signed in!";
    private String auth_option = "Sign in!";

    /**
     * constructor sets the initial query string to allow the user to sign in
     * 
     */
    public Authentication(){
        request = "select * from 'roles' r join 'users' u on r.id=u.id where u.uname=? and u.pword=?";
    }

    /**
     * 
     * The Method sets up the Authentication object for each user, allowing the user to sign in with username and password.
     * The username and hashed password are stored as attributes of the Authentication class.
     * It also allows user data and requests to be stored for processing.
     * @param user: USERNAME
     * @param pw: PASSWORD
     * @throws SQLException
     */
    public void authenticate(String user, String pw) {
        if(user=="" && pw==""){
            logout();
        }
        else{
            conn = new DBConnect();
            String sql = "select * from roles r join users u on r.name=u.role where u.username=? and u.password=?";
            this.user = user;
            this.pw = pw.hashCode(); //pw.hashCode()
            try{
                PreparedStatement p = getDbConn().prepareStatement(sql);
                p.setString(1,user);
                p.setString(2,pw);
                ResultSet roles = p.executeQuery();
                if (roles.next()){
                    role = roles.getString("privilege");
                    String[] items = role.split(",");
                    userMenu.addAll(Arrays.asList(items));
                    login();
                }else{
                    logout();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     * Method deconstructs the Authentication attributes
     * @return sing out confirmation
     */
    public String logout(){
        role = null;
        user = null;
        pw = 0;
        userMenu = new ArrayList<String>();
        request = "select * from 'roles' r join 'users' u on r.id=u.id where u.username=? and u.password=?";
        auth_message = "You are not signed in!";
        auth_option = "Sign in!";
        conn.close();
        return "Sign out completed. "+auth_message;
    }

    /**
     * 
     * Method sets up link between menu options and the sql statements required to rexecute them
     * @return sign in confirmation
     */
    private String login(){
        auth_message = "You are logged in";
        menuHashMap.put("create user",createStock);
        menuHashMap.put("create stock",createStock);
        menuHashMap.put("create bracelet",createStock);
        menuHashMap.put("create customer",createStock);
        menuHashMap.put("edit user",updateStock);
        menuHashMap.put("edit stock",updateStock); 
        menuHashMap.put("use stock",useStock); 
        menuHashMap.put("edit bracelet",updateStock);
        menuHashMap.put("edit customer",updateStock);
        menuHashMap.put("view users",view);
        menuHashMap.put("view user",viewById);
        menuHashMap.put("view inventory",view);
        menuHashMap.put("view stock",viewByName);
        menuHashMap.put("view bracelets",view);
        menuHashMap.put("view bracelet",viewByName);
        menuHashMap.put("view customers",view);
        menuHashMap.put("view customer",viewByNamePhone);
        auth_option = "Sign out!";
        // Bracelet.populate();
        return "Welcome "+getUser()+" "+auth_message;
    }

    // ---------- //PRIVATE METHODS USED TO ACHIEVE THE SYSTEM'S FUNCTIONALITIES// ---------- //

    /**
     * 
     * Retrieves SQL request statement that has been set by the system, based on the menu option selected. 
     * Only used by the application logic layer
     * @return String SQL statement to be executed to fulfill the user's menu request
     */
    public String getRequest(){
        return request;
    }

    // ---------- //PUBLIC METHODS// ---------- //

    /**
     * 
     * Method sets the current sql request attribute for the current user, based on the menu option selected. 
     * Only used by the Interface Management layer
     * @param option: THE STRING REPRESENTATION OF MENU OPTION CHOSEN BY THE USER
     */
    public void setRequest(String option){
        request = menuHashMap.get(option);
    }
    
    // ---------- //GETTERS FOR THE CLASS// ---------- //

    /**
     * Method retrieves the user's role
     * @return user's role 
     */
    public String getRole() {
        return role;
    }

    /**
     * 
     * @return user's hashed password
     */
    public int getPwd() {
        return pw;
    }

    /**
     * 
     * Method retrieves the current user's username
     * @return user's username
     */
    public String getUser() {
        return user;
    }

    /**
     * Method retrieves the connection object for the database
     * @return database connection
     */
    public static Connection getDbConn() {
        return conn.dbconnection();
    }

    /**
     * 
     * Method retrieves the authentication status of the user
     * @return authentication status
     */
    public String getAuth_message() {
        return auth_message;
    }


    // ---------- //MENU RELATED FUNCTIONS// ---------- //

    /**
     * 
     * Method retrieves the list of menu options available to the authenticated user
     * @return user's menu
     */
    public ArrayList<String> getUserMenu() {
        return userMenu;
    }

    public String getAuth_option() {
        return auth_option;
    }

    public PreparedStatement getPS() {
        try{
            ps = getDbConn().prepareStatement(getRequest());
        }catch(SQLException e){
            e.printStackTrace();
            ps = null;
        }
        return ps;
    }

    public String toString(){
        String result = "Welcome %s! %s";
        if (getUser()==null){
            result = String.format(result,"Guest",getAuth_message());
        }else{
            result = String.format(result,getUser(),getAuth_message());
        }
        return result;
    }

}
