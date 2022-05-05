package Main;

import javax.swing.*;
import java.awt.*;

public class Main{
    public static void main(String[] args){
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Menù");

        Startup s = new Startup(window);

        window.add(s);
        window.setSize(new Dimension(600,400));
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.pack();
    }
}