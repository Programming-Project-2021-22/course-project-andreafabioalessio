package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Settings extends JPanel {
    private Image background;

    public Settings(JButton resume){
        this.setPreferredSize(new Dimension(350, 350));
        JLabel settingsLabel = new JLabel("Settings");
        settingsLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        settingsLabel.setForeground(new Color(205, 58, 218));

        JButton idk = new JButton("I'm useless");
        idk.setPreferredSize(new Dimension(120, 30));
        idk.setMaximumSize(new Dimension(120, 30));
        idk.setForeground(new Color(205, 58, 218));

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());

        c.gridy = 2;
        add(Box.createRigidArea(new Dimension(0, 50)), c);

        c.gridy = 3;
        add(idk, c);

        c.gridy = 4;
        add(Box.createRigidArea(new Dimension(0,30)), c);

        c.gridy = 5;
        add(resume, c);
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