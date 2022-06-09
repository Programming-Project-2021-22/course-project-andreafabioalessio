package Main;
import gamestates.Gamestate;
import gamestates.MainMenu;
import gamestates.Playing;

import java.awt.Graphics;

/*

CLASS SUMMARY

class status: currently ok, but comments need to be added and the structure might be quite confused
 */

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private Playing playing;
    private MainMenu mainMenu;

    private final int FPS = 60;
    public final static int originalTileSize = 16; // grandezza in pixel di ogni tile
    public final static int scale = 3; // scaling del tile
    public final static int maxScreenCol = 20; // Ratio = 4:3
    public final static int maxScreenRow = 15;
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
        mainMenu = new MainMenu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        switch (Gamestate.state){
            case PLAYING -> playing.update();
        }
    }

    public void render(Graphics g){
        switch (Gamestate.state){
            case PLAYING -> playing.draw(g);
        }

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
    public MainMenu getMainMenu(){
        return mainMenu;
    }
    public Playing getPlaying(){
        return playing;
    }
}
