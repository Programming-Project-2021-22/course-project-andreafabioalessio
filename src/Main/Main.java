package Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        Startup s = new Startup(window);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Menù");

        window.add(s);
        window.setSize(new Dimension(768,624));
        window.setVisible(true);
        window.pack();
        window.setLocationRelativeTo(null);
    }
}