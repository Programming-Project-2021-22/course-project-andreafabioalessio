package entity;

import Main.Game;
import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilz.HelpMethods.CanMoveHere;

public class Player extends Entity {



    public Player(int x, int y, int speed, int jumpStrenght, int weight, Skin skin) {
        super(x, y, speed, jumpStrenght, weight, skin);
        this.xAcc = 0;
        this.yAcc = 1;
        this.direction = 1;
        this.xOffset = 4 * scale;
        createHitBox(x, y, 8 * scale, 16 * scale);
    }

@Override
    public void update() {
        checkGravity();
        setCentDirection();
        playerMovement();
        updateHitBox();
        x += xAcc;
        y += yAcc;
    }

    public void draw(Graphics g) {
        g.drawImage(getCurrentImage(), (int) (hitbox.x - xOffset), (int) (hitbox.y - yOffset), Game.tileSize, Game.tileSize, null);

        //TO SHOW THE PLAYER HITBOX:
        drawHitBox(g);
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    public void playerMovement() {

        if (KeyHandler.upPressed) {
           jump();
        } else if (KeyHandler.downPressed) {
            //ySpeed = entitySpeed;
            // yAcc = 1*weight;
        } else if (KeyHandler.leftPressed) {
            left();

        } else if (KeyHandler.rightPressed) {
            right();

        } else {
            still();
        }

        /*
        if (CanMoveHere(hitbox.x + entitySpeed, hitbox.y + yAcc, hitbox.width, hitbox.height, lvlData)) {
            x += xAcc * entitySpeed;
            y += yAcc;
        }
        */

    }


}
