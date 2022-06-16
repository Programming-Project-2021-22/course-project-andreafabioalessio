package gamestates;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Commands extends JPanel {
    Image background;

    public Commands(JButton play){
        this.setPreferredSize(new Dimension(960, 720));

        ImageIcon playIcon = new ImageIcon("res/Images/play-button2.png");
        ImageIcon playIconHovered = new ImageIcon("res/Images/play-button-hovered2.png");
        ImageIcon playIconPressed = new ImageIcon("res/Images/play-button-pressed2.png");

        play.setIcon(playIcon);
        play.setRolloverIcon(playIconHovered);
        play.setPressedIcon(playIconPressed);

        play.setPreferredSize(new Dimension(103, 40));
        play.setMinimumSize(new Dimension(103, 40));
        play.setMaximumSize(new Dimension(103, 40));
        play.setBackground(Color.BLACK);
        play.setBorderPainted(false);

        ImageIcon backIcon = new ImageIcon("res/Images/back-button2.png");
        ImageIcon backIconHovered = new ImageIcon("res/Images/back-button-hovered2.png");

        JButton back = new JButton(backIcon);
        back.setRolloverIcon(backIconHovered);

        back.setMinimumSize(new Dimension(92, 40));
        back.setMaximumSize(new Dimension(92, 40));
        back.setPreferredSize(new Dimension(92, 40));
        back.setBackground(Color.BLACK);
        back.setBorderPainted(false);
        back.addActionListener(e-> Gamestate.state = Gamestate.MAINMENU);

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());

        c.gridy = 0;
        c.gridx = 1;
        add(Box.createRigidArea(new Dimension(0, 550)), c);
        c.gridy = 1;
        add(play, c);
        c.gridx = 2;
        add(Box.createRigidArea(new Dimension(30, 0)), c);
        c.gridx = 3;
        add(back, c);

    }
    //Overridden paintComponent method that paints the background
    @Override
    public void paintComponent(Graphics g){
        try {
            background = ImageIO.read(new File("res/Images/commands.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
    }
}
