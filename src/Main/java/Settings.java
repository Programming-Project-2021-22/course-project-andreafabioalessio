import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Settings extends JPanel {
    private Image background;
    private ImageIcon muteIcon, muteIconHovered, unmutedIcon, unmutedIconHovered;
    JButton mute;

    public Settings(){
        this.setPreferredSize(new Dimension(350, 350));

        ImageIcon backIcon = new ImageIcon(getClass().getResource("Images/back-button2.png"));
        ImageIcon backIconHovered = new ImageIcon(getClass().getResource("Images/back-button-hovered2.png"));

        JButton back = new JButton(backIcon);
        back.setRolloverIcon(backIconHovered);

        back.setMinimumSize(new Dimension(92, 40));
        back.setMaximumSize(new Dimension(92, 40));
        back.setPreferredSize(new Dimension(92, 40));
        back.setBackground(Color.BLACK);
        back.setBorderPainted(false);
        back.addActionListener(e -> Gamestate.state = Gamestate.MAINMENU);

        muteIcon = new ImageIcon(getClass().getResource("Images/mute2.png"));
        muteIconHovered = new ImageIcon(getClass().getResource("Images/mute-hovered2.png"));

        unmutedIcon = new ImageIcon(getClass().getResource("Images/unmuted2.png"));
        unmutedIconHovered = new ImageIcon(getClass().getResource("Images/unmuted-hovered2.png"));

        mute = new JButton(unmutedIcon);
        mute.setRolloverIcon(unmutedIconHovered);

        mute.setMinimumSize(new Dimension(54, 50));
        mute.setPreferredSize(new Dimension(54, 50));
        mute.setMaximumSize(new Dimension(54, 50));
        mute.setBorderPainted(false);
        mute.setBackground(Color.BLACK);
        mute.addActionListener(e -> {
            changeMuteStatus();
        });

        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());

        c.gridy = 0;
        add(Box.createRigidArea(new Dimension(0, 45)), c);

        c.gridy = 1;
        add(mute, c);

        c.gridy = 2;
        add(Box.createRigidArea(new Dimension(0,30)), c);

        c.gridy = 3;
        add(back, c);
    }

    /***
     * Changes the mute status from muted to unmuted and vice versa, updating the button icon as well
     */
    private void changeMuteStatus() {
        if(Entity.muted){
            mute.setIcon(unmutedIcon);
            mute.setRolloverIcon(unmutedIconHovered);
            Entity.muted = false;
        }

        else if(!Entity.muted){
            mute.setIcon(muteIcon);
            mute.setRolloverIcon(muteIconHovered);
            Entity.muted = true;
        }
    }

    /***
     * Overridden paintComponent method that paints the background
     * @param g the Graphics object to protect
     */
    @Override
    public void paintComponent(Graphics g){
        try {
            InputStream i = Settings.class.getResourceAsStream("/Images/settings-background.png");
            background = ImageIO.read(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
    }
}