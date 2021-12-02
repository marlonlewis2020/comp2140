package GUI;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Inventory.*;


public class FindStock extends JFrame {
    


    private JLabel titleLabel;
    private JLabel stckFind;
    private JTextField stckEntry;

    private JButton finds;

    private String sName ;
    private String sLevel ;

    private JLabel sNameLabel;
    private JLabel lLabel;

    
    private JLabel notFound;
    private JLabel tryAgain;

    private JButton closeBtn;


    public FindStock()
    {
        setLayout(null);
        setSize(800,600);

        titleLabel = new JLabel("FIND ITEM INFORMATION");
        titleLabel.setBounds(120,30,220,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        stckFind = new JLabel("Please enter name of the stock ITEM");
        stckFind.setBounds(140,90,300,40);
        stckFind.setFont(stckFind.getFont().deriveFont(15f));

        stckEntry = new JTextField();
        stckEntry.setBounds(140,120,150,20);
        
        finds = new JButton("FIND");
        finds.setBounds(160,155,100,35);
        finds.addActionListener(new findSListener());
        sNameLabel = new JLabel(sName);
        sNameLabel.setBounds(160,180,200,35);
        sNameLabel.setVisible(false);


        lLabel = new JLabel(sLevel);
        lLabel.setBounds(160,200,50,35);
        lLabel.setVisible(false);
        


        notFound = new JLabel("Stock Item Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);


        closeBtn = new JButton("CLOSE RETURN TO STOCK MENU");
        closeBtn.setBounds(110,500,300,35);
        closeBtn.addActionListener(new closeTabListener());


        add(titleLabel);
        add(stckFind);
        add(stckEntry);
        add(finds);
        add(sNameLabel);
        add(lLabel);
        add(notFound);
        add(tryAgain);
        add(closeBtn);

    }

    public class findSListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (Stock.viewItem(stckEntry.getText()) == null)
            {
                sNameLabel.setVisible(false);
                lLabel.setVisible(false);
                notFound.setVisible(true);
                tryAgain.setVisible(true);
            }else{
                String stkName = Stock.viewItem(stckEntry.getText()).getStockName();
                sNameLabel.setText(stkName);
                sNameLabel.setVisible(true);
                int qLevel = Stock.getQuantity(stkName);
                String qtyLevel = String.valueOf(qLevel);
                lLabel.setText(qtyLevel);
                lLabel.setVisible(true);
                notFound.setVisible(false);
                tryAgain.setVisible(false);
                
            }

        }
    }

    public class closeTabListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            StockMenu newBc = new StockMenu();
            newBc.setVisible(true);
            dispose();
        }
    }

}
