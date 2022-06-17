import java.awt.Graphics;

/*

CLASS SUMMARY

class status: currently ok, but comments need to be added and the structure might be quite confused
 */

/***
 * Class that initializes all the Game related classes. It contains the GameLoop
 */
public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private Playing playing;

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

    /***
     * Initializes the Menu and Playing classes
     */
    private void getClasses() {
        playing = new Playing(this);
    }

    /***
     * starts the Gameloop
     */
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /***
     * Update method, that updates the player and level position. It check also the Gamestate.
     */
    public void update(){
        if (Gamestate.state == Gamestate.MAINMENU) {
            gameWindow.window.setVisible(true);
            gameWindow.settingsWindow.dispose();
            gameWindow.jframe.dispose();
            gameWindow.commandsWindow.dispose();
            playing.getPlayer().teleportToBeginning();

        }

        else if (Gamestate.state == Gamestate.SETTINGS){
            gameWindow.settingsWindow.setVisible(true);
        }

        else if (Gamestate.state == Gamestate.COMMANDS){
            gameWindow.window.dispose();
            gameWindow.commandsWindow.setVisible(true);
        }

        else if (Gamestate.state == Gamestate.PLAYING){
            gameWindow.jframe.setVisible(true);
            gameWindow.window.dispose();
            gameWindow.settingsWindow.dispose();
        }

        switch (Gamestate.state){
            case PLAYING -> playing.update();
        }
    }

    /***
     * Renders the level and Player on the screen
     * @param g Graphics engine
     */
    public void render(Graphics g){
        switch (Gamestate.state){
            case PLAYING -> playing.draw(g);
        }
    }

    /***
     * Creates the GameLoop. The game runs at 60 FPS
     */
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

    public Playing getPlaying(){
        return playing;
    }
}