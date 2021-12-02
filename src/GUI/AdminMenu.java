package GUI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  



public class AdminMenu extends JFrame
{  
    
    JButton addUser ; 
    JButton deleteUser;
    JButton updateUser;
    JButton displayStock;
    JButton displayAdmin;
    JButton goBack;
    JPanel newPanel;  
    JLabel titleLabel;   
      
    //calling constructor  
    public AdminMenu()  
    {   
        setLayout(null);  
        
        this.setSize(800,800);
        this.setTitle("USER ADMIN");

          
          
        titleLabel = new JLabel("ADMIN ONLY");  
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
        add(addUser);     
        add(deleteUser);   
        add(updateUser);      
        add(goBack); 

    }


    public class addUserListener implements ActionListener{
      public void  actionPerformed(ActionEvent event)
      {
          AddUser newUser = new AddUser();
          newUser.setVisible(true);
          dispose();

      }
    }

    public class deleteUserListener implements ActionListener{
        public void  actionPerformed(ActionEvent event)
        {
            DeleteUser newDelete = new DeleteUser();
            newDelete.setVisible(true);
            dispose();
        }
      }
      
    public class updateUserListener implements ActionListener{
        public void  actionPerformed(ActionEvent event)
        {
            UpdateUser newUpdate = new UpdateUser();
            newUpdate.setVisible(true);
            dispose();
            
        }
      }
    
      public class goBackListener implements ActionListener{
        public void  actionPerformed(ActionEvent event)
        {
          MenuScreen newMen = new MenuScreen();
          newMen.setState(Frame.NORMAL);
          newMen.setVisible(true);
          dispose();
            
        }
      }
           
        
      
}