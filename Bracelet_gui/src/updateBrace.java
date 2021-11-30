import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class updateBrace extends JFrame {
    int PREF_W = 450;
    int PREF_H = PREF_W;
    private JButton braceFind;
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


        JButton updateBtn = new JButton("UPDATE");
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

        

        /*cancel = new JButton ("PRESS CANCEL TO GO BACK TO MAIN MENU");// Naming of button
        cancel.setBounds(160,379,300,50);// placement of button.. might need to change to fit properly
        findPerson.addActionListener(new FindPersonListener());
        delPerson.addActionListener(new DeletePersonListener());
        cancel.addActionListener(new CancelScreenListener());// action listener for cancel button


        found = new JLabel("Promoter Found!");
        found.setBounds(150,195,150,50);
        found.setFont(found.getFont().deriveFont(15f));


        pNameBudg = new JLabel("Promoter Name -- Promoter Budget");
        //pNameBudg.setBounds(120,250,350,50);
        pNameBudg.setFont(pNameBudg.getFont().deriveFont(15f));*/


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
        /*add(found);
        add(notFound);
        add(tryAgain);
        add(delPerson);
        add(succDeleted);
        add(cancel);//cancel button added to content pane
        delPerson.setVisible(false);
        succDeleted.setVisible(false);
        found.setVisible(false);
        notFound.setVisible(false);
        tryAgain.setVisible(false);*/

        setBackground(Color.MAGENTA);

    }

   public class braceFindListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (braceEntry.getText().equals("TEST")) // Bracelet.serachbyName (braceEntry.getText()) != null
            {
                //brace = Bracelet.serachByName(braceEntry.getText())
                //if newColl.getText().equals(""){
                //    editedColl = brace.getCollection()
               // }else{
                //    editedColl = newColl.getText()
               // }
               //if newCost.getText().equals(""){
                //    editedCost = brace.getCost()
               // }else{
                //    editedCost = newCost.getText()
               // }
                //editedBracelet = new Bracelet(braceEntry.getText(),editedCost,brace.getSmallBeadQty,brace.getMedBeadQty, brace.getLGBeadQty,editedColl)
                //print get Name //check if the fields are empty
                //print get collection //check if the fields are empty
                //print cost //check if fields are empty
                //bracd type and number in variables
                nameBraceLabel.setVisible(true);
                collectionLabel.setVisible(true);
                costLabel.setVisible(true);
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

    public class updateBletListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            //check if fields are empty 
            //use get id, get small bead quantity
            //Bracelet.updateBracelet(Name of old brace,newname, new coll,newcost, with old info on type of number)
            succUpdated.setVisible(true);
            dispose();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}



