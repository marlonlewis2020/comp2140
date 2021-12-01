import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UpdateUser extends JFrame {
    int PREF_W = 450;
    int PREF_H = PREF_W;
    private JButton findPerson;
    
    private JTextField userEntry;
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

        JLabel titleLabel = new JLabel("UPDATE USER INFORMATION");
        titleLabel.setBounds(120,30,220,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        findUser = new JLabel("Please Enter the Username of the Person to be Updated:");
        findUser.setBounds(140,90,200,40);
        findUser.setFont(findUser.getFont().deriveFont(15f));

        userEntry = new JTextField();
        userEntry.setBounds(140,120,150,20);
    

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

        JTextField newName = new JTextField();
        newName.setBounds(140,350,200,40);


        JLabel newRole = new JLabel("Role");
        newRole.setVisible(true);
        newRole.setBounds(140,380,200,40);

        roles = new JComboBox(Role.values());
        roles.setVisible(true);
        roles.setBounds (160,430,200,40);

        //String[] empRoles = { "CHOICE 1","CHOICE 2", "CHOICE 3","CHOICE 4","CHOICE 5","CHOICE 6"};
        //final JComboBox<String> roles = new JComboBox<String>(empRoles);
        //roles.setVisible(true);
        //roles.setBounds (160,430,200,40);

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
            }else if ((!(newUse == "") )&& ((!roleVal.equals(""))))

                User.updateUser(userEntry.getText(),"role", roleVal);
                User.updateUser(userEntry.getText(),"username", newUse);
                succUpdated.setVisible(true);

            }{
                String newUse = newUser.getText();
                User.updateUser(userEntry.getText(),"username", newUse);
            }
        }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}


