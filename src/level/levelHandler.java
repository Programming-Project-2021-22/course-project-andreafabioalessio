package level;

import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class levelHandler {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public levelHandler(Game game){
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LevelLoad.GetLevelData());
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

    public void draw(Graphics g) {
        for (int j = 0; j < Game.screenHeight; j++)
            for (int i = 0; i < Game.screenWidth; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], i*Game.tileSize, j*Game.tileSize, Game.tileSize, Game.tileSize, null);
            }
    }

    public void update() {

    }

}
