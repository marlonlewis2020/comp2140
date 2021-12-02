package GUI;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*; 
import Authentication.*;




class CreatePass extends JFrame 
{  
    int PREF_W = 600;
    int PREF_H = PREF_W;
    JButton change_btn;  
    JPanel newPanel;  
    JLabel userLabel, passLabel, errorLabel, titleLabel,useLabel;  
    final JTextField  newPass_txtField, confirmPass_txtField,use_txtField;  
      
    //calling constructor  
    public CreatePass()  
    {   
      setTitle("Crete Password");
        setLayout(null);  
        this.setSize(800,800);
          
          
        titleLabel = new JLabel("BEADITUP.JA CREATE NEW PASSWORD");  //set label value for textField1  
        titleLabel.setBounds(120,30,500,40);
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f)); 
        
        useLabel = new JLabel("PLEASE ENTER YOUR USERNAME");  //set label value for textField1  
        useLabel.setBounds(120,80,500,40);
        useLabel.setFont(useLabel.getFont().deriveFont(15f)); 

        use_txtField = new JTextField();    //set length of the text  
        use_txtField.setBounds(230,120,150,20);
        
        
        
          
        //create label for username 
        userLabel = new JLabel("Please Enter New Password");
        userLabel.setBounds(230,140,280,40);
        userLabel.setFont(userLabel.getFont().deriveFont(15f)); 

        //create text field to get username from the user  
        newPass_txtField = new JTextField();    //set length of the text  
        newPass_txtField.setBounds(230,160,150,20);
        
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
          
        
  
        add(titleLabel);
        add(userLabel);    
        add(newPass_txtField);    
        add(passLabel);     
        add(confirmPass_txtField);   
        add(change_btn);            
        add(errorLabel).setVisible(false);
          
       setBackground(Color.BLUE);

    }  

    public class changeListener implements ActionListener
  {
   

    String passValue = newPass_txtField.getText();        
    String newPassValue = confirmPass_txtField.getText();
      public void  actionPerformed(ActionEvent event)
      {
        if (passValue.equals(newPassValue)){
            User.updateUser(use_txtField.getText(),"password",newPassValue);
            dispose();
            MenuScreen screen = new MenuScreen();
            screen.setVisible(true);

            

        }else{
            errorLabel.setVisible(true);
        }

      }
      
  }


    

}