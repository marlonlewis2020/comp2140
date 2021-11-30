import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class EstimateBracelet extends JFrame {
    int PREF_W = 450;
    int PREF_H = PREF_W;
    private JLabel titleLabel;
    private JLabel findBrace;
    private JLabel showEst;
    private String [] dropChoices;
    private ArrayList<String>choices  = new ArrayList<String>();

    static JComboBox<String> braceletOps;

    private JButton estBtn;
    private JButton returnMen;


    public EstimateBracelet()
    {
        setLayout(null);
        setSize(800,800);

        titleLabel = new JLabel("ESTIMATE BRACELET QUANTITY");
        titleLabel.setBounds(120,30,220,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        findBrace = new JLabel("Please Choose which BRACELET to get an Estimate for:");
        findBrace.setBounds(140,90,200,40);
        findBrace.setFont(findBrace.getFont().deriveFont(15f));

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

        showEst = new JLabel("Please Choose which BRACELET to get an Estimate for:");
        showEst.setBounds(140,200,200,40);
        showEst.setFont(showEst.getFont().deriveFont(15f));




        returnMen  = new JButton("Return to Bracelet Menu");
        returnMen.setBounds(140,250,200,40);
        returnMen.addActionListener(new returnListener());


        add(titleLabel);
        add(findBrace);
        add(braceletOps);
        add(estBtn);
        add(returnMen);

    }

    
    public class returnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
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
                estimateTxt += String.valueOf(qty.get(x));
            }

            showEst.setText(estimateTxt);
        }

        }
    }


}

