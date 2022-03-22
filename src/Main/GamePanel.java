package Main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16; // grandezza in pixel di ogni tile
    final int scale = 3; // scaling del tile

    public int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16; // Ratio = 4:3
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 1536 pixel
    final int screenHeight = tileSize * maxScreenRow; // 1248 pixel

    // FPS
    int fps = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // serve a creare il game loop di tempo.
                       // un thread serve a creare la UI (quindi il gioco) e un alro pr eseguire il codice

    Player player = new Player(this,keyH,3);

    // settare posizione default
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; //4 pixel

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true); // il rendering avviene più efficientemente
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread(){ // creazione di un thread
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // Game-Loop

        double drawInterval = 1000000000f/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){

            // settare gli fps del gioco. Arcade games andavano a 60 fps
            currentTime = System.nanoTime();

            delta += (currentTime-lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1){
                // Update: update information
                update();
                // Draw: draw the screen with new information
                repaint();
                // reset delta
                delta--;
            }
        }
    }

    public void update(){ //update la posizione del giocatore
        // questo comando viene effettuato 60 volte al secondo grazie al loop del gioco
        player.update();
    }

    public void paintComponent(Graphics g){ //Graphics è una classe default di java per disegnare oggetti sullo schermo

        super.paintComponent(g); // super significa la classe parent di questa classe. in questo caso JPanel

        Graphics2D g2 = (Graphics2D)g; // più funzioni

        player.draw(g2);

        g2.dispose(); // salvare un pò di memoria

    }
}
