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

    private JLabel sNLabel;
    private JLabel lvlLabel;

    
    private JLabel notFound;
    private JLabel tryAgain;
    private JLabel editStck;
    private JLabel newSName;
    private JTextField newNameStck;
    private JLabel newQuant;
    private JLabel stckUpdate;
    private JLabel stckNUpdate;
    private JTextField newQ;
    private JRadioButton addTo;
    private JRadioButton takeFrom;


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

        notFound = new JLabel("Stock Item Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);

        sNLabel = new JLabel(sName);
        sNLabel.setBounds(160,180,200,35);
        sNLabel.setVisible(false);


        lvlLabel = new JLabel(sLevel);
        lvlLabel.setBounds(160,250,200,35);
        lvlLabel.setVisible(false);

        editStck = new JLabel("SELECT ADD OR TAKE FROM STOCK");
        editStck.setBounds(160,300,300,35);
        editStck.setFont(editStck.getFont().deriveFont(15f));

        addTo = new JRadioButton("ADD TO QUANTITY");
        addTo.setBounds(160,330,300,40);

        takeFrom = new JRadioButton("TAKE FROM QUANTITY");
        takeFrom.setBounds(160,360,300,35);


        newSName = new JLabel("ENSURE ONLY ONE IS SELECTED");
        newSName.setBounds(160,390,200,40);
        newSName.setFont(newSName.getFont().deriveFont(15f));

        newQuant = new JLabel("ENTER THE QUANTITY BY WHICH IT IS TO BE UPDATED BY");
        newQuant.setBounds(160,420,200,40);
        newQuant.setFont(newQuant.getFont().deriveFont(15f));

        newQ = new JTextField(10);
        newQ.setBounds(140,440,150,20);
        
        upStck = new JButton("UPDATE");
        upStck.setBounds (160,480,200,40);
        upStck.addActionListener(new updateStockListener());
        upStck.setVisible(false);

        stckUpdate = new JLabel ("Stock Item Successfully UPDATED!");
        stckUpdate.setBounds(110,520,250,50);
        stckUpdate.setFont(stckUpdate.getFont().deriveFont(15f));
        stckUpdate.setVisible(false);

        stckNUpdate = new JLabel ("Stock Item Not UPDATED!");
        stckNUpdate.setBounds(110,520,250,50);
        stckNUpdate.setFont(stckUpdate.getFont().deriveFont(15f));
        stckNUpdate.setVisible(false);



        closeBtn = new JButton("CLOSE RETURN TO STOCK MENU");
        closeBtn.setBounds(110,550,300,35);
        closeBtn.addActionListener(new closeTabListener());


        add(titleLabel);
        add(stckFind);
        add(stckEntry);
        add(finds);
        add(notFound);
        add(tryAgain);
        add(sNLabel);
        add(lvlLabel);
        add(editStck);
        add(addTo);
        add(takeFrom);
        add(newSName);
        add(newQuant);
        add(newQ);
        add(upStck);
        add(stckUpdate);
        add(stckNUpdate);
        add(closeBtn);

    }

    public class findSListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
        if (Stock.viewItem(stckEntry.getText()) == null)
            //if (stckEntry.getText().equals("TEST")) 
            {
                sNLabel.setVisible(false);
                lvlLabel.setVisible(false);
                notFound.setVisible(true);
                tryAgain.setVisible(true);
            }else{
                String stk = Stock.viewItem(stckEntry.getText()).getStockName();
                sNLabel.setText(stk);
                sNLabel.setVisible(true);
                int qLvl = Stock.getQuantity(stk);
                String qtyLvl = String.valueOf(qLvl);
                lvlLabel.setText(qtyLvl);
                lvlLabel.setVisible(true);
                notFound.setVisible(false);
                tryAgain.setVisible(false);
                upStck.setVisible(true);
                
            }
        }

    }

    public class updateStockListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            if (addTo.isSelected()){
                String ops = "+";
                char operation =  ops.charAt(0);
                int newQty = Integer.parseInt(newQ.getText());
                Stock.updateStock(operation,newQty,stckEntry.getText());
                stckUpdate.setVisible(true);
                stckNUpdate.setVisible(false);


            }else if (takeFrom.isSelected()){
                String opts = "-";
                char upOp =  opts.charAt(0);
                int newQy = Integer.parseInt(newQ.getText());
                Stock.updateStock(upOp,newQy,stckEntry.getText());
                stckUpdate.setVisible(true);
                stckNUpdate.setVisible(false);
            }else{
                stckNUpdate.setVisible(false);
            }
        }
    }

    public class closeTabListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }

}

