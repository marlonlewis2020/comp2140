package GUI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.lang.Exception;  



public class AdminMenu extends JFrame
{  
    //initialize button, panel, label, and text field  
    //int PREF_W = 300;
    //int PREF_H = PREF_W;
    JButton addUser ; 
    JButton deleteUser;
    JButton updateUser;
    JButton displayStock;
    JButton displayAdmin;
    JButton goBack;
    private MenuScreen thisForm ;
    JPanel newPanel;  
    JLabel titleLabel;   
      
    //calling constructor  
    public AdminMenu()  
    {   
        setLayout(null);  
        //thisForm = this;
        this.setSize(800,800);
        this.setTitle("USER ADMIN");

          
          
        titleLabel = new JLabel("ADMIN ONLY");  //set label value for textField1  
        titleLabel.setBounds(120,30,500,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));             
          
        //create button to add a user
        addUser = new JButton("ADD USER");
        addUser.setBounds(170,100,300,40);
        addUser.setFont(addUser.getFont().deriveFont(15f)); 
        addUser.setBackground(Color. yellow);
        addUser.addActionListener(new addUserListener());


        //create button delete a user
        deleteUser = new JButton("DELETE USER");   
        deleteUser.setBounds(200,180,220,40);
        deleteUser.setFont(deleteUser.getFont().deriveFont(15f)); 
        deleteUser.setBackground(Color. yellow);
        deleteUser.addActionListener(new deleteUserListener());

        //create button for customer class functionalities
        updateUser = new JButton("UPDATE");   
        updateUser.setBounds(200,260,220,40);
        updateUser.setFont(updateUser.getFont().deriveFont(15f)); 
        updateUser.setBackground(Color. yellow);
        updateUser.addActionListener(new updateUserListener());

        goBack= new JButton("RETURN TO MAIN MENU");   
        goBack.setBounds(200,330,250,40);
        goBack.setFont(goBack.getFont().deriveFont(15f)); 
        goBack.setBackground(Color. yellow);
        goBack.addActionListener(new goBackListener());



        add(titleLabel);
        add(addUser);    //set username label to panel  
        add(deleteUser);   //set text field to panel  
        add(updateUser);    //set password label to panel  
        add(goBack); 

    }


    public class addUserListener implements ActionListener{
      public void  actionPerformed(ActionEvent event)
      {
          AddUser newUser = new AddUser();
          newUser.setVisible(true);

      }
    }

    public class deleteUserListener implements ActionListener{
        public void  actionPerformed(ActionEvent event)
        {
            DeleteUser newDelete = new DeleteUser();
            newDelete.setVisible(true);//take out find out of user and bracelet
        }
      }
      
    public class updateUserListener implements ActionListener{
        public void  actionPerformed(ActionEvent event)
        {
            UpdateUser newUpdate = new UpdateUser();
            newUpdate.setVisible(true);
            
        }
      }
    
      public class goBackListener implements ActionListener{
        public void  actionPerformed(ActionEvent event)
        {
            dispose();
            
        }
      }
          
        //set border to panel   
        //add(newPanel, BorderLayout.CENTER);  
          
        //perform action on button click   
       //add action listener to button  
       // setTitle("BEADITUP.JA USER SYSTEM");         set title to the login form  
      
}