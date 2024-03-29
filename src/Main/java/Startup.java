import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Startup extends JPanel {

    private Image background;
    private static User[] userArray = new User[0];
    private Scanner userLineScan = null;

    public Startup(JFrame window){
        fillArray(); //reads users from UserList.txt and fills an array with users

        this.setPreferredSize(new Dimension(960, 720));
        this.setBackground(Color.white);

        Dimension loginButtonDimension = new Dimension(190, 75);
        Dimension signupButtonDimension = new Dimension(236, 75);

        ImageIcon loginIcon = new ImageIcon(getClass().getResource("images/login-button2.png"), "login button icon");
        ImageIcon loginIconHovered = new ImageIcon(getClass().getResource("images/login-button-hovered2.png"), "login button icon");
        ImageIcon loginIconPressed = new ImageIcon(getClass().getResource("images/login-button-pressed2.png"), "login button icon");

        JButton loginButton = new JButton(loginIcon);
        loginButton.setRolloverIcon(loginIconHovered);
        loginButton.setPressedIcon(loginIconPressed);

        loginButton.setMinimumSize(loginButtonDimension);
        loginButton.setMaximumSize(loginButtonDimension);
        loginButton.setPreferredSize(loginButtonDimension);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);

        ImageIcon signupIcon = new ImageIcon(getClass().getResource("images/signup-button2.png"));
        ImageIcon signupIconHovered = new ImageIcon(getClass().getResource("images/signup-button-hovered2.png"));
        ImageIcon signupIconPressed = new ImageIcon(getClass().getResource("images/signup-button-pressed2.png"));

        JButton signupButton = new JButton(signupIcon);
        signupButton.setRolloverIcon(signupIconHovered);
        signupButton.setPressedIcon(signupIconPressed);

        signupButton.setMinimumSize(signupButtonDimension);
        signupButton.setMaximumSize(signupButtonDimension);
        signupButton.setPreferredSize(signupButtonDimension);
        signupButton.setContentAreaFilled(false);
        signupButton.setBorderPainted(false);

        signupButton.addActionListener(e -> openSignUpWindow(window));
        loginButton.addActionListener(e -> openLoginWindow(window));

        JLabel developers = new JLabel("Developed by: Eritale Alessio, Marconi Fabio, Parodi Andrea");
        developers.setFont(new Font("Dialog", Font.BOLD, 12));
        developers.setForeground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());
        c.gridx = 1;
        c.gridy = 0;
        add(Box.createRigidArea(new Dimension(0,220)), c);
        c.gridy = 1;
        add(loginButton, c);
        c.gridy = 2;
        add(Box.createRigidArea(new Dimension(0,50)), c);
        c.gridy = 3;
        add(signupButton, c);
        c.gridy = 4;
        add(Box.createRigidArea(new Dimension(0, 190)), c);
        c.gridy = 5;
        add(developers, c);
    }

    /***
     * Opens Login Window
     * @param window window on which the Login page gets painted on
     */
    private void openLoginWindow(JFrame window){
        Login l = new Login(window, userArray);
        window.getContentPane().removeAll();
        window.setTitle("Login");
        window.setContentPane(l);
        window.revalidate();
        window.repaint();
    }

    /***
     * Opens Signup Window
     * @param window window on which the Signup page gets painted on
     */
    private void openSignUpWindow(JFrame window){
        Signup s = new Signup(window, userArray);
        window.getContentPane().removeAll();
        window.setTitle("Sign Up");
        window.setContentPane(s);
        window.revalidate();
        window.repaint();
    }

    /***
     * Fills an array with Users, taking data from the usersList file
     */
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
            String userLine = userLineScan.next().trim();
            Scanner userTokenScan = new Scanner(userLine);
            userTokenScan.useDelimiter(";");

            String u = userTokenScan.next().trim();
            String p = userTokenScan.next().trim();
            int l = Integer.parseInt(userTokenScan.next().trim());
            User user = new User(u, p, l);
            temp.add(user);
        }

        userArray = new User[temp.size()];

        for (int i = 0; i < temp.size(); i++){
            userArray[i] = temp.get(i);
        }
    }

    /***
     * Overridden paintComponent method that paints the background
     * @param g the Graphics object to protect
     */
    @Override
    public void paintComponent(Graphics g){
        try {
            InputStream i = Startup.class.getResourceAsStream("/images/Startup-background-resized.png");
            background = ImageIO.read(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
    }
}