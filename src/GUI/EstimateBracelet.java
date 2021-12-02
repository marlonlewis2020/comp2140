package GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import Inventory.*;


public class EstimateBracelet extends JFrame {
    int PREF_W = 450;
    int PREF_H = PREF_W;
    private JLabel titleLabel;
    private JLabel showEst;
    private JLabel EstShw;
    private JLabel EstShw1;
    private String [] dropChoices;
    private ArrayList<String>choices  = new ArrayList<String>();

    static JComboBox<String> braceletOps;

    private JButton estBtn;
    private JButton returnMen;


    public EstimateBracelet()
    {
        setLayout(null);
        setSize(1000,1000);

        titleLabel = new JLabel("ESTIMATE BRACELET QUANTITY");
        titleLabel.setBounds(120,30,430,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        showEst = new JLabel("Please Choose which BRACELET to get an Estimate for:");
        showEst.setBounds(140,90,400,40);
        showEst.setFont(showEst.getFont().deriveFont(15f));


        for (int i = 0; i < Bracelet.getBracelets().size(); i++){
            choices.add (Bracelet.getBracelets().get(i).getName());
        }
        
        dropChoices = choices.toArray(new String[choices.size()]);
        braceletOps = new JComboBox<String>(dropChoices);
        braceletOps.setBounds(140,120,200,40);
        braceletOps.setVisible(true);

        estBtn = new JButton("ESTIMATE");
        estBtn.setBounds(140,150,200,40);
        estBtn.addActionListener(new EstimateListener());

        EstShw = new JLabel("Showing estimate for small medium and large, (small,medium,large)");
        EstShw.setBounds(140,220,550,40);
        EstShw.setFont(EstShw.getFont().deriveFont(15f));

        EstShw1 = new JLabel("SMALL,MEDIUM,LARGE");
        EstShw1.setBounds(140,240,200,40);
        EstShw1.setFont(EstShw1.getFont().deriveFont(15f));
        EstShw1.setVisible(false);


        returnMen  = new JButton("Return to Bracelet Menu");
        returnMen.setBounds(140,290,300,40);
        returnMen.addActionListener(new returnListener());


        add(titleLabel);
        add(showEst);
        add(braceletOps);
        add(estBtn);
        add(EstShw);
        add(EstShw1);
        add(returnMen);

    }

    
    public class returnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            BraceletMenu newBc = new BraceletMenu();
            newBc.setVisible(true);
            dispose();

        }
    }

    public class EstimateListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){

        ArrayList<Integer>qty = new ArrayList<Integer>();
        String estimateTxt = "";

        {
            String option = String.valueOf(braceletOps.getSelectedItem());
            Bracelet brace  = Bracelet.searchByName(option);
            qty = brace.estimateQty();

            for (int x = 0; x < qty.size(); x++){
                estimateTxt += String.valueOf(qty.get(x)) + ",";
            }

            EstShw1.setText(estimateTxt);
            EstShw1.setVisible(true);
        }

        }
    }


}

