package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel {

    private Image background;
    private final JPanel level;
    private int numLevel = 1;
    private final JPanel dot1, dot2, dot3, dot4, dots;
    private final Dimension dotSelected = new Dimension(30, 10);
    private final Dimension dotUnselected = new Dimension(10, 10);

    public Menu (JFrame window, User user) throws IOException {
        window.setTitle("Menù");

        this.setPreferredSize(new Dimension(768, 624));
        this.setBackground(Color.WHITE);

        Dimension levelPanelDimensions = new Dimension(500, 200);

        JLabel userInfo = new JLabel("<html><div style = 'text-align: right;'>" + user.getUsername() +
                "<br/>Lv:" + user.getLevel() + "</div></html>");
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

        updateGraphics();

        ImageIcon forwardIcon = new ImageIcon("res/Images/forward-arrow.png");
        ImageIcon forwardIconHovered = new ImageIcon("res/Images/forward-arrow-hovered.png");

        JButton forward = new JButton(forwardIcon);
        forward.setBackground(new Color(0, 0, 0));
        forward.setBorderPainted(false);
        //Changes the arrow when you hover on it
        forward.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                forward.setIcon(forwardIconHovered);
            }
            public void mouseExited(MouseEvent e) {
                forward.setIcon(forwardIcon);
            }
        });

        ImageIcon backIcon = new ImageIcon("res/Images/back-arrow.png");
        ImageIcon backIconHovered = new ImageIcon("res/Images/back-arrow-hovered.png");

        JButton back = new JButton(backIcon);
        back.setBackground(new Color(0, 0, 0));
        back.setBorderPainted(false);
        //Changes the arrow when you hover on it
        back.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                back.setIcon(backIconHovered);
            }
            public void mouseExited(MouseEvent evt) {
                back.setIcon(backIcon);
            }
        });

        forward.addActionListener(e -> {
            try {
                processForwardButtonPress();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
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

        select.addActionListener(e -> loadLevel(window));

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e ->{
            openSettings();
            }
        );

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

    //Updates the graphics of the dots and level cover
    private void updateGraphics() throws IOException {
        //Updates the level cover
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
            updateGraphics();
        }
        else {
            numLevel = 1;
            updateGraphics();
        }
    }

    //Updates the value of numLevel
    private void processBackwardButtonPress() throws IOException {
        if(numLevel > 1){
            numLevel--;
            updateGraphics();
        }
        else{
            numLevel = 4;
            updateGraphics();
        }
    }

    //Opens the Settings window
    private void openSettings(){
        JFrame settings = new JFrame("Settings");
        Settings set = new Settings();

        settings.add(set);
        settings.setPreferredSize(new Dimension(350, 350));
        settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settings.setResizable(false);
        settings.setVisible(true);
        settings.pack();
        settings.setLocationRelativeTo(null);
    }

    //Loads the level corresponding to the numLevel value
    private void loadLevel(JFrame window){
        switch (numLevel){
            case 1:
                GamePanel g = new GamePanel();
                window.getContentPane().removeAll();
                window.setTitle("Let's Play");
                window.setContentPane(g);
                window.revalidate();
                window.repaint();
                break;

            case 2:
                level.setBackground(Color.red);
                break;

            case 3:
                level.setBackground(Color.cyan);
                break;

            case 4:
                level.setBackground(Color.yellow);
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