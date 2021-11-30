import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class deleteBrace extends JFrame {
    


    private JLabel titleLabel;
    private JLabel bFind;
    private JTextField bEntry;

    private JButton findBlet;

    private String bletName ; 
    private String bletColl ;
    private String bletCost ;

    private JLabel bletNameLabel;
    private JLabel clxnLabel;
    private JLabel cstLabel;

    private JButton deleteBtn;
    
    private JLabel notFound;
    private JLabel tryAgain;
    private JLabel bletDeleted;

    private JButton closeBtn;


    public deleteBrace()
    {
        setLayout(null);
        setSize(800,600);

        titleLabel = new JLabel("DELETE BRACELET");
        titleLabel.setBounds(120,30,220,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        bFind = new JLabel("Please enter name of Bracelet to be Deleted:");
        bFind.setBounds(140,90,200,40);
        bFind.setFont(bFind.getFont().deriveFont(15f));

        bEntry = new JTextField();
        bEntry.setBounds(140,120,150,20);
        
        findBlet = new JButton("FIND");
        findBlet.setBounds(160,155,100,35);
        findBlet.addActionListener(new findBletListener());

        this.bletName  = "Bracelet Name" ;
        this.bletColl = "Collection";
        this.bletCost = "Cost";

        bletNameLabel = new JLabel(bletName);
        bletNameLabel.setBounds(160,180,200,35);
        bletNameLabel.setVisible(false);

        clxnLabel = new JLabel(bletColl);
        clxnLabel.setBounds(160,220,200,35);
        clxnLabel.setVisible(false);

        cstLabel = new JLabel(bletCost);
        cstLabel.setBounds(160,250,200,35);
        cstLabel.setVisible(false);
        

        deleteBtn = new JButton("DELETE");
        deleteBtn.setBounds (160,380,200,40);
        deleteBtn.addActionListener(new deleteUserListener());
        deleteBtn.setVisible(false);


        notFound = new JLabel("Bracelet Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);


        bletDeleted = new JLabel ("Bracelet Successfully DELETED!");
        bletDeleted.setBounds(110,450,250,50);
        bletDeleted.setFont(bletDeleted.getFont().deriveFont(15f));
        bletDeleted.setVisible(false);

        closeBtn = new JButton("CLOSE RETURN TO BRACELET MENU");
        closeBtn.setBounds(110,500,300,35);
        closeBtn.addActionListener(new closeTabListener());


        add(titleLabel);
        add(bFind);
        add(bEntry);
        add(findBlet);
        add(bletNameLabel);
        add(clxnLabel);
        add(cstLabel);
        add(deleteBtn);
        add(notFound);
        add(tryAgain);
        add(bletDeleted);
        add(closeBtn);

    }

    public class findBletListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (bEntry.getText().equals("TEST")) 
            {
                bletNameLabel.setVisible(true);
                clxnLabel.setVisible(true);
                cstLabel.setVisible(true);
                notFound.setVisible(false);
                tryAgain.setVisible(false);
                deleteBtn.setVisible(true);
            }else{
                notFound.setVisible(true);
                tryAgain.setVisible(true);
                bletNameLabel.setVisible(false);
                clxnLabel.setVisible(false);
                cstLabel.setVisible(false);
                deleteBtn.setVisible(false);
                //succDeleted.setVisible(false);
                
            }

        }
    }

    public class deleteUserListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            //Bracelet.deleteBracelet(braceletName);
            bletDeleted.setVisible(true);
            closeBtn.setVisible(true);
        }
    }

    public class closeTabListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }

}

