package GUI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;   


public class StockMenu extends JFrame
{  
    //initialize button, panel, label, and text field  
    //int PREF_W = 300;
    //int PREF_H = PREF_W;
    JButton displayStock; 
    JButton updateStock;
    JButton searchStock;
    JButton createStock;
    JButton deleteStock ;
    JButton returnMain;
    JPanel newPanel;  
    JLabel titleLabel;   
      
    //calling constructor  
    public StockMenu()  
    {   
        setLayout(null);  
        this.setSize(800,800);
        this.setTitle("STOCK MENU");
          
          
        titleLabel = new JLabel("STOCK ITEM CREATION AND MANAGEMENT");  //set label value for textField1  
        titleLabel.setBounds(120,30,500,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));             
          
        //create button for display all items and their quantity levels
        displayStock = new JButton("DISPLAY ALL CURRENT ITEMS");
        displayStock.setBounds(170,100,300,40);
        displayStock.setFont(displayStock.getFont().deriveFont(15f)); 
        displayStock.addActionListener(new displayAllStockListener());

        //create button for creating aN ITEM
        createStock = new JButton("CREATE ITEM");   
        createStock.setBounds(200,180,220,40);
        createStock.setFont(createStock.getFont().deriveFont(15f)); 
        createStock.setBackground(Color. yellow);
        createStock.addActionListener(new createStockListener());

        searchStock = new JButton("FIND An ITEM");   
        searchStock.setBounds(200,260,220,40);
        searchStock.setFont(searchStock.getFont().deriveFont(15f)); 
        searchStock.addActionListener(new findStockListener());


        //create button for update a STOCK ITEM 
        updateStock = new JButton("UPDATE STOCK ITEM INFORMATION");   
        updateStock.setBounds(170,340,300,40);
        updateStock.setFont(updateStock.getFont().deriveFont(15f)); 
        updateStock.addActionListener(new updateStockListener());

        //create delete bracelet button 
        deleteStock = new JButton("DELETE AN ITEM"); 
        deleteStock.setBounds(200,420,200,40);
        deleteStock.setFont(deleteStock.getFont().deriveFont(15f)); 
        deleteStock.addActionListener(new deleteStockListener());


        returnMain = new JButton("RETURN TO MAIN MENU");
        returnMain.setBounds(480,550,250,40);
        returnMain.setFont(returnMain.getFont().deriveFont(15f)); 
        returnMain.addActionListener(new returnMainListener());
          
        
       //create panel to put form elements  
        //newPanel = new JPanel(new GridLayout(3, 1));  
        add(titleLabel);
        add(displayStock);    //set username label to panel  
        add(createStock);   //set text field to panel
        add(searchStock);  
        add(updateStock);   //set text field to panel  
        add(deleteStock);           //set button to panel  
        add(returnMain);
          
    }  

  public class createStockListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
          CreateStock newCreate = new CreateStock();
          newCreate.setVisible(true);
          dispose();
      }
      
  }

  public class displayAllStockListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
          DisplayAllStock newAll = new DisplayAllStock();
          newAll.setVisible(true);
      }
      
  }

  public class findStockListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
          FindStock newSearch = new FindStock();
          newSearch.setVisible(true);
          dispose();
      }
      
  }



  public class deleteStockListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        DeleteStock delStock = new DeleteStock();
        delStock.setVisible(true);
        dispose();
      }
      
  }

  public class updateStockListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
          EditStock edStock = new EditStock();
          edStock.setVisible(true);
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
