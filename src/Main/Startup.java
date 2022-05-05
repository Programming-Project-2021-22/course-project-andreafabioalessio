package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Startup extends JPanel {

    static User [] userArray = new User[0];
    private Scanner userLineScan = null;
    private Image image;

    public Startup(JFrame window){
        fillArray(); //reads users from UserList.txt and fills an array with users

        this.setPreferredSize(new Dimension(600, 400));
        this.setBackground(Color.white);

        Dimension loginButtonDimension = new Dimension(168, 50);
        Dimension signupButtonDimension = new Dimension(201, 50);

        ImageIcon loginIcon = new ImageIcon("src/Images/login-button-resized2.png", "login button icon");
        JButton loginButton = new JButton(loginIcon);
        loginButton.setMinimumSize(loginButtonDimension);
        loginButton.setMaximumSize(loginButtonDimension);
        loginButton.setPreferredSize(loginButtonDimension);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);

        ImageIcon signupIcon = new ImageIcon("src/Images/signup-button-resized2.png", "signup button icon");
        JButton signupButton = new JButton(signupIcon);
        signupButton.setMinimumSize(signupButtonDimension);
        signupButton.setMaximumSize(signupButtonDimension);
        signupButton.setPreferredSize(signupButtonDimension);
        signupButton.setContentAreaFilled(false);
        signupButton.setBorderPainted(false);

        ImageIcon background = new ImageIcon("src/Images/background.jpg");
        Image img = background.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        background = new ImageIcon(img);
        JLabel back = new JLabel(background);
        back.setLayout(new BorderLayout());
        back.setBounds(0,0,600,400);

        signupButton.addActionListener(e -> openSignUpWindow(window));
        loginButton.addActionListener(e -> openLoginWindow(window));

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());
        c.gridx = 1;
        c.gridy = 1;
        add(loginButton, c);
        c.gridy = 2;
        add(Box.createRigidArea(new Dimension(0,30)), c);
        c.gridy = 3;
        add(signupButton, c);
    }

    private void openLoginWindow(JFrame window){
        Login l = new Login(window);
        window.getContentPane().removeAll();
        window.setTitle("Login");
        window.setContentPane(l);
        window.revalidate();
        window.repaint();
    }

    private void openSignUpWindow(JFrame window){
        Signup s = new Signup(window);
        window.getContentPane().removeAll();
        window.setTitle("Sign Up");
        window.setContentPane(s);
        window.revalidate();
        window.repaint();
    }

    private void fillArray(){
        ArrayList<User> temp = new ArrayList<>();
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
            Scanner userTokenScan = new Scanner(userLine);
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

    private String arrayToString(User [] userArray){
        String users = "List of users:\n";
        for (User u : userArray){
            users += toString(u);
        }
        return users;
    }

    private String toString(User u){
        return u.getUsername() + ";" + u.getPassword() + ";" + u.getLevel() + ";:\n";
    }

    @Override
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