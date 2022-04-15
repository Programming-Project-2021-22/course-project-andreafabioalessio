package Main;

import entity.Player;
import entity.PlayerSkin;
import entity.Skin;


import javax.swing.*;
import java.awt.*;

import static Main.Game.screenHeight;
import static Main.Game.screenWidth;


public class GamePanel extends JPanel{

    private Game game;

    public GamePanel(Game game){

        this.game = game;
        setPanelSize();
        this.setBackground(Color.white);
        this.setDoubleBuffered(true); // il rendering avviene più efficientemente
        addKeyListener(new KeyHandler());
        this.setFocusable(true);

    }

    public void setPanelSize(){
        Dimension size = new Dimension(screenWidth, screenHeight);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g){ //Graphics è una classe default di java per disegnare oggetti sullo schermo

        super.paintComponent(g); // super significa la classe parent di questa classe. in questo caso JPanel

        game.render(g);

        g.dispose(); // salvare un pò di memoria

    }
}
