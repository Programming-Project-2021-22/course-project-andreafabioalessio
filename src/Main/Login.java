package Main;

import Exeptions.InvalidUsernameError;
import Exeptions.WrongPasswordError;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Main.Startup.userArray;

public class Login extends JPanel {

    JTextField usernameTField, passwordTField;
    Button loginButton;
    JLabel errorLabel;

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

        errorLabel = new JLabel("");
        JPanel errorPanel = new JPanel();

        errorPanel.add(errorLabel);
        errorPanel.setMaximumSize(new Dimension(180, 50));
        errorPanel.setMinimumSize(new Dimension(180, 50));

        JPanel panel = new JPanel();
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

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());

        c.gridx = 1;
        c.gridy = 1;
        add(panel, c);

        c.gridx = 1;
        c.gridy = 2;
        add(Box.createRigidArea(new Dimension(0, 20)), c);

        c.gridx = 1;
        c.gridy = 3;
        add(errorPanel, c);
    }

    public void checkUserInArray(){
        boolean found = false;
        String usernameEntered = usernameTField.getText();
        String passwordEntered = passwordTField.getText();

        for (int i = 0; i < userArray.length; ){
            User userCheck = userArray[i];
            if (usernameEntered.equalsIgnoreCase(formatUsername(userCheck.getUsername()))) {//checks if given username matches one in the array
                found = true;
                if (passwordEntered.equalsIgnoreCase(formatUsername(userCheck.getPassword()))) { //checks the password
                    System.out.println("Login successful");
                    errorLabel.setText("Login successful");
                    errorLabel.setForeground(Color.green);
                    return;
                } else {
                    try {
                        throw new WrongPasswordError("Wrong password.");
                    } catch (WrongPasswordError e) {
                        e.printStackTrace();
                    }
                    errorLabel.setText("Wrong password.");
                    errorLabel.setForeground(Color.red);
                    return;
                }
            }
            else {
                System.out.println("Entered: " + usernameEntered + " " + passwordEntered + "\n" +
                        "In array: " + formatUsername(userCheck.getUsername()) + " " + formatUsername(userCheck.getPassword()));
                i++; //checks next user in the array
            }
        }
        if (!found){
            try {
                throw new InvalidUsernameError("Invalid username,\nplease sign up first");
            } catch (InvalidUsernameError e) {
                e.printStackTrace();
            }
            errorLabel.setText("Invalid username, please sign up first");
            errorLabel.setForeground(Color.red);
        }
    }

    public String formatUsername(String s){
        return s.replace("\n", "").replace(" ", "");
    }
}
