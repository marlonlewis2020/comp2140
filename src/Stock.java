/**
 * Stock is an invertory item located in the bead it up ja app.
 * @version 1.0
 * @author Kimani Munn
 */

 import ClassInterface.DBAccess;
 import java.sql.*;
 import Authentication.Authentication;

class Stock{
    private String type;
    private String name;
    private int quantity;
    private int level;
    /*String filter number*/
    private Connection conn = Authentication.getDbConn();
    DBAccess dba;

    public Stock(Authentication auth, String type,String name,int quantity,int level){
        this.type=type;
        this.level=level;
        this.name=name;
        this.quantity=quantity;
        dba = new DBAccess(auth);

        String fields = "type,name,quantity,limit,color";
        String values = String.join(",",this.type,this.name,String.valueOf(this.quantity),String.valueOf(this.level),"n/a");

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

    public int getQuantity(){
        return this.quantity;
    }

    public String getStockName(){
        return name;
    }

    public int getcheckLevel(){
        return this.level;
    }
    
    public void updateStock(int value, int id){
        ResultSet res = dba.viewSpecific("stock","quantity","id="+id);
        int val = res.getInt("quantity")+value;
        dba.update("stock", "quantity", val, id);
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

}
