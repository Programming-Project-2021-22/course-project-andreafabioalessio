package gamestates;

import Main.Game;
import entity.Player;
import entity.PlayerSkin;
import level.LevelLoad;
import level.levelHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Playing extends State implements StateMethods{
    private Player player;
    private level.levelHandler levelHandler;

    private int xlvlOffset;
    private int leftBorder = (int) (0.2 * Game.screenWidth);
    private int rightBorder = (int) (0.8 * Game.screenWidth);
    private int maxLvlOffsetX;

    private boolean lvlCompleted = false;

    public Playing(Game game) {
        super(game);
        getClasses();
        
        calcLvlOffset();

    }

    public void loadNextLevel(){
        levelHandler.loadNextLevel();
    }

    private void calcLvlOffset() {
        maxLvlOffsetX = levelHandler.getLevel().getLvlOffset();
    }

    private void getClasses() {
        levelHandler = new levelHandler(game);
        PlayerSkin skin = new PlayerSkin();
        player = new Player(100,300,7,20,1,skin);
        player.loadLvlData(levelHandler.getLevel().getLvlData());
    }

    public Player getPlayer(){
        return player;
    }

    public void setMaxLvlOffset(int lvlvOffset){
        this.maxLvlOffsetX = lvlvOffset;
    }

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
    @Override
    public void update() {

        levelHandler.update();
        player.update();
        checkBorder();
    }

    @Override
    public void draw(Graphics g) {
        levelHandler.draw(g, xlvlOffset);
        player.draw(g, xlvlOffset);
    }

}
