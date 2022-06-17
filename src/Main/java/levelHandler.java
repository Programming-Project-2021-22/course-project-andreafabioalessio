import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*

CLASS SUMMARY

class status: idk
 */

/***
 * Creates the level
 */
public class levelHandler {

    private static Game game;
    private BufferedImage[] levelSprite;
    private static ArrayList<Level> levels;
    private static int lvlIndex = 0;

    public levelHandler(Game game){
        levelHandler.game = game;
        importOutsideSprites();
        levels = new ArrayList<>();
        buildAllLevels();
    }

    /***
     * Builds all the levels in advance
     */
    private void buildAllLevels() {
        BufferedImage[] allLevels = LevelLoad.getAllLevels();
        for (BufferedImage img: allLevels)
            levels.add(new Level(img));
    }

    /***
     * Imports the Level Sprites
     */
    private void importOutsideSprites() {
        BufferedImage img = LevelLoad.GetSpriteAtlas(LevelLoad.LEVEL_ATLAS);
        levelSprite = new BufferedImage[18];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 6; j++) {
                int index = i * 3 + j;
                levelSprite[index] = img.getSubimage(j * 16, i * 16, 16, 16);
            }
    }

    /***
     * Draws the level
     * @param g Graphics engine
     * @param lvlOffset level offset. Is needed for scrolling level
     */
    public void draw(Graphics g, int lvlOffset) {
        for (int j = 0; j < Game.maxScreenRow; j++)
            for (int i = 0; i < levels.get(Menu.getNumLevel()-1).getLvlData()[0].length; i++) {
                int index = levels.get(Menu.getNumLevel()-1).getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], i*Game.tileSize - lvlOffset, j*Game.tileSize, Game.tileSize, Game.tileSize, null);
            }
    }

    public void update() {

    }

    public Level getLevel(){
        return levels.get(Menu.getNumLevel()-1);
    }

    public int getAmountOfLevels(){
        return levels.size();
    }


    /***
     * loads the next level based on the value of the Numlevel in the Menu class
     */
    public static void loadNextLevel() {

        Level newLevel = levels.get(Menu.getNumLevel()-1);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLvlData());
        game.getPlaying().setMaxLvlOffset(newLevel.getLvlOffset());
    }
}
