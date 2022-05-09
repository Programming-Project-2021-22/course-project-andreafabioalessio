package Main;
import entity.Player;
import entity.PlayerSkin;
import level.levelHandler;

import java.awt.Graphics;


public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private Player player;
    private levelHandler levelHandler;
    private final int FPS = 60;

    public final static int originalTileSize = 16; // grandezza in pixel di ogni tile
    public final static int scale = 3; // scaling del tile

    public final static int maxScreenCol = 16; // Ratio = 4:3
    public final static int maxScreenRow = 12;
    public final static int tileSize = originalTileSize * scale;
    public final static int screenWidth = tileSize * maxScreenCol; // 1536 pixel
    public final static int screenHeight = tileSize * maxScreenRow; // 1248 pixel



    public Game(){
        getClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void getClasses() {
        PlayerSkin skin = new PlayerSkin();
        player = new Player(100,300,6,20,1,skin);
        levelHandler = new levelHandler(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        levelHandler.update();
        player.update();
    }

    public void render(Graphics g){
        levelHandler.draw(g);
        player.draw(g);
    }

    @Override
    public void run() {
        double drawInterval = 1000000000f/ FPS;
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
                gamePanel.repaint();
                // reset delta
                delta--;
            }
        }
    }
}
