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


public class DisplayAllStock extends JFrame {
    private JButton     cmdAddPerson;
    private JButton     cmdClose;
    private JButton     cmdSortAge;
    private JButton cmdSortName;
  

    private ArrayList<Stock> allStock;
    //private PersonListing thisForm;
    private  JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;

    public DisplayAllStock() {

        setSize(900,900);


        String[] columnNames=  {"NAME","TYPE","QUANTITY","LOW LEVEL NUMBER"};
        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        model.addRow(columnNames);
        allStock = Stock.viewStock(1);
        for (int i = 0 ;i < allStock.size();i++){
            String name= allStock.get(i).getStockName();
            String type = allStock.get(i).getType();

            Stock stk = allStock.get(i);
            String quant = String.valueOf(Stock.getQuantity(stk.getStockName()));
            String lowLevel = String.valueOf(allStock.get(i).getLevel());
            String[] item={name,type,quant,lowLevel};
            model.addRow(item);        
        }
        table.setVisible(true);

        add(table);

       
        

    }


}

