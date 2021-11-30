import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUser extends JFrame
{
    private JTextField  userName;       //name
    private JTextField  userRole;        //age
    private JButton     cmdSave;
    private JButton     cmdClose;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
  
    public AddUser()
    {
        //this.plisting = plisting;
        //entry = this;
        setTitle("ADD NEW USER TO SYSTEM");
        pnlCommand = new JPanel(); //NEW PANEL FOR ENERTING INFOR INTO FIELDS
        pnlDisplay = new JPanel(); //NEW PANEL FOR Saving and closing info


        pnlDisplay.add(new JLabel("UserName:")); 
        userName = new JTextField(20);
        pnlDisplay.add(userName);


        pnlDisplay.add(new JLabel("ROLE"));
        userRole = new JTextField(20);
        pnlDisplay.add(userRole);

        pnlDisplay.setLayout(new GridLayout(3,4));

       
        cmdSave   = new JButton("Save");
        cmdClose   = new JButton("Close");
        cmdClose.addActionListener(new VisibleListener());
        cmdSave.addActionListener(new SaveListener());
        //cmdSave.addActionListener(new AddToListListener());
        

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        //add(pnlAdd);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }



    private class VisibleListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            dispose();
        }
    }
    
   private class SaveListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
          String name = userName.getText();
          String role = userRole.getText();
          String password = ("temp");
          //User newUser = User(name,password,role);
          //newUser.addUser();
          //dispose();
        }
    }
}