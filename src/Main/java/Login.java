import Exceptions.InvalidUsernameError;
import Exceptions.WrongPasswordError;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Login extends Registration {

    private Image background;
    private final JTextField usernameTField;
    private final JPasswordField passwordTField;
    private final JLabel errorLabel;

    public Login(JFrame window, User[] userArray) {
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.WHITE);

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
        showPassword.setBackground(Color.BLACK);
        showPassword.setBorderPainted(false);
        showPasswordLabel.setFont(new Font("Dialog", Font.BOLD, 8));

        showPassword.addActionListener(e-> {passwordTField.setEchoChar(
                showPassword.isSelected() ? '\u0000' : '\u2022');});

        ImageIcon loginIcon = new ImageIcon(getClass().getResource("Images/login-button3.png"));
        ImageIcon loginIconHovered = new ImageIcon(getClass().getResource("Images/login-button-hovered3.png"));
        ImageIcon loginIconPressed = new ImageIcon(getClass().getResource("Images/login-button-pressed3.png"));

        JButton loginButton = new JButton(loginIcon);
        loginButton.setRolloverIcon(loginIconHovered);
        loginButton.setPressedIcon(loginIconPressed);

        loginButton.setMinimumSize(new Dimension(101, 40));
        loginButton.setMaximumSize(new Dimension(101, 40));
        loginButton.setPreferredSize(new Dimension(101, 40));
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.addActionListener(e -> {
            try {
                String passwordEntered = String.valueOf(passwordTField.getPassword());
                String usernameEntered = usernameTField.getText().trim();

                if(checkUserInArray(userArray, usernameEntered, passwordEntered)){
                    getUser(userArray, window, usernameEntered);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        ImageIcon backIcon = new ImageIcon(getClass().getResource("Images/back-button2.png"));
        ImageIcon backIconHovered = new ImageIcon(getClass().getResource("Images/back-button-hovered2.png"));

        JButton backButton = new JButton(backIcon);
        backButton.setRolloverIcon(backIconHovered);

        backButton.setMinimumSize(new Dimension(92, 40));
        backButton.setMaximumSize(new Dimension(92, 40));
        backButton.setPreferredSize(new Dimension(92, 40));
        backButton.setBackground(Color.BLACK);
        backButton.setBorderPainted(false);
        backButton.addActionListener(e -> super.goBackToStartup(window));

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        Panel errorPanel = new Panel();
        errorPanel.setLayout(new GridBagLayout());
        errorPanel.setMaximumSize(new Dimension(600, 50));
        errorPanel.setMinimumSize(new Dimension(600, 50));
        errorPanel.setPreferredSize(new Dimension(600, 50));
        errorPanel.add(errorLabel);
        errorPanel.setBackground(new Color(0, 0, 0, 0));

        Dimension panelDimension = new Dimension(600, 460);

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

        //Positioning of the username text field (185px from the left of panel and 136px down)
        panelLayout.putConstraint(SpringLayout.WEST, usernameTField, 185, SpringLayout.WEST , panel);
        panelLayout.putConstraint(SpringLayout.NORTH, usernameTField, 136, SpringLayout.NORTH, panel);
        //Positioning of the password text field (185px from the left of panel and 105px down from bottom of the usernameTField)
        panelLayout.putConstraint(SpringLayout.WEST, passwordTField, 185, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, passwordTField, 105, SpringLayout.SOUTH, usernameTField);
        //Positioning of the show password label (260px from left of panel and 15px down from passwordTField)
        panelLayout.putConstraint(SpringLayout.WEST, showPasswordLabel, 260, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, showPasswordLabel, 15, SpringLayout.SOUTH, passwordTField);
        //Positioning of the show password checkbox (325px from left of panel and same height as the showPasswordLabel)
        panelLayout.putConstraint(SpringLayout.WEST, showPassword, 325, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, showPassword, -15, SpringLayout.SOUTH, showPasswordLabel);
        //Positioning of the login button (250px from left of panel and 30px down from showPassword)
        panelLayout.putConstraint(SpringLayout.WEST, loginButton, 250, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, loginButton, 30, SpringLayout.SOUTH, showPassword);
        //Positioning of the back button (255px from left of panel and 22px down from loginButton)
        panelLayout.putConstraint(SpringLayout.WEST, backButton, 255, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, backButton, 22, SpringLayout.SOUTH, loginButton);

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

    //Abstract method implementation from the Registration class

    /***
     * Checks if a user with the same username entered exists or not
     * @param userArray array containing all registered Users
     * @param usernameEntered username provided by the player in textField
     * @param passwordEntered password provided by the player in textField
     * @return true if the username and the password entered by the player match one of the User's username and password,
     *              false otherwise;
     * @throws WrongPasswordError if the password provided by the player does not match the one registered and associated to the username
     * @throws InvalidUsernameError if the username provided by the player is not registered
     */
    @Override
    protected boolean checkUserInArray(User[] userArray, String usernameEntered, String passwordEntered) throws WrongPasswordError, InvalidUsernameError {
        boolean usernameFound = false;

        for (int i = 0; i < userArray.length; ){
            User userCheck = userArray[i];

            //Checks if username entered exists in the array
            if (usernameEntered.equalsIgnoreCase(userCheck.getUsername())) {
                usernameFound = true;

                //Checks if the password entered matches the one registered
                if (passwordEntered.equalsIgnoreCase(userCheck.getPassword())) {
                    return true;
                }

                //Username matches, password doesn't
                else {
                    errorLabel.setText("Wrong password");
                    throw new WrongPasswordError("Password does not match username");
                }
            }
            else {
                i++; //checks next user in the array
            }
        }

        //Username not found
        if (!usernameFound){
            errorLabel.setText("Username not found, please sign up first.");

            throw new InvalidUsernameError("Username not found in User List");
        }
        return false;
    }

    /***
     * Overridden paintComponent method that paints the background
     * @param g the Graphics object to protect
     */
    @Override
    public void paintComponent(Graphics g){
        try {
            InputStream i = Login.class.getResourceAsStream("/Images/LoginSignup-background-resized.png");
            background = ImageIO.read(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
    }
}