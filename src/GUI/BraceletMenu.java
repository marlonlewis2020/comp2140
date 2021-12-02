package GUI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;   


public class BraceletMenu extends JFrame
{  
    JButton displayBrace ; 
    JButton searchBrace;
    JButton estimateBrace;
    JButton updateBrace;
    JButton addBrace;
    JButton deleteBrace ;
    JButton returnMain;
    JPanel newPanel;  
    JLabel titleLabel;   
      
    //calling constructor  
    public BraceletMenu()  
    {   
        setLayout(null);  
        this.setSize(800,800);
        this.setTitle("BRACELET MENU");
          
          
        titleLabel = new JLabel("BRACELET CREATION AND EDITING");  
        titleLabel.setBounds(120,30,500,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));             
          
        //create button for display bracelets
        displayBrace = new JButton("DISPLAY ALL BRACELETS");
        displayBrace.setBounds(170,100,300,40);
        displayBrace.setFont(displayBrace.getFont().deriveFont(15f)); 
        displayBrace.addActionListener(new displayBraceListener());

        //create button for creating a bracelet 
        addBrace = new JButton("CREATE A BRACELET");   
        addBrace.setBounds(200,180,220,40);
        addBrace.setFont(addBrace.getFont().deriveFont(15f)); 
        addBrace.setBackground(Color. yellow);
        addBrace.addActionListener(new addBraceListener());

        //create button for searching for a bracelet 
        searchBrace = new JButton("FIND A BRACELET");   
        searchBrace.setBounds(200,260,220,40);
        searchBrace.setFont(searchBrace.getFont().deriveFont(15f)); 
        searchBrace.addActionListener(new findBraceListener());

        //create button for update a bracelet 
        updateBrace = new JButton("UPDATE BRACELET INFORMATION");   
        updateBrace.setBounds(170,340,300,40);
        updateBrace.setFont(updateBrace.getFont().deriveFont(15f)); 
        updateBrace.addActionListener(new updateBraceListener());

        //create delete bracelet button 
        deleteBrace = new JButton("DELETE A BRACELET"); 
        deleteBrace.setBounds(200,420,200,40);
        deleteBrace.setFont(deleteBrace.getFont().deriveFont(15f)); 
        deleteBrace.addActionListener(new deleteBraceListener());

        //create estimate qty bracelet button 
        estimateBrace = new JButton("ESTIMATE BRACELETS");
        estimateBrace.setBounds(180,500,250,40);
        estimateBrace.setFont(estimateBrace.getFont().deriveFont(15f)); 
        estimateBrace.addActionListener(new estimateBraceListener());

        returnMain = new JButton("RETURN TO MAIN MENU");
        returnMain.setBounds(480,550,250,40);
        returnMain.setFont(returnMain.getFont().deriveFont(15f)); 
        returnMain.addActionListener(new returnMainListener());
          
         
        add(titleLabel);
        add(displayBrace);    
        add(addBrace);   
        add(searchBrace);     
        add(updateBrace);    
        add(deleteBrace);            
        add(estimateBrace);
        add(returnMain);
          
    }  

  public class addBraceListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        CreateNewBrace bracelet =  new CreateNewBrace();
        bracelet.setVisible(true);
        dispose();
      }
      
  }

  public class displayBraceListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        DisplayAllBrace displayTable =  new DisplayAllBrace();
        displayTable.setVisible(true);
      }
      
  }

  public class findBraceListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        findBrace findB =  new findBrace();
        findB.setVisible(true);
        dispose();
      }
      
  }

  public class deleteBraceListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        deleteBrace delBrace =  new deleteBrace();
        delBrace.setVisible(true);
        dispose();
      }
      
  }

  public class updateBraceListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        UpdateBracelet upBrace =  new UpdateBracelet();
        upBrace.setVisible(true);
        dispose();
      }
      
  }


 public class estimateBraceListener implements ActionListener
  {
      public void  actionPerformed(ActionEvent event)
      {
        EstimateBracelet estBrace =  new EstimateBracelet();
        estBrace.setVisible(true);
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
