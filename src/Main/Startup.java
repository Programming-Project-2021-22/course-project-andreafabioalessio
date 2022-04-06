package Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Startup extends JPanel {

    Button loginButton = new Button("Login");
    Button signupButton = new Button ("Sign up");
    static User [] userArray = new User[0];
    Scanner userLineScan = null;
    Scanner userTokenScan = null;

    public Startup(){

        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(Color.white);

        JPanel buttons = new JPanel();
        buttons.add(loginButton);
        buttons.add(Box.createRigidArea(new Dimension(0,20)));
        buttons.add(signupButton);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        fillArray();

        signupButton.addActionListener(e -> openSignUpWindow());

        loginButton.addActionListener(e -> openLoginWindow());

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());
        c.gridx = 1;
        c.gridy = 1;
        add(buttons, c);
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

    public void fillArray(){

        ArrayList<User> temp = new ArrayList<User>();
        {
            try {
                userLineScan = new Scanner(new File("src/UsersList.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        userLineScan.useDelimiter(":");

        while (userLineScan.hasNext()){
            String userLine = userLineScan.next();
            userTokenScan = new Scanner(userLine);
            userTokenScan.useDelimiter(";");

            String u = userTokenScan.next();
            String p = userTokenScan.next();
            int l = Integer.parseInt(userTokenScan.next());
            User user = new User(u, p, l);
            temp.add(user);
        }
        userArray = new User[temp.size()];
        for (int i = 0; i < temp.size(); i++){
            userArray[i] = temp.get(i);
        }
        System.out.println(arrayToString(userArray));
    }

    public String arrayToString(User [] userArray){
        String users = "List of users:\n";
        for (User u : userArray){
            users += toString(u);
        }
        return users;
    }

    public String toString(User u){
        return u.getUsername() + ";" + u.getPassword() + ";" + u.getLevel() + ";:\n";
    }
}