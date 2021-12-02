package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Inventory.*;


public class findBrace extends JFrame implements ActionListener {
    int PREF_W = 450;
    int PREF_H = PREF_W;
    private JButton braceFind_2;
    private JButton delPerson;
    private JButton returnToMenu;// initialization of new button
    private JTextField braceEntry_2;
    private JTextField newNameBrace;
    private JTextField newCol;
    private JTextField newCostT;
    private JLabel label;
    private JLabel findBrace;
    private JLabel nameBraceLabel_1;
    private JLabel nameBraceLabel_2;
    private JLabel collectionLabel_1;
    private JLabel editBrace;
    private JLabel newBName;
    private JLabel costLabel_1;
    private JLabel succUpdated;
    


    private JLabel found;
    private JLabel notFound;
    private JLabel tryAgain;
    private JLabel pNameBudg;
    private String bracelet;
    private String collection;
    private String cost;

    public findBrace()
    {
        setLayout(null);
        setSize(800,800);

        JLabel titleLabel = new JLabel("FIND BRACELET INFORMATION");
        titleLabel.setBounds(120,30,220,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        findBrace = new JLabel("Please Enter BRACELET NAME:");
        findBrace.setBounds(140,90,200,40);
        findBrace.setFont(findBrace.getFont().deriveFont(15f));

        braceEntry_2 = new JTextField();
        braceEntry_2.setBounds(140,120,150,20);
        
        braceFind_2 = new JButton("FIND");
        braceFind_2.setBounds(160,155,100,35);
        

        nameBraceLabel_1 = new JLabel(bracelet);
        nameBraceLabel_1.setBounds(160,180,200,35);
        nameBraceLabel_1.setVisible(false);

        collectionLabel_1 = new JLabel(collection);
        collectionLabel_1.setBounds(160,220,200,35);
        collectionLabel_1.setVisible(false);

        costLabel_1 = new JLabel(cost);
        costLabel_1.setBounds(160,250,200,35);
        costLabel_1.setVisible(false);

        notFound = new JLabel("Bracelet Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);

        returnToMenu = new JButton("RETURN TO BRACELT MENU");
        returnToMenu.setBounds(160,500,100,35);
        returnToMenu.addActionListener(this);



        add(titleLabel);
        add(findBrace);
        add(braceEntry_2);
        add(braceFind_2);
        add(nameBraceLabel_1);
        add(collectionLabel_1);
        add(costLabel_1);
        add(notFound);
        add(tryAgain);
        add(returnToMenu);

        braceFind_2.addActionListener(this);

       

    }

    public void actionPerformed(ActionEvent ae)    
    {
        if (ae.getSource() == returnToMenu){
            dispose();

        }else if (ae.getSource()== braceFind_2){
            
            if (Bracelet.searchByName(braceEntry_2.getText()) != null) {
                System.out.println("bracelt");
                
                bracelet = braceEntry_2.getText();
                collection = Bracelet.searchByName(braceEntry_2.getText()).getCollection();
                cost  = String.valueOf((Bracelet.searchByName(braceEntry_2.getText()).getCost()));
                System.out.println(bracelet);
                System.out.println(collection);
                System.out.println(cost);
                nameBraceLabel_1.setText(bracelet);
                nameBraceLabel_1.setVisible(true);

                collectionLabel_1.setText(collection);
                collectionLabel_1.setVisible(true);

                costLabel_1.setText(cost);
                costLabel_1.setVisible(true);

                notFound.setVisible(false);
                tryAgain.setVisible(false);

            }else{
                notFound.setVisible(true);
                tryAgain.setVisible(true);
                nameBraceLabel_1.setVisible(false);
                collectionLabel_1.setVisible(false);
                costLabel_1.setVisible(false);
            }
            

        }
   

    
    }
}

