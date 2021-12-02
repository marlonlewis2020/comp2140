package GUI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;   
import Order.*;


public class  CustomerMenu extends JFrame
{  
    //initialize button, panel, label, and text field  
    //int PREF_W = 300;
    //int PREF_H = PREF_W;
    JButton displayCust ; 
    JButton upCust;
    JButton deleteCust ;
    JButton returnMain;
    JPanel newPanel;  
    JLabel titleLabel;   
      
    //calling constructor  
    public CustomerMenu()  
    {   
        setLayout(null);  
        this.setSize(800,800);
        this.setTitle("CUSTOMER MENU");
          
          
        titleLabel = new JLabel("CUSTOMER EDITING AND DISPLAY");   
        titleLabel.setBounds(120,30,500,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));             
          
        //create button for display bracelets
        displayCust = new JButton("DISPLAY ALL CURRENT CUSTOMERS");
        displayCust.setBounds(170,100,300,40);
        displayCust.setFont(displayCust.getFont().deriveFont(15f)); 
        displayCust.addActionListener(new displayCustListener());

        //create button for creating a bracelet 
        upCust = new JButton("UPDATE CUSTOMER INFORMATION");   
        upCust.setBounds(200,180,300,40);
        upCust.setFont(upCust.getFont().deriveFont(15f)); 
        upCust.setBackground(Color. yellow);
        upCust.addActionListener(new upCustListener());


        //create delete order button 
        deleteCust = new JButton("DELETE A CUSTOMER"); 
        deleteCust.setBounds(200,260,400,40);
        deleteCust.setFont(deleteCust.getFont().deriveFont(15f)); 
        deleteCust.addActionListener(new deleteCustListener());

        returnMain = new JButton("RETURN TO MAIN MENU");
        returnMain.setBounds(480,400,250,40);
        returnMain.setFont(returnMain.getFont().deriveFont(15f)); 
        returnMain.addActionListener(new returnMainListener());
          
        
        add(titleLabel);
        add(displayCust);    
        add(upCust);   
        add(deleteCust);          
        add(returnMain);
          
    }  

  public class displayCustListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        DisplayAllCustomers displayCustTable =  new DisplayAllCustomers();
        displayCustTable.setVisible(true);
      }
      
  }

  public class upCustListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        UpdateCustomer uCust =  new UpdateCustomer();
        uCust.setVisible(true);
        dispose();
      }
      
  }


  public class deleteCustListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        DeleteCustomer delCust =  new DeleteCustomer();
        delCust.setVisible(true);
        dispose();
      }
      
  }


  public class returnMainListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        MenuScreen newMen = new MenuScreen();
       newMen.setState(Frame.NORMAL);
       newMen.setVisible(true);
       dispose();
      }
      
  }
 
} 
