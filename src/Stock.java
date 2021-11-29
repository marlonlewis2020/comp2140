/**
 * Stock is an invertory item located in the bead it up ja app.
 * @version 1.1
 * @author Kimani Munn,Marlon Lewis
 */

import ClassInterface.DBAccess;
import java.sql.*;
import java.util.ArrayList;

import Authentication.Authentication;

class Stock{
    
    private int quantity;
    private StockType stockType;
    private String name;
    private int stockID=0;
    private int level;
    private static ArrayList<Stock> inventory = new ArrayList<Stock>();
    private static Authentication auth = new Authentication();

        // auth.setRequest("view stock");
        // DBAccess dba;
        // dba = new DBAccess(auth);

        /**
         * add int id to attribute 
         * remove the filternum from attributes
         * change checkLevel to getLevel, return an integer
         * updateStock(char ch,int qty, String name), return void
         * viewStock(int filterNum), return ArrayList<Stock>
         */

    public Stock(int id_input, StockType type, String name,int quantity,int level){
        this.stockID=id_input;
        this.stockType=type;
        this.level=level;
        this.name=name;
        this.quantity=quantity;
    }

    public Stock(StockType type, String name,int quantity,int level){
        this.stockType=type;
        this.level=level;
        this.name=name;
        this.quantity=quantity;
    }

    public Stock(StockType type, String name,int quantity){
        this.stockType=type;
        this.name=name;
        this.quantity=quantity;
    }

    public int getID(){
        return this.stockID;
    }

    public static int getQuantity(String name){
        try{
            auth.setRequest("view stock");
            DBAccess dba;
            dba = new DBAccess(auth);
            System.out.println("[Stock - getQuantity method] view stock: "+auth.getRequest());
            ResultSet r = dba.viewSpecific("stock",name);
            if(r!=null){
                r.next();
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
    
    public static void updateStock(char ch,int qty, String name){
        DBAccess dba;
        if (ch=='+'){
            auth.setRequest("edit stock");
            dba = new DBAccess(auth);
            dba.update(qty,name);
        }
        else if(ch=='-'){
            auth.setRequest("use stock");
            dba = new DBAccess(auth);
            dba.update(qty,name);
        }
    }

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
            PreparedStatement p = Authentication.getDbConn().prepareStatement(sql);
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
    public void createStock(){
        if(!exists(name)){
            auth.setRequest("create stock");
            DBAccess dba;
            dba = new DBAccess(auth);
            dba.create(String.valueOf(this.stockType),this.name,this.quantity,this.level);
            System.out.println("created");
        }
        else{
            System.out.println("not created");
            updateStock('+',quantity, name);
        }
    }

    /**
     * Function creates an ArrayList of Stock items from the database
     * @return
     */
    public static ArrayList<Stock> viewStock(){
        //filter by level
        inventory = new ArrayList<Stock>();
        auth.setRequest("view inventory");
        DBAccess dba;
        dba = new DBAccess(auth);
        try {
            ResultSet r = dba.viewAll();
            while(r.next()){
                Stock e = new Stock(r.getInt("id"),StockType.valueOf(r.getString("type")),r.getString("name"),r.getInt("quantity"),r.getInt("limit"));
                inventory.add(e);
                System.out.println("added stock from database");
            }
            return inventory;
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
        inventory = viewStock();
        for(Stock s: inventory){
            System.out.println(s.getStockName()+", "+name);
            if (s.getStockName()==name){
                System.out.println("INVENTORY ITEM FOUND");
                return s;
            }
        }
        return null;
    }

    public int getLevel(){
        return this.level;
    }

    //Support Functions//

    private boolean exists(String sname){
        auth.setRequest("view stock");
        System.out.println("[Stock - exists method] request: "+auth.getRequest());
        DBAccess dba;
        dba = new DBAccess(auth);
        ResultSet res = dba.viewSpecific("stock",sname);
        System.out.println("[Stock - exists method] res: "+res);
        if(res!=null){
            System.out.println("testin bool: "+Boolean.valueOf("True"));
            return Boolean.valueOf("True");
        }
        System.out.println("testin bool: "+Boolean.valueOf("False"));
        return Boolean.valueOf("False");
        
    }

    public String toString(){
        return "[Stock item] "+"id: "+getID()+", name: "+getStockName()+", qty: "+this.quantity+", limmit: "+getLevel();
    }
}
