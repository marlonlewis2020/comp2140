import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
//import java.lang.Exception;  



public class MenuScreen extends JFrame 
{  
    //initialize button, panel, label, and text field  
    //int PREF_W = 300;
    //int PREF_H = PREF_W;
    JButton displayBrace ; 
    JButton displayCustomer;
    JButton displayReport;
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


        //create button to generate report
        displayReport = new JButton("REPORT GENERATION");   
        displayReport.setBounds(170,340,300,40);
        displayReport.setFont(displayReport.getFont().deriveFont(15f)); 
        displayReport.setBackground(Color. yellow);


        //create button for admin duties
        displayAdmin = new JButton("ADMIN"); 
        displayAdmin.setBounds(200,420,200,40);
        displayAdmin.setFont(displayAdmin.getFont().deriveFont(15f));
        displayAdmin.setBackground(Color. yellow); 
        displayAdmin.addActionListener(new adminListener());
        
          
        
       //create panel to put form elements  
        //newPanel = new JPanel(new GridLayout(3, 1));  
        add(titleLabel);
        add(displayCustomer);    //set username label to panel  
        add(displayStock);   //set text field to panel  
        add(displayBrace);    //set password label to panel  
        add(displayReport);   //set text field to panel  
        add(displayAdmin);           //set button to panel  
          
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
      }
      
  }

  public class stockListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        StockMenu stockMenu = new StockMenu();
        stockMenu.setVisible(true);
      }
      
  }

  public class adminListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        AdminMenu menu = new AdminMenu();
        menu.setVisible(true);
      }
      
  }


} 
