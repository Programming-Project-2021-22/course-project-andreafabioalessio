package Main;

import gamestates.Gamestate;
import gamestates.Startup;

import javax.swing.*;
import java.awt.*;

/*

CLASS SUMMARY

class status: currently ok
 */

public class GameWindow {

    protected JFrame jframe;

    JFrame window = new JFrame();
    Startup s = new Startup(window);



    public GameWindow(GamePanel gamePanel){

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

        if (Gamestate.state == Gamestate.MAINMENU)
            window.setVisible(true);
        else
            jframe.setVisible(true);


    }

}
