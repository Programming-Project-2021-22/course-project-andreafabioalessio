package level;

import Main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class levelHandler {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public levelHandler(Game game){
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[16];
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 4; i++) {
                int index = j * 4 + i;
                levelSprite[index] = img.getSubimage(i * Game.tileSize, j * Game.tileSize, Game.tileSize, Game.tileSize);
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
