import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Authentication.Authentication;
import java.sql.ResultSet;

class User
{
  private int userID;
  private static int nextid =0;
  private String userName;
  private int passWord;
  private String role;
  public ArrayList<String> menu = new ArrayList<String>();
  


  public User(){}
  
  public User( String userName, String passWord, String role)
  {
    this.userName = userName;
    this.passWord = passWord.hashCode();
    this.role = role;
    this.userID = nextid;
    nextid++;
  }

  public int getUserID()
  {
    return this.userID;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public String getPassword()
  {
    return String.valueOf(this.passWord);
  }

  public String getRole()
  {
    return this.role;
  }

  
  public void addUser()
  {

    try
    {
      
      Connection conn = Authentication.getDbConn();

      String checkname = this.getUserName();
      String namecheck = "select username from users where username = ?";

      PreparedStatement statement = conn.prepareStatement(namecheck);
      statement.setString(1, checkname);
      ResultSet result = statement.executeQuery();

      if (!(result.next()))
      {
        String query = "insert into users (username, password, role)" + " values (?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, this.getUserName());
        preparedStmt.setString(2, this.getPassword() );
        preparedStmt.setString(3, this.getRole());
        preparedStmt.execute(); 
      }
      else
      {
        System.out.println("Username already exists");
      }
       
    }
    catch(Exception e)
    {
      String message = e.getMessage();
      System.out.println(message);
    }
    
    
  }

  public void deleteUser(String username)
  {
    try{
  

    Connection conn = Authentication.getDbConn();

    String query = "Delete from users where username = ?";
         PreparedStatement preparedStmt = conn.prepareStatement(query);
          preparedStmt.setString(1, username); 

          preparedStmt.execute();
  }
  catch(Exception e)
  {
    String message = "Username not found";
    System.out.println(message);
  }
  }

  public void updateUser(String username, String field, String value)
  {
    Connection conn = Authentication.getDbConn();

       
        try
        {
          if (field == "role")
          {
            String query = "Update users set role =? where username = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, value);
            preparedStmt.setString(2, username);

            preparedStmt.execute();
          }
          else if(field == "password")
          {
            String query = "Update users set password=? where username = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, value);
            preparedStmt.setString(2, username);

            preparedStmt.execute();
          }
          else if(field == "username")
          {
            String query = "Update users set username=? where username = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, value);
            preparedStmt.setString(2, username);

            preparedStmt.execute();
          }
        }
        catch(Exception e)
        {
          String message = "Username not found";
          System.out.println(message);

         
        }
}
}
