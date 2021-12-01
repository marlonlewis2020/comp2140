import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.lang.Exception;  
import Authentication.Authentication;
  

public class LoginFormDemo  
{  
    //main() method start  
    public static void main(String arg[])  
    {  

        try  
        {  
            //create instance of the CreateLoginForm  
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new MyContentPane());
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
            //Authentication auth = new Authentication();
            //Bracelet.populate();
            //System.out.println(Bracelet.getBracelets().get(4).getName());
           // CreateLoginForm form = new CreateLoginForm();  
            //form.setSize(300,100);  //set size of the frame  
           // form.setVisible(true);  //make form visible to the user  
        }  
        catch(Exception e)  
        {     
            //handle exception   
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
    }  
} 