package Main;

import Exeptions.InvalidUsernameError;
import Exeptions.WrongPasswordError;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static Main.Startup.userArray;

public class Login extends JPanel {

    private Image image;
    private final JTextField usernameTField;
    private final JPasswordField passwordTField;
    private final JLabel errorLabel;

    public Login(JFrame window) {
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel("Insert username:");
        usernameTField = new JTextField(15);
        JPanel usernamePanel = new JPanel();
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTField);
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));
        usernamePanel.setBackground(new Color(0, 0, 0, 0));

        JLabel passwordLabel = new JLabel("Password:");
        passwordTField = new JPasswordField(15);

        JLabel showPasswordLabel = new JLabel("Show Password");
        showPasswordLabel.setForeground(new Color(255, 255, 255));
        JCheckBox showPassword = new JCheckBox();
        showPassword.setBackground(new Color(0, 0, 0, 0));

        showPasswordLabel.setFont(new Font("Dialog", Font.BOLD, 8));

        showPassword.addActionListener(e-> {passwordTField.setEchoChar(
                showPassword.isSelected() ? '\u0000' : '\u2022');});

        JPanel showPasswordPanel = new JPanel();
        showPasswordPanel.add(showPasswordLabel);
        showPasswordPanel.add(showPassword);
        showPasswordPanel.setBackground(new Color(0, 0, 0, 0));

        JPanel passwordPanel = new JPanel();
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTField);
        passwordPanel.add(showPasswordPanel);
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setBackground(new Color(0, 0, 0, 0));

        ImageIcon loginIcon = new ImageIcon("src/Images/login-button-resized.png", "login button icon");
        JButton loginButton = new JButton(loginIcon);
        loginButton.setMinimumSize(new Dimension(75, 24));
        loginButton.setMaximumSize(new Dimension(75, 24));
        loginButton.setPreferredSize(new Dimension(75, 24));
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.addActionListener(e -> checkUserInArray());

        ImageIcon backIcon = new ImageIcon("src/Images/back-button-resized.png", "back button icon");
        JButton backButton = new JButton(backIcon);
        backButton.setMinimumSize(new Dimension(75, 24));
        backButton.setMaximumSize(new Dimension(75, 24));
        backButton.setPreferredSize(new Dimension(75, 24));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.addActionListener(e -> goBackToMenu(window));

        errorLabel = new JLabel();
        JPanel errorPanel = new JPanel();
        errorPanel.add(errorLabel);
        errorPanel.setMaximumSize(new Dimension(180, 50));
        errorPanel.setMinimumSize(new Dimension(180, 50));
        errorPanel.setBackground(new Color(0, 0, 0, 0));

        GridBagConstraints panelConstraints = new GridBagConstraints();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panelConstraints.gridx = 1;
        panelConstraints.gridy = 1;
        panel.add(usernamePanel, panelConstraints);
        panelConstraints.gridy = 2;
        panel.add(Box.createRigidArea(new Dimension(0, 20)), panelConstraints);
        panelConstraints.gridy = 3;
        panel.add(passwordPanel, panelConstraints);
        panelConstraints.gridy = 4;
        panel.add(loginButton, panelConstraints);
        panelConstraints.gridy = 5;
        panel.add(Box.createRigidArea(new Dimension(0,20)), panelConstraints);
        panelConstraints.gridy = 6;
        panel.add(backButton, panelConstraints);
        panel.setBackground(new Color(0, 0, 0, 0));

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

    public void goBackToMenu(JFrame window) {
        Startup s = new Startup(window);
        window.getContentPane().removeAll();
        window.setTitle("Menù");
        window.setContentPane(s);
        window.revalidate();
        window.repaint();
    }

    public void checkUserInArray(){
        boolean found = false;
        String usernameEntered = usernameTField.getText();
        String passwordEntered = String.valueOf(passwordTField.getPassword());

        for (int i = 0; i < userArray.length; ){
            User userCheck = userArray[i];
            if (usernameEntered.equalsIgnoreCase(formatUsername(userCheck.getUsername()))) {//checks if given username matches one in the array
                found = true;
                if (passwordEntered.equalsIgnoreCase(formatUsername(userCheck.getPassword()))) { //checks the password
                    errorLabel.setText("Login successful");
                    errorLabel.setForeground(new Color(0, 220, 30));
                    //+Load of level and actual login
                    return;
                } else {
                    try {
                        throw new WrongPasswordError("Password does not match username");//username matches, password doesn't
                    } catch (WrongPasswordError e) {
                        e.printStackTrace();
                    }
                    errorLabel.setText("Wrong password");
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
                throw new InvalidUsernameError("Username not found in User List");//username not found case
            } catch (InvalidUsernameError e) {
                e.printStackTrace();
            }
            errorLabel.setText("<html><div style='text-align: center;'>" +
                    "Username not found<br/>Please sign up first" +
                    "</div></html>");
            errorLabel.setForeground(Color.red);

        }
    }

    public String formatUsername(String s){
        return s.replace("\n", "").replace(" ", "");
    }

    public void paintComponent(Graphics g){
        try {
            image = ImageIO.read(new File("src/Images/background-resized.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);

        g.drawImage(image, 0, 0, null);
    }
}