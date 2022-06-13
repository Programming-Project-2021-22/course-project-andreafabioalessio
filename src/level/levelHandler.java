package level;

import Main.Game;
import gamestates.Gamestate;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static gamestates.Menu.numLevel;

/*

CLASS SUMMARY

class status: idk
 */

public class levelHandler {

    private Game game;
    private BufferedImage[] levelSprite;
    private ArrayList<Level> levels;
    private  int lvlIndex = 0;

    public levelHandler(Game game){
        this.game = game;
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
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 12; i++) {
                int index = j * 4 + i;
                levelSprite[index] = img.getSubimage(i * 16, j * 16, 16, 16);
            }
    }

    public void draw(Graphics g, int lvlOffset) {
        for (int j = 0; j < Game.maxScreenRow; j++)
            for (int i = 0; i < levels.get(lvlIndex).getLvlData()[0].length; i++) {
                int index = levels.get(lvlIndex).getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], i*Game.tileSize - lvlOffset, j*Game.tileSize, Game.tileSize, Game.tileSize, null);
            }
    }

    public void update() {

    }

    public Level getLevel(){
        return levels.get(lvlIndex);
    }

    public int getAmountOfLevels(){
        return levels.size();
    }

    public void loadNextLevel() {

        lvlIndex ++;
        if (lvlIndex >= levels.size()){
            lvlIndex = 0;
            System.out.println("No more levels");
            Gamestate.state = Gamestate.MAINMENU;
        }

        Level newLevel = levels.get(numLevel);
        game.getPlaying();
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLvlData());
        game.getPlaying().setMaxLvlOffset(newLevel.getLvlOffset());
    }
}
