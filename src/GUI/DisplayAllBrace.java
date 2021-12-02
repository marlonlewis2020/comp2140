package GUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Inventory.*;


public class DisplayAllBrace extends JFrame {
  

    private ArrayList<Bracelet> bracePop;
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
