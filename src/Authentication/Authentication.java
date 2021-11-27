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

import ClassInterface.Operations;

public class Authentication implements Operations{
    private PreparedStatement ps;
    
    private String role = null;
    private String user = null;
    private int pw = 0;
    private ArrayList<String> userMenu = null;
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
            String sql = "select * from roles r join users u on r.id=u.id where u.username=? and u.password=?";
            this.user = user;
            this.pw = pw.hashCode(); //pw.hashCode()
            try{
                PreparedStatement p = getDbConn().prepareStatement(sql);
                p.setString(1,user);
                p.setString(2,pw);
                ResultSet roles = p.executeQuery();
                if (roles.next()){
                    role = roles.getString("role");
                    String[] items = role.split(";");
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
        userMenu = null;
        request = "select * from 'roles' r join 'users' u on r.id=u.id where u.username=? and u.password=?";
        auth_message = "You are not signed in!";
        auth_option = "Sign in!";
        return "Sign out completed. "+auth_message;
    }

    /**
     * 
     * Method sets up link between menu options and the sql statements required to rexecute them
     * @return sign in confirmation
     */
    private String login(){
        auth_message = "You are logged in";
        menuHashMap.put("create user",create);
        menuHashMap.put("create stock",create);
        menuHashMap.put("create bracelet",create);
        menuHashMap.put("create customer",create);
        menuHashMap.put("edit user",update);
        menuHashMap.put("edit stock",update);
        menuHashMap.put("edit bracelet",update);
        menuHashMap.put("edit customer",update);
        menuHashMap.put("view users",view);
        menuHashMap.put("view user",viewSelected);
        menuHashMap.put("view inventory",view);
        menuHashMap.put("view stock",viewSelected);
        menuHashMap.put("view bracelets",view);
        menuHashMap.put("view bracelet",viewSelected);
        menuHashMap.put("view customers",view);
        menuHashMap.put("view customer",viewSelected);
        auth_option = "Sign out!";
        return "Welcome "+getUser()+" "+auth_message;
    }

    // ---------- //PRIVATE METHODS USED TO ACHIEVE THE SYSTEM'S FUNCTIONALITIES// ---------- //

    /**
     * 
     * Retrieves SQL request statement that has been set by the system, based on the menu option selected. 
     * Only used by the application logic layer
     * @return String SQL statement to be executed to fulfill the user's menu request
     */
    private String getRequest(){
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
        return DBConnect.dbconnection();
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

    private PreparedStatement usePreparedstmt(String[] vals){
        PreparedStatement sql = getPS();
        try{
            for(int i = 0; i<vals.length;i++){
                sql.setString(i+1, vals[i]);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return sql;
        }

    private String setter(int n, String[] c, String[] v){
        String resultString= "";
        for(int i=0;i<n-1;i++){
            resultString+=c[i]+"="+v[i]+",";
        }
        resultString+=c[n]+"="+v[n];
        return resultString;
    }

    @Override
    public void update(String table, String columns, String values, int id) {
        int sized = columns.split(",").length;
        String resultString = setter(sized,columns.split(","),values.split(","));
        String[] localList = {table,resultString};
        PreparedStatement sql = usePreparedstmt(localList);
        try {
            sql.setInt(3, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void create(String table, String columns, String values) {
        int sized = columns.split(",").length;
        String resultString = setter(sized,columns.split(","),values.split(","));
        String[] localList = {table,resultString};
        PreparedStatement sql = usePreparedstmt(localList);
        try {
            sql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public ResultSet viewAll(String table, String columns) {
        ResultSet result = null;
        String[] localList = {columns,table};
        PreparedStatement sql = usePreparedstmt(localList);
        try {
            result = sql.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
        

    @Override
    public ResultSet viewSpecific(String table, String columns, String criteria) {
        ResultSet result = null;
        String[] localList = {columns,table,criteria};
        PreparedStatement sql = usePreparedstmt(localList);
        try {
            result = sql.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        //run tests
        // String admin = "admin";
        // Authentication test = new Authentication();
        System.out.println("password123".hashCode());
    }
}
