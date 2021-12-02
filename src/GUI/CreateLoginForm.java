package GUI;

import javax.swing.*;

import java.awt.*;  
import java.awt.event.*;   
import Inventory.*;
import Authentication.*;
  
//create CreateLoginForm class to create login form  
//class extends JFrame to create a window where our component add  
//class implements ActionListener to perform an action on button click 

class MyContentPane extends JPanel {
    int PREF_W = 1200;
    int PREF_H = 1000;
  
    public MyContentPane() {
      setLayout(new GridBagLayout());
      add(new CreateLoginForm());
    }
  
    @Override
    public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
    }
  }
class CreateLoginForm extends JPanel implements ActionListener 
{  
    //initialize button, panel, label, and text field  
    int PREF_W = 600;
    int PREF_H = PREF_W;
    JButton login_btn;  
    JPanel newPanel;  
    JLabel userLabel, passLabel, errorLabel, titleLabel,roleLabel;  
    final JTextField  user_txtField, pass_txtField,role_txtField;  
    Authentication auth;
      
    public CreateLoginForm()  
    {   
        setLayout(null);  
        auth = new Authentication();
        
          
        titleLabel = new JLabel("BEADITUP.JA STOCK AND ORDER SYSTEM");   
        titleLabel.setBounds(120,30,500,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f));             
          
        //create label for username 
        userLabel = new JLabel("Please Enter Username");
        userLabel.setBounds(230,90,200,40);
        userLabel.setFont(userLabel.getFont().deriveFont(15f)); 

        //create text field to get username from the user  
        user_txtField = new JTextField();    //set length of the text  
        user_txtField.setBounds(230,120,150,20);
        
        //create label for password  
        passLabel = new JLabel("Please Enter Password");  //set label value for password 
        passLabel.setBounds(230,170,200,40);
        passLabel.setFont(passLabel.getFont().deriveFont(15f)); 

        //create text field to get password from the user  
        pass_txtField = new JPasswordField();    //set length for the password  
        pass_txtField.setBounds(230,200,150,20);

         
        roleLabel = new JLabel("Please Enter Role");  //set label value for password 
        roleLabel.setBounds(230,270,200,40);
        roleLabel.setFont(roleLabel.getFont().deriveFont(15f)); 

        //create text field to get password from the user  
        role_txtField = new JTextField();    //set length for the password  
        role_txtField.setBounds(230,300,150,20);

        //create submit button 
        login_btn = new JButton("LOGIN"); //set label to button
        login_btn.setBounds(250,330,100,35);

        errorLabel = new JLabel("Invalid Credentials");
        errorLabel.setBounds(230,350,200,40);
        errorLabel.setFont(errorLabel.getFont().deriveFont(15f)); 
          
        
       //create panel to put form elements  
        //newPanel = new JPanel(new GridLayout(3, 1));  
        add(titleLabel);
        add(userLabel);    //set username label to panel  
        add(user_txtField);   //set text field to panel  
        add(passLabel);    //set password label to panel  
        add(pass_txtField);   //set text field to panel  
        //add(roleLabel);
        //add(role_txtField);
        add(login_btn);           //set button to panel  
        add(errorLabel).setVisible(false);
          
          
        //perform action on button click   
        login_btn.addActionListener(this);     //add action listener to button  
       
    }  
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
  }  

  public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter  
    {  
      
        String userValue = user_txtField.getText();        //get user entered username from the textField1  
        String passValue = pass_txtField.getText(); //get user entered pasword from the textField2  
      

    if (auth.authenticate(userValue,passValue).equals ("ADMIN") && passValue !=("temp")){
      Bracelet.populate();
      MenuScreen newMenu = new MenuScreen(); //make page visible to the user  
      newMenu.setVisible(true);  
      errorLabel.setVisible(false);
    }else if (auth.authenticate(userValue,passValue).equals("BUSINESS_OWNER") && passValue.equals("temp")){
        CreatePass newPass = new CreatePass();
        newPass.setVisible(true);
    }else{
      errorLabel.setVisible(true);
      
    }
  }  
} 

