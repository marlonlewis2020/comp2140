package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Authentication.*;


public class DeleteUser extends JFrame {
    int PREF_W = 450;
    int PREF_H = PREF_W;
    private JButton delUser;
    private JButton deleteBtn;
    private JButton closeBtn;


    private JTextField userEntry;
    
    private JLabel findUser;
    private JLabel nameLabel;
    private JLabel roleLabel;
    private JLabel passLabel;
    private JLabel succDeleted;


    private JLabel notFound;
    private JLabel tryAgain;
    private String username;
    private String role;
    private String password;

    public DeleteUser()
    {
        setLayout(null);
        setSize(400,400);

        JLabel titleLabel = new JLabel("DELETE USER");
        titleLabel.setBounds(120,30,220,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        findUser = new JLabel("Please enter userName TO BE TERMINATED:");
        findUser.setBounds(140,90,200,40);
        findUser.setFont(findUser.getFont().deriveFont(15f));

        userEntry = new JTextField();
        userEntry.setBounds(140,120,150,20);
        
        delUser = new JButton("DELETE");
        delUser.setBounds(160,155,100,35);
        delUser.addActionListener(new deleteUserListener());


        notFound = new JLabel("User Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);


        succDeleted = new JLabel ("USER Successfully DELETED!");
        succDeleted.setBounds(110,180,250,50);
        succDeleted.setFont(succDeleted.getFont().deriveFont(15f));
        succDeleted.setVisible(false);

        closeBtn = new JButton("CLOSE");
        closeBtn.setBounds(110,240,100,35);
        closeBtn.addActionListener(new closeTabListener());

        

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
        add(findUser);
        add(userEntry);
        add(delUser);
        add(notFound);
        add(tryAgain);
        add(succDeleted);
        add(closeBtn);


        setBackground(Color.MAGENTA);

    }

    public class deleteUserListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            User.deleteUser(userEntry.getText());
            succDeleted.setVisible(true);
            closeBtn.setVisible(true);
        }
    }

    public class closeTabListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}


