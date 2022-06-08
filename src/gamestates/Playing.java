package gamestates;

import Main.Game;
import entity.Player;
import entity.PlayerSkin;
import level.levelHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Playing extends State implements StateMethods{
    private Player player;
    private level.levelHandler levelHandler;

    public Playing(Game game) {
        super(game);
        getClasses();
    }

    private void getClasses() {
        levelHandler = new levelHandler(game);
        PlayerSkin skin = new PlayerSkin();
        player = new Player(100,300,7,25,2,skin);
        player.loadLvlData(levelHandler.getLevel().getLvlData());
    }

    public Player getPlayer(){
        return player;
    }

    @Override
    public void update() {
        levelHandler.update();
        player.update();
    }

    @Override
    public void draw(Graphics g) {
        levelHandler.draw(g);
        player.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
