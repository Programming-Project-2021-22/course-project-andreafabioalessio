package Main;

import Exeptions.InvalidUsernameError;
import Exeptions.InvalidPasswordError;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Signup extends JPanel {

    private final JTextField usernameTField;
    private final JPasswordField passwordTField;
    private final JLabel errorLabel;
    private Image background;
    private User[] userArray;

    public Signup(JFrame window, User[] userArray){
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

        ImageIcon signupIcon = new ImageIcon("res/Images/signup-button-resized.png", "signup button icon");
        JButton createButton = new JButton(signupIcon);
        createButton.setMinimumSize(new Dimension(75, 24));
        createButton.setMaximumSize(new Dimension(75, 24));
        createButton.setPreferredSize(new Dimension(75, 24));
        createButton.setContentAreaFilled(false);
        createButton.setBorderPainted(false);
        createButton.addActionListener(e -> {
            try {
                createButtonPress(window);
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
        backButton.addActionListener(e -> goBackToStartup(window));

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
        panel.add(createButton);
        panel.add(backButton);

        //Positioning of the username text field (210px from the left of panel and 105px down)
        panelLayout.putConstraint(SpringLayout.WEST, usernameTField, 210, SpringLayout.WEST , panel);
        panelLayout.putConstraint(SpringLayout.NORTH, usernameTField, 105, SpringLayout.NORTH, panel);
        //Positioning of the password text field (210px from the left of panel and 90px down from bottom of the usernameTField)
        panelLayout.putConstraint(SpringLayout.WEST, passwordTField, 210, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, passwordTField, 90, SpringLayout.SOUTH, usernameTField);
        //Positioning of the show password label (255px from left of panel and 5px down from passwordTField)
        panelLayout.putConstraint(SpringLayout.WEST, showPasswordLabel, 255, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, showPasswordLabel, 15, SpringLayout.SOUTH, passwordTField);
        //Positioning of the show password checkbox (320px from left of panel and same height as the showPasswordLabel)
        panelLayout.putConstraint(SpringLayout.WEST, showPassword, 320, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, showPassword, -15, SpringLayout.SOUTH, showPasswordLabel);
        //Positioning of the create button (255px from left of panel and 20px down from showPassword)
        panelLayout.putConstraint(SpringLayout.WEST, createButton, 255, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, createButton, 20, SpringLayout.SOUTH, showPassword);
        //Positioning of the back button (255px from left of panel and 20px down from create)
        panelLayout.putConstraint(SpringLayout.WEST, backButton, 255, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, backButton, 20, SpringLayout.SOUTH, createButton);

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
    private void goBackToStartup(JFrame window) {
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
            if((u.getUsername().trim()).equalsIgnoreCase(usernameEntered)){
                openMenu(window, u, userArray);
            }
        }
    }

    //Processes the press of the signUpButton
    private void createButtonPress(JFrame window) throws IOException {
        String username = usernameTField.getText();
        String password = String.valueOf(passwordTField.getPassword());

        //Checks that the username field is not empty
        if(!checkInputNotNull(username)){
            errorLabel.setText("Please insert a valid username");

            throw new InvalidUsernameError("Username field cannot be empty");
        }

        //Checks that the password field is not empty
        if(!checkInputNotNull(password)) {
            errorLabel.setText("Please insert a valid password");

            throw new InvalidPasswordError("Password field cannot be empty");
        }

        if (!checkUser(username)){
            if (checkInputNotNull(password)){
                createUser(username, password);
                getUser(userArray, window);  //Gets user from array and starts menu window
            }
        }
        else{
            errorLabel.setText("<html><div style = 'text-align: center;'>" +
                    "Username already exists.<br/>" +
                    "Please select another one or login" +
                    "</div></html>");

            throw new InvalidUsernameError("Username already exists");
        }
    }

    //Checks if the Input username is not already taken
    private boolean checkUser(String us) throws FileNotFoundException {
        String existingUsername;
        boolean exists = false;

        Scanner userCheckerScan = new Scanner(new File("src/UsersList.txt"));

        userCheckerScan.useDelimiter("\n|;");

        while (userCheckerScan.hasNext() && !exists) {
            existingUsername = userCheckerScan.next();

            exists = existingUsername.compareToIgnoreCase(us) == 0;

            System.out.println("Checking: " + existingUsername + "|" + us + "|" + exists);
            //Scanner also scans password, level and :, call to next() 3 times to skip these comparisons,
            // allowing usernames to match other user's passwords.
            System.out.println("Skipping: " + userCheckerScan.next());
            System.out.println("Skipping: " + userCheckerScan.next());
            System.out.println("Skipping: " + userCheckerScan.next());
        }
        return exists;
    }

    //Checks that the input is not empty
    private boolean checkInputNotNull (String s){
        return !s.equalsIgnoreCase("");
    }

    //Creates a User object with the given parameters
    private void createUser(String us, String pa) throws IOException {
        User d = new User (us, pa, 1);
        User [] temp = new User[userArray.length + 1];
        for (int i = 0; i < userArray.length; i++){
            temp [i] = userArray[i];
        }

        temp[temp.length - 1] = d;
        userArray = temp;
        addToFile(d);
    }

    //Adds the user data to the file
    private void addToFile(User d) throws IOException {

        FileWriter fw = new FileWriter("src/UsersList.txt", true);

        PrintWriter pw = new PrintWriter(fw);
        pw.append("\n");
        pw.append(userToString(d));
        pw.close();
    }

    //Opens the menu window
    private void openMenu(JFrame window, User u, User[] userArray) throws IOException {
        Menu m = new Menu(window, u, userArray);
        window.getContentPane().removeAll();
        window.setTitle("Login");
        window.setContentPane(m);
        window.revalidate();
        window.repaint();
    }

    //Formats user data for the userList file
    private String userToString(User u){
        return u.getUsername() + ";" + u.getPassword() + ";" + u.getLevel() + ";:";
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