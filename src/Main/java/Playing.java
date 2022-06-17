import java.awt.*;
import java.awt.image.BufferedImage;

/***
 * Class that initializes the player and the level in order to create a GameState that can be referenced to in the Game class
 */
public class Playing extends State implements StateMethods{
    private Player player;
    private static levelHandler levelHandler;

    private int xlvlOffset;
    private int leftBorder = (int) (0.2 * Game.screenWidth);
    private int rightBorder = (int) (0.6 * Game.screenWidth);
    private int maxLvlOffsetX;

    private BufferedImage bgImage,clouds;

    private boolean lvlCompleted = false;

    public Playing(Game game) {
        super(game);
        getClasses();

        bgImage = LevelLoad.GetSpriteAtlas(LevelLoad.BG_IMAGE);
        clouds = LevelLoad.GetSpriteAtlas(LevelLoad.CLOUDS);

        calcLvlOffset();
    }

    public static void loadNextLevel(){
       levelHandler.loadNextLevel();
    }

    private void calcLvlOffset() {
        maxLvlOffsetX = levelHandler.getLevel().getLvlOffset();
    }

    /***
     * Initializes all the necessary classes:
     * Levelhandler
     * PlayerSkin
     * Player
     */
    private void getClasses() {
        levelHandler = new levelHandler(game);
        PlayerSkin skin = new PlayerSkin();
        player = new Player(200,300,7,20,1,skin);
        player.loadLvlData(levelHandler.getLevel().getLvlData());
    }

    public Player getPlayer(){
        return player;
    }

    public void setMaxLvlOffset(int lvlvOffset){
        this.maxLvlOffsetX = lvlvOffset;
    }

    /***
     * checks if the player is in any of the two scrolling borders. If so it updates the Offset
     */
    private void checkBorder() {
        int playerX = (int) player.getHitbox().x;
        int diff = playerX - xlvlOffset;

        if (diff > rightBorder)
            xlvlOffset += diff - rightBorder;
        else if(diff < leftBorder)
            xlvlOffset += diff - leftBorder;

        if (xlvlOffset > maxLvlOffsetX)
            xlvlOffset = maxLvlOffsetX;
        else if (xlvlOffset < 0)
            xlvlOffset = 0;
    }

    /***
     * updates the player and the level scrolling Border
     */
    @Override
    public void update() {
        player.update();
        checkBorder();
    }

    /***
     * Draws the Background, the player, the level and the clouds
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(bgImage,0,0, Game.screenWidth,Game.screenHeight, null);

        drawClouds(g);

        levelHandler.draw(g, xlvlOffset);
        player.draw(g, xlvlOffset);
    }

    private void drawClouds(Graphics g) {
        for (int i = 0; i<5;i++){
            g.drawImage(clouds, i * (360 * 2) - (int) (xlvlOffset*0.2),0, 360*2, 180*2,null);
        }

    }

}
