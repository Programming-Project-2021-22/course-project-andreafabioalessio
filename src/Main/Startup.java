package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Startup extends JPanel {

    Button loginButton = new Button("Login");
    Button signupButton = new Button ("Sign up");

    public Startup(){

        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.white);

        JPanel buttons = new JPanel();
        buttons.add(Box.createRigidArea(new Dimension(0,150)));
        buttons.add(loginButton);
        buttons.add(Box.createRigidArea(new Dimension(0,20)));
        buttons.add(signupButton);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSignUpWindow();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openLoginWindow();
            }
        });

        add(buttons);
    }
    public void openLoginWindow(){

        JFrame loginWindow = new JFrame();
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setResizable(false);
        loginWindow.setTitle("Login");

        Login l = new Login();
        loginWindow.add(l);

        loginWindow.pack();
        loginWindow.setLocationRelativeTo(null);
        loginWindow.setVisible(true);
    }

    public void openSignUpWindow(){

        JFrame signupWindow = new JFrame();
        signupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signupWindow.setResizable(false);
        signupWindow.setTitle("Sign up");

        Signup s = new Signup();
        signupWindow.add(s);

        signupWindow.pack();
        signupWindow.setLocationRelativeTo(null);
        signupWindow.setVisible(true);
    }

}