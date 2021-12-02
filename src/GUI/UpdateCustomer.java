package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Order.Customer;


public class UpdateCustomer extends JFrame {
    int PREF_W = 450;
    int PREF_H = PREF_W;
    private JButton cerFind;
    private JButton updateBtn;
    private JButton returnBtn;
    private JTextField cNEntry;
    private JTextField cPEntry;
    private JLabel cNameFind;
    private JLabel cPhoneFind;

    private JLabel nameCustLabel;
    private JLabel tPhoneLabel;
    private JLabel picLabel;
    
    private JLabel editCust;
    private JLabel edCName;
    private JTextField newCName;
   

    private JLabel edPhone;
    private JTextField newPhone;

    private JLabel edPic;
    private JTextField newPic;


   
    private JLabel succUpdated;

    private JLabel notFound;
    private JLabel tryAgain;
    private String cerName="";
    private String cerPhone = "";
    private String cerPLtn = "";
    private String editedName;
    private String editedPhone;
    private String editedPic;

    public UpdateCustomer()
    {
        setLayout(null);
        setSize(800,800);
        setTitle("Update Customer");

        JLabel titleLabel = new JLabel("UPDATE CUSTOMER INFORMATION");
        titleLabel.setBounds(120,30,500,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        cNameFind = new JLabel("Please Enter Customer's Name:");
        cNameFind.setBounds(140,90,350,40);
        cNameFind.setFont(cNameFind.getFont().deriveFont(15f));

        cNEntry = new JTextField();
        cNEntry.setBounds(140,120,150,20);

        cPhoneFind = new JLabel("Please Enter Customer's Phone Number:");
        cPhoneFind.setBounds(140,150,300,40);
        cPhoneFind.setFont(cPhoneFind.getFont().deriveFont(15f));

        cPEntry = new JTextField();
        cPEntry.setBounds(140,180,150,20);
        
        cerFind = new JButton("FIND");
        cerFind.setBounds(160,210,100,35);
        cerFind.addActionListener(new cFindListener());

        cerName  = "Customer Name" ;
        cerPhone = "Phone Number";
        cerPLtn = "Pickup Location";

        nameCustLabel = new JLabel(cerName);
        nameCustLabel.setBounds(160,240,200,35);
        nameCustLabel.setVisible(false);

        tPhoneLabel = new JLabel(cerPhone);
        tPhoneLabel.setBounds(160,260,200,35);
        tPhoneLabel.setVisible(false);

        picLabel = new JLabel(cerPLtn);
        picLabel.setBounds(160,280,200,35);
        picLabel.setVisible(false);
        
        
        editCust = new JLabel("EDIT DESIRED FIELDS");
        editCust.setBounds(160,300,300,35);

        edCName = new JLabel("Customer Name");
        edCName.setBounds(160,320,200,40);
        edCName.setFont(edCName.getFont().deriveFont(15f));

        newCName= new JTextField();
        newCName.setBounds(140,350,150,20);


        edPhone = new JLabel("Phone Number");
        edPhone.setVisible(true);
        edPhone.setBounds(140,380,200,40);

        newPhone= new JTextField(10);
        newPhone.setBounds(140,410,150,20);

        
        edPic = new JLabel("Pickup Location");
        edPic.setVisible(true);
        edPic.setBounds (160,440,200,40);

        newPic = new JTextField();
        newPic.setBounds(140,480,150,20);
        
        updateBtn = new JButton("UPDATE");
        updateBtn.setBounds (160,520,200,40);
        updateBtn.addActionListener(new updateCustListener());
        updateBtn.setVisible(false);


        notFound = new JLabel("Customer Not Found!");
        notFound.setBounds(140,250,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,270,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);


        succUpdated = new JLabel ("Customer Successfully Updated!");
        succUpdated.setBounds(110,550,250,50);
        succUpdated.setFont(succUpdated.getFont().deriveFont(15f));
        succUpdated.setVisible(false);

        returnBtn = new JButton("RETURN TO MAIN BRACELET MENU");
        returnBtn.setBounds (160,580,300,40);
        returnBtn.addActionListener(new returnListener());



        add(titleLabel);
        add(cNameFind);
        add(cNEntry);
        add(cPhoneFind);
        add(cPEntry);
        add(cerFind);
        add(nameCustLabel);
        add(tPhoneLabel);
        add(picLabel);
        add(editCust);
        add(edCName);
        add(newCName);
        add(edPhone);
        add(newPhone);
        add(returnBtn);
        add(edPic);
        add(newPic);        
        add(updateBtn);
        add(notFound);
        add(tryAgain);
        add(succUpdated);

    }

    public class cFindListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (Customer.search(cNEntry.getText(),cPEntry.getText()) != null) {
                System.out.println("bracelt");
                
                cerName= cNEntry.getText();
                cerPhone = Customer.search(cNEntry.getText(),cPEntry.getText()).getphoneNumber();
                cerPLtn  = Customer.search(cNEntry.getText(),cPEntry.getText()).getpickupLocation();
               
                nameCustLabel.setText(cerName);
                nameCustLabel.setVisible(true);

                tPhoneLabel.setText(cerPhone);
                tPhoneLabel.setVisible(true);

                picLabel.setText(cerPLtn);
                picLabel.setVisible(true);

                notFound.setVisible(false);
                tryAgain.setVisible(false);
                updateBtn.setVisible(true);

            }else{
                notFound.setVisible(true);
                tryAgain.setVisible(true);
                nameCustLabel.setVisible(false);
                tPhoneLabel.setVisible(false);
                picLabel.setVisible(false);
                updateBtn.setVisible(false);
            }


        }
    }

    
    

   public class updateCustListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Customer cTmer = Customer.search(cNEntry.getText(),cPEntry.getText());

            if (cTmer != null)
            {
                if (newCName.getText().equals("")){
                    editedName = cTmer.getcustomerName();
               }else{
                   editedName = newCName.getText();
               }

               if (newPhone.getText().equals("")){
                   editedPhone = cTmer.getphoneNumber();
               }else{
                    editedPhone = (newPhone.getText());
               }
                
                if (newPic.getText().equals("")){
                        editedPic = cTmer.getpickupLocation();
                    }else{
                        editedPic = (newPic.getText());
                }
                cTmer.updateCustomer(cNEntry.getText(),cPEntry.getText(),editedName,editedPhone,editedPic);
                succUpdated.setVisible(true);

                }else{
                dispose();
                
            }

        }
    }


    public class returnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)

        {
            CustomerMenu newBc = new CustomerMenu();
            newBc.setVisible(true);
            dispose();

        }
    }

    
}

