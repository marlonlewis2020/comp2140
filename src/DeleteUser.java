import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DeleteUser extends JFrame {
    int PREF_W = 450;
    int PREF_H = PREF_W;
    private JButton findPerson;
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

        JLabel titleLabel = new JLabel("DELETE USER");
        titleLabel.setBounds(120,30,220,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));

        findUser = new JLabel("Please ID OF USER TO BE TERMINATED:");
        findUser.setBounds(140,90,200,40);
        findUser.setFont(findUser.getFont().deriveFont(15f));

        userEntry = new JTextField();
        userEntry.setBounds(140,120,150,20);
        
        findPerson = new JButton("FIND");
        findPerson.setBounds(160,155,100,35);
        findPerson.addActionListener(new FindPersonListener());

        this.username  = "username" ;
        this.role = "role";
        this.password = "password";

        nameLabel = new JLabel(username);
        nameLabel.setBounds(160,180,200,35);
        nameLabel.setVisible(false);

        roleLabel = new JLabel(role);
        roleLabel.setBounds(160,220,200,35);
        roleLabel.setVisible(false);

        passLabel = new JLabel(password);
        passLabel.setBounds(160,250,200,35);
        passLabel.setVisible(false);
        

        deleteBtn = new JButton("DELETE");
        deleteBtn.setBounds (160,480,200,40);
        deleteBtn.addActionListener(new deleteUserListener());
        deleteBtn.setVisible(false);


        notFound = new JLabel("User Not Found!");
        notFound.setBounds(140,195,200,50);
        notFound.setFont(notFound.getFont().deriveFont(15f));
        notFound.setVisible(false);

        tryAgain = new JLabel("Try Again!");
        tryAgain.setBounds(180,230,100,35);
        tryAgain.setFont(tryAgain.getFont().deriveFont(15f));
        tryAgain.setVisible(false);


        succDeleted = new JLabel ("USER Successfully DELETED!");
        succDeleted.setBounds(110,520,250,50);
        succDeleted.setFont(succDeleted.getFont().deriveFont(15f));
        succDeleted.setVisible(false);

        closeBtn = new JButton("CLOSE");
        closeBtn.setBounds(110,540,100,35);
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
        add(findPerson);
        add(nameLabel);
        add(roleLabel);
        add(passLabel);
        add(deleteBtn);
        add(notFound);
        add(tryAgain);
        add(succDeleted);
        add(closeBtn);
        /*add(found);
        add(notFound);
        add(tryAgain);
        add(delPerson);
        add(succDeleted);
        add(cancel);//cancel button added to content pane
        delPerson.setVisible(false);
        succDeleted.setVisible(false);
        found.setVisible(false);
        notFound.setVisible(false);
        tryAgain.setVisible(false);*/

        setBackground(Color.MAGENTA);

    }

    public class FindPersonListener implements ActionListener//there is no find user function   
    {
        public void actionPerformed(ActionEvent e)
        {
            if (userEntry.getText().equals("TEST")) // use callays find function if it returns a value then set labels to values and show delete btn
            {//also if not found then it should show the delet label taken form callays class
                nameLabel.setVisible(true);
                roleLabel.setVisible(true);
                passLabel.setVisible(true);
                deleteBtn.setVisible(true);
            }else{
                notFound.setVisible(true);
                tryAgain.setVisible(true);
                nameLabel.setVisible(false);
                roleLabel.setVisible(false);
                passLabel.setVisible(false);
                deleteBtn.setVisible(false);
                //succDeleted.setVisible(false);
                
            }

        }
    }

    public class deleteUserListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e){
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


