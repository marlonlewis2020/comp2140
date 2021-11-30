import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class updateBrace extends JFrame {
    int PREF_W = 450;
    int PREF_H = PREF_W;
    private JButton braceFind;
    private JButton updateBtn;
    private JButton returnBtn;
    private JTextField braceEntry;
    private JTextField newNameBrace;
    private JTextField newCol;
    private JTextField newCostT;
    private JLabel findBrace;
    private JLabel nameBraceLabel;
    private JLabel collectionLabel;
    private JLabel editBrace;
    private JLabel newBName;
    private JLabel costLabel;
    private JLabel succUpdated;

    private JLabel notFound;
    private JLabel tryAgain;
    private String bracelet;
    private String collection;
    private String cost;
    private String editedColl;
    private Double editedCost;

    public updateBrace()
    {
        setLayout(null);
        setSize(800,800);

        JLabel titleLabel = new JLabel("UPDATE BRACELET INFORMATION");
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
        
        
        editBrace = new JLabel("EDIT DESIRED FIELDS");
        editBrace.setBounds(160,300,300,35);

        newBName = new JLabel("Bracelet Name");
        newBName.setBounds(160,320,200,40);
        newBName.setFont(newBName.getFont().deriveFont(15f));

        newNameBrace = new JTextField();
        newNameBrace.setBounds(140,350,150,20);


        JLabel newCollection = new JLabel("Collection");
        newCollection.setVisible(true);
        newCollection.setBounds(140,380,200,40);

        newCol= new JTextField(10);
        newCol.setBounds(140,410,150,20);

        
        JLabel newCost = new JLabel("COST");
        newCost.setVisible(true);
        newCost.setBounds (160,440,200,40);

        newCostT = new JTextField();
        newCostT.setBounds(140,480,150,20);


        updateBtn = new JButton("UPDATE");
        updateBtn.setBounds (160,520,200,40);
        updateBtn.addActionListener(new updateBletListener());


        notFound = new JLabel("Bracelet Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);


        succUpdated = new JLabel ("Bracelet Successfully Updated!");
        succUpdated.setBounds(110,550,250,50);
        succUpdated.setFont(succUpdated.getFont().deriveFont(15f));
        succUpdated.setVisible(false);

        returnBtn = new JButton("RETURN TO MAIN BRACELET MENU");
        returnBtn.setBounds (160,580,300,40);
        returnBtn.addActionListener(new returnListener());



        add(titleLabel);
        add(findBrace);
        add(braceEntry);
        add(braceFind);
        add(nameBraceLabel);
        add(collectionLabel);
        add(costLabel);
        add(editBrace);
        add(newBName);
        add(newNameBrace);
        add(newCollection);
        add(newCol);
        add(newCost);
        add(newCostT);
        add(updateBtn);
        add(notFound);
        add(tryAgain);

    }

    public class updateBletListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Bracelet brace = Bracelet.searchByName(braceEntry.getText());

            if (brace != null)
            {
                bracelet = braceEntry.getText();
                if (newCol.getText().equals("")){
                    editedColl = brace.getCollection();
               }else{
                   editedColl = newCol.getText();
               }
               if (newCostT.getText().equals("")){
                   editedCost = brace.getCost();
               }else{
                    editedCost = Double.parseDouble(newCostT.getText());
               }
            Bracelet editedBrace = new Bracelet(braceEntry.getText(),editedCost,brace.getSmallBeadQty(),brace.getMedBeadQty(), brace.getLgBeadQty(),editedColl);
            Bracelet.updateBracelet(braceEntry.getText(), editedBrace);
            editedBrace.addToDatabase();
            succUpdated.setVisible(true);
            }else{
                notFound.setVisible(true);
                tryAgain.setVisible(true);
                nameBraceLabel.setVisible(false);
                collectionLabel.setVisible(false);
                costLabel.setVisible(false);
                
            }

        }
    }

    public class braceFindListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            if (Bracelet.searchByName(braceEntry.getText()) != null) // if statement to test findPromoter method
            {
                bracelet = braceEntry.getText();
                nameBraceLabel.setVisible(true);
                collection = Bracelet.searchByName(braceEntry.getText()).getCollection();
                collectionLabel.setVisible(true);
                cost = String.valueOf(Bracelet.searchByName(braceEntry.getText()).getCost());
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

    
    public class returnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            dispose();

        }
    }




    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}



