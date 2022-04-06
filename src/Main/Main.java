package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Gioco Among Us");

        GamePanel panel = new GamePanel();
        Startup s = new Startup();
        window.add(s);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.startGameThread();
    }
}