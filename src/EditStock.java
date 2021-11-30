import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EditStock extends JFrame {
    


    private JLabel titleLabel;
    private JLabel stckFind;
    private JTextField stckEntry;

    private JButton finds;
    private JButton upStck;

    private String sName ;
    private String sLevel ;

    private JLabel sNameLabel;
    private JLabel lLabel;

    
    private JLabel notFound;
    private JLabel tryAgain;
    private JLabel editStck;
    private JLabel newSName;
    private JTextField newNameStck;
    private JLabel newQuant;
    private JLabel stckUpdate;
    private JTextField newQ;

    private JButton closeBtn;


    public EditStock()
    {
        setLayout(null);
        setSize(800,600);

        titleLabel = new JLabel("FIND ITEM INFORMATION");
        titleLabel.setBounds(120,30,220,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        stckFind = new JLabel("Please enter name of the stock ITEM to be updated");
        stckFind.setBounds(140,90,300,40);
        stckFind.setFont(stckFind.getFont().deriveFont(15f));

        stckEntry = new JTextField();
        stckEntry.setBounds(140,120,150,20);
        
        finds = new JButton("FIND");
        finds.setBounds(160,155,100,35);
        finds.addActionListener(new findSListener());

        this.sName  = "ITEM Name" ;
        this.sLevel = "ITEM LEVEL";

        sNameLabel = new JLabel(sName);
        sNameLabel.setBounds(160,180,200,35);
        sNameLabel.setVisible(false);


        lLabel = new JLabel(sLevel);
        lLabel.setBounds(160,250,200,35);
        lLabel.setVisible(false);

        notFound = new JLabel("Stock Item Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);

        editStck = new JLabel("EDIT DESIRED FIELDS");
        editStck.setBounds(160,300,300,35);

        newSName = new JLabel("Bracelet Name");
        newSName.setBounds(160,320,200,40);
        newSName.setFont(newSName.getFont().deriveFont(15f));

        newNameStck = new JTextField();
        newNameStck.setBounds(140,350,150,20);


        newQuant = new JLabel("Collection");
        newQuant.setVisible(true);
        newQuant.setBounds(140,380,200,40);

        newQ = new JTextField(10);
        newQ.setBounds(140,410,150,20);
        
        upStck = new JButton("UPDATE");
        upStck.setBounds (160,450,200,40);
        upStck.addActionListener(new updateStockListener());

        stckUpdate = new JLabel ("Stock Item Successfully UPDATED!");
        stckUpdate.setBounds(110,490,250,50);
        stckUpdate.setFont(stckUpdate.getFont().deriveFont(15f));
        stckUpdate.setVisible(false);


        closeBtn = new JButton("CLOSE RETURN TO STOCK MENU");
        closeBtn.setBounds(110,520,300,35);
        closeBtn.addActionListener(new closeTabListener());


        add(titleLabel);
        add(stckFind);
        add(stckEntry);
        add(finds);
        add(sNameLabel);
        add(lLabel);
        add(notFound);
        add(tryAgain);
        add(editStck);
        add(newSName);
        add(newNameStck);
        add(newQuant);
        add(newQ);
        add(upStck);
        add(stckUpdate);
        add(closeBtn);

    }

    public class findSListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (stckEntry.getText().equals("TEST")) 
            {
                sNameLabel.setVisible(true);
                lLabel.setVisible(true);
                notFound.setVisible(false);
                tryAgain.setVisible(false);
            }else{
                notFound.setVisible(true);
                tryAgain.setVisible(true);
                sNameLabel.setVisible(false);
                lLabel.setVisible(false);
            }

        }
    }

    public class updateStockListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            stckUpdate.setVisible(true);
        }
    }

    public class closeTabListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }

}

