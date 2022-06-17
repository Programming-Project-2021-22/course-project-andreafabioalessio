import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Menu extends JPanel {

    private Image background;
    private final JPanel level;
    private static final Label userInfoLv = new Label();
    public static int numLevel = 1;
    private final JPanel dot1, dot2, dot3, dot4, dots;
    private static User user;

    public Menu (JFrame window, User user) throws IOException {
        window.setTitle("Menù");

        Menu.user = user;

        this.setPreferredSize(new Dimension(960, 720));
        this.setBackground(Color.WHITE);

        Dimension levelPanelDimensions = new Dimension(650, 260);

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
        level.setBackground(Color.BLACK);

        JButton select = new JButton();

        select.setMaximumSize(new Dimension(630, 240));
        select.setMinimumSize(new Dimension(630, 240));
        select.setPreferredSize(new Dimension(630, 240));
        select.setOpaque(false);
        select.setBorderPainted(false);
        select.setContentAreaFilled(false);

        select.addActionListener(e -> loadLevel(user));

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
        dots.setBackground(Color.BLACK);

        updateLevelGraphics(user);

        ImageIcon forwardIcon = new ImageIcon(getClass().getResource("Images/forward-arrow2.png"));
        ImageIcon forwardIconHovered = new ImageIcon(getClass().getResource("Images/forward-arrow-hovered2.png"));
        ImageIcon forwardIconPressed = new ImageIcon(getClass().getResource("Images/forward-arrow-pressed2.png"));

        JButton forward = new JButton(forwardIcon);
        forward.setBackground(Color.BLACK);
        forward.setBorderPainted(false);
        //Changes arrow on hover and press
        forward.setPressedIcon(forwardIconPressed);
        forward.setRolloverIcon(forwardIconHovered);

        forward.addActionListener(e -> {
            try {
                processForwardButtonPress(user);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        ImageIcon backIcon = new ImageIcon(getClass().getResource("Images/back-arrow2.png"));
        ImageIcon backIconHovered = new ImageIcon(getClass().getResource("Images/back-arrow-hovered2.png"));
        ImageIcon backIconPressed = new ImageIcon(getClass().getResource("Images/back-arrow-pressed2.png"));

        JButton back = new JButton(backIcon);
        back.setBackground(Color.BLACK);
        back.setBorderPainted(false);
        //Changes arrow on hover and press
        back.setPressedIcon(backIconPressed);
        back.setRolloverIcon(backIconHovered);

        back.addActionListener(e -> {
            try {
                processBackwardButtonPress(user);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        ImageIcon settingsIcon = new ImageIcon(getClass().getResource("Images/settings-button2.png"));
        ImageIcon settingsIconHovered = new ImageIcon(getClass().getResource("Images/settings-button-hovered2.png"));

        JButton settingsButton = new JButton(settingsIcon);
        settingsButton.setRolloverIcon(settingsIconHovered);

        Dimension settingsButtonDimension = new Dimension(50, 50);
        settingsButton.setPreferredSize(settingsButtonDimension);
        settingsButton.setBorderPainted(false);
        settingsButton.setBackground(Color.BLACK);

        settingsButton.addActionListener(e ->{
            openSettings();
        });

        JPanel panel = new JPanel();
        SpringLayout panelLayout = new SpringLayout();
        Dimension panelDimension = new Dimension(960, 720);

        panel.setPreferredSize(panelDimension);
        panel.setMaximumSize(panelDimension);
        panel.setMinimumSize(panelDimension);
        panel.setLayout(panelLayout);
        panel.setBackground(new Color(0, 0, 0 ,0));

        panel.add(userInfo);
        panel.add(back);
        panel.add(level);
        panel.add(select);
        panel.add(forward);
        panel.add(dots);
        panel.add(settingsButton);

        //User info upper right corner
        panelLayout.putConstraint(SpringLayout.EAST, userInfo, -30, SpringLayout.EAST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, userInfo, 20, SpringLayout.NORTH, panel);
        //Level button selector and images perfectly centered
        panelLayout.putConstraint(SpringLayout.NORTH, level, 230, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.WEST, level, 155, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, select, 235, SpringLayout.NORTH, panel);
        panelLayout.putConstraint(SpringLayout.WEST, select, 160, SpringLayout.WEST, panel);
        //Arrows placed vertically centered and on the sides of the level panel
        panelLayout.putConstraint(SpringLayout.EAST, back, -20, SpringLayout.WEST, level);
        panelLayout.putConstraint(SpringLayout.NORTH, back, 70, SpringLayout.NORTH, level);
        panelLayout.putConstraint(SpringLayout.WEST, forward, 20, SpringLayout.EAST, level);
        panelLayout.putConstraint(SpringLayout.NORTH, forward, 70, SpringLayout.NORTH, level);
        //Dots placed directly under the level panel and horizontally centered
        panelLayout.putConstraint(SpringLayout.NORTH, dots, 0, SpringLayout.SOUTH, level);
        panelLayout.putConstraint(SpringLayout.WEST, dots, 355, SpringLayout.WEST, panel);
        //Settings button on top left corner
        panelLayout.putConstraint(SpringLayout.WEST, settingsButton, 30, SpringLayout.WEST, panel);
        panelLayout.putConstraint(SpringLayout.NORTH, settingsButton, 30, SpringLayout.NORTH, panel);

        add(panel);
    }

    /***
     * Updates the level of the player in the array
     * @param user: the logged-in User
     * @throws IOException: thrown by the updateUserLevelInFile(User user) method
     */
    public static void updateUserLevelInArray(User user) throws IOException {
        //If the level that has been just beaten is the last unlocked
        if(user.getLevel() == numLevel){
            //If the level was not the last one
            if(user.getLevel() < 4){
                user.setLevel(user.getLevel() + 1);

                updateUserLevelInFile(user);
            }
        }
    }

    /***
     * Updates the level of the player writing on the usersList file
     * @param user: the logged-in User
     * @throws IOException: if the named file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason
     */
    private static void updateUserLevelInFile(User user) throws IOException {
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
                currentLine = reader.readLine();
            }
            //Else we write the data
            else{
                writer.write(currentLine.trim());
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

    /***
     * Updates userInfo label
     * @param user: the logged-in User
     */
    private static void updateUserInfoLabel(User user){
        userInfoLv.setText("Lv: " + user.getLevel()
               );
    }

    /***
     * Updates the graphics of the dots and level cover
     * @param user: the logged-in User
     * @throws IOException: if an error occurs during reading
     */
    private void updateLevelGraphics(User user) throws IOException {
        //Updates the level cover
        Dimension dotSelected = new Dimension(30, 10);
        Dimension dotUnselected = new Dimension(10, 10);
        String imagePath = "LevelCovers/Lv" + numLevel + ".png";

        if(numLevel > user.getLevel()){
            imagePath = "LevelCovers/Lv" + numLevel + "-locked.png";

        }
        InputStream i = Menu.class.getResourceAsStream(imagePath);
        BufferedImage img = ImageIO.read(i);
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

    /***
     * Updates the value of numLevel
     * @param user: the logged-in User
     * @throws IOException: thrown because of the updateLevelGraphics(User user) method call
     */
    private void processForwardButtonPress(User user) throws IOException {
        if (numLevel < 4) {
            numLevel++;
            updateLevelGraphics(user);
        }
        else {
            numLevel = 1;
            updateLevelGraphics(user);
        }
    }

    /***
     * Updates the value of numLevel
     * @param user: the logged-in User
     * @throws IOException: thrown because of the updateLevelGraphics(User user) method call
     */
    private void processBackwardButtonPress(User user) throws IOException {
        if(numLevel > 1){
            numLevel--;
            updateLevelGraphics(user);
        }
        else{
            numLevel = 4;
            updateLevelGraphics(user);
        }
    }

    /***
     * Opens the Settings window by changing GameState
     */
    private void openSettings(){
        Gamestate.state = Gamestate.SETTINGS;
    }

    /***
     * Opens command window by changing the GameState
     */
    private void openCommands(){
        Gamestate.state = Gamestate.COMMANDS;
    }

    /***
     * Loads the level corresponding to the numLevel value
     * @param user: the logged-in User
     */
    private void loadLevel(User user){
        switch (numLevel){
            case 1:
                openCommands();
                levelHandler.loadNextLevel();
                break;

            case 2:
                if(user.getLevel() < numLevel){
                    throw new LevelTooLowError("Level locked, reach level " + numLevel + " to unlock it");
                }
                else{
                    Gamestate.state = Gamestate.PLAYING;
                    levelHandler.loadNextLevel();
                }
                break;

            case 3:
                if(user.getLevel() < numLevel){
                    throw new LevelTooLowError("Level locked, reach level " + numLevel + " to unlock it");
                }
                else{
                    Gamestate.state = Gamestate.PLAYING;
                    levelHandler.loadNextLevel();
                }
                break;

            case 4:
                if(user.getLevel() < numLevel){
                    throw new LevelTooLowError("Level locked, reach level " + numLevel + " to unlock it");
                }
                else{
                    Gamestate.state = Gamestate.PLAYING;
                    levelHandler.loadNextLevel();
                }
                break;
        }
    }

    /***
     * After a level is won, updates the level of the user if necessary
     * @throws IOException
     */
    public static void updateUserAfterWin() throws IOException {
        updateUserLevelInArray(user);
    }

    /***
     * Gets the numLevel value, the number of the level the player is selecting
     * @return = the value of the Level
     */
    public static int getNumLevel(){
        return numLevel;
    }

    /***
     * Overridden paintComponent method that paints the background
     * @param g: the Graphics object to protect
     */
    @Override
    public void paintComponent(Graphics g){
        try {
            InputStream i = Menu.class.getResourceAsStream("/Images/Menu-background-resized.png");
            background = ImageIO.read(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
    }
}