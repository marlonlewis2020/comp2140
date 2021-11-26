package ClassInterface;

import java.sql.Connection;
import java.util.HashMap;
import Connection.DBConnect;

/**
 * BeadItUpJa Project
 * Methods to be implemented by all the business classes of the BeadItUpJa
 * @version 1.0
 * @author Marlon Lewis
 * 
 */

public interface Operations {
    //uses connection from DBConnect class 
    public final Connection conn = DBConnect.conn;
    
    // {
    //     "create user", "create stock", "create bracelet", "create customer",
    //     "edit user","edit stock","edit bracelet","edit customer","view users",
    //     "view user","view inventory","view stock","view bracelets","view customer"
    // };

    public static HashMap<String,String> menuHashMap = new HashMap<String,String>();

    public final String createUser = "";
    public final String createStock = "";
    public final String createBracelet = "";
    public final String createCustomer = "";
    public final String editUser = "";
    public final String editStock = "";
    public final String editBracelet = "";
    public final String editCustomer = "";
    public final String viewUsers = "";
    public final String viewUser = "";
    public final String viewInventory = "";
    public final String viewStock = "";
    public final String viewBracelets = "";
    public final String viewCustomer = "";
    public final String viewBracelet = "";
}
    // /*
    //  * Adds the created object to the database
    //  */
    // public abstract boolean create();

    // /*
    //  * Edits the object in the database
    //  */
    // public abstract boolean edit();

    // /*
    //  * Provides a list of objects of this class type from the database
    //  */
    // public abstract String[] view();

    // /*
    //  * Returns the id for this object
    //  */
    // public abstract int getID();

    // /*
    //  * Returns the string identifier for this object
    //  */
    // public abstract String toString();
