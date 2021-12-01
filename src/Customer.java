/**
 * This class stores customer details along with editing and deleting these details from the database
 * @version 1.0
 * @author Mercedes Smith, Taye-Vaughn Jones
 */

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Authentication.Authentication;

public class Customer
{
    private String phoneNumber;
    private String customerName;
    private String pickupLocation;
    private static ArrayList <Customer> customers = new ArrayList <Customer>();
    private Connection conn = Authentication.getDbConn(); //Connection object created
    private int id;


    /**
     * Method used when loading customer into customer objects from the database
     * @param phoneNumber - String representing Customer's phone number
     * @param customerName - String representing Customer's name 
     * @param pickupLocation - String representing Customer's pickup location
     */

    public Customer(String phoneNumber, String customerName, String pickupLocation)
    {
        this.phoneNumber = phoneNumber;
        this.customerName = customerName;
        this.pickupLocation = pickupLocation;
    }


    public String getphoneNumber() {return this.phoneNumber;}
    public String getcustomerName() {return this.customerName;}
    public String getpickupLocation() {return this.pickupLocation;}

    /**
     * Function used to store customer details in database
     */

    public void addToDatabase()
    {
        try{
      
          String query = "insert into customers (name,telephone,pickup_location)"
            + " values (?, ?, ?)";

          // create the mysql insert prepared statement
          PreparedStatement preparedStmt = conn.prepareStatement(query); 
          preparedStmt.setString(1, getcustomerName());
          preparedStmt.setString(2, getphoneNumber());
          preparedStmt.setString(3, getpickupLocation());
          
  
          // execute the preparedstatement
          preparedStmt.execute();
          customers.add(this);
          }
          
        catch(Exception e)
        {
          e.printStackTrace();
          System.out.println(e.getMessage());
        }
    }

    public static ArrayList <Customer> getCustomers()
    {
        return customers;
    }
  
    public int getID()
    {
      return id;
    }

    public void setID(int id)
    {
      this.id = id;
    }

    public void setName(String name)
    {
      this.customerName = name;
    }

    public void setNumber(String number)
    {
      this.phoneNumber = number;
    }

    public void setLocation(String location)
    {
      this.pickupLocation = location;
    }
    
    /**
     * function updates customer details
     * @param oldName - String representing the previous name of customer to be changed
     * @param oldNumber - String representing the previous number of customer to be changed
     * @param newName - String representing the customer's name after being updated
     * @param newNumber - String representing the customer's number after being updated
     * @param newPickupLocation - String representing the customer's pickup location after being updated
     */
    public void updateCustomer(String oldName, String oldNumber, String newName, String newNumber, String newPickupLocation)
    {
      String editedName = newName;
      String editedNumber = newNumber;
      String editedLocation = newPickupLocation;
      Customer c = search(oldName,oldNumber);
      if((newName.equals(""))){
          editedName = c.getcustomerName();
      }
      if((newNumber.equals(""))){
          editedNumber = c.getphoneNumber();
      }
      if((newPickupLocation.equals(""))){
          editedLocation = c.getpickupLocation();
      }
      try {  
          Connection conn = Authentication.getDbConn();
          PreparedStatement st = conn.prepareStatement("UPDATE customers SET name = ?, telephone = ?, pickupLocation = ? where name = ? AND telephone = ?");
          st.setString(1,editedName);
          st.setString(2,editedNumber);
          st.setString(3,editedLocation);
          st.setString(4,c.getcustomerName());
          st.setString(5,c.getphoneNumber());
          st.executeUpdate();
          c.setName(editedName);
          c.setNumber(editedNumber);
          c.setLocation(editedLocation);
          System.out.println("Customer Updated!");
          } 

         catch(Exception e) 
         {
          System.out.println(e);
          e.printStackTrace();
         } 
    }

    /**
     * Function used to search the database for the customer with name and number entered
     * @param name - String representing the customer's name
     * @param phoneNumber - String representing the customer's phone number
     * @return null
     */
    public static Customer search(String name, String phoneNumber)
    {
      for (int i = 0; i < getCustomers().size(); i++)
      {
        if (getCustomers().get(i).getcustomerName().equals(name) && getCustomers().get(i).getphoneNumber().equals(phoneNumber))
        {
          return getCustomers().get(i);
        }
      
      }
      return null;
    }

    /**
     * Function used to remove customer from database by identifying their details with the customer ID
     * @param customerID - int represents the customer ID
     */
    public static void deleteCustomer(int customerID)
    {
        try
        {
            int idx = -999;
            Connection conn = Authentication.getDbConn(); 
            String query = "Delete from customers where id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
               preparedStmt.setInt(1, customerID); 
               preparedStmt.execute();

             for (int i = 0; i < Customer.getCustomers().size(); i++)
              {
                if (Customer.getCustomers().get(i).getID() == customerID)
                {
                  idx = i;
                }

              } 
              if (idx != -999)
              {
                Customer.getCustomers().remove(idx);
                System.out.println("Customer removed :)");
              }
              
        }
             
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
             
    }

    /**
     * Function used to display customer details
     */
    public static void populate()
    {
      try{
            Connection conn = Authentication.getDbConn(); 
            String query = "select * from customers";

            // create the mysql insert prepared statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
        

            // execute the prepared statement
            ResultSet result = preparedStmt.executeQuery();

            while (result.next()) 
            {
                Customer e = new Customer(result.getString("telephone"), result.getString("name"), result.getString("pickup_location"));
                customers.add(e);
                e.setID(result.getInt("id"));
            }
           
            
       }

       catch(Exception e)
       {
            e.printStackTrace();
            System.out.println(e.getMessage());
            
       }
            
    }



  /**
   * Function used to retrieve customer ID
   * @param cusName
   * @param cusPhoneNumber
   * @param pickupLocation
   */
  public static int getCusId(String cusName,String cusPhoneNumber, String pickupLocation)
  {
    String sql = "select * from customers where name = ? and telephone = ?";
    try 
    {
      Connection conn = Authentication.getDbConn(); 
      PreparedStatement p = conn.prepareStatement(sql);
      p.setString(1, cusName);
      p.setLong(2, Long.parseLong(cusPhoneNumber));
      ResultSet r = p.executeQuery();

    if(r.next())
    {
      return r.getInt("id");
    }

    else
    {
      Customer c = new Customer(cusPhoneNumber, cusName, pickupLocation);
      c.addToDatabase();
      //customers.add(c);
      sql = "select * from customers where name = ? and telephone = ?";
      p = conn.prepareStatement(sql);
      p.setString(1, cusName);
      p.setLong(2, Long.parseLong(cusPhoneNumber));
      r = p.executeQuery();

      if(r.next())
      {
        c.setID(r.getInt("id"));
        return r.getInt("id");
        // call create Customer
        // get customer id
        // return customer id
       //return 0;
      }

    } 
    }
      
    catch (Exception e) 
    {
      e.printStackTrace();
      //return 0;
      return -1;
    }
      return -1; 
    }
}
