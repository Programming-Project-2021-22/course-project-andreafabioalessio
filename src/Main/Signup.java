package Main;

import Exeptions.InvalidUsernameError;
import Exeptions.InvalidPasswordError;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import static Main.Startup.userArray;

public class Signup extends JPanel {

    private final JTextField usernameTField;
    private final JPasswordField passwordTField;
    private final JLabel errorLabel;
    private String username = "", password = "";
    private FileWriter fw;
    private Scanner userCheckerScan;
    private Image image;

    public Signup(JFrame window){
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

        ImageIcon signupIcon = new ImageIcon("src/Images/signup-button-resized.png", "signup button icon");
        JButton createButton = new JButton(signupIcon);
        createButton.setMinimumSize(new Dimension(75, 24));
        createButton.setMaximumSize(new Dimension(75, 24));
        createButton.setPreferredSize(new Dimension(75, 24));
        createButton.setContentAreaFilled(false);
        createButton.setBorderPainted(false);
        createButton.addActionListener(e -> createButtonPress());

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
        panel.add(createButton, panelConstraints);
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

    private void goBackToMenu(JFrame window) {
        Startup s = new Startup(window);
        window.getContentPane().removeAll();
        window.setTitle("Menù");
        window.setContentPane(s);
        window.revalidate();
        window.repaint();
    }

    private void createButtonPress(){
        username = usernameTField.getText();
        password = String.valueOf(passwordTField.getPassword());

        if (!checkUser(username)){
            if (checkPassword(password)){
                createUser(username, password);
                errorLabel.setText("User successfully created");
                errorLabel.setForeground(new Color(0, 220, 30));
                //+Login
            }
            else{
                try {
                    throw new InvalidPasswordError("Password field can not be empty");
                } catch (InvalidPasswordError ex) {
                    ex.printStackTrace();
                }
                errorLabel.setText("Please insert a valid password");
                errorLabel.setForeground(Color.red);
            }
        }
        else{
            try {
                throw new InvalidUsernameError("Username already exists");
            } catch (InvalidUsernameError ex) {
                ex.printStackTrace();
            }
            errorLabel.setText("<html><div style = 'text-align: center;'>" +
                    "Username already exists.<br/>Please select another one or login" +
                    "</div></html>");
            errorLabel.setForeground(Color.red);
        }
    }

    public boolean checkUser(String us){
        String existingUsername;
        boolean exists = false;
        {
            try {
                userCheckerScan = new Scanner(new File("src/UsersList.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        userCheckerScan.useDelimiter(";|\n");

        while (userCheckerScan.hasNext() && !exists) {
            existingUsername = userCheckerScan.next();

            exists = existingUsername.compareToIgnoreCase(us) == 0;
            System.out.println(existingUsername + "|" + us + "|" + exists);
        }
        return exists;
    }

    public boolean checkPassword (String pa){
        return !pa.equalsIgnoreCase("");
    }

    public void createUser(String us, String pa){
        User d = new User (us, pa, 1);
        User [] temp = new User[userArray.length + 1];
        for (int i = 0; i < userArray.length; i++){
            temp [i] = userArray[i];
        }

        temp[temp.length - 1] = d;
        userArray = temp;
        addToFile(d);
    }

    public void addToFile(User d) {
        {
            try {
                fw = new FileWriter("src/UsersList.txt", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.append("\n");
        pw.append(toString(d));
        pw.close();
    }

    public String toString(User u){
        return u.getUsername() + ";" + u.getPassword() + ";" + u.getLevel() + ";:";
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