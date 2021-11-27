/**
 * Stock is an invertory item located in the bead it up ja app.
 * @version 1.0
 * @author Kimani Munn
 */

 import DBAccess;
 import java.sql.*;
 import Authentication.Authentication;

class Stock extends DBAccess{
    private String type;
    private String name;
    private int quantity;
    private int level;
    /*String filter number*/
    private Connection conn = Authentication.getDbConn();

    public Stock(String type,String name,int quantity,int level){
        this.type=type;
        this.level=level;
        this.name=name;
        this.quantity=quantity;

        String fields = "type,name,quantity,limit,color";
        String values = String.join(",",this.type,this.name,String.valueOf(this.quantity),String.valueOf(this.level),"n/a");

        ResultSet res =viewSpecific("stock","id,quantity","name="+this.name);

        if(res.next()){
          updateStock(res.getInt("quantity"), res.getInt("id"));
        }
        else{
          createStock(fields, values);
        }

    }
    public String getQuantity(){
        return this.quantity;
    }

    public string getStockName(){
        return name;
    }

    public String getcheckLevel(){
        return this.level;
    }
    
    public void updateStock(int value, int id){
        ResultSet res = viewSpecific("stock","quantity","id="+id);
        int val = res.getInt("quantity")+value;
        update("stock", "quantity", val, id);
    }

    public void deleteStock(string name){
        String sql = "delete from stock where name=?";
        PreparedStatement p = conn.PreparedStatement(sql);
        p.setString(1,name);
        p.executeUpdate();
    }

    public void createStock(String fields, String values){
        create(stock,fields,values);
    }

    public estimate(){
        
    }
}
