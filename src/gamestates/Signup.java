package gamestates;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import Exeptions.InvalidUsernameError;
import Exeptions.InvalidPasswordError;
import User.User;

public class Signup extends Registration {
    
    private Image background;
    private final JTextField usernameTField;
    private final JPasswordField passwordTField;
    private final JLabel errorLabel;

    public Signup(JFrame window, User[] userArray){
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

        ImageIcon signupIcon = new ImageIcon("res/Images/signup-button3.png");
        ImageIcon signupIconHovered = new ImageIcon("res/Images/signup-button-hovered3.png");
        ImageIcon signupIconPressed = new ImageIcon("res/Images/signup-button-pressed3.png");

        JButton signupButton = new JButton(signupIcon);
        signupButton.setRolloverIcon(signupIconHovered);
        signupButton.setPressedIcon(signupIconPressed);

        signupButton.setMinimumSize(new Dimension(126, 40));
        signupButton.setMaximumSize(new Dimension(126, 40));
        signupButton.setPreferredSize(new Dimension(126, 40));
        signupButton.setBackground(Color.BLACK);
        signupButton.setBorderPainted(false);

        signupButton.addActionListener(e -> {
            try {
                createButtonPress(window, userArray);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        ImageIcon backIcon = new ImageIcon("res/Images/back-button2.png");
        ImageIcon backIconHovered = new ImageIcon("res/Images/back-button-hovered2.png");

        JButton backButton = new JButton(backIcon);
        backButton.setRolloverIcon(backIconHovered);

        backButton.setMinimumSize(new Dimension(92, 40));
        backButton.setMaximumSize(new Dimension(92, 40));
        backButton.setPreferredSize(new Dimension(92, 40));
        backButton.setBackground(Color.BLACK);
        backButton.setBorderPainted(false);
        backButton.addActionListener(e -> super.goBackToStartup(window));

        ImageIcon infoIcon = new ImageIcon("res/Images/infobutton-icon2.png");
        ImageIcon infoIconHovered = new ImageIcon("res/Images/infobutton-icon-hovered2.png");

        JButton passwordInfo = new JButton(infoIcon);
        passwordInfo.setPreferredSize(new Dimension(25, 25));
        passwordInfo.setBackground(Color.BLACK);
        passwordInfo.setBorderPainted(false);
        passwordInfo.setRolloverIcon(infoIconHovered);

        passwordInfo.addActionListener(e->
                JOptionPane.showMessageDialog(window,
                        "Password format:" +
                                "\n- Between 4 and 10 characters;" +
                                "\n- At least one letter character;" +
                                "\n- Can have numbers;" +
                                "\n- Can not have special characters.",
                        "Password Format",
                        JOptionPane.INFORMATION_MESSAGE, infoIcon));

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);

        Panel errorPanel = new Panel();
        errorPanel.setLayout(new GridBagLayout());
        errorPanel.setMaximumSize(new Dimension(600, 50));
        errorPanel.setMinimumSize(new Dimension(600, 50));
        errorPanel.setPreferredSize(new Dimension(600, 50));
        errorPanel.add(errorLabel);
        errorPanel.setBackground(new Color(0, 0, 0, 0));

        Dimension panelDimension = new Dimension(700, 460);

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
        panel.add(signupButton);
        panel.add(backButton);
        panel.add(passwordInfo);

        //Positioning of the username text field (210px from the left of panel and 132px down)
        panelLayout.putConstraint(SpringLayout.WEST, usernameTField, 235, SpringLayout.WEST , panel);
        panelLayout.putConstraint(SpringLayout.NORTH, usernameTField, 135, SpringLayout.NORTH, panel);
        //Positioning of the password text field (210px from the left of panel and 90px down from bottom of the usernameTField)
        panelLayout.putConstraint(SpringLayout.WEST, passwordTField, 235, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, passwordTField, 105, SpringLayout.SOUTH, usernameTField);
        //Positioning of the show password label (260px from left of panel and 5px down from passwordTField)
        panelLayout.putConstraint(SpringLayout.WEST, showPasswordLabel, 310, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, showPasswordLabel, 15, SpringLayout.SOUTH, passwordTField);
        //Positioning of the show password checkbox (325px from left of panel and same height as the showPasswordLabel)
        panelLayout.putConstraint(SpringLayout.WEST, showPassword, 375, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, showPassword, -15, SpringLayout.SOUTH, showPasswordLabel);
        //Positioning of the create button (237px from left of panel and 20px down from showPassword)
        panelLayout.putConstraint(SpringLayout.WEST, signupButton, 285, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, signupButton, 30, SpringLayout.SOUTH, showPassword);
        //Positioning of the back button (255px from left of panel and 20px down from create)
        panelLayout.putConstraint(SpringLayout.WEST, backButton, 300, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, backButton, 22, SpringLayout.SOUTH, signupButton);
        //Positioning of the password info button
        panelLayout.putConstraint(SpringLayout.WEST, passwordInfo, 2, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, passwordInfo, 271, SpringLayout.NORTH, panel);

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

    //Processes the press of the signUpButton
    private void createButtonPress(JFrame window, User[] userArray) throws IOException {
        String usernameEntered = usernameTField.getText().trim();
        String passwordEntered = String.valueOf(passwordTField.getPassword());

        //Checks that the username field is not empty
        if(inputIsNull(usernameEntered)){
            errorLabel.setText("Please insert a valid username");

            throw new InvalidUsernameError("Username field cannot be empty");
        }

        //Checks if the username is already taken
        if(checkUserInArray(userArray, usernameEntered)){
            errorLabel.setText("<html><div style = 'text-align: center;'>" +
                    "Username already exists.<br/>" +
                    "Please select another one or login" +
                    "</div></html>");

            throw new InvalidUsernameError("Username already exists");
        }

        //Password matches the regex
        if(!passwordIsRightFormat(passwordEntered)){
            errorLabel.setText("<html><div style = 'text-align: center;'>" +
                                        "Password is not of the right format.<br/>" +
                                        "Check the infobox for more information." +
                                        "</div></html>");

            throw new InvalidPasswordError("Password is not of the right format");
        }

        if (!checkUserInArray(userArray, usernameEntered)){
            if(passwordIsRightFormat(passwordEntered)){
                createUser(usernameEntered, passwordEntered, userArray, window);
            }
        }
    }

    //Abstract method implementation from the Registration class
    //Checks if the input username is not already taken
    @Override
    protected boolean checkUserInArray(User[] userArray, String usernameEntered){
        System.out.println("Checking if the username entered is not already taken:");
        for(User u : userArray){
            System.out.println("Username entered: " + usernameEntered + "; Username in array: " + u.getUsername() + ";");
            if(usernameEntered.equalsIgnoreCase(u.getUsername())){
                return true;
            }
        }
        return false;
    }

    //Checks that the input is not empty
    private boolean inputIsNull (String passwordEntered){
        return passwordEntered.equalsIgnoreCase("");
    }

    //Checks if the password matches the regex
    private boolean passwordIsRightFormat(String passwordEntered){
        if(4 <= passwordEntered.length() && passwordEntered.length() <= 10){
            return passwordEntered.matches("^[a-zA-Z0-9]*[a-zA-Z][a-zA-Z0-9]*$");
        }

        return false;
    }

    //Creates a User object with the given parameters
    private void createUser(String us, String pa, User[] userArray, JFrame window) throws IOException {
        User d = new User (us, pa, 1);
        addToFile(d);

        User[] temp = new User[userArray.length + 1];
        System.arraycopy(userArray, 0, temp, 0, userArray.length);

        temp[temp.length - 1] = d;
        userArray = temp;

        super.getUser(userArray, window, usernameTField.getText().trim());  //Gets user from array and starts menu window
    }

    //Adds the user data to the file
    private void addToFile(User user) throws IOException {

        FileWriter fw = new FileWriter("src/UsersList.txt", true);

        PrintWriter pw = new PrintWriter(fw);
        pw.append("\n");
        pw.append(userToString(user));
        pw.close();
    }

    //Formats user data for the userList file
    private String userToString(User user){
        return user.getUsername() + ";" + user.getPassword() + ";" + user.getLevel() + ";:";
    }

    //Overridden paintComponent method that paints the background
    @Override
    public void paintComponent(Graphics g){
        try {
            background = ImageIO.read(new File("res/Images/LoginSignup-background-resized.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
    }
}