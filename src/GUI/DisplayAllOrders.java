package GUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Order.*;


public class DisplayAllOrders extends JFrame {
  

    private ArrayList<Order> allOrders;
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
