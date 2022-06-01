package Main;

import Exeptions.LevelTooLowError;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Menu extends JPanel {

    private Image background;
    private final JPanel level;
    private final Label userInfoLv = new Label();
    private int numLevel = 1;
    private final JPanel dot1, dot2, dot3, dot4, dots;

    JButton removeMe;

    public Menu (JFrame window, User user) throws IOException {
        window.setTitle("Menù");

        this.setPreferredSize(new Dimension(768, 624));
        this.setBackground(Color.WHITE);

        Dimension levelPanelDimensions = new Dimension(500, 200);

        Label userInfoName = new Label(user.getUsername());
        userInfoLv.setText("Lv: " + user.getLevel());
        JPanel userInfo = new JPanel();
        userInfo.setLayout(new BoxLayout(userInfo, BoxLayout.Y_AXIS));
        userInfo.add(userInfoName);
        userInfo.add(userInfoLv);
        userInfoName.setAlignment(Label.RIGHT);
        userInfoLv.setAlignment(Label.RIGHT);
        userInfo.setBackground(new Color(0, 0, 0, 0));
        userInfo.setBorder(BorderFactory.createBevelBorder(2));
        userInfo.setFont(new Font("Dialog", Font.PLAIN, 18));
        userInfo.setForeground(new Color(205, 58, 218));

        level = new JPanel();
        level.setMinimumSize(levelPanelDimensions);
        level.setMinimumSize(levelPanelDimensions);
        level.setPreferredSize(levelPanelDimensions);

        dot1 = new JPanel();
        dot2 = new JPanel();
        dot3 = new JPanel();
        dot4 = new JPanel();

        dots = new JPanel();
        dots.setMinimumSize(new Dimension(250, 30));
        dots.setMaximumSize(new Dimension(250, 30));
        dots.setPreferredSize(new Dimension(250, 30));
        dots.setLayout(new GridBagLayout());
        GridBagConstraints dotsConstraints = new GridBagConstraints();
        dotsConstraints.gridx = 0;
        dotsConstraints.gridy = 0;
        dots.add(dot1, dotsConstraints);
        dotsConstraints.gridx = 1;
        dots.add(Box.createRigidArea(new Dimension(40,0)));
        dotsConstraints.gridx = 2;
        dots.add(dot2, dotsConstraints);
        dotsConstraints.gridx = 3;
        dots.add(Box.createRigidArea(new Dimension(40,0)));
        dotsConstraints.gridx = 4;
        dots.add(dot3, dotsConstraints);
        dotsConstraints.gridx = 5;
        dots.add(Box.createRigidArea(new Dimension(40,0)));
        dotsConstraints.gridx = 6;
        dots.add(dot4, dotsConstraints);
        dots.setBackground(new Color(0, 0, 0));

        updateLevelGraphics();

        ImageIcon forwardIcon = new ImageIcon("res/Images/forward-arrow.png");
        ImageIcon forwardIconHovered = new ImageIcon("res/Images/forward-arrow-hovered.png");
        ImageIcon forwardIconPressed = new ImageIcon("res/Images/forward-arrow-pressed.png");

        JButton forward = new JButton(forwardIcon);
        forward.setBackground(new Color(0, 0, 0));
        forward.setBorderPainted(false);
        //Changes arrow on hover and press
        forward.setPressedIcon(forwardIconPressed);
        forward.setRolloverIcon(forwardIconHovered);

        forward.addActionListener(e -> {
            try {
                processForwardButtonPress();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        ImageIcon backIcon = new ImageIcon("res/Images/back-arrow.png");
        ImageIcon backIconHovered = new ImageIcon("res/Images/back-arrow-hovered.png");
        ImageIcon backIconPressed = new ImageIcon("res/Images/back-arrow-pressed.png");

        JButton back = new JButton(backIcon);
        back.setBackground(new Color(0, 0, 0));
        back.setBorderPainted(false);
        //Changes arrow on hover and press
        back.setPressedIcon(backIconPressed);
        back.setRolloverIcon(backIconHovered);

        back.addActionListener(e -> {
            try {
                processBackwardButtonPress();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        JButton select = new JButton();

        select.setMaximumSize(levelPanelDimensions);
        select.setMinimumSize(levelPanelDimensions);
        select.setPreferredSize(levelPanelDimensions);
        select.setOpaque(false);
        select.setBorderPainted(false);
        select.setContentAreaFilled(false);

        select.addActionListener(e -> loadLevel(user));

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e ->{
            openSettings();
            });

        JPanel panel = new JPanel();
        SpringLayout panelLayout = new SpringLayout();
        Dimension panelDimension = new Dimension(748, 604);

        panel.setPreferredSize(panelDimension);
        panel.setMaximumSize(panelDimension);
        panel.setMinimumSize(panelDimension);
        panel.setLayout(panelLayout);
        panel.setBackground(new Color(0, 0, 0 ,0));

        panel.add(userInfo);
        panel.add(back);
        panel.add(select);
        panel.add(level);
        panel.add(forward);
        panel.add(dots);
        panel.add(settingsButton);

        /////////////////////////////TO BE REMOVED/////////////////////////////
        removeMe = new JButton("Can I play this level?");
        removeMe.setPreferredSize(new Dimension(170, 40));
        removeMe.setSize(new Dimension(20, 20));
        panel.add(removeMe);
        panelLayout.putConstraint(SpringLayout.WEST, removeMe, 289, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, removeMe, 100, SpringLayout.NORTH, panel);
        removeMe.addActionListener(e->{
            try {
                updateUserLevelInArray(user);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        ///////////////////////////////////////////////////////////////////////

        //User info upper right corner
        panelLayout.putConstraint(SpringLayout.EAST, userInfo, -10, SpringLayout.EAST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, userInfo, 15, SpringLayout.NORTH, panel);
        //Level button selector and images perfectly centered
        panelLayout.putConstraint(SpringLayout.NORTH, level, 202, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.WEST, level, 124, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, select, 202, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.WEST, select, 124, SpringLayout.WEST, panel);
        //Arrows placed vertically centered and on the sides of the level panel
        panelLayout.putConstraint(SpringLayout.EAST, back, 0, SpringLayout.WEST, level);
        panelLayout.putConstraint(SpringLayout.NORTH, back, 277, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.WEST, forward, 0, SpringLayout.EAST, level);
        panelLayout.putConstraint(SpringLayout.NORTH, forward, 277, SpringLayout.NORTH, panel);
        //Dots placed directly under the level panel and horizontally centered
        panelLayout.putConstraint(SpringLayout.NORTH, dots, 0, SpringLayout.SOUTH, level);
        panelLayout.putConstraint(SpringLayout.WEST, dots, 249, SpringLayout.WEST, panel);
        //Settings button on top left corner
        panelLayout.putConstraint(SpringLayout.WEST, settingsButton, 20, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, settingsButton, 20, SpringLayout.NORTH, panel);

        add(panel);
    }

    //Updates the level of the player in the array
    private void updateUserLevelInArray(User user) throws IOException {

        if(user.getLevel() < 4){
            System.out.println("User data: " + user.getUsername() + "; Lv: " + user.getLevel());
            user.setLevel(user.getLevel() + 1);
            System.out.println("New user data: " + user.getUsername() + "; Lv: " + user.getLevel());

            updateUserLevelInFile(user);
        }
    }

    //Updates the level of the player writing on the usersList file
    private void updateUserLevelInFile(User user) throws IOException {
        //Appends new user data in UsersList.txt
        FileWriter fw = new FileWriter("src/UsersList.txt", true);
        PrintWriter pw = new PrintWriter(fw);

        String line = user.getUsername() + ";" + user.getPassword() + ";" + user.getLevel() + ";:";

        pw.append("\n");
        pw.append(line.trim());
        pw.close();

        //Copies all data besides old user data in myTempFile.txt
        File usersList = new File("src/UsersList.txt");
        File tempList = new File("src/tempUsersList.txt");

        BufferedReader reader = new BufferedReader(new FileReader(usersList));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempList ));

        String lineToRemove = user.getUsername() + ";" + user.getPassword() + ";" + (user.getLevel() - 1) + ";:";
        String currentLine = reader.readLine();

        while(currentLine != null) {
            //If line matches the old user's data
            if(currentLine.trim().equalsIgnoreCase(lineToRemove.trim())) {
                System.out.println("MATCH: Deleting this line..." + "(" + currentLine + ")");
                currentLine = reader.readLine();
            }
            //Else we write the data
            else{
                writer.write(currentLine.trim());
                System.out.println("Just wrote this line: " + currentLine);
                currentLine = reader.readLine();
                //if the next line is not null we go to a new line
                if(currentLine != null) {
                    writer.write("\n");
                }
            }
        }

        writer.close();
        reader.close();

        //Rewrites content of myTempFile to UsersList
        reader = new BufferedReader(new FileReader(tempList));
        writer = new BufferedWriter(new FileWriter(usersList));

        currentLine = reader.readLine();

        while(currentLine != null){
            writer.write(currentLine.trim());
            currentLine = reader.readLine();
            if(currentLine != null){
                writer.write("\n");
            }
        }

        writer.close();
        reader.close();

        updateUserInfoLabel(user);
    }

    //Updates userInfo label
    private void updateUserInfoLabel(User user){
        userInfoLv.setText("Lv: " + user.getLevel()
               );
    }

    //Updates the graphics of the dots and level cover
    private void updateLevelGraphics() throws IOException {
        //Updates the level cover
        Dimension dotSelected = new Dimension(30, 10);
        Dimension dotUnselected = new Dimension(10, 10);
        String imagePath = "res/LevelCovers/Level-cover-" + numLevel + ".jpg";
        BufferedImage img = ImageIO.read(new File(imagePath));
        JLabel pic = new JLabel(new ImageIcon(img));

        level.removeAll();
        level.add(pic);
        level.revalidate();
        level.repaint();

        //Dot modifier
        switch (numLevel) {
            case 1 -> {
                dot4.setBackground(Color.gray);
                dot4.setPreferredSize(dotUnselected);

                dot1.setBackground(new Color(205, 58, 218));
                dot1.setPreferredSize(dotSelected);

                dot2.setBackground(Color.gray);
                dot2.setPreferredSize(dotUnselected);

                dot3.setBackground(Color.gray);
                dot3.setPreferredSize(dotUnselected);

                dots.revalidate();
                dots.repaint();
            }
            case 2 -> {
                dot1.setBackground(Color.gray);
                dot1.setPreferredSize(dotUnselected);

                dot2.setBackground(new Color(205, 58, 218));
                dot2.setPreferredSize(dotSelected);

                dot3.setBackground(Color.gray);
                dot3.setPreferredSize(dotUnselected);

                dots.revalidate();
                dots.repaint();
            }
            case 3 -> {
                dot2.setBackground(Color.gray);
                dot2.setPreferredSize(dotUnselected);

                dot3.setBackground(new Color(205, 58, 218));
                dot3.setPreferredSize(dotSelected);

                dot4.setBackground(Color.gray);
                dot4.setPreferredSize(dotUnselected);

                dots.revalidate();
                dots.repaint();
            }
            case 4 -> {
                dot3.setBackground(Color.gray);
                dot3.setPreferredSize(dotUnselected);

                dot4.setBackground(new Color(205, 58, 218));
                dot4.setPreferredSize(dotSelected);

                dot1.setBackground(Color.gray);
                dot1.setPreferredSize(dotUnselected);

                dots.revalidate();
                dots.repaint();
            }
        }

    }

    //Updates the value of numLevel
    private void processForwardButtonPress() throws IOException {
        if (numLevel < 4) {
            numLevel++;
            updateLevelGraphics();
        }
        else {
            numLevel = 1;
            updateLevelGraphics();
        }
    }

    //Updates the value of numLevel
    private void processBackwardButtonPress() throws IOException {
        if(numLevel > 1){
            numLevel--;
            updateLevelGraphics();
        }
        else{
            numLevel = 4;
            updateLevelGraphics();
        }
    }

    //Opens the Settings window
    private void openSettings(){
        JFrame settings = new JFrame("Settings");

        JButton resume = new JButton("Close");
        resume.setPreferredSize(new Dimension(120, 30));
        resume.setMaximumSize(new Dimension(120, 30));
        resume.setForeground(new Color(205, 58, 218));

        resume.addActionListener(e ->{
            settings.dispose();
        });

        Settings set = new Settings(resume);
        set.setPreferredSize(new Dimension(350, 350));
        settings.add(set);

        settings.setPreferredSize(new Dimension(350, 350));
        settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settings.setResizable(false);
        settings.setVisible(true);
        settings.pack();
        settings.setLocationRelativeTo(null);
    }

    //Loads the level corresponding to the numLevel value
    private void loadLevel(User user){
        switch (numLevel){
            case 1:
                    level.setBackground(Color.green);
                    removeMe.setBackground(Color.green);
                    break;

            case 2:
                if(user.getLevel() < numLevel){
                    level.setBackground(Color.red);
                    removeMe.setBackground(Color.red);
                    throw new LevelTooLowError("Level locked, reach level " + numLevel + " to unlock it");
                }
                else{
                    level.setBackground(Color.green);
                    removeMe.setBackground(Color.green);
                }
                break;

            case 3:
                if(user.getLevel() < numLevel){
                    level.setBackground(Color.red);
                    removeMe.setBackground(Color.red);
                    throw new LevelTooLowError("Level locked, reach level " + numLevel + " to unlock it");
                }
                else{
                    level.setBackground(Color.cyan);
                    removeMe.setBackground(Color.green);
                }
                break;

            case 4:
                if(user.getLevel() < numLevel){
                    level.setBackground(Color.red);
                    removeMe.setBackground(Color.red);
                    throw new LevelTooLowError("Level locked, reach level " + numLevel + " to unlock it");
                }
                else{
                    level.setBackground(Color.yellow);
                    removeMe.setBackground(Color.green);
                }
                break;
        }
    }

    //Overridden paintComponent method that paints the background
    @Override
    public void paintComponent(Graphics g){
        try {
            background = ImageIO.read(new File("res/Images/Menu-background-resized.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
    }
}