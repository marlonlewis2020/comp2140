import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
  
    public CreateNewBrace()
    {
        //this.plisting = plisting;
        //entry = this;
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

        JLabel lbl = new JLabel("BRACELET TYPE");
        lbl.setVisible(true);
        pnlDisplay.add(lbl);
        String[] choices = { "CHOICE 1","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setVisible(true);
        pnlDisplay.add(cb);
        
        pnlDisplay.add(new JLabel("Amount for Type:"));
        typeNumber = new JTextField(20);
        pnlDisplay.add(typeNumber);

        JButton btn = new JButton("ADD TYPE AND NUMBER");
        pnlDisplay.add(btn,Component.CENTER_ALIGNMENT);

        JButton n_btn = new JButton("ADD TYPE AND NUMBER");
        pnlDisplay.add(n_btn);
        n_btn.setVisible(false);

        //MEDIUM BRACELET
        pnlDisplay.add(new JLabel("MEDIUM SIZED BRACELET: PLEASE CHOOSE EACH TYPE ONCE"));
        medBrace = new JTextField(20);
        pnlDisplay.add(medBrace);
        medBrace.setVisible(false);

        JLabel lb2 = new JLabel("BRACELET TYPE");
        lb2.setVisible(true);
        pnlDisplay.add(lb2);
        String[] choices_2 = { "CHOICE 1","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
        final JComboBox<String> cb_2 = new JComboBox<String>(choices_2);
        cb_2.setVisible(true);
        pnlDisplay.add(cb_2);
        
        pnlDisplay.add(new JLabel("Amount for Type:"));
        typeNumber_2 = new JTextField(20);
        pnlDisplay.add(typeNumber_2);

        JButton btn_2 = new JButton("ADD TYPE AND NUMBER");
        pnlDisplay.add(btn_2);

        JButton nbtn_2 = new JButton("ADD TYPE AND NUMBER");
        pnlDisplay.add(nbtn_2);
        nbtn_2.setVisible(false);
        
        //LARGE BRACELET
        pnlDisplay.add(new JLabel("LARGE SIZED BRACELET: PLEASE CHOOSE EACH TYPE ONCE"));
        largeBrace = new JTextField(20);
        pnlDisplay.add(largeBrace);
        largeBrace.setVisible(false);

        JLabel lb3 = new JLabel("BRACELET TYPE");
        lb3.setVisible(true);
        pnlDisplay.add(lb3);
        String[] choices_3 = { "CHOICE 1","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
        final JComboBox<String> cb_3 = new JComboBox<String>(choices_3);
        cb_3.setVisible(true);
        pnlDisplay.add(cb_3);
        

        pnlDisplay.add(new JLabel("Amount for Type:"));
        typeNumber_3 = new JTextField(20);
        pnlDisplay.add(typeNumber_3);

        JButton btn_3 = new JButton("ADD TYPE AND NUMBER");
        pnlDisplay.add(btn_3);


        pnlDisplay.setLayout(new GridLayout(15,15));

       
        cmdSave   = new JButton("Save");
        cmdClose   = new JButton("Close");
        cmdClose.addActionListener(new VisibleListener());
        //cmdSave.addActionListener(new AddToListListener());
        

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

    //implemet the add to listners for each bead typ and number

   private class AddToListListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event){}
        //create new bracelet
        //Bracelet.addToArray(newbRacelet created)

        //For bead type and Number
        //For Small("Option 1-number;Option2-number")
        //Same for medium and large
        
        

    }
        


}
