import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DeleteStock extends JFrame {
    


    private JLabel titleLabel;
    private JLabel sFind;
    private JTextField sEntry;

    private JButton findstck;

    private String stckName ;
    private String stckLevel ;

    private JLabel stckNameLabel;
    private JLabel lvlLabel;

    private JButton deleteBtn;
    
    private JLabel notFound;
    private JLabel tryAgain;
    private JLabel stckDeleted;

    private JButton closeBtn;


    public DeleteStock()
    {
        setLayout(null);
        setSize(800,600);

        titleLabel = new JLabel("DELETE ITEM");
        titleLabel.setBounds(120,30,220,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        sFind = new JLabel("Please enter name of the stock item to be Deleted:");
        sFind.setBounds(140,90,200,40);
        sFind.setFont(sFind.getFont().deriveFont(15f));

        sEntry = new JTextField();
        sEntry.setBounds(140,120,150,20);
        
        findstck = new JButton("FIND");
        findstck.setBounds(160,155,100,35);
        findstck.addActionListener(new findStckListener());

        this.stckName  = "ITEM Name" ;
        this.stckLevel = "ITEM LEVEL";

        stckNameLabel = new JLabel(stckName);
        stckNameLabel.setBounds(160,180,200,35);
        stckNameLabel.setVisible(false);


        lvlLabel = new JLabel(stckLevel);
        lvlLabel.setBounds(160,250,200,35);
        lvlLabel.setVisible(false);
        

        deleteBtn = new JButton("DELETE");
        deleteBtn.setBounds (160,380,200,40);
        deleteBtn.addActionListener(new deleteItemListener());
        deleteBtn.setVisible(false);


        notFound = new JLabel("Stock Item Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);


        stckDeleted = new JLabel ("Stock Item Successfully DELETED!");
        stckDeleted.setBounds(110,450,250,50);
        stckDeleted.setFont(stckDeleted.getFont().deriveFont(15f));
        stckDeleted.setVisible(false);

        closeBtn = new JButton("CLOSE RETURN TO STOCK MENU");
        closeBtn.setBounds(110,500,300,35);
        closeBtn.addActionListener(new closeTabListener());


        add(titleLabel);
        add(sFind);
        add(sEntry);
        add(findstck);
        add(stckNameLabel);
        add(lvlLabel);
        add(deleteBtn);
        add(notFound);
        add(tryAgain);
        add(stckDeleted);
        add(closeBtn);

    }

    public class findStckListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (sEntry.getText().equals("TEST")) 
            {
                stckNameLabel.setVisible(true);
                lvlLabel.setVisible(true);
                notFound.setVisible(false);
                tryAgain.setVisible(false);
                deleteBtn.setVisible(true);
            }else{
                notFound.setVisible(true);
                tryAgain.setVisible(true);
                stckNameLabel.setVisible(false);
                lvlLabel.setVisible(false);
                deleteBtn.setVisible(false);
                //succDeleted.setVisible(false);
                
            }

        }
    }

    public class deleteItemListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            stckDeleted.setVisible(true);
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


