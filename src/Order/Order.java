package Order;
/**
 * class is responsible for creating order objects, querying these orders and storing the details to the database
 * @version 1.1
 * @author Mercedes Smith, Marlon Lewis
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.sql.PreparedStatement;
import Authentication.Authentication;
import Inventory.Bracelet;
import Inventory.Stock;
import Inventory.StockType;

import java.sql.Date;

public class Order
{
  private int orderNo;
  public static ArrayList <Order> orders = new ArrayList <Order>();
  private Date orderDate;
  private int customerID;
  private String bracelets = "";
  private String braceletQuantities;
  private String pickupLocation;
  private double cost;
  private Connection conn = Authentication.getDbConn(); //Connection object created
  private String size;

    /**
     * Function used to create new orders and add it to the database
     * @param cusPhoneNumber - phone number for customer
     * @param cusName - name of customer
     * @param braceletQuantities - quantities for each bracelet in the order, in the same order of the string of bracelets in the order
     * @param bracelets - the string of bracelets in the order
     * @param pickupLocation - customer's pickup location
     */
    public Order(String cusPhoneNumber, String cusName, String sizes, String braceletQuantities, String bracelets, String pickupLocation)
    {
      this.size = sizes;
      System.out.println("[NEW CONSTRUCTOR USED] SIZE: "+this.size);
      reducer(bracelets,braceletQuantities);
      String[] bsizes = this.size.split(",");      
      if(verify(bsizes)){
        // verified beads exist and quantity of each bead required to make each bracelet is within estimates
        // PROCEEDING TO CREATE ORDER
        this.pickupLocation = pickupLocation;
        this.orderDate = new java.sql.Date(new java.util.Date().getTime());
        this.cost = calcTotalCost(braceletQuantities, bracelets); //calculate the cost based on items and quantities in bracelets and braceletQuantities respectively. 
        this.customerID = getCusId(cusName,cusPhoneNumber);
        System.out.println(String.format("[line 58] bracelets: '%s' \nQuantities: %s \nSizes: %s \nPickupLocation: %s \nCost: %s", this.bracelets,this.braceletQuantities,this.size,this.pickupLocation,this.cost));

        addToDatabase();      }
    }

    /**
     * overloaded constructor for creating an Order using the older database configuration with the stock table 
     * that doesn't have a size field
     */
    public Order(String cusPhoneNumber, String cusName, String braceletQuantities, String bracelets, String pickupLocation){
      setSizesSmall(bracelets);
      reducer(bracelets, braceletQuantities);
      if(verify(this.size.split(","))){
        this.pickupLocation = pickupLocation;
        this.orderDate = new java.sql.Date(new java.util.Date().getTime());
        this.cost = calcTotalCost(braceletQuantities, bracelets); //calculate the cost based on items and quantities in bracelets and braceletQuantities respectively. 
        this.customerID = getCusId(cusName,cusPhoneNumber);
        System.out.println(String.format("[line 74] bracelets: '%s' \nQuantities: %s \nSizes: %s \nPickupLocation: %s \nCost: %s", this.bracelets,this.braceletQuantities,this.size,this.pickupLocation,this.cost));

        addToDatabase();
      }
    }

    /**
     * Function creates 2 separate strings without duplicates.
     * One string contains all the order items, separated by ','.
     * The other string contains all the order quantities, separated by ','.
     * These strings are stored in their respective attributes, to be added to the database where/when applicable.
     * the bracelets, braceletQuantities and size fields must be set before reducer can be used.
     * this function consolidates duplicate order items with their respective order quantities, preventing unnecessary duplicate items being written
     */
    private void reducer(String b, String q){
      String x="";
      String y="";
      ArrayList<Integer> k = new ArrayList<Integer>();
      HashMap<String,Integer> c = new HashMap<String,Integer>();

      String[] bs = b.split(",");
      String[] qs = q.split(",");
      int bl = bs.length;
      for(int i = 0; i < bl; i++){
        c.put(bs[i],0);
        // System.out.println(String.valueOf(c.get(bs[i]))+" [VALUE FOR C] "+c.toString());
        if(c.get(bs[i])!=null){
          k.add(i);
        }
      }
      System.out.println("[NUMBER OF INDEXES] "+k.size());
      for(int i = 0; i < bl; i++){
        c.put(bs[i],c.get(bs[i])+Integer.valueOf(qs[i]));
      }
      String r = c.toString().substring(1).replace('}', ' ').strip();
      // System.out.println("[REDUCER ========== ]"+r);
      String[] bqp = r.split(",");
      for(String pair: bqp){
        String[] par = pair.split("=");
        x+=par[0]+",";
        y+=par[1]+",";
      }
      this.bracelets=x.substring(0, x.length()-1);
      this.braceletQuantities=y.substring(0, y.length()-1);
      String val = this.size;
      String[] sa = val.split(",");
      // System.out.println("[bracelets] "+this.bracelets+"\n[quantities] "+this.braceletQuantities+"\n[sizes] "+val);
      // update sizes to match bracelet/qty strings
      String z="";
      int g = this.bracelets.split(",").length;
      for(int p=0; p<g; p++){
        z+=sa[k.get(p)]+",";
        System.out.println("[PRINTING SA[P]] "+sa[p]);
      }
      // System.out.println("[STRING OF SIZES] Z: "+z);
      this.size=z.substring(0,z.length()-1);
      System.out.println("\n[bracelet sizes] "+this.size);
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
    public Order(int cus_id, String sizes, String order_quantity, String bracelets, String pickup_location,int order_number, Double total, Date order_date){
      this.customerID = cus_id;
      this.orderNo = order_number;
      this.size = sizes;
      reducer(bracelets, order_quantity);
      this.pickupLocation = pickup_location;
      this.orderDate = order_date;
      this.cost = total;
    }

    /**
     * overloaded constructor for loading Orders from the old stock table in database that doesn't have the size field.
     */
    public Order(int cus_id, String order_quantity, String bracelets, String pickup_location,int order_number, Double total, Date order_date){
      this.customerID = cus_id;
      this.orderNo = order_number;
      setSizesSmall(bracelets);
      reducer(bracelets, order_quantity);
      this.pickupLocation = pickup_location;
      this.orderDate = order_date;
      this.cost = total;
      
    }

    /**
     * Function works along with the old database constunctor that doesn't accept size as a parameter. 
     * In these situations, the system automatically assigns the size small to each bracelet in the order.
     */
    private void setSizesSmall(String bracelets){
      String sizes = "";
      for(int i = 0; i<bracelets.split(",").length;i++){
        sizes+="SMALL,";
      }
      this.size = sizes.substring(0, sizes.length()-1);
    }

    /**
     * Function verifies if enough stock is availale to make each of the bracelets in the order. 
     * @param bsizes the precisely ordered/arranged string array of sizes for each bracelet in the order.
     * @return boolean value true if the order can be made with the available stock or false if it cannot.
     */
    private boolean verify(String[] bsizes){
      int sum = 0;
      int count= 0;
      for(String bx: this.bracelets.split(",")){
        Bracelet brace = Bracelet.searchByName(bx);

        //checks to ensure the beads required to make the bracelet exists
        // CHECKING BEAD AVAILABILITY FOR ALL BRACELETS IN ORDER
        for(String s: Bracelet.searchByName(bx).getSmallBeadQty().split(";")){
          
          Stock stock = new Stock (StockType.Beads,s.split("-")[0], 0, 0);
          try {
            String sql = "select * from stock where name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,s.split("-")[0]);
            ResultSet result = stmt.executeQuery();
            
            if(!result.next()){
              stock.createStock();
              this.bracelets = "";
              this.braceletQuantities = "";
              this.size = "";
              System.out.println("[ORDER NOT CREATED] ONE OR MORE ITEMS IN THE ORDER DOES NOT HAVE STOCK VALUE");
              return false;
            }
          } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("[DATABASE ERROR WHILE CREATING ORDER] ORDER NOT CREATED! ");
            System.out.println(String.format("select * from stock where name = '%s'",s.split("-")[0]));
            //return false;
          }  
        }

        // checking to ensure we can make the total number of bracelets in the order
        // CHECKING THE BRACELET QUANTITY FOR EACH BRACELET IS WITHIN ESTIMATES
        if(bsizes[count].toUpperCase().equals("SMALL")){
          System.out.println("Beadcount: "+bsizes[count]);
          int est = brace.estimateQty().get(0);
          sum+=Integer.valueOf(this.braceletQuantities.split(",")[count]);
          
          if(sum>est){
            System.out.println("SMALL [BRACELET QUANTITY EXCEEDS STOCK AVAILABILITY] ORDER NOT CREATED");
            return false;
          }
          count++;
          return true;
        }
        
        else if(bsizes[count].toUpperCase().equals("MEDIUM")){
          int est = brace.estimateQty().get(1);
          sum+=Integer.valueOf(this.braceletQuantities.split(",")[count]);
          if(sum>est){
            System.out.println("MEDIUM [BRACELET QUANTITY EXCEEDS STOCK AVAILABILITY] ORDER NOT CREATED");
            return false;
          }
          count++;
          return true;
        }

        else if(bsizes[count].toUpperCase().equals("LARGE")){
          int est = brace.estimateQty().get(2);
          sum+=Integer.valueOf(this.braceletQuantities.split(",")[count]);
          if(sum>est){
            System.out.println("LARGE [BRACELET QUANTITY EXCEEDS STOCK AVAILABILITY] ORDER NOT CREATED");
            return false;
          }
          count++;
          return true;
        }
        else{
          System.out.println("[INVALID SIZE ENTERED] NO MATCHNG SIZE FOUND IN DATABASE FOR '"+String.valueOf(bsizes[count])+"'!");
          return false;
        }
      }
      return true;
    }
    
    /**
     * function gets the total cost of order
     * @return int - total cost of order
     */ 
    private double calcTotalCost(String braceletQuantities, String bracelets){
      double total = 0;
      ArrayList<String> items = new ArrayList<String>();
      items.addAll(Arrays.asList(bracelets.split(",")));
      ArrayList<String> qtys = new ArrayList<String>();
      qtys.addAll(Arrays.asList(braceletQuantities.split(",")));
      try {
        for(int i = 0; i<items.size();i++){
          Bracelet bracelet = Bracelet.searchByName(items.get(i));
          total+=bracelet.getCost()*Integer.valueOf(qtys.get(i));
        }
      } catch (NullPointerException e) {
        System.out.println("[Error] No bracelet exists for one or more items in yout order!");
      }
      return total;
    }

    /**
     * function gets the customer id for the order from the database
     * @param cusName - name of customer
     * @param cusPhoneNumber - customer's phone number
     * @return customer id
     */
    private int getCusId(String cusName,String cusPhoneNumber){     
      return Customer.getCusId(cusName, cusPhoneNumber, this.pickupLocation);
        
    }

    /**
     * adds an order to the database
     * @return boolean true if added to the database, false if unsuccessful
     */
    public boolean addToDatabase()
    {
      try{
        String query = "insert into Orders (bracelets, order_quantity, customer_id, pickup_location, order_date, total, size) values (?, ?, ?, ?, ?, ?, ?)";

        // create the mysql insert prepared statement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, this.bracelets);
        preparedStmt.setString(2, this.braceletQuantities);
        preparedStmt.setInt(3, this.customerID);
        preparedStmt.setString(4, this.pickupLocation);
        preparedStmt.setDate(5, this.orderDate);
        preparedStmt.setDouble(6, this.cost);
        preparedStmt.setString(7,this.size);
        
        // execute the preparedstatement
        preparedStmt.execute();
        
        update('-'); 
        Stock.viewStock(1);
        populate();
        Customer.populate();
        System.out.println("[ADDED TO DATABASE]");
        return true;
      }
      catch(Exception e)
      {
        e.printStackTrace();
        System.out.println("EXCEPTION ABOVE CAUGHT FROM PREVIOUS BLOCK");
        try {
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
          
          update('-'); 
          Stock.viewStock(1);
          populate();
          Customer.populate();
          System.out.println("[ADDED TO DATABASE]");
          return true;
          } catch (Exception exc) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
          }
        }
      }

    /**
     * function updates the beadcount for the beads involved in creating a specific bracelet
     * @param ch the + or - operator character symbolizing either of those 2 operations to perform to the database
     * @return boolean value indicating the status utcome of ths update process
     */
    private boolean update(char ch){
      String[] allbeads = this.size.split(","); //{"SMALL","SMALL"} - SIZES (EACH BRACELET)
        
      String[] bracelets = this.bracelets.split(","); //{"b1","b1"} - NAMES (EACH BRACELET)
      String[] qtys = this.braceletQuantities.split(","); //splitting quantitied "5,3" into {"5","3"} - QTY (EACH BRACELET)
      for(int i = 0; i < bracelets.length; i++){ // INDEXING {0,1} - INDEX FOR EACH BRACELET IN ORDER
        // System.out.println("Value of i: "+i); 
        // System.out.println("sizes: "+allbeads[i]); 
        int qty = Integer.valueOf(qtys[i]); // {"2","3"}[i] | i.e. qty = {"2","3"}[0] = 5
        String size = allbeads[i]; // SIZE (SPECIFIC BRACELET NAME) | {"SMALL","SMALL"}[i] | i.e {"SMALL","SMALL"}[0] = "SMALL"
        String name = bracelets[i]; // NAME (SPECIFIC BRACELET NAME) | {"b1","b1"}[i]  | i.e {"b1","b1"}[0] = "b1"
        Bracelet brace = Bracelet.searchByName(name); //BRACELET (FINDS SPECIFIC BRACELET) | Bracelet.searchByName("b1")
        System.out.println("BRACELET NAME: "+name);
        String beadcount = getB(brace,size); // "yellow-10;green-16" BEADCOUNT STRING BY SIZE (SPECIFIC BRACELET/SIZE)
        System.out.println("[BEADCOUNT TEST]");

        if(!processOrder(ch,beadcount,qty)){ //("yellow-10;green-16",5)
          return false;
        }
      }
      return true;
    }

    /**
     * function produces the beadcount information for making the specific size of a specific bracelet
     * @param b Bracelet object
     * @param s String size for the bracelet b
     * @return String representing the beadcount information for making the specific size of the specified Bracelet b
     */
    private String getB(Bracelet b, String s){
      s=s.toUpperCase();
      if(s.equals("SMALL")){
        return b.getSmallBeadQty();
      }
      else if(s.equals("MEDIUM")){
        return b.getMedBeadQty();
      }
      else{
        return b.getLgBeadQty();
      }
    }

    /**
     * Function updates the bead count in the database for all the beads required to make a specific sized bracelet in the order 
     * when an order is created or deleted. For each bracelet, the function retrieves the beads and qtys (i.e "yellow-10;green-16") 
     * required to make a unit, call the updateStock function on each bead and include the bracelet qty to calculate the adjustment 
     * to stock quantity.
     * @param ch is the operator either + or - to perform on the quantity of a bead item in the database
     * @param bracelet is the String formatted beadcount for all the beads required for making the a specific bracelet
     * @param qty is the quantity of a specific bracelet in the order
     * @return booloean value representing the update status of the bead
     */
    private boolean processOrder(char cr, String bracelet, int qty){ // ("yellow-10;green-16",5)
      try{
        String[] beads = bracelet.split(";"); //{"yellow-10","green-16"}
        for(String bead: beads){ //"yellow-10" | "green-16"
          String[] parts = bead.split("-"); // {"yellow","10"}
          String name = parts[0].toLowerCase(); // "yellow" | "green"
          int beadcount = Integer.valueOf(parts[1]); //10 | 16
          int quantity = beadcount*qty; //10*5 | 16*5
          Stock.updateStock(cr,quantity,name); //updateStock('-',50,"yellow") | updateStock('-',80,"green")
          System.out.println(name+" bead updated!"); // "yellow bead updated!" | "yellow bead updated!"
        }
        return true;
      }catch(Exception e){
        e.printStackTrace();
        System.out.println(e.getMessage());
        return false;
      }
    }

    /**
     * deletes order from database and returns beads to inventory
     * 
     */
    public void deleteOrder(){
      try {
        populate();
        String query = "Delete from Orders where order_number = ?";
        PreparedStatement preparedStmt = Authentication.getDbConn().prepareStatement(query);
        preparedStmt.setInt(1, this.orderNo); 
        preparedStmt.execute();
        for (Order o:orders){if(o.orderNo==orderNo){orders.remove(orders.indexOf(o));}}
        update('+');
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    /**
     * function updates an order
     * @param orderNo - order number of the order to be updated
     * @param columns - ArrayList of Strings containing field names of the table for the record that should be updated.
     * @param inputs - ArrayList of Object containing values to enter into the fields in the same order as the fields were specified.
     * @return boolean confirming successful update or not
     */
    public boolean updateOrder(int orderNo, ArrayList<String> columns, ArrayList<Object> inputs)
    { 
      for (int i = 0; i < columns.size(); i++) 
      {
        if(makeChange(columns,inputs,i)==false){
          return false;
        }
      }
      // makeChange updates the database. 
      // Now apply changes to this object. 
      // recalculate total for database and this object.
      try{
        PreparedStatement prep = conn.prepareStatement("select bracelets,order_quantity from orders where order_number=?");
        prep.setInt(1,this.customerID);
        ResultSet orderDetails = prep.executeQuery();
        this.braceletQuantities = orderDetails.getString("order_quantity");
        this.bracelets = orderDetails.getString("bracelets");
        this.pickupLocation = orderDetails.getString("pickup_location");
        this.size = orderDetails.getString("size");
        this.customerID = orderDetails.getInt("customer_id");
        this.orderDate = orderDetails.getDate("order_date");
        this.cost = calcTotalCost(this.braceletQuantities, this.bracelets);

        PreparedStatement preparedStmt = conn.prepareStatement("Update orders set total=? where order_number = ?");
        preparedStmt.setDouble(1, this.cost);
        preparedStmt.executeUpdate();
        System.out.println(String.format("[SUCCESSFULLY UPDATED %s IN ORDER]", "TOTAL COST"));
        return true;
        
      }
      catch(Exception e){
        e.printStackTrace();
        return false;
      }
    }

    private boolean makeChange(ArrayList<String> columns, ArrayList<Object> inputs, int i){
      try {
        String query = String.format("Update orders set %s=? where order_number = ?",columns.get(i));
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setObject(1, inputs.get(i)); //for Date, String or Double
        preparedStmt.setInt(2, this.orderNo);
        preparedStmt.executeUpdate();
        System.out.println(String.format("[SUCCESSFULLY UPDATED %s IN ORDER]", columns.get(i).toUpperCase()));
        return true;
      } catch (Exception e) {  
        e.printStackTrace();
        System.out.println(e.getMessage());
        System.out.println(String.format("[FAILED TO UPDATE %s IN ORDER]",columns.get(i).toUpperCase()));
        return false;
      }
    }

    /**
     * function populates a list of orders from the database
     * @return ArrayList<Order>
     */
   public static ArrayList <Order> populate()
   {
     ResultSet result;
    try{
      String query = "select * from orders";

      // create the mysql insert prepared statement
      PreparedStatement preparedStmt = Authentication.getDbConn().prepareStatement(query); //.prepareStatement(query);
      
      // execute the prepared statement
      result = preparedStmt.executeQuery();
    
      while (result.next()) 
      {
        try{
          Order e = new Order(result.getInt("customer_id"), result.getString("size"), result.getString("order_quantity"), result.getString("bracelets"), 
          result.getString("pickup_location"),result.getInt("order_number"), result.getDouble("total"),result.getDate("order_date"));
          orders.add(e);
        }
        catch(SQLException exc){
          Order e = new Order(result.getInt("customer_id"), result.getString("order_quantity"), result.getString("bracelets"), 
          result.getString("pickup_location"),result.getInt("order_number"), result.getDouble("total"),result.getDate("order_date"));
          orders.add(e);
        }
      }
      return orders;
    }
    catch(Exception e)
    {
      e.printStackTrace();
      System.out.println(e.getMessage());
      return orders;
    }
  }

  // ---------- GETTERS ---------- //

    /**
     * function gets the set order number
     * @return int - the order number that has been set.
     */
    public int getOrderNo(){return this.orderNo;}

    /**
     * function gets the oder date
     * @return Date - date the order was placed
     */
    public Date getorderDate(){return this.orderDate;}

    /**
     * function gets the ordered string with braceletquantities for the order
     * @return String - the ordered string with braceletquantities for the order
     */ 
    public String getbraceletQuantities(){return this.braceletQuantities;}

    /**
     * function gets the pickup location chosen by Customer
     * @return String - pickup location chosen by Customer
     */ 
    public String getpickupLocation(){return this.pickupLocation;}

    /**
     * function gets the customer ID from object
     * @return int - customer id for order
     */ 
    public int getcustomerID(){return this.customerID;}

    /**
     * function gets the set cost of this order
     * @return int - cost of the order
     */ 
    public double getCost(){
      if(this.cost>0){
        return cost;
      }
      return calcTotalCost(braceletQuantities, bracelets);
      }

    /**
     * Function locates the customer details for this order's customer. 
     * @return the customer details from the search results are provided as a ResultSet. 
     */
    private ResultSet getCustomer(){
      try{
        PreparedStatement cus = conn.prepareStatement("select * from customers where id=?");
        cus.setString(1,String.valueOf(this.customerID));
        ResultSet customer = cus.executeQuery();
        customer.next();
        return customer;
      }
      catch(Exception e){
        e.printStackTrace();
        return null;
      }
    }

    /**
     * function converts the order to a String
     * @return object String
     */ 
    public String toString(){
      String pl = "";
      String[] b = this.bracelets.split(",");
      String[] q = this.braceletQuantities.split(",");
      String[] s = this.size.split(",");
      System.out.println("");
      for(int n = 0; n<b.length; n++){
        pl+="Bracelet: "+b[n]+" \t Size: "+s[n]+" \t Quantity: "+q[n]+" \t Unit Cost: "+Bracelet.searchByName(b[n]).getCost()+"\n";
      }
      ResultSet customer = getCustomer();
      String st = "\nOrder #: "+getOrderNo()+"\tPickup: "+this.pickupLocation+"\n";
      String ci="";
      try{
        ci = "Customer id: "+this.customerID+"\tCustomer: "+customer.getString("name")+"\tCustomer tel: "+customer.getString("telephone")+"\n";
      }
      catch(Exception e){
        e.printStackTrace();
      }
      return st+ci+pl+"Total: $"+String.valueOf(getCost())+"0";
    }
  }