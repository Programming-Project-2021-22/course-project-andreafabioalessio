package Main;

import gamestates.Gamestate;
import gamestates.Settings;
import gamestates.Startup;

import javax.swing.*;
import java.awt.*;

/*

CLASS SUMMARY

class status: currently ok
 */

public class GameWindow {

    protected JFrame jframe;
    protected JFrame window;
    protected JFrame settingsWindow;

    public GameWindow(GamePanel gamePanel){
        window = new JFrame();

        Startup s = new Startup(window);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Menù");

        window.add(s);

        window.setSize(new Dimension(960, 720));

        window.pack();
        window.setLocationRelativeTo(null);

        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setLocationRelativeTo(null);


        Settings set = new Settings();
        set.setPreferredSize(new Dimension(350, 350));

        settingsWindow = new JFrame("Settings");
        settingsWindow.add(set);
        settingsWindow.setPreferredSize(new Dimension(350, 350));
        settingsWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        settingsWindow.setResizable(false);
        settingsWindow.setVisible(false);
        settingsWindow.pack();
        settingsWindow.setLocationRelativeTo(null);

        if (Gamestate.state == Gamestate.MAINMENU) {
            window.setVisible(true);
            jframe.dispose();
        }

        else if (Gamestate.state == Gamestate.SETTINGS) {
            settingsWindow.setVisible(true);
        }

        else {
            jframe.setVisible(true);
            window.dispose();
        }
    }
}