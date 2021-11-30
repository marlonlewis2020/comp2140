/**
 * class is responsible for creating order objects, querying these orders and storing the details to the database
 * @version 1.0
 * @author Mercedes Smith, Marlon Lewis
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.PreparedStatement;
import Authentication.Authentication;
import java.sql.Date;

public class Order
{
    private int orderNo;
    private ArrayList <Order> orders = new ArrayList <Order>();
    private Date orderDate;
    private int customerID;
    private String bracelets = "";
    private String braceletQuantities;
    private String pickupLocation;
    private double cost;
    
    private Connection conn = Authentication.getDbConn(); //Connection object created

    public Order(String cusPhoneNumber, String cusName, String braceletQuantities, String bracelets, String pickupLocation)
    {
      this.bracelets = bracelets;
      this.braceletQuantities = braceletQuantities;
      this.pickupLocation = pickupLocation;
      this.orderDate = new java.sql.Date(new java.util.Date().getTime());
      this.cost = getTotalCost(braceletQuantities, bracelets); //calculate the cost based on items and quantities in bracelets and braceletQuantities respectively. 
      this.customerID = getCusId(cusName,cusPhoneNumber);
    }

    /**
     * Method used when loading orders into order objects from the database
     * @param cus_id - int representing customer if
     * @param order_quantity - String representing the number of each bracelet in the bracelets string in order
     * @param bracelets - String representing the bracelets in an Order
     * @param pickup_location - String representing the pickup location
     * @param order_number - int representing the order id for the order
     * @param total - total cost of the Order
     * @param order_date - Date when the order was placed
     */
    public Order(int cus_id, String order_quantity, String bracelets, String pickup_location,int order_number, Double total, Date order_date){
      this.customerID = cus_id;
      this.orderNo = order_number;
      this.braceletQuantities = order_quantity;
      this.bracelets = bracelets;
      this.pickupLocation = pickup_location;
      this.orderDate = order_date;
      this.cost = total;
    }

    public int getOrderNo(){return this.orderNo;}

    public Date getorderDate(){return this.orderDate;}

    public String getbraceletQuantities(){return this.braceletQuantities;}

    public String getpickupLocation(){return this.pickupLocation;}

    public int getcustomerID(){return this.customerID;}

    public double getcost(){return this.cost;}

    public String toString(){
      return getOrderNo()+": \n Bracelets: "+this.bracelets+"\n Quantities: "+getbraceletQuantities()+"\n Total: "+String.valueOf(getcost());
    }

    private int getTotalCost(String braceletQuantities, String bracelets){
      int total = 0;
      ArrayList<String> items = new ArrayList<String>();
      items.addAll(Arrays.asList(bracelets.split(",")));
      ArrayList<String> qtys = new ArrayList<String>();
      qtys.addAll(Arrays.asList(braceletQuantities.split(",")));
      for(int i = 0; i<items.size();i++){
        total+=Bracelet.getCost(items.get(i))*Integer.valueOf(qtys.get(i)); // call a static function from Taye-Vaughn bracelet that gets the cost of a specific bracelet by name
      }
      return total;
    }

    private int getCusId(String cusName,String cusPhoneNumber){
      String sql = "select * from customers where name = ? and telephone = ?";
      try {
        
        PreparedStatement p = conn.prepareStatement(sql);
        p.setString(1, cusName);
        p.setLong(2, Long.parseLong(cusPhoneNumber));
        ResultSet r = p.executeQuery();
        if(r.next()){
          return r.getInt("id");
        }
        else{
          // call create Customer
          // get customer id
          // return customer id
          return 0;
        }
      } catch (Exception e) {
        e.printStackTrace();
        return 0;
      }
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
          e.printStackTrace();
          System.out.println(e.getMessage());
        }
    }

    public boolean updateOrder(int orderNo, String fields, String values)
    { 
      boolean result = true;
        ArrayList<String> columns = new ArrayList<String>();
        columns.addAll(Arrays.asList(fields.split(","))); 
        ArrayList<String> inputs = new ArrayList<String>();
        inputs.addAll(Arrays.asList(values.split(","))); 
        for (int i = 0; i < columns.size(); i++) 
        {
          try {
            String query = String.format("Update orders set %s=? where order_number = ?",columns.get(i));
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, inputs.get(i));
            preparedStmt.setInt(2, orderNo);
            preparedStmt.executeUpdate();
            System.out.println(String.format("[SUCCESSFULLY UPDATED %s IN ORDER]", columns.get(i).toUpperCase()));
          } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println(String.format("[FAILED TO UPDATE %s IN ORDER]",columns.get(i).toUpperCase()));
            return false;
          }
        }
        return result;
    }

    

   public ArrayList <Order> populate()
   {
     ResultSet result;
    try{
      String query = "select * from orders";

      // create the mysql insert prepared statement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      
      // execute the prepared statement
      result = preparedStmt.executeQuery();
    
      while (result.next()) 
      {
        Order e = new Order(result.getInt("customer_id"), result.getString("order_quantity"), result.getString("bracelets"), 
        result.getString("pickup_location"),result.getInt("order_number"), result.getDouble("total"),result.getDate("order_date"));
        this.orders.add(e);
      }
      return this.orders;
    }
    catch(Exception e)
    {
      e.printStackTrace();
      System.out.println(e.getMessage());
      return this.orders;
    }
  }

  }