package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Authentication.*;

public class AddUser extends JFrame
{
    private JTextField  userName;        
    private JButton     cmdSave;
    private JButton     cmdClose;
    private JComboBox<String> roles;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
  
    public AddUser()
    {
       
        setTitle("ADD NEW USER TO SYSTEM");
        pnlCommand = new JPanel(); //NEW PANEL FOR ENERTING INFOR INTO FIELDS
        pnlDisplay = new JPanel(); //NEW PANEL FOR Saving and closing info


        pnlDisplay.add(new JLabel("UserName:")); 
        userName = new JTextField(20);
        pnlDisplay.add(userName);


        pnlDisplay.add(new JLabel("ROLE"));
        
        roles = new JComboBox(Role.values());
        pnlDisplay.add(roles);


        pnlDisplay.setLayout(new GridLayout(3,4));

       
        cmdSave   = new JButton("Save");
        cmdClose   = new JButton("Close");
        cmdClose.addActionListener(new VisibleListener());
        cmdSave.addActionListener(new SaveListener());
        

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }



    private class VisibleListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            AdminMenu newAm = new AdminMenu();
            newAm.setVisible(true);
            dispose();
        }
    }
    
   private class SaveListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
          String name = userName.getText();
          String password = ("temp");
          String role = String.valueOf(roles.getSelectedItem());

          User newUser = new User(name,password,Role.valueOf(role));
          newUser.addUser();
        }
    }
}