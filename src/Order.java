import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;
import Authentication.*;

public class Order
{
    private int orderNo;
    private int phoneNumber;
    private Customer customer;
    private static int nextorderNo = 0;
    private double cost;
    private Date orderDate;
    private ArrayList <String> btypeQuantity;
    private String pickupLocation;
    private ArrayList <String> bracelets = new ArrayList <String>();
    private ArrayList <Order> orders = new ArrayList <Order>();
    public int customerID;
    private String customerName;
    private Connection conn = Authentication.getDbConn(); //Connection object created
    private String orderItems;

    public Order(int customerID, Date orderDate, String btypeQuantity, String bracelets, String pickupLocation, String orderNo, double cost)
    {
      this.customerID = customerID;
      this.orderDate = orderDate;
      this.pickupLocation = pickupLocation;
      this.orderNo = nextorderNo;
      this.cost = cost;
      this.btypeQuantity.addAll(Arrays.asList(btypeQuantity.split(",")));
      //this.bracelets = Arrays.asList(bracelets.split(","));
      this.bracelets.addAll(Arrays.asList(bracelets.split(",")));
      
    }

    public String listtoString(List <String> olist)
    {
      String m = "";
      for (String s: olist)
      {
        m += s + ",";
      }
      return m;
    }

    public Order(int phoneNumber, Date orderDate, ArrayList <String> btypeQuantity, String pickupLocation, ArrayList <Bracelet> braceletName)
    {
        //Used to get bracelet names from database if there are more than one bracelets in an order. (to calulate total cost)
        String temp = "";
        String names = "";
        for (int i = 0; i < braceletName.size(); i++) 
        {
            names += braceletName.get(i).getName() + ",";
            temp += braceletName.get(i).getName() + "," + btypeQuantity.get(i) + ";";
            cost += (braceletName.get(i)).getCost();
        } 
        this.bracelets.addAll(Arrays.asList(names.split(",")));
        this.orderItems = temp;

        this.orderNo = nextorderNo;
        this.phoneNumber = phoneNumber;
        this.cost = 0;
        this.orderDate = orderDate;
        this.btypeQuantity = btypeQuantity;
        this.pickupLocation = pickupLocation;
        //this.customerID = customer.getcustomerID();
        //this.customerName = customerName.getcustomerName();
        nextorderNo++;

           

    }

    public int getOrderNo(){return this.orderNo;}
    public Date getorderDate(){return this.orderDate;}
    public ArrayList <String> getbtypeQuantity(){return this.btypeQuantity;}
    public String getpickupLocation(){return this.pickupLocation;}
    public int getcustomerID(){return this.customerID;}
    public String getcustomerName(){return this.customerName;}
    public double getcost(){return this.cost;}

    // public void deleteOrder(int orderNo)
    // {
    //    try{
    //      String query = "Delete from Orders where order_number = ?";
    //      PreparedStatement preparedStmt = conn.prepareStatement(query);
    //       preparedStmt.setInt(1, orderNo); 

    //       preparedStmt.execute();
    //     }
        
    //     catch(Exception e)
    //     {
    //       e.printStackTrace();
    //       System.Out.println(e.getMessage());
    //     }
        
    // }

    // public void updateOrder(int orderNo, String fields, String values)
    // {
    //     String [] columns = fields.split(","); 
    //     String [] inputs = values.split(","); 
    //     String updates = "";
    //     for (int i = 0; i < columns.size() - 1; i++) 
    //     {
    //         updates += columns [i] + "=" + inputs [i] + ",";
    //     }
    //     updates += columns [columns.size()] + "=" + inputs [columns.size()];

    //     try
    //     {
    //       String query = "Update orders set ? where order_number = ?";
    //       PreparedStatement preparedStmt = conn.prepareStatement(query);
    //       preparedStmt.setString(1, updates);
    //       preparedStmt.setInt(2, orderNo);

    //     }

    //     catch(Exception e)
    //     {
    //       e.printStackTrace();
    //       System.Out.println(e.getMessage());
    //     }
    // }

    public void addToDatabase()
    {
        try{
  
          String query = "insert into Orders (order_number, bracelets, order_quantity, customer_id, pickup_location, order_date, total)"
            + " values (?, ?, ?, ?, ?, ?, ?)";
  
            Date now = new java.sql.Date(((new java.util.Date()).getTime()));

          // create the mysql insert prepared statement
          PreparedStatement preparedStmt = conn.prepareStatement(query);
          preparedStmt.setInt(1, getOrderNo());
          preparedStmt.setString(2, this.orderItems);
          preparedStmt.setString(3, listtoString(btypeQuantity));
          preparedStmt.setInt(4, this.customerID);
          preparedStmt.setString(5, getpickupLocation());
          preparedStmt.setDate(6, now);
          preparedStmt.setDouble(7, getcost());
          
  
          // execute the preparedstatement
          preparedStmt.execute();
          conn.close();
        }
        catch(Exception e)
        {
          e.printStackTrace();
          System.out.println(e.getMessage());
        }
    }

   public ArrayList <Order> populate()
    {
      try{
  
        String query = "select * from orders";

        // create the mysql insert prepared statement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        

        // execute the prepared statement
        ResultSet result = preparedStmt.executeQuery();
        conn.close();
      }

      catch(Exception e)
      {
        e.printStackTrace();
        System.out.println(e.getMessage());
      }

      while (result.next()) 
      {
        Order e = new Order(result.getInt("customer_id"), result.getDate("order_date"), result.getString("order_quantity"), result.getString("bracelets"), result.getString("pickup_location"), result.getString("orderNo"), cost);
        this.orders.add(e);
      }
      this.orders = temp;
      return this.orders;
    }


}