package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Settings extends JPanel {
    private Image background;

    public Settings(){
        this.setPreferredSize(new Dimension(350, 350));
        JLabel settingsLabel = new JLabel("Settings");
        settingsLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        settingsLabel.setForeground(new Color(205, 58, 218));

        JButton idk = new JButton("I'm just a button");
        JButton resume = new JButton("Resume");

        resume.addActionListener(e ->{
            resumeButtonPress();
                });

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());
        c.gridy = 0;
        add(settingsLabel, c);

        c.gridy = 1;
        add(Box.createRigidArea(new Dimension(50, 50)), c);

        c.gridy = 2;
        add(resume, c);

        c.gridy = 3;
        add(Box.createRigidArea(new Dimension(50,50)), c);

        c.gridy = 4;
        add(idk, c);
    }

    private void resumeButtonPress() {
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
