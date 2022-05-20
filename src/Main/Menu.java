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
    JPanel dot1, dot2, dot3, dot4, dots;
    Dimension dotSelected = new Dimension(30, 10);
    Dimension dotUnselected = new Dimension(10, 10);

    public Menu (JFrame window, User user) throws IOException {
        window.setTitle("Menù");

        this.setPreferredSize(new Dimension(768, 624));
        this.setBackground(Color.WHITE);

        Dimension levelPanelDimensions = new Dimension(500, 200);

        JLabel userInfo = new JLabel("<html><div style = 'text-align: right;'>" +
                "Username: " + user.getUsername() +
                "<br/>Level: " + user.getLevel() + "</div></html>");
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
        forward.setContentAreaFilled(false);
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
        back.setContentAreaFilled(false);
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

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 2;
        add(userInfo, c);
        c.gridy = 1;
        c.gridx = 0;
        add(back, c);
        c.gridx = 1;
        add(select, c);
        add(level, c);
        c.gridx = 2;
        add(forward, c);
        c.gridy = 3;
        c.gridx = 1;
        add(dots, c);
    }

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