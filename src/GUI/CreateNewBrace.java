package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import Inventory.*;

public class CreateNewBrace extends JFrame
{
    private JTextField  braceName;       //name
    private JTextField  braceCost;        //age
    private JTextField  braceCollection;       //name
    private JTextField  typeNumber;
    private JTextField  typeNumber_2;
    private JTextField  typeNumber_3;
    private JTextField  smallBrace;
    private JTextField  medBrace;
    private JTextField  largeBrace;  
    private JButton     cmdSave;
    private JButton     cmdClose;
    private JButton     btn_1;
    private JButton     btn_2;
    private JButton     btn_3;
    public String small = "";
    public String med = "";
    public String lg = "";

    static JComboBox<String> cb_1;
    static JComboBox<String> cb_2;
    static JComboBox<String> cb_3;
    private String [] braceChoices;
    private ArrayList<String>stockChoices  = new ArrayList<String>();
    




    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
  
    public CreateNewBrace()
    {
        //this.plisting = plisting;
        //entry = this;

        for (int i = 0; i < Stock.viewStock(1).size(); i++){
            if (Stock.viewStock(1).get(i).getType().equals("Beads")){
                stockChoices.add((Stock.viewStock(1).get(i).getStockName()));
            }
        }
        braceChoices = stockChoices.toArray(new String[stockChoices.size()]);

        setTitle("CREATING A NEW BRACELET");
        pnlCommand = new JPanel(); //NEW PANEL FOR ENERTING INFOR INTO FIELDS
        pnlDisplay = new JPanel(); //NEW PANEL FOR Saving and closing info



        pnlDisplay.add(new JLabel("Name:")); 
        braceName = new JTextField(20);
        pnlDisplay.add(braceName);


        pnlDisplay.add(new JLabel("Cost:"));
        braceCost = new JTextField(20);
        pnlDisplay.add(braceCost);

        

        pnlDisplay.add(new JLabel("Collection:"));
        braceCollection = new JTextField(20);
        pnlDisplay.add(braceCollection);

        //SMALL BRACELET
        pnlDisplay.add(new JLabel("SMALL SIZED BRACELET: CHOOSE EACH TYPE ONCE"));
        smallBrace = new JTextField(20);
        pnlDisplay.add(smallBrace);
        smallBrace.setVisible(false);

        JLabel lbl = new JLabel("BEAD TYPE");
        lbl.setVisible(true);
        pnlDisplay.add(lbl);
        cb_1 = new JComboBox<String>(braceChoices);
        cb_1.setVisible(true);
        pnlDisplay.add(cb_1);
        
        pnlDisplay.add(new JLabel("Amount for Type:"));
        typeNumber = new JTextField(20);
        pnlDisplay.add(typeNumber);

        btn_1 = new JButton("ADD TYPE AND NUMBER");
        pnlDisplay.add(btn_1,Component.CENTER_ALIGNMENT);
        btn_1.addActionListener(new SMALLbtn_1Listener());

        //PLACE HOLDER FOR LAYOUT
        JButton btn= new JButton("ADD TYPE AND NUMBER");
        pnlDisplay.add(btn);
        btn.setVisible(false);

        //MEDIUM BRACELET
        pnlDisplay.add(new JLabel("MEDIUM SIZED BRACELET: PLEASE CHOOSE EACH TYPE ONCE"));
        medBrace = new JTextField(20);
        pnlDisplay.add(medBrace);
        medBrace.setVisible(false);

        JLabel lb2 = new JLabel("BEAD TYPE");
        lb2.setVisible(true);
        pnlDisplay.add(lb2);
        //String[] choices_2 = { "CHOICE 1","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
        cb_2 = new JComboBox<String>(braceChoices);
        cb_2.setVisible(true);
        pnlDisplay.add(cb_2);
        
        pnlDisplay.add(new JLabel("Amount for Type:"));
        typeNumber_2 = new JTextField(20);
        pnlDisplay.add(typeNumber_2);

        btn_2 = new JButton("ADD TYPE AND NUMBER");
        pnlDisplay.add(btn_2);
        btn_2.addActionListener(new MEDbtn_2Listener());

        //place holder for layout
        JButton nbtn_2 = new JButton("ADD TYPE AND NUMBER");
        pnlDisplay.add(nbtn_2);
        nbtn_2.setVisible(false);
        
        //LARGE BRACELET
        pnlDisplay.add(new JLabel("LARGE SIZED BRACELET: PLEASE CHOOSE EACH TYPE ONCE"));
        largeBrace = new JTextField(20);
        pnlDisplay.add(largeBrace);
        largeBrace.setVisible(false);

        JLabel lb3 = new JLabel("BEAD TYPE");
        lb3.setVisible(true);
        pnlDisplay.add(lb3);
        //String[] choices_3 = { "CHOICE 1","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
        cb_3 = new JComboBox<String>(braceChoices);
        cb_3.setVisible(true);
        pnlDisplay.add(cb_3);
        

        pnlDisplay.add(new JLabel("Amount for Type:"));
        typeNumber_3 = new JTextField(20);
        pnlDisplay.add(typeNumber_3);

        btn_3 = new JButton("ADD TYPE AND NUMBER");
        pnlDisplay.add(btn_3);
        btn_3.addActionListener(new LGbtn_3Listener());


        pnlDisplay.setLayout(new GridLayout(15,15));

       
        cmdSave   = new JButton("Save");
        cmdClose   = new JButton("Close");
        cmdClose.addActionListener(new VisibleListener());
       cmdSave.addActionListener(new saveListener());
        

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        //add(pnlAdd);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    private class VisibleListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            dispose();
        }
    }

    private class SMALLbtn_1Listener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)

        {
            if (!(cb_1.getSelectedItem().equals("") && typeNumber.getText().equals(""))){
                small += cb_1.getSelectedItem() + "-" + typeNumber.getText()+ ";";
            }
            else{
                small +="";
            }
            
           
        }
    }

    private class MEDbtn_2Listener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            if (!(cb_2.getSelectedItem().equals("") && typeNumber_2.getText().equals(""))){
                med += cb_2.getSelectedItem() + "-" + typeNumber_2.getText()+ ";";
            }
            else{
                med +="";
            }
            
        }
    }

    private class LGbtn_3Listener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)

        {
            if (!(cb_3.getSelectedItem().equals("") && typeNumber_3.getText().equals(""))){
                lg += cb_3.getSelectedItem() + "-" + typeNumber_3.getText()+ ";";
            }
            else{
                lg +="";
            }
        
           
        }
    }

    //implemet the add to listners for each bead typ and number

   private class saveListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
        //create new bracelet
        System.out.println("Why is this not working");
        //Bracelet.populate();
        System.out.println(Bracelet.searchByName(braceName.getText()));
        if (Bracelet.searchByName(braceName.getText())==null){
            System.out.println("Deeven care");
        
        try{
            System.out.println("go home");
            String name = braceName.getText();
            String collection = braceCollection.getText();
            double cost = Double.parseDouble(braceCost.getText());
            if (!(small.equals(""))){
                small = small.substring(0,small.length()-1); }

            if (!(med.equals(""))){
                med = med.substring(0,med.length()-1); }

            if  (!(lg.equals(""))){
                lg = lg.substring(0,lg.length()-1); }

            Bracelet newBrace = new Bracelet(name,cost,small,med,lg,collection);
            newBrace.addToDatabase();
            System.out.println(newBrace.getName());

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    }
}

}
        
