package Main;

import entity.Player;
import entity.Skin;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{

    final static int originalTileSize = 16; // grandezza in pixel di ogni tile
    final static int scale = 3; // scaling del tile

    public static int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16; // Ratio = 4:3
    final static int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 1536 pixel
    public static int screenHeight = tileSize * maxScreenRow; // 1248 pixel

    public BufferedImage[] left = new BufferedImage[6];
    public BufferedImage[] right = new BufferedImage[6];
    public BufferedImage[] center = new BufferedImage[2];

    public void getPlayerImage(){

        center[0] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_0.png"));
        left[0] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_1.png"));
        left[1] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_2.png"));
        left[2] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_3.png"));
        left[3] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_4.png"));
        left[4] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_5.png"));
        left[5] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_6.png"));
        center[1] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_0.png"));
        right[0] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_1.png"));
        right[1] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_2.png"));
        right[2] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_3.png"));
        right[3] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_4.png"));
        right[4] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_5.png"));
        right[5] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_6.png"));
    }

    // FPS
    int fps = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // serve a creare il game loop di tempo.
                       // un thread serve a creare la UI (quindi il gioco) e un alro pr eseguire il codice

//to fix
    Skin skin = new Skin(left,right,center);
    Player player = new Player(300,300,4,10,0,skin);




    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true); // il rendering avviene più efficientemente
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public static int getScreenHeight(){
        return screenHeight;
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
