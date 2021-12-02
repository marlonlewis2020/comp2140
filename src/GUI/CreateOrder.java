package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import Inventory.*;
import Order.*;
import Authentication.*;

public class CreateOrder extends JFrame
{
    private JTextField  cuName;      
    private JTextField  pNum;        
    private JTextField  ordQty; 
    private JLabel succAdded; 
    private JLabel pHold;  
    private JButton addToOrd;     
    private JButton     cmdSave;
    private JButton     cmdClose;
    private JComboBox<String> bType;
    private JComboBox<String> pickLoc;
    private String [] braceCOrder;
    private ArrayList<String>ordChoices  = new ArrayList<String>();
    private String actName = "";
    private String actQuantity = "";

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
  
    public CreateOrder()
    {

        setTitle("ADD NEW ORDER TO SYSTEM");
        pnlCommand = new JPanel(); 
        pnlDisplay = new JPanel(); 


        pnlDisplay.add(new JLabel("CUSTOMER NAME")); 
        cuName = new JTextField();
        pnlDisplay.add(cuName);

        pnlDisplay.add(new JLabel("PHONE NUMBER"));
        pNum = new JTextField();
        pnlDisplay.add(pNum);

        pnlDisplay.add(new JLabel("CHOOSE THE BRACELET YOU WISH TO PURCHASE")); 
        for (int i = 0; i < Bracelet.getBracelets().size(); i++){
            ordChoices.add (Bracelet.getBracelets().get(i).getName());
        }
        
        braceCOrder = ordChoices.toArray(new String[ordChoices.size()]);
        bType= new JComboBox<String>(braceCOrder);
        bType.setVisible(true);
        pnlDisplay.add(bType);
        
        pnlDisplay.add(new JLabel("ORDER QUANTITY"));
        ordQty = new JTextField();
        pnlDisplay.add(ordQty);

        addToOrd = new JButton("ADD ITEM AND QUANTITY");
        pnlDisplay.add(addToOrd,Component.CENTER_ALIGNMENT);
        addToOrd.addActionListener(new addOrderListener());

        //PLACE HOLDER FOR LAYOUT
        JButton btn= new JButton("ADD TYPE AND NUMBER");
        pnlDisplay.add(btn);
        btn.setVisible(false);


        pnlDisplay.add(new JLabel("CHOOSE PICKUP LOCATION"));  
        String []  choiceLoc = {"Half Way Tree", "Downtown", "Liguanea", "Cross-Roads"};
        pickLoc= new JComboBox<String>(choiceLoc);
        pickLoc.setVisible(true);
        pnlDisplay.add(pickLoc);

        succAdded = new JLabel("SUCESSFULLY ADDED");  
        succAdded.setVisible(false);
        pnlDisplay.add(succAdded);

        pHold = new JLabel("SUCESSFULLY ADDED");  
        pHold.setVisible(false);
        pnlDisplay.add(pHold);
        
        pnlDisplay.setLayout(new GridLayout(7,7));

       
        cmdSave   = new JButton("Save");
        cmdClose   = new JButton("Close");
        cmdClose.addActionListener(new CloseListener());
        cmdSave.addActionListener(new SaveListener());
        

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }



    private class CloseListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            OrderMenu newOd = new OrderMenu();
            newOd.setVisible(true);
            dispose();
        }
    }

    private class addOrderListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            actName += bType.getSelectedItem() + ",";
            actQuantity += ordQty.getText() + ",";
            

        }
    }
    
   private class SaveListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            String custName  = cuName.getText();
            String phone = pNum.getText();
            String pickupLoc = String.valueOf(pickLoc.getSelectedItem());
            Order newOrder = new Order(phone,custName,actQuantity,actName,pickupLoc);
        }
    }
}

