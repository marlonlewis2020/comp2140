package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.Collections;
import java.awt.Color;
import Inventory.*;
import Order.*;


public class DisplayAllCustomers extends JFrame {
    private JButton     cmdAddPerson;
    private JButton     cmdClose;
    private JButton     cmdSortAge;
    private JButton cmdSortName;
  

    private ArrayList<Customer> allCustomers;
    //private PersonListing thisForm;
    private  JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;

    public DisplayAllCustomers() {
        setSize(900,900);


        String[] columnNames=  {"CUSTOMER ID","CUSTOMER NAME","TELEPHONE", "PICKUP LOCATION",};
        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        model.addRow(columnNames);
        allCustomers = Customer.getCustomers();
        for (int i = 0 ;i < allCustomers.size();i++){
            String custIdString= String.valueOf(allCustomers.get(i).getID());
            String custNameString= allCustomers.get(i).getcustomerName();
            String tPhone= String.valueOf(allCustomers.get(i).getphoneNumber());
            String picLtn = allCustomers.get(i).getpickupLocation();
            String[] item={custIdString,custNameString,tPhone,picLtn};
            model.addRow(item);        
        }
        table.setVisible(true);

        add(table);

       
        

    }


}
