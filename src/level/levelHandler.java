package level;

import Main.Game;
import gamestates.Gamestate;
import gamestates.Menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Menu;

/*

CLASS SUMMARY

class status: idk
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

    private void buildAllLevels() {
        BufferedImage[] allLevels = LevelLoad.getAllLevels();
        for (BufferedImage img: allLevels)
            levels.add(new Level(img));
    }

    private void importOutsideSprites() {
        BufferedImage img = LevelLoad.GetSpriteAtlas(LevelLoad.LEVEL_ATLAS);
        levelSprite = new BufferedImage[18];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 6; j++) {
                int index = i * 3 + j;
                levelSprite[index] = img.getSubimage(j * 16, i * 16, 16, 16);
            }
    }

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


    //To Load next level after the level is complete
    public static void loadNextLevel() {

        //Levlindex = current level index
        lvlIndex++;
        if (lvlIndex > Menu.getNumLevel()-1){
            lvlIndex = Menu.getNumLevel() -1;
        }

        Level newLevel = levels.get(lvlIndex);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLvlData());
        game.getPlaying().setMaxLvlOffset(newLevel.getLvlOffset());
    }
}
