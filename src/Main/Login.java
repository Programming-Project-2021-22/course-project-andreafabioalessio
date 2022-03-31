package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Main.Startup.userArray;

public class Login extends JPanel {

    JTextField usernameTField, passwordTField;
    Button loginButton;

    public Login() {

        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel("Insert username: ");
        usernameTField = new JTextField(15);
        JPanel usernamePanel = new JPanel();
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTField);
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));

        JLabel passwordLabel = new JLabel("Password: ");
        passwordTField = new JTextField(15);
        JPanel passwordPanel = new JPanel();
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTField);
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));

        loginButton = new Button ("Login");

        JPanel panel = new JPanel();
        panel.add(Box.createRigidArea(new Dimension(0,120)));
        panel.add(usernamePanel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(passwordPanel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(loginButton);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               checkUserInArray();
            }
        });
        add(panel);
    }

    public void checkUserInArray(){
        boolean found = false;
        String usernameEntered = usernameTField.getText();
        String passwordEntered = passwordTField.getText();

        for (int i = 0; i < userArray.length; ){
            User userCheck = userArray[i];
            if (usernameEntered.equalsIgnoreCase(userCheck.getUsername())) {//checks if given username matches one in the array
                found = true;
                if (passwordEntered.equalsIgnoreCase(userCheck.getPassword())) { //checks the password
                    System.out.println("Login successful");
                    return;
                } else {
                    System.out.println("Wrong password");
                    return;
                }
            }
            else {
                i++; //checks next user in the array
            }
        }
        if (!found){
            System.out.println("User not found, please sign up first");
        }
    }
}
