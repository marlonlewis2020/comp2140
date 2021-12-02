package GUI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;   
import Order.*;


public class  OrderMenu extends JFrame
{  
    
    JButton displayOrder ; 
    JButton searchOrder;
    JButton updateOrder;
    JButton addOrder;
    JButton deleteOrder ;
    JButton returnMain;
    JPanel newPanel;  
    JLabel titleLabel;   
      
    //calling constructor  
    public OrderMenu()  
    {   
        setLayout(null);  
        this.setSize(800,800);
        this.setTitle("ORDER MENU");
          
          
        titleLabel = new JLabel("ORDER CREATION AND EDITING");  
        titleLabel.setBounds(120,30,500,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));             
          
        //create button for display bracelets
        displayOrder = new JButton("DISPLAY ALL CURRENT ORDERS");
        displayOrder.setBounds(170,100,300,40);
        displayOrder.setFont(displayOrder.getFont().deriveFont(15f)); 
        displayOrder.addActionListener(new displayOrderListener());

        //create button for creating a bracelet 
        addOrder = new JButton("ADD AN ORDER");   
        addOrder.setBounds(200,180,220,40);
        addOrder.setFont(addOrder.getFont().deriveFont(15f)); 
        addOrder.setBackground(Color. yellow);
        addOrder.addActionListener(new addOrderListener());


        //create delete order button 
        deleteOrder = new JButton("DELETE AN ORDER"); 
        deleteOrder.setBounds(200,260,200,40);
        deleteOrder.setFont(deleteOrder.getFont().deriveFont(15f)); 
        deleteOrder.addActionListener(new deleteOrderListener());

        returnMain = new JButton("RETURN TO MAIN MENU");
        returnMain.setBounds(480,400,250,40);
        returnMain.setFont(returnMain.getFont().deriveFont(15f)); 
        returnMain.addActionListener(new returnMainListener());
          
        
       
        add(titleLabel);
        add(displayOrder);     
        add(addOrder);   
        add(deleteOrder);           
        add(returnMain);
          
    }  

  public class addOrderListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        CreateOrder cOrder =  new CreateOrder();
        cOrder.setVisible(true);
        dispose();
      }
      
  }

  public class displayOrderListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        DisplayAllOrders displayOrdTable =  new DisplayAllOrders();
        displayOrdTable.setVisible(true);
      }
      
  }


  public class deleteOrderListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        DeleteOrder delOrd =  new DeleteOrder();
        delOrd.setVisible(true);
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

