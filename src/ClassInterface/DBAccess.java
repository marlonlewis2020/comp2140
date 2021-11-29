
/**
 * Class provides methods for querying and updating the database.
 * @version 1.1
 * @author Marlon Lewis
 * 
 */

package ClassInterface;

import java.sql.*;
import Authentication.Authentication;

public class DBAccess {
    private Authentication auth;

    /**
     * 
     * Constuctor for the DBAccess class
     * @param auth1 an Authentication object that is set to this object's authentication attribute.
     */
    public DBAccess(Authentication auth1){
        this.auth = auth1;
    }

    /**
     * Function updates the quantity of a stock item
     * @param qty - the value to update by.
     * @param name - the name of the stock item.
     * @return boolean true if successfully update to quantity, false otherwise.
     */
    public boolean update(int qty, String name){
        PreparedStatement sql = auth.getPS();
        try{
            sql.setInt(1,qty);
            sql.setString(2,name);
            sql.execute();
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Function creates a record in any table in the beaditupja database
     * @param type - is the type of stock.
     * @param name - is the name of the stock.
     * @param qty - is the quantity of stock.
     * @param limit - is the low-level for this stock item that will create an alert once reached.
     */
    public void create(String type, String name, int qty, int limit) {
        PreparedStatement sql = auth.getPS();
        try {
            sql.setString(1, type);
            sql.setString(2, name);
            sql.setInt(3, qty);
            sql.setInt(4, limit);
            sql.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // System.out.println("[DBAccess create method (sql)] "+sql);
        }   
    }

    /**
     * Function returns specific fields for all the records in the table.
     * @param table - is the table being queried.
     * @param columns - is the string of field names to be included in the resultset.
     * @return Resultset of field values from specified table in database
     */
    public ResultSet viewAll() {
        ResultSet result = null;
        PreparedStatement sql = auth.getPS();
        try {
            result = sql.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
        
    /**
     * Function locates a specific record and returns only the fields specified.
     * @param table - is the table being queried.
     * @param criteria - is the specific criteria the record must match in order to be selected to return results.
     * @return Resultset of field values from specified table in database with matching records.
     */
    public ResultSet viewSpecific(String table, String criteria) {
        ResultSet result = null;
        PreparedStatement sql = auth.getPS();
        // System.out.println("[DBAccess - viewSpecific method] viewSpecific sql: "+sql);
        try {
            sql.setString(1, criteria);
            // System.out.println("[DBAccess - viewSpecific method] viewSpecific sql: "+sql);
            result = sql.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println("[DBAccess - viewSpecific method] viewSpecific Results: "+result);
        return result;
    }
}