package gamestates;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Settings extends JPanel {
    private Image background;

    public Settings(){
        this.setPreferredSize(new Dimension(350, 350));
        JButton back = new JButton("Back");

        back.addActionListener(e -> Gamestate.state = Gamestate.MAINMENU);

        back.setPreferredSize(new Dimension(120, 30));
        back.setMaximumSize(new Dimension(120, 30));
        back.setForeground(new Color(205, 58, 218));

        JButton mute = new JButton("Mute Music");

        mute.setPreferredSize(new Dimension(120, 30));
        mute.setMaximumSize(new Dimension(120, 30));
        mute.setForeground(new Color(205, 58, 218));

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());

        c.gridy = 1;
        add(Box.createRigidArea(new Dimension(0, 30)), c);

        c.gridy = 2;
        add(mute, c);

        c.gridy = 3;
        add(Box.createRigidArea(new Dimension(0,30)), c);

        c.gridy = 4;
        add(back, c);
    }

    //Overridden paintComponent method that paints the background
    @Override
    public void paintComponent(Graphics g){
        try {
            background = ImageIO.read(new File("res/Images/settings-background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
    }
}