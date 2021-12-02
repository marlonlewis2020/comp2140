package GUI;

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
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new MyContentPane());
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        }  
        catch(Exception e)  
        {     
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
    }  
} 
    