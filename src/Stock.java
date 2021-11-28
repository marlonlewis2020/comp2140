/**
 * Stock is an invertory item located in the bead it up ja app.
 * @version 1.1
 * @author Kimani Munn,Marlon Lewis
 */

 import ClassInterface.DBAccess;
 import java.sql.*;
 import Authentication.Authentication;

class Stock{
    private StockType type;
    private String name;
    private int quantity;
    private int level;
    /*String filter number*/
    private Connection conn = Authentication.getDbConn();
    private static DBAccess dba;

    public Stock(StockType type, String name,int quantity,int level){
        this.type=type;
        this.level=level;
        this.name=name;
        this.quantity=quantity;
    }

    public Stock(Authentication auth, StockType type, String name,int quantity,int level){
        this.type=type;
        this.level=level;
        this.name=name;
        this.quantity=quantity;
        dba = new DBAccess(auth);

        String fields = "type,name,quantity,limit,color";
        String values = String.join(",",String.valueOf(this.type),this.name,String.valueOf(this.quantity),String.valueOf(this.level),"n/a");

        ResultSet res = dba.viewSpecific("stock","id,quantity","name="+this.name);
        int q;
        int i;
        try{
            q = res.getInt("quantity");
            i = res.getInt("id");
            if(res.next()){
            updateStock(q, i);
            }
            else{
            createStock(fields, values);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static ResultSet inventory(){
        return dba.viewAll("stock","*");
    }

    public static boolean exists(String sname){
        ResultSet res = dba.viewSpecific("stock","id","name="+sname);
        try{
            if(res.next()){
                return Boolean.valueOf("True");
            }
            return Boolean.valueOf("False");
        }
        catch(SQLException e){
            e.printStackTrace();
            return Boolean.valueOf("False");
        }
    }

    public static int getQuantity(String name){
        try{
            ResultSet r = dba.viewSpecific("stock","quantity","name="+name);
            return Integer.valueOf(r.getInt("quantity"));
        }
        catch(SQLException e){
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
    
    public void updateStock(int value, int id){
        try{
            ResultSet res = dba.viewSpecific("stock","quantity","id="+id);
            int val = res.getInt("quantity")+value;
            dba.update("stock", "quantity", String.valueOf(val), id);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteStock(String name) throws SQLException{
        String sql = "delete from stock where name=?";
        PreparedStatement p = conn.prepareStatement(sql);
        p.setString(1,name);
        p.executeUpdate();
    }

    public void createStock(String fields, String values){
        dba.create("stock",fields,values);
    }

    // public static 

}
