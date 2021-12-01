import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateStock extends JFrame
{
    private JTextField  itemName;      //name
    private JTextField  quantity;        //age
    private JTextField  lowLevel;        //age
    private JButton     cmdSave;
    private JButton     cmdClose;
    private JComboBox<StockType> sType;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
  
    public CreateStock()
    {
        //this.plisting = plisting;
        //entry = this;
        setTitle("ADD NEW ITEM TO SYSTEM");
        pnlCommand = new JPanel(); //NEW PANEL FOR ENERTING INFOR INTO FIELDS
        pnlDisplay = new JPanel(); //NEW PANEL FOR Saving and closing info


        pnlDisplay.add(new JLabel("ITEMNAME:")); 
        itemName = new JTextField();
        pnlDisplay.add(itemName);

        pnlDisplay.add(new JLabel("TYPE")); 
        sType = new JComboBox(StockType.values());
        pnlDisplay.add(sType);


        pnlDisplay.add(new JLabel("QUANTITY"));
        quantity = new JTextField();
        pnlDisplay.add(quantity);

        pnlDisplay.add(new JLabel("LOW-LEVEL NUMBER (OPTIONAL)"));
        lowLevel = new JTextField();
        pnlDisplay.add(lowLevel);

        pnlDisplay.setLayout(new GridLayout(4,5));

       
        cmdSave   = new JButton("Save");
        cmdClose   = new JButton("Close");
        cmdClose.addActionListener(new CloseListener());
        cmdSave.addActionListener(new SaveListener());
        //cmdSave.addActionListener(new AddToListListener());
        

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        //add(pnlAdd);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }



    private class CloseListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            dispose();
        }
    }
    
   private class SaveListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            String sName = itemName.getText();
            int qty = Integer.parseInt(quantity.getText());
            int sLevel = Integer.parseInt(lowLevel.getText());
            String stkType = String.valueOf(sType.getSelectedItem());
            Stock newStock = new Stock(StockType.valueOf(stkType),sName,qty,sLevel);
            newStock.createStock();
        }
    }
}
