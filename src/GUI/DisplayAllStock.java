package GUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Inventory.*;


public class DisplayAllStock extends JFrame {
  

    private ArrayList<Stock> allStock;
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

