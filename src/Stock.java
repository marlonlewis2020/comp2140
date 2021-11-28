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
    private int id;
    private StockType type;
    private String name;
    private int quantity;
    private int level;
    /*String filter number*/
    // private Connection conn = Authentication.getDbConn();
    private static Authentication auth = new Authentication();

    public Stock(int id_input, StockType type, String name,int quantity,int level){
        this.id=id_input;
        this.type=type;
        this.level=level;
        this.name=name;
        this.quantity=quantity;
    }

    public Stock(StockType type, String name,int quantity,int level){
        this.type=type;
        this.level=level;
        this.name=name;
        this.quantity=quantity;
        
        // auth.setRequest("view stock");
        // DBAccess dba;
        // dba = new DBAccess(auth);
    }

    public int getID(){
        return this.id;
    }

    public static ArrayList<Stock> inventory(){
        ArrayList<Stock> inventory = new ArrayList<Stock>();
        auth.setRequest("view inventory");
        DBAccess dba;
        dba = new DBAccess(auth);
        try {
            ResultSet r = dba.viewAll();
            while(r.next()){
                Stock e = new Stock(r.getInt("id"),StockType.valueOf(r.getString("type")),r.getString("name"),r.getInt("quantity"),r.getInt("limit"));
                inventory.add(e);
            }
            return inventory;
        } catch (Exception e) {
            e.printStackTrace();
            return inventory;
        }
    }

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

    public String getStockName(){
        return name;
    }

    public int getcheckLevel(){
        return this.level;
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
        dba.create(String.valueOf(this.type),this.name,this.quantity,this.level);
        }
        else{
            updateStock('+',quantity, name);
        }
        
    }
}
