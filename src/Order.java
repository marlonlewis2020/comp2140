import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Order
{
    private int orderNo;
    private double cost;
    private Date orderDate;
    private int orderQuantity;
    private string pickupLocation;
    private ArrayList <Bracelet> bracelets = new ArrayList <Bracelet>();
    public int customerID;

    private DBConnect db = new DBConnect();
          
    //Connection object created
    private Connection conn = db.dbconnection();

    public Order(Date orderDate, int orderQuantity, string pickupLocation, ArrayList <Bracelet> braceletName)
    {
        this.cost = 0;
        this.orderDate = orderDate;
        this.orderQuantity = orderQuantity;
        this.pickupAddress = pickupLocation;
        this.customerID = customerID;
        for (int i = 0; i < braceletName.size(); i++) 
        {
            cost += (braceletName.get(i)).getCost();
        } 
        this.bracelets = braceletName;
    }

    public int getOrderNo(){return this.orderNo;}
    public Date orderDate(){return this.orderDate;}
    public int orderQuantity(){return this.orderQuantity;}
    public string pickupLocation(){return this.pickupLocation;}
    public int customerID(){return this.customerID;}

    public double getcost(){

        return this.cost;
    }

    public int getOrder(int orderNo) {return orderNo;}

    public void deleteOrder(int orderNo)
    {
       try{
         String query = "Delete from Orders where order_number = ?";
         PreparedStatement preparedStmt = conn.prepareStatement(query);
          preparedStmt.setInt(1, orderNo); 

          preparedStmt.execute();
        }
        
        catch(Exception e)
        {
          System.Out.println("Got an exception!");
          System.Out.println(e.getMessage());
        }
        
    }

    public void updateOrder(int orderNo, string fields, string values)
    {
        ArrayList <string> columns = fields.split(","); 
        ArrayList <string> inputs = values.split(","); 
        String updates = "";
        for (int i = 0; i < columns.size() - 1; i++) 
        {
            updates += columns [i] + "=" + inputs [i] + ",";
        }
        updates += columns [columns.size()] + "=" + inputs [columns.size()];

        try
        {
          String query = "Update orders set ? where order_number = ?";
          PreparedStatement preparedStmt = conn.prepareStatement(query);
          preparedStmt.setString(1, updates);
          preparedStmt.setInt(2, orderNo);

        }

        catch(Exception e)
        {
          System.Out.println("Got an exception!");
          System.Out.println(e.getMessage());
        }
    }

    public void addToDatabase(Order or)
    {
        try{
  
          String query = "insert into Orders (order_number, items, customer_id, pickup_location, total)"
            + " values (?, ?, ?, ?, ?)";
  
          // create the mysql insert prepared statement
          PreparedStatement preparedStmt = conn.prepareStatement(query);
          preparedStmt.setInt(1, or.getOrderNo());
          preparedStmt.setArray(2, this.bracelets);
          preparedStmt.setInt(3, or.getCustomerID());
          preparedStmt.setString(4, or.getpickupAddress());
          preparedStmt.setDouble(5, or.getcost());
          
  
          // execute the preparedstatement
          preparedStmt.execute();
          conn.close();
        }
        catch(Exception e)
        {
          System.Out.println("Got an exception!");
          System.Out.println(e.getMessage());
        }
    }


}