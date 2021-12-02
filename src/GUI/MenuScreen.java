package GUI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  




public class MenuScreen extends JFrame 
{  

    JButton displayBrace ; 
    JButton logOt ; 
    JButton displayCustomer;
    JButton displayOrder;
    JButton displayStock;
    JButton displayAdmin;
    JPanel newPanel;  
    JLabel titleLabel;   
    
      
    //calling constructor  
    public MenuScreen()  
    {   
        setLayout(null);  
        //thisForm = this;
        this.setSize(800,800);

          
          
        titleLabel = new JLabel("NAVIGATE TO THE OPTION OF YOUR CHOICE");  //set label value for textField1  
        titleLabel.setBounds(120,30,500,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));             
          
        //create button for display bracelets
        displayBrace = new JButton("BRACELETS");
        displayBrace.setBounds(170,100,300,40);
        displayBrace.setFont(displayBrace.getFont().deriveFont(15f)); 
        displayBrace.setBackground(Color. yellow);
        displayBrace.addActionListener(new braceletListener());


        //create button for stock functionalities
        displayStock = new JButton("STOCK");   
        displayStock.setBounds(200,180,220,40);
        displayStock.setFont(displayStock.getFont().deriveFont(15f)); 
        displayStock.setBackground(Color. yellow);
        displayStock.addActionListener(new stockListener());

        //create button for customer class functionalities
        displayCustomer = new JButton("CUSTOMER");   
        displayCustomer.setBounds(200,260,220,40);
        displayCustomer.setFont(displayCustomer.getFont().deriveFont(15f)); 
        displayCustomer.setBackground(Color. yellow);
        displayCustomer.addActionListener(new customerListener());


        //create button to generate report
        displayOrder = new JButton("ORDERS");   
        displayOrder.setBounds(170,340,300,40);
        displayOrder.setFont(displayOrder.getFont().deriveFont(15f)); 
        displayOrder.setBackground(Color. yellow);
        displayOrder.addActionListener(new OrderListener());


        //create button for admin duties
        displayAdmin = new JButton("ADMIN"); 
        displayAdmin.setBounds(200,420,200,40);
        displayAdmin.setFont(displayAdmin.getFont().deriveFont(15f));
        displayAdmin.setBackground(Color. yellow); 
        displayAdmin.addActionListener(new adminListener());


        logOt = new JButton("LOGOUT"); 
        logOt.setBounds(400,530,200,40);
        logOt.setFont(logOt.getFont().deriveFont(15f));
        logOt.setBackground(Color. yellow); 
        logOt.addActionListener(new logoutListener());
        
          
        
       //create panel to put form elements  
        //newPanel = new JPanel(new GridLayout(3, 1));  
        add(titleLabel);
        add(displayCustomer);    //set username label to panel  
        add(displayStock);   //set text field to panel  
        add(displayBrace);    //set password label to panel  
        add(displayOrder);   //set text field to panel  
        add(displayAdmin);           //set button to panel  
        add(logOt);
          
        //set border to panel   
        //add(newPanel, BorderLayout.CENTER);  
          
        //perform action on button click   
       //add action listener to button  
       // setTitle("BEADITUP.JA USER SYSTEM");         set title to the login form  
       ///setBackground(C);
    }  

  public class braceletListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        BraceletMenu menu = new BraceletMenu();
        menu.setVisible(true);
        dispose();
      }
      
  }

  public class OrderListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        OrderMenu oMenu = new OrderMenu();
        oMenu.setVisible(true);
        dispose();
      }
      
  }

  public class stockListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        StockMenu stockMenu = new StockMenu();
        stockMenu.setVisible(true);
        dispose();
      }
      
  }

  public class customerListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        CustomerMenu cMenu = new CustomerMenu();
        cMenu.setVisible(true);
        dispose();
      }
      
  }

  public class adminListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        AdminMenu menu = new AdminMenu();
        menu.setVisible(true);
       dispose();
      }
      
  }

  public class logoutListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        System.exit(0);
      }
      
  }


} 
