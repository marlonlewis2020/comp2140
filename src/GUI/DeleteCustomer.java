package GUI;

import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Customizer;
import java.util.ArrayList;

import Inventory.*;
import Order.*;


public class DeleteCustomer extends JFrame {
    


    private JLabel titleLabel;
    private JLabel cFind;
    private JTextField cEntry;

    private JButton deleteBtn;
    
    private JLabel notFound;
    private JLabel tryAgain;
    private JLabel stckDeleted;

    private JButton closeBtn;


    public DeleteCustomer()
    {
        setLayout(null);
        setSize(800,600);
        setTitle("Delete Customer");

        titleLabel = new JLabel("DELETE A CUSTOMER");
        titleLabel.setBounds(120,30,220,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        cFind = new JLabel("Enter the ID Number of tperson to be deleted:");
        cFind.setBounds(140,90,400,40);
        cFind.setFont(cFind.getFont().deriveFont(15f));

        cEntry = new JTextField();
        cEntry.setBounds(140,120,150,20);
        
        deleteBtn = new JButton("DELETE");
        deleteBtn.setBounds (160,380,200,40);
        deleteBtn.addActionListener(new deleteCustListener());
        deleteBtn.setVisible(true);


        notFound = new JLabel("ORDER Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);


        stckDeleted = new JLabel ("CUSTOMER Successfully DELETED!");
        stckDeleted.setBounds(110,450,250,50);
        stckDeleted.setFont(stckDeleted.getFont().deriveFont(15f));
        stckDeleted.setVisible(false);

        closeBtn = new JButton("CLICK TO RETURN TO ORDER MENU");
        closeBtn.setBounds(110,500,300,35);
        closeBtn.addActionListener(new closeTabListener());


        add(titleLabel);
        add(cFind);
        add(cEntry);
        add(deleteBtn);
        add(notFound);
        add(tryAgain);
        add(stckDeleted);
        add(closeBtn);

    }


    public class deleteCustListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            ArrayList<Integer>cList = new ArrayList<Integer>();

            for (int z = 0; z < Customer.getCustomers().size(); z++){
                cList.add(Customer.getCustomers().get(z).getID());
            }
            
            if (cList.contains(Integer.parseInt(cEntry.getText()))){
                Customer.deleteCustomer(Integer.parseInt(cEntry.getText()));
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
            CustomerMenu newCc = new CustomerMenu();
            newCc.setVisible(true);
            dispose();
        }
    }
}


