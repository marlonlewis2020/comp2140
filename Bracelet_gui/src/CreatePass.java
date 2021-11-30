import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*; 




class CreatePass extends JFrame 
{  
    //initialize button, panel, label, and text field  
    int PREF_W = 600;
    int PREF_H = PREF_W;
    JButton change_btn;  
    JPanel newPanel;  
    JLabel userLabel, passLabel, errorLabel, titleLabel;  
    final JTextField  newPass_txtField, confirmPass_txtField;  
      
    //calling constructor  
    public CreatePass()  
    {   
        setLayout(null);  
        this.setSize(800,800);
          
          
        titleLabel = new JLabel("BEADITUP.JA CREATE NEW PASSWORD");  //set label value for textField1  
        titleLabel.setBounds(120,30,500,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));   
        
        //enter your username
          
        //create label for username 
        userLabel = new JLabel("Please Enter New Password");
        userLabel.setBounds(230,90,280,40);
        userLabel.setFont(userLabel.getFont().deriveFont(15f)); 

        //create text field to get username from the user  
        newPass_txtField = new JTextField();    //set length of the text  
        newPass_txtField.setBounds(230,120,150,20);
        
        //create label for password  
        passLabel = new JLabel("Confirm New Password");  //set label value for password 
        passLabel.setBounds(230,170,280,40);
        passLabel.setFont(passLabel.getFont().deriveFont(15f)); 

        //create text field to get password from the user  
        confirmPass_txtField = new JPasswordField();    //set length for the password  
        confirmPass_txtField.setBounds(230,200,150,20);

        //create submit button  
        change_btn = new JButton("CHANGE PASSWORD"); //set label to button
        change_btn.setBounds(250,230,100,35);
        change_btn.addActionListener(new changeListener());

    

        errorLabel = new JLabel("Passwords Do Not Match Please Try Again");
        errorLabel.setBounds(230,270,250,40);
        errorLabel.setFont(errorLabel.getFont().deriveFont(15f)); 
          
        
       //create panel to put form elements  
        //newPanel = new JPanel(new GridLayout(3, 1));  
        add(titleLabel);
        add(userLabel);    //set username label to panel  
        add(newPass_txtField);   //set text field to panel  
        add(passLabel);    //set password label to panel  
        add(confirmPass_txtField);   //set text field to panel  
        add(change_btn);           //set button to panel  
        add(errorLabel).setVisible(false);
          
        //set border to panel   
        //add(newPanel, BorderLayout.CENTER);  
          
        //perform action on button click   
          //add action listener to button  
       // setTitle("BEADITUP.JA USER SYSTEM");         set title to the login form  
       setBackground(Color.BLUE);

    }  

    public class changeListener implements ActionListener
  {
    //check if passVal and new passval equal then...
    //get username
    //update user(getUsername, password, actual password)
    //press update button, then it takes you to the menu screen

    String passValue = newPass_txtField.getText();        //get user entered username from the textField1  
    String newPassValue = confirmPass_txtField.getText();
      public void  actionPerformed(ActionEvent event)
      {
        if (passValue.equals(newPassValue)){
            dispose();
            MenuScreen screen = new MenuScreen();
            screen.setVisible(true);

        }else{
            errorLabel.setVisible(true);
        }
        // find the username in the database and add the password to the user

      }
      
  }


    

}