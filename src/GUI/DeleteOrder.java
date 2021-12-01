package GUI;

import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Inventory.*;
import Order.*;


public class DeleteOrder extends JFrame {
    


    private JLabel titleLabel;
    private JLabel oFind;
    private JTextField oEntry;

    private JButton deleteBtn;
    
    private JLabel notFound;
    private JLabel tryAgain;
    private JLabel stckDeleted;

    private JButton closeBtn;


    public DeleteOrder()
    {
        setLayout(null);
        setSize(800,600);

        titleLabel = new JLabel("DELETE AN ORDER");
        titleLabel.setBounds(120,30,220,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        oFind = new JLabel("Enter the Order Number of the Order to be deleted:");
        oFind.setBounds(140,90,400,40);
        oFind.setFont(oFind.getFont().deriveFont(15f));

        oEntry = new JTextField();
        oEntry.setBounds(140,120,150,20);
        
        deleteBtn = new JButton("DELETE");
        deleteBtn.setBounds (160,380,200,40);
        deleteBtn.addActionListener(new deleteItemListener());
        deleteBtn.setVisible(true);


        notFound = new JLabel("ORDER Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);


        stckDeleted = new JLabel ("ORDER Successfully DELETED!");
        stckDeleted.setBounds(110,450,250,50);
        stckDeleted.setFont(stckDeleted.getFont().deriveFont(15f));
        stckDeleted.setVisible(false);

        closeBtn = new JButton("CLICK TO RETURN TO ORDER MENU");
        closeBtn.setBounds(110,500,300,35);
        closeBtn.addActionListener(new closeTabListener());


        add(titleLabel);
        add(oFind);
        add(oEntry);
        add(deleteBtn);
        add(notFound);
        add(tryAgain);
        add(stckDeleted);
        add(closeBtn);

    }


    public class deleteItemListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            
            ArrayList<Integer>oList = new ArrayList<Integer>();

            for (int z = 0; z < Order.orders.size(); z++){
                oList.add(Order.orders.get(z).getOrderNo());
            }
            
            if (oList.contains(Integer.parseInt(oEntry.getText()))){
                Order.deleteOrder(Integer.parseInt(oEntry.getText()));
                notFound.setVisible(false);
               tryAgain.setVisible(false);
               // deleteBtn.setVisible(true);
                stckDeleted.setVisible(true);
                closeBtn.setVisible(true);
            
            }else{
                    notFound.setVisible(true);
                    tryAgain.setVisible(true);
                    stckDeleted.setVisible(false);
                   //deleteBtn.setVisible(f);
                }
        }}

    public class closeTabListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }
}

