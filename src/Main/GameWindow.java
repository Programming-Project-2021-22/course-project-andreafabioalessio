package Main;

import javax.swing.*;

/*

CLASS SUMMARY

class status: currently ok
 */

public class GameWindow {

    private JFrame jframe;

    public GameWindow(GamePanel gamePanel){
        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);
    }

}