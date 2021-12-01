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


public class DisplayAllBrace extends JFrame {
    private JButton     cmdAddPerson;
    private JButton     cmdClose;
    private JButton     cmdSortAge;
    private JButton cmdSortName;
  

    private ArrayList<Bracelet> bracePop;
    //private PersonListing thisForm;
    private  JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;

    public DisplayAllBrace() {
        setSize(900,900);


        String[] columnNames=  {"Name","COST","SMALL BEAD","MED BEAD", "LARGE BEAD","COLLECTION"};
        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        model.addRow(columnNames);
        bracePop = Bracelet.getBracelets();
        for (int i = 0 ;i < bracePop.size();i++){
            String name= bracePop.get(i).getName();
            String collection = bracePop.get(i).getCollection();
            String small = bracePop.get(i).getSmallBeadQty();
            String med  = bracePop.get(i).getMedBeadQty();
            String large = bracePop.get(i).getLgBeadQty();
            String cost  =String.valueOf(bracePop.get(i).getCost());
            String[] item={name,cost,small,med,large,collection};
            model.addRow(item);        
        }
        table.setVisible(true);

        add(table);

       
        

    }


}
