import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class findBrace extends JFrame {
    int PREF_W = 450;
    int PREF_H = PREF_W;
    private JButton braceFind;
    private JButton delPerson;
    private JButton returnToMenu;// initialization of new button
    private JTextField braceEntry;
    private JTextField newNameBrace;
    private JTextField newCol;
    private JTextField newCostT;
    private JLabel label;
    private JLabel findBrace;
    private JLabel nameBraceLabel;
    private JLabel collectionLabel;
    private JLabel editBrace;
    private JLabel newBName;
    private JLabel costLabel;
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

        braceEntry = new JTextField();
        braceEntry.setBounds(140,120,150,20);
        
        braceFind = new JButton("FIND");
        braceFind.setBounds(160,155,100,35);
        braceFind.addActionListener(new braceFindListener());

        this.bracelet  = "Bracelet Name" ;
        this.collection = "Collection";
        this.cost = "Cost";

        nameBraceLabel = new JLabel(bracelet);
        nameBraceLabel.setBounds(160,180,200,35);
        nameBraceLabel.setVisible(false);

        collectionLabel = new JLabel(collection);
        collectionLabel.setBounds(160,220,200,35);
        collectionLabel.setVisible(false);

        costLabel = new JLabel(cost);
        costLabel.setBounds(160,250,200,35);
        costLabel.setVisible(false);

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
        returnToMenu.addActionListener(new returnToListener());



        add(titleLabel);
        add(findBrace);
        add(braceEntry);
        add(braceFind);
        add(nameBraceLabel);
        add(collectionLabel);
        add(costLabel);
        add(notFound);
        add(tryAgain);
        add(returnToMenu);

    }

   public class braceFindListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (braceEntry.getText().equals("TEST")) // if statement to test findPromoter method
            {
                nameBraceLabel.setVisible(true);
                collectionLabel.setVisible(true);
                costLabel.setVisible(true);
                notFound.setVisible(false);
                tryAgain.setVisible(false);
            }else{
                notFound.setVisible(true);
                tryAgain.setVisible(true);
                nameBraceLabel.setVisible(false);
                collectionLabel.setVisible(false);
                costLabel.setVisible(false);
                //delPerson.setVisible(false);
                //succDeleted.setVisible(false);
                
            }

        }
    }

    public class returnToListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           dispose();

        }
    }

}