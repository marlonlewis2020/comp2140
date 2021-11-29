import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Authentication.Authentication;

class User
{
  private int userID;
  private static int nextid =0;
  private String userName;
  private int passWord;
  private String role;
  public ArrayList<String> menu = new ArrayList<String>();
  private ArrayList<User> approvedUsers = new ArrayList<User>();


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

  public int findUser(ArrayList<User> users, int userID)
	{
		int pdx =-1;
		int currdx=0;
		while ((currdx<users.size())&&(pdx==-1))
		{
			if (users.get(currdx).getUserID()==userID)
				pdx = currdx;
			currdx++;

		}

		return pdx;
  }

  public void addUser()
  {

    try
    {
     // approvedUsers.add(user);
      
      Connection conn = Authentication.getDbConn();
      String query = "insert into users (username, password, role)" + " values (?, ?, ?)";
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setString(1, this.getUserName());
      preparedStmt.setString(2, this.getPassword() );
      preparedStmt.setString(3, this.getRole());
      preparedStmt.execute();  
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    
    
  }

  public void deleteUser(int userID)
  {
    try{
    int index = findUser(approvedUsers, userID);
    approvedUsers.remove(index);

    Connection conn = Authentication.getDbConn();

    String query = "Delete from users where id = ?";
         PreparedStatement preparedStmt = conn.prepareStatement(query);
          preparedStmt.setInt(1, userID); 

          preparedStmt.execute();
  }
  catch(Exception e)
  {}
  }

  public void updateUser(int userID, String fields, String values)
  {
    Connection conn = Authentication.getDbConn();

        String[] columns = fields.split(","); 
        String[] inputs = values.split(","); 
        String updates = "";

        for (int i = 0; i < columns.length - 1; i++) 
        {
            updates += columns [i] + "=" + inputs [i] + ",";
        }
        updates += columns [columns.length] + "=" + inputs [columns.length];
        
        try
        {
          String query = "Update orders set ? where order_number = ?";
          PreparedStatement preparedStmt = conn.prepareStatement(query);
          preparedStmt.setString(1, updates);
          preparedStmt.setInt(2, userID);
        }
        catch(Exception e){}
}
}
