package GUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Order.*;


public class DisplayAllCustomers extends JFrame {
  

    private ArrayList<Customer> allCustomers;
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
