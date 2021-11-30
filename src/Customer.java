import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Authentication.Authentication;

public class Customer
{
    private long phoneNumber;
    private String customerName;
    private String pickupLocation;
    private ArrayList <Customer> customers = new ArrayList <Customer>();
    private Connection conn = Authentication.getDbConn(); //Connection object created

    

    public Customer(long phoneNumber, String customerName, String pickupLocation)
    {
        this.phoneNumber = phoneNumber;
        this.customerName = customerName;
        this.pickupLocation = pickupLocation;
    }


    public long getphoneNumber() {return this.phoneNumber;}
    public String getcustomerName() {return this.customerName;}
    public String getpickupLocation() {return this.pickupLocation;}
    

    public void addToDatabase()
    {
        try{

          String query = "insert into customers (name,telephone,order_address)"
            + " values (?, ?, ?)";

          // create the mysql insert prepared statement
          PreparedStatement preparedStmt = conn.prepareStatement(query); 
          preparedStmt.setString(1, getcustomerName());
          preparedStmt.setLong(2, getphoneNumber());
          preparedStmt.setString(3, getpickupLocation());
          
  
          // execute the preparedstatement
          preparedStmt.execute();
          }
          
        catch(Exception e)
        {
          e.printStackTrace();
          System.out.println(e.getMessage());
        }
    }
//}

//     public void updateCustomer(int customerID, String fields, String values)
//     {
//         String [] columns = fields.split(","); 
//         String [] inputs = values.split(","); 
//         String updates = "";
//         for (int i = 0; i < columns.length - 1; i++) 
//         {
//             updates += columns [i] + "=" + inputs [i] + ",";
//         }
//             updates += columns [columns.length] + "=" + inputs [columns.length];
    
//             try
//             {
//               String query = "Update Customers set ? where id = ?";
//               PreparedStatement preparedStmt = conn.prepareStatement(query);
//               preparedStmt.setString(1, updates);
//               preparedStmt.setInt(2, customerID);
    
//             }
    
//             catch(Exception e)
//             {
//                 e.printStackTrace();
//               System.out.println(e.getMessage());
//             }
//     }
    


//     public void deleteCustomer(int customerID)
//     {
//         try
//         {
//             String query = "Delete from Customers where id = ?";
//             PreparedStatement preparedStmt = conn.prepareStatement(query);
//                preparedStmt.setInt(1, customerID); 
//                preparedStmt.execute();
//         }
             
//         catch(Exception e)
//         {
//             e.printStackTrace();
//             System.out.println(e.getMessage());
//         }
             
//     }


//     public ArrayList <Customer> populate()
//     {
//       try{
//             String query = "select * from customer";

//             // create the mysql insert prepared statement
//             PreparedStatement preparedStmt = conn.prepareStatement(query);
        

//             // execute the prepared statement
//             ResultSet result = preparedStmt.executeQuery();

//             while (result.next()) 
//             {
//                 Customer e = new Customer(result.getInt("telephone"), result.getString("name"), result.getString("pickup_location"));
//                 this.customers.add(e);
//             }
//             return this.customers;
            
//        }

//        catch(Exception e)
//        {
//             e.printStackTrace();
//             System.out.println(e.getMessage());
//             return this.customers;
//        }
            
//     }
// }



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
}