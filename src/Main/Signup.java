package Main;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Signup extends JPanel {

    JTextField usernameTField, passwordTField;
    static User [] userArray = new User[0];
    Button createButton;
    String username = "", password = "";
    FileWriter fw;
    Scanner userCheckerScan;

    public Signup(){
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

        createButton = new Button ("Create profile");

        JPanel panel = new JPanel();
        panel.add(Box.createRigidArea(new Dimension(0,120)));
        panel.add(usernamePanel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(passwordPanel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(createButton);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                username = usernameTField.getText();
                password = passwordTField.getText();

                if (!checkUser(username, password)){
                    createUser(username, password);
                }
                else{
                    System.out.println("Username already exists.\nPlease select another one or login!");
                }
            }
        });
        add(panel);
    }

    public boolean checkUser (String us, String pa){
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
           if (exists)
               return true;
        }
        return exists;
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
        System.out.println(arrayToString(userArray));
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
        pw.append(toString(d));
        pw.close();
    }

    public String arrayToString(User [] userArray){
        String users = "List of users:\n";
        for (User u : userArray){
            users += toString(u);
        }
        return users;
    }

    public String toString(User u){
        return u.getUsername() + ";" + u.getPassword() + ";\n";
    }
}