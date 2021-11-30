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


public class DisplayAllBrace extends JFrame {
    private JButton     cmdAddPerson;
    private JButton     cmdClose;
    private JButton     cmdSortAge;
    private JButton cmdSortName;
    private JTable braceTable;
  
    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    //private ArrayList<Person> plist;
    private DisplayAllBrace thisForm;
    private  JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;

    public DisplayAllBrace() {
        this.setLayout(new GridLayout(2,1));
        this.setSize(800,500);

        pnlCommand = new JPanel();
        pnlCommand.setSize(30,50);
        pnlCommand.setBackground(Color.BLUE);
        
        cmdAddPerson  = new JButton("Add Person");
        cmdSortAge  = new JButton("Sort by Age");
        cmdClose   = new JButton("Close");
        cmdSortName = new JButton ("Sort by First Name");

        pnlCommand.add(cmdAddPerson);
        pnlCommand.add(cmdSortAge);
        pnlCommand.add(cmdClose);
        pnlCommand.add(cmdSortName);
        
        pnlDisplay = new JPanel();
        pnlDisplay.setSize(100,200);
        pnlDisplay.setBackground(Color.MAGENTA);

        String[][] data = {
            { "Kundan Kumar Jha", "4031", "CSE" },
            { "Anand Jha", "6014", "IT" }
        };
 
        // Column Names
        String[] columnNames = { "Name", "Roll Number", "Department" };
 
        // Initializing the JTable
        braceTable = new JTable(data, columnNames);
        braceTable.setSize(100,50);
 
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(braceTable);
        
        pnlDisplay.add(braceTable);
        pnlDisplay.add(sp);


        add(pnlCommand);
        add(pnlDisplay);




    }
}
