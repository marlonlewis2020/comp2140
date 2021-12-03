package Inventory;
/**
 * Stock is an invertory item located in the beaditupja app.
 * @version 1.2
 * @author Kimani Munn,Marlon Lewis
 */

import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import Authentication.Authentication;

public class Stock{
    
    private int quantity;
    private StockType stockType;
    private String name;
    private int stockID=0;
    private int level;
    private static ArrayList<Stock> inventory = new ArrayList<Stock>();
    private static Connection conn = Authentication.getDbConn();

        // DBAccess dba;
        // dba = new DBAccess(auth);

         /**
          * overloaded constructor to load stock item from database into object for handling.
          * @param id_input - id for stock item
          * @param type - type of stock item
          * @param name - name of stock item
          * @param quantity - quantity of stock item
          * @param level - low level for the stock item
          */
    protected Stock(int id_input, StockType type, String name,int quantity,int level){
        this.stockID=id_input;
        this.stockType=type;
        this.level=level;
        this.name=name;
        this.quantity=quantity;
    }

    public void changeStockName(String newName){
        try{
            PreparedStatement sql = conn.prepareStatement("update stock set name = ? where name = ?");
            sql.setString(1, newName);
            sql.setString(2, this.name);
            sql.execute();
            this.name=newName;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void changeStockType(StockType newName){
        try{
            PreparedStatement sql = conn.prepareStatement("update stock set type = ? where type = ?");
            sql.setString(1, String.valueOf(newName));
            sql.setString(2, String.valueOf(this.stockType));
            sql.execute();
            this.stockType=newName; 
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * overloaded constructor for creating Stock item
     * @param type - type of stock item
     * @param name - name of stock item
     * @param quantity - quantity of stock item
     * @param level - low level for the stock item
     */
    public Stock(StockType type, String name,int quantity,int level){
        this.stockType=type;
        this.level=level;
        this.name=name;
        this.quantity=quantity;
    }

    /**
     * overloaded stock constructor to create stock item
     * @param type - type of stock item
     * @param name - name of stock item
     * @param quantity - quantity of stock item
     */
    public Stock(StockType type, String name,int quantity){
        this.stockType=type;
        this.name=name;
        this.quantity=quantity;
    }

    /**
     * getter method for id
     * @return integer id
     */
    public int getID(){
        return this.stockID;
    }

    public String getType(){
        return String.valueOf(this.stockType);
    }

    /**
     * getter method for Stock item's quantity
     * @return integer quantity of the named stock item
     */
    public static int getQuantity(String name){
        try{
            PreparedStatement sql = conn.prepareStatement("SELECT * FROM `stock` WHERE name=?");
            sql.setString(1, name);
            ResultSet r = sql.executeQuery();
            if(r.next()){
                return r.getInt("quantity");
            }
                return 0;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * function adds or subtracts from the named stock quantity.
     * @param ch character indicating mathematical operation to perform
     * @param qty - quantity to add or subtract from database quantity.
     * @param name -  name of the stock in the database to update.
     * @return boolean true if quantity successfully updated
     */
    public static boolean updateStock(char ch,int qty, String name){
        String sql;
        if (ch=='+'){
            sql = "update `stock` set quantity=quantity+? where name=?";
        }
        else if(ch=='-'){
            sql = "update `stock` set quantity=quantity-? where name=?";
        }
        else{
            return false;
        }
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,qty);
            stmt.setString(2,name);
            stmt.execute();
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * getter for the stock name
     * @return the name of the stock
     */
    public String getStockName(){
        return this.name;
    }

    /**
     * Function deletes an entore record of a stock item from the database.
     * @param name - the name of a stock item to be deleted
     * @throws SQLException
     */
    public static void deleteStock(String name){
        try{
            String sql = "DELETE FROM `stock` WHERE `name` = ?";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1,name);
            p.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * function writes a new stock item to database
     */
    public boolean createStock(){
        // System.out.println(this.name+": "+"[esists method status] "+exists(this.name));
        if(exists(this.name)){
            updateStock('+',quantity, name);
            System.out.println("[updated] "+this.name);
            return true;
        }
        else{
            try {
                PreparedStatement sql = conn.prepareStatement("INSERT INTO `stock` (`type`, `name`, `quantity`, `limit`) VALUES (?, ?, ?, ?)");
                sql.setString(1, String.valueOf(this.stockType));
                sql.setString(2, this.name);
                sql.setInt(3, this.quantity);
                sql.setInt(4, this.level);
                sql.execute();
                System.out.println("[ADDED NEW BEAD]");
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    /**
     * Function creates an ArrayList of Stock items from the database
     * @return inventory list of Stock items/objects in the database
     */
    public static ArrayList<Stock> viewStock(int filter){
        //filter by level
        inventory = new ArrayList<Stock>();
        String stmt = "select * from `stock`";
        
        try {
            PreparedStatement sql = conn.prepareStatement(stmt);
            ResultSet r = sql.executeQuery();
            
            while(r.next()){
                Stock e = new Stock(r.getInt("id"),StockType.valueOf(r.getString("type")),r.getString("name"),r.getInt("quantity"),r.getInt("limit"));
                inventory.add(e);
                // System.out.println("added stock from database");
            }
            if(filter==1){
                return inventory;
            }
            ArrayList<Stock> filtered = new ArrayList<Stock>(); 
            for(Stock stock: inventory){
                if(Stock.getQuantity(stock.getStockName())<=stock.getLevel()){
                    filtered.add(stock);
                }
            }
            return filtered;
        } catch (Exception e) {
            e.printStackTrace();
            return inventory;
        }
    }

    /**
     * Function gets a Stock object from the database.
     * @param name - name of a stock item in the database
     * @return Stock matching the name or null if no match found
     */
    public static Stock viewItem(String name){
        inventory = viewStock(1);
        for(Stock s: inventory){
            if (s.getStockName().equals(name)){
                // System.out.println("INVENTORY ITEM FOUND");
                return s;
            }
        }
        return null;
    }

    public int getLevel(){
        return this.level;
    }

    //Support Functions//

    /**
     * function checks if a named stock exists in the database
     * @param sname - name of stock item
     * @return boolean true if the stock item is found to match the name
     */
    private boolean exists(String sname){
        ResultSet result;
        try {
            PreparedStatement sql = conn.prepareStatement("SELECT * FROM `stock` WHERE name=?");
            sql.setString(1, sname);
            result = sql.executeQuery();
            return result.next();
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * method to convert a Stock item to string
     */
    public String toString(){
        return "[Stock item] "+"id: "+getID()+", name: "+getStockName()+", qty: "+this.quantity+", limmit: "+getLevel();
    }
}
