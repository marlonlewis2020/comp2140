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
import java.sql.ResultSetMetaData;

import ClassInterface.Operations;

public class Authentication implements Operations{
    private PreparedStatement verify;
    
    private String role = null;
    private String user = null;
    private int pw = 0;
    public ArrayList<String> menu_list = null;
    private ArrayList<String> userMenu = null;
    private String request = null;
    private ResultSet result = null;
    private String auth_message = "You are not signed in!";
    private String auth_option = "Sign in!";

    /**
     * constructor sets the initial query string to allow the user to sign in
     * 
     */
    public Authentication(){
        request = String.format("select * from 'roles' r join 'users' u on r.id where u.uname=%s and u.pword=%d", user, pw);
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
        this.user = user;
        this.pw = pw.hashCode();
        try{
            ResultSet roles = sendMenuRequest("query");
            ResultSetMetaData roles_md = roles.getMetaData();
            if (roles.next()){
                for (int column=0;column<roles.getFetchSize();column++){
                    String menuItem = roles_md.getColumnName(column+1);
                    getMenu_list().add(menuItem);
                    if (roles.getString(column+1)=="True"){
                        userMenu.add(sepStringOn(menuItem,"_"));
                    }
                }
                role = roles.getString("role");
                login();
            }
            logout();
        }
        catch(SQLException e){
            e.printStackTrace();
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
        menu_list = null;
        userMenu = null;
        request = String.format("select * from 'roles' r join 'users' u on r.id where u.uname=%s and u.pword=%d", user, pw);
        result = null;
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
        menuHashMap.put("create user",createUser);
        menuHashMap.put("create stock",createStock);
        menuHashMap.put("create bracelet",createBracelet);
        menuHashMap.put("create customer",createCustomer);
        menuHashMap.put("edit user",editUser);
        menuHashMap.put("edit stock",editStock);
        menuHashMap.put("edit bracelet",editBracelet);
        menuHashMap.put("edit customer",editCustomer);
        menuHashMap.put("view users",viewUsers);
        menuHashMap.put("view user",viewUser);
        menuHashMap.put("view inventory",viewInventory);
        menuHashMap.put("view stock",viewStock);
        menuHashMap.put("view bracelets",viewBracelets);
        menuHashMap.put("view bracelet",viewBracelet);
        menuHashMap.put("view customer",viewCustomer);
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
    private String getMenuRequest(){
        return request;
    }

    /**
     * Private method used internally to retrieve the ResultSet results of a sql query
     * @return ResultSet query's result
     */
    private ResultSet getResult() {
        return result;
    }

    /**
     * 
     * Private method used internally that takes a string that has a delimiter, replaces the delimiter with a sapce and returns the new string.
     * @param word: string containing a specific delimiter 
     * @param sep: delimiter in the word to be replaced 
     * @return string with replaced delimiter
     */
    private String sepStringOn(String word,String sep){
        String[] new_word = word.split(sep);
        return String.join(" ",new_word);
    }


    // ---------- //PUBLIC METHODS// ---------- //

    /**
     * 
     * Method sets the current sql request attribute for the current user, based on the menu option selected. 
     * Only used by the Interface Management layer
     * @param option: THE STRING REPRESENTATION OF MENU OPTION CHOSEN BY THE USER
     */
    public void setMenuRequest(String option){
        request = menuHashMap.get(option);
    }

    /**
     * 
     * Method sends the current sql request that has been set for the user, based on the menu option selected. 
     * It uses the getMenuRequest function to send the relevant sql to process the user's request based on their menu selection. 
     * Only to be used by the Data Access layer.
     * @param type
     * @return resultset of the processed user query
     */
    public ResultSet sendMenuRequest(String type){
        try{
            PreparedStatement p = getVerify();
            if(type=="query"){
                result = p.executeQuery();
            }else{
                p.executeUpdate();
                result=null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return getResult();
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
    public Connection getDbConn() {
        return conn;
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
    
    /**
     * 
     * Method retrieves list of all menu options
     * @return 
     */
    public ArrayList<String> getMenu_list() {
        return menu_list;
    }

    /**
     * 
     * Method retrieves sql statement of the user's request
     * @return
     */
    public String getRequest() {
        return request;
    }

    public String getAuth_option() {
        return auth_option;
    }

    public PreparedStatement getVerify() {
        try{
            verify = getDbConn().prepareStatement(getMenuRequest());
        }catch(SQLException e){
            e.printStackTrace();
            verify = null;
        }
        return verify;
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

    public static void main(String[] args) {
        //run tests
        String admin = "admin";
        Authentication test = new Authentication();
        test.authenticate(admin, admin);
    }
}
