package ClassInterface;

import java.util.HashMap;

/**
 * BeadItUpJa Project
 * Methods to be implemented by all the business classes of the BeadItUpJa
 * @version 1.0
 * @author Marlon Lewis
 * 
 */

public interface Operations {
    //uses connection from DBConnect class 
    // public final Connection conn = DBConnect.dbconnection();
    
    // {
    //     "create user", "create stock", "create bracelet", "create customer",
    //     "edit user","edit stock","edit bracelet","edit customer","view users",
    //     "view user","view inventory","view stock","view bracelets","view customer"
    // };

    public static HashMap<String,String> menuHashMap = new HashMap<String,String>();

    public final String CREATESTOCK = "INSERT INTO `stock` (`type`, `name`, `quantity`, `limit`) VALUES (?, ?, ?, ?)";
    public final String UPDATESTOCK = "update `stock` set quantity=quantity+? where name=?";
    public final String USESTOCK = "update `stock` set quantity=quantity-? where name=?";
    public final String VIEW = "select * from `stock`";
    public final String VIEWBYID = "select * from ? where id=?";
    public final String VIEWBYNAME = "SELECT * FROM `stock` WHERE name=?"; 
    public final String VIEWBYNAMEPHONE = "select * from customers where name = ? and telephone=?";
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