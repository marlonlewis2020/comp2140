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


public class DisplayAllOrders extends JFrame {
    private JButton     cmdAddPerson;
    private JButton     cmdClose;
    private JButton     cmdSortAge;
    private JButton cmdSortName;
  

    private ArrayList<Order> allOrders;
    //private PersonListing thisForm;
    private  JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;

    public DisplayAllOrders() {
        setSize(900,900);


        String[] columnNames=  {"ORDER NUMBER","BRACELETS AND ORDER QUANTITY","CUSTOMER ID","PICKUP LOCATION","ORDER DATE"};
        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        model.addRow(columnNames);
        allOrders = Order.orders;
        for (int i = 0 ;i < allOrders.size();i++){
            String ordNumString= String.valueOf(allOrders.get(i).getOrderNo());
            String bleString= allOrders.get(i).getbraceletQuantities();
            String cusId = String.valueOf(allOrders.get(i).getcustomerID());
            String picTion = allOrders.get(i).getpickupLocation();
            String dateOrd = String.valueOf(allOrders.get(i).getorderDate());
            String[] item={ordNumString,bleString,cusId,picTion,dateOrd};
            model.addRow(item);        
        }
        table.setVisible(true);

        add(table);

       
        

    }


}
