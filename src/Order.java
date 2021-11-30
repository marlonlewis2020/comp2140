//import java.util.ArrayList;
//import java.util.Date;
import java.sql.Connection;
//import java.sql.ResultSet;
import java.sql.PreparedStatement;
import Authentication.Authentication;

public class Order
{
    // private int orderNo;
    // private int phoneNumber;
    // private Customer customer;
    // private static int nextorderNo = 0;
    //private ArrayList <Order> orders = new ArrayList <Order>();
    // private String customerName;
    //private String orderItems;
    //private ArrayList <Bracelet> allBracelets = new ArrayList <Bracelet>();
    private java.sql.Date orderDate;
    private int customerID;
    private String bracelets = "";
    private String braceletQuantities;
    private String pickupLocation;
    private double cost;
    
    private Connection conn = Authentication.getDbConn(); //Connection object created

    // public String listtoString(List <String> olist)
    // {
    //   String m = "";
    //   for (String s: olist)
    //   {
    //     m += s + ",";
    //   }
    //   return m;
    // }

    // public Order(int phoneNumber, Date orderDate, String braceletQuantities, String pickupLocation, String braceletNames)
    // {
    //     //Used to get bracelet names from database if there are more than one bracelets in an order. (to calulate total cost)
    //     String temp = "";
    //     String names = "";
    //     for (int i = 0; i < braceletNames.size(); i++) 
    //     {
    //         names += braceletNames.get(i).getName() + ",";
    //         temp += braceletNames.get(i).getName() + "," + braceletQuantities.get(i) + ";";
    //         cost += (braceletNames.get(i)).getCost();
    //     } 
    //     this.bracelets.addAll(Arrays.asList(names.split(",")));

    //     this.cost = 0;
    //     this.orderDate = orderDate;
    //     this.braceletQuantities = braceletQuantities;
    //     this.pickupLocation = pickupLocation;
    //     //this.customerID = customer.getcustomerID();
    //     //this.customerName = customerName.getcustomerName();
    // }

    public Order(int cusId, String braceletQuantities, String bracelets, String pickupLocation)
    {
      this.bracelets = bracelets;
      this.braceletQuantities = braceletQuantities;
      this.pickupLocation = pickupLocation;
      this.orderDate = new java.sql.Date(new java.util.Date().getTime());
      this.cost = 10.00; //calculate the cost based on items
      this.customerID = cusId;
      // addToDatabase();
    }

    public boolean addToDatabase()
    {
        try{
          String query = "insert into Orders (bracelets, order_quantity, customer_id, pickup_location, order_date, total) values (?, ?, ?, ?, ?, ?)";

          // create the mysql insert prepared statement
          PreparedStatement preparedStmt = conn.prepareStatement(query);
          preparedStmt.setString(1, this.bracelets);
          preparedStmt.setString(2, this.braceletQuantities);
          preparedStmt.setInt(3, this.customerID);
          preparedStmt.setString(4, this.pickupLocation);
          preparedStmt.setDate(5, this.orderDate);
          preparedStmt.setDouble(6, this.cost);
          
          // execute the preparedstatement
          preparedStmt.execute();
          System.out.println("[ADDED TO DATABASE]");
          return true;
        }
        catch(Exception e)
        {
          e.printStackTrace();
          System.out.println(e.getMessage());
          return false;
        }
    }

    // public int getOrderNo(){return this.orderNo;}
    // public Date getorderDate(){return this.orderDate;}
    // public ArrayList <String> getbraceletQuantities(){return this.braceletQuantities;}
    // public String getpickupLocation(){return this.pickupLocation;}
    // public int getcustomerID(){return this.customerID;}
    // public String getcustomerName(){return this.customerName;}
    // public double getcost(){return this.cost;}

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

    

  //  public ArrayList <Order> populate()
  //   {
  //     try{
  
  //       String query = "select * from orders";

  //       // create the mysql insert prepared statement
  //       PreparedStatement preparedStmt = conn.prepareStatement(query);
        

  //       // execute the prepared statement
  //       ResultSet result = preparedStmt.executeQuery();
  //       conn.close();
  //     }

  //     catch(Exception e)
  //     {
  //       e.printStackTrace();
  //       System.out.println(e.getMessage());
  //     }

  //     while (result.next()) 
  //     {
  //       Order e = new Order(result.getInt("customer_id"), result.getDate("order_date"), result.getString("order_quantity"), result.getString("bracelets"), result.getString("pickup_location"), result.getString("orderNo"), cost);
  //       this.orders.add(e);
  //     }
  //     this.orders = temp;
  //     return this.orders;
  //   }


}