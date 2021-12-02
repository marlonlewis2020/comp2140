package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Authentication.*;


public class UpdateUser extends JFrame {
    int PREF_W = 450;
    int PREF_H = PREF_W;
    
    private JTextField userEntry;
    private JTextField newName;
    private JLabel findUser;
    private JLabel nameLabel;
    private JLabel roleLabel;
    private JLabel editUser;
    private JLabel newUser;
    private JLabel passLabel;
    private JLabel succUpdated;
    private JComboBox<Role> roles;


    
    private JLabel tryAgain;
    private JLabel notFound;
    private String username;
    private String role;
    private String password;

    public UpdateUser()
    {
        setLayout(null);
        setSize(900,900);

        JLabel titleLabel = new JLabel("UPDATE USER INFORMATION");
        titleLabel.setBounds(120,30,300,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        findUser = new JLabel("Please Enter the Username of the Person to be Updated:");
        findUser.setBounds(100,90,500,40);
        findUser.setFont(findUser.getFont().deriveFont(15f));

        userEntry = new JTextField();
        userEntry.setBounds(140,150,150,20);
    

        nameLabel = new JLabel(username);
        nameLabel.setBounds(160,180,200,35);
        nameLabel.setVisible(false);

        roleLabel = new JLabel(role);
        roleLabel.setBounds(160,220,200,35);
        roleLabel.setVisible(false);

        passLabel = new JLabel(password);
        passLabel.setBounds(160,250,200,35);
        passLabel.setVisible(false);
        
        
        editUser = new JLabel("EDIT DESIRED FIELDS");
        editUser.setBounds(160,300,300,35);

        newUser = new JLabel("USERNAME");
        newUser.setBounds(160,320,200,40);
        newUser.setFont(newUser.getFont().deriveFont(15f));

        newName = new JTextField();
        newName.setBounds(160,350,150,20);


        JLabel newRole = new JLabel("Role");
        newRole.setVisible(true);
        newRole.setBounds(160,380,200,40);
        newRole.setFont(newRole.getFont().deriveFont(15f));

        roles = new JComboBox(Role.values());
        roles.setVisible(true);
        roles.setBounds (160,430,200,40);


        JButton updateBtn = new JButton("UPDATE");
        updateBtn.setBounds (160,480,200,40);
        updateBtn.addActionListener(new updateUserListener());


        notFound = new JLabel("User Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);


        succUpdated = new JLabel ("USER Successfully Updated!");
        succUpdated.setBounds(110,520,250,50);
        succUpdated.setFont(succUpdated.getFont().deriveFont(15f));
        succUpdated.setVisible(false);

        JButton close = new JButton("RETURN TO MAIN ADMIN MENU");
        close.setBounds(110,550,250,50);
        close.setVisible(true);
        close.addActionListener(new closeListener());


        



        add(titleLabel);
        add(findUser);
        add(userEntry);
        add(nameLabel);
        add(roleLabel);
        add(passLabel);
        add(editUser);
        add(newUser);
        add(newName);
        add(newRole);
        add(roles);
        add(succUpdated);
        add(close);
        add(updateBtn);
        add(notFound);
        add(tryAgain);
        

    }
   

    public class updateUserListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            String newUse = newUser.getText();
            String roleVal = String.valueOf(roles.getSelectedItem());

            if ((newUse == "") && (roleVal == "")){
                succUpdated.setVisible(true);

            }else if ((newUse == "") && ((!roleVal.equals("")))){
                User.updateUser(userEntry.getText(),"role", roleVal);
                succUpdated.setVisible(true);
            }else if ((!(newUse == "") )&& ((!roleVal.equals("")))){
                
                String newUserN = newName.getText();
                User.updateUser(userEntry.getText(),"role", roleVal);
                User.updateUser(userEntry.getText(),"username", newUserN);
                succUpdated.setVisible(true);

            }{
                String newUserN_2 = newName.getText();
                User.updateUser(userEntry.getText(),"username", newUserN_2);
            }
        }
    }


    public class closeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
            AdminMenu newBc = new AdminMenu();
            newBc.setVisible(true);
            dispose();
        }
    }


}


