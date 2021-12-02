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
    private JButton closeBtn;


    private JTextField userEntry;
    
    private JLabel findUser;
    
    private JLabel succDeleted;


    private JLabel notFound;
    private JLabel tryAgain;

    public DeleteUser()
    {
        setLayout(null);
        setSize(500,500);

        JLabel titleLabel = new JLabel("DELETE USER");
        titleLabel.setBounds(120,30,220,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        findUser = new JLabel("Please enter username to be terminated:");
        findUser.setBounds(100,90,300,40);
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

        


        add(titleLabel);
        add(findUser);
        add(userEntry);
        add(delUser);
        add(notFound);
        add(tryAgain);
        add(succDeleted);
        add(closeBtn);


       

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
            AdminMenu newBc = new AdminMenu();
            newBc.setVisible(true);
            dispose();
        }
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}


