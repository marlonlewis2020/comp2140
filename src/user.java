/**
 * User class allows access to the system by allowing the user to create a user name and password and access a menu based on privileges assigned to a role.
 * @author Callay Jarrett
 * @version 1.6
 */
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
  private Role role;
  public ArrayList<String> menu = new ArrayList<String>();
  


  public User(){}
  /**
   * used to construct a user object
   * @param userName username of the user
   * @param passWord password of the user
   * @param role  role of the user
   */
  
  public User( String userName, String passWord, Role role)
  {
    this.userName = userName;
    this.passWord = passWord.hashCode();
    this.role = role;
    this.userID = nextid;
    nextid++;
  }

  /**
   * used to get the user id
   * @return the user id
   */
  public int getUserID()
  {
    return this.userID;
  }

  /**
   * used to get the username
   * @return the user name of the user
   */
  public String getUserName()
  {
    return this.userName;
  }

  /**
   * gets the password of the user
   * @return password of the user
   */
  private String getPassword()
  {
    return String.valueOf(this.passWord);
  }

  /**
   * used to the get the role of the user
   * @return role of the user
   */
  public Role getRole()
  {
    return this.role;
  }

  /**
   * used to add a new user to the system
   */
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
        preparedStmt.setString(3, String.valueOf(this.getRole()));
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
  /**
   * deletes a user from the system using the username
   * @param username user name of the user to be deleted
   */
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

  /**
   * allows the user to update either username, password or role of the user
   * @param username username of the user to be updated
   * @param field column heading to be updated (username,password,role)
   * @param value updated version of the username, password or role
   */
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