package Main;

import Exeptions.InvalidUsernameError;
import Exeptions.WrongPasswordError;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Login extends JPanel {

    private Image background;
    private final JTextField usernameTField;
    private final JPasswordField passwordTField;
    private final JLabel errorLabel;
    private final User[] userArray;

    public Login(JFrame window, User[] userArray) {
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.WHITE);

        this.userArray = userArray;

        usernameTField = new JTextField(13);
        usernameTField.setForeground(new Color(205, 58, 218));
        usernameTField.setBackground(Color.BLACK);
        usernameTField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        usernameTField.setFont(new Font("Dialog" , Font.PLAIN, 20));

        passwordTField = new JPasswordField(13);
        passwordTField.setForeground(new Color(205, 58, 218));
        passwordTField.setBackground(Color.BLACK);
        passwordTField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        passwordTField.setFont(new Font("Dialog" , Font.PLAIN, 20));

        JLabel showPasswordLabel = new JLabel("Show Password");
        showPasswordLabel.setForeground(new Color(255, 255, 255));
        JCheckBox showPassword = new JCheckBox();
        showPassword.setContentAreaFilled(false);
        showPassword.setBorderPainted(false);
        showPassword.setOpaque(false);
        showPassword.setBackground(new Color(0, 0, 0, 0));

        showPasswordLabel.setFont(new Font("Dialog", Font.BOLD, 8));

        showPassword.addActionListener(e-> {passwordTField.setEchoChar(
                showPassword.isSelected() ? '\u0000' : '\u2022');});

        JPanel showPasswordPanel = new JPanel();
        showPasswordPanel.add(showPasswordLabel);
        showPasswordPanel.add(showPassword);
        showPasswordPanel.setBackground(new Color(0, 0, 0, 0));

        ImageIcon loginIcon = new ImageIcon("res/Images/login-button-resized.png", "login button icon");
        JButton loginButton = new JButton(loginIcon);
        loginButton.setMinimumSize(new Dimension(75, 24));
        loginButton.setMaximumSize(new Dimension(75, 24));
        loginButton.setPreferredSize(new Dimension(75, 24));
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.addActionListener(e -> {
            try {
                checkUserInArray(window);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        ImageIcon backIcon = new ImageIcon("res/Images/back-button-resized.png", "back button icon");
        JButton backButton = new JButton(backIcon);
        backButton.setMinimumSize(new Dimension(75, 24));
        backButton.setMaximumSize(new Dimension(75, 24));
        backButton.setPreferredSize(new Dimension(75, 24));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.addActionListener(e -> goBackToMenu(window));

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        Panel errorPanel = new Panel();
        errorPanel.setLayout(new GridBagLayout());
        errorPanel.setMaximumSize(new Dimension(600, 50));
        errorPanel.setMinimumSize(new Dimension(600, 50));
        errorPanel.setPreferredSize(new Dimension(600, 50));
        errorPanel.add(errorLabel);
        errorPanel.setBackground(new Color(0, 0, 0, 0));

        Dimension panelDimension = new Dimension(600, 380);

        JPanel panel = new JPanel();
        SpringLayout panelLayout = new SpringLayout();

        panel.setMaximumSize(panelDimension);
        panel.setMinimumSize(panelDimension);
        panel.setPreferredSize(panelDimension);
        panel.setLayout(panelLayout);

        panel.add(usernameTField);
        panel.add(passwordTField);
        panel.add(showPasswordLabel);
        panel.add(showPassword);
        panel.add(loginButton);
        panel.add(backButton);

        //Positioning of the username text field (210px from the left of panel and 105px down)
        panelLayout.putConstraint(SpringLayout.WEST, usernameTField, 210, SpringLayout.WEST , panel);
        panelLayout.putConstraint(SpringLayout.NORTH, usernameTField, 105, SpringLayout.NORTH, panel);
        //Positioning of the password text field (210px from the left of panel and 90px down from bottom of the usernameTField)
        panelLayout.putConstraint(SpringLayout.WEST, passwordTField, 210, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, passwordTField, 90, SpringLayout.SOUTH, usernameTField);
        //Positioning of the show password label (255px from left of panel and 15px down from passwordTField)
        panelLayout.putConstraint(SpringLayout.WEST, showPasswordLabel, 255, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, showPasswordLabel, 15, SpringLayout.SOUTH, passwordTField);
        //Positioning of the show password checkbox (320px from left of panel and same height as the showPasswordLabel)
        panelLayout.putConstraint(SpringLayout.WEST, showPassword, 320, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, showPassword, -15, SpringLayout.SOUTH, showPasswordLabel);
        //Positioning of the login button (255px from left of panel and 20px down from showPassword)
        panelLayout.putConstraint(SpringLayout.WEST, loginButton, 255, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, loginButton, 20, SpringLayout.SOUTH, showPassword);
        //Positioning of the back button (255px from left of panel and 20px down from loginButton)
        panelLayout.putConstraint(SpringLayout.WEST, backButton, 255, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, backButton, 20, SpringLayout.SOUTH, loginButton);

        panel.setBackground(new Color(0, 0, 0, 0));

        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(new GridBagLayout());
        constraints.gridy = 1;
        constraints.gridx = 1;
        add(panel, constraints);
        constraints.gridy = 2;
        add(Box.createRigidArea(new Dimension(0,20)), constraints);
        constraints.gridy = 3;
        add(errorPanel, constraints);
    }

    //Processes the press of the back button and goes back to Startup page
    private void goBackToMenu(JFrame window) {
        Startup s = new Startup(window);
        window.getContentPane().removeAll();
        window.setTitle("Menù");
        window.setContentPane(s);
        window.revalidate();
        window.repaint();
    }

    //Gets the user from the array and starts menu window
    private void getUser (User[] userArray, JFrame window) throws IOException {
        String usernameEntered = usernameTField.getText();

        for (User u : userArray) {
            if(formatUsername(u.getUsername()).equalsIgnoreCase(usernameEntered)){
                openMenu(window, u);
            }
        }
    }

    //Checks if a user with the same username entered exists or not
    private void checkUserInArray(JFrame window) throws IOException {
        boolean found = false;
        String usernameEntered = usernameTField.getText();
        String passwordEntered = String.valueOf(passwordTField.getPassword());

        for (int i = 0; i < userArray.length; ){
            User userCheck = userArray[i];
            //Checks if username entered exists in the array
            if (usernameEntered.equalsIgnoreCase(formatUsername(userCheck.getUsername()))) {//checks if given username matches one in the array
                found = true;
                //Checks if the password entered matches the correct one
                if (passwordEntered.equalsIgnoreCase(formatUsername(userCheck.getPassword()))) { //checks the password
                    getUser(userArray, window);
                }
                else {
                    try {
                        throw new WrongPasswordError("Password does not match username");//username matches, password doesn't
                    } catch (WrongPasswordError e) {
                        e.printStackTrace();
                    }
                    errorLabel.setText("Wrong password");
                }
                return;
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
            errorLabel.setText("Username not found, please sign up first.");
        }
    }

    //Formats username eliminating unwanted tokens
    private String formatUsername(String s){
        return s.replace("\n", "").replace(" ", "");
    }

    //Opens the menu window
    private void openMenu(JFrame window, User u) throws IOException {
        Menu m = new Menu(window, u);
        window.getContentPane().removeAll();
        window.setTitle("Login");
        window.setContentPane(m);
        window.revalidate();
        window.repaint();
    }

    //Overridden paintComponent method that paints the background
    @Override
    public void paintComponent(Graphics g){
        try {
            background = ImageIO.read(new File("res/Images/LoginSignup-screen-background-resized.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
    }
}