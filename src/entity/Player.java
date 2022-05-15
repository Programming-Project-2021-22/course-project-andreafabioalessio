package entity;

import Main.KeyHandler;


/*

CLASS SUMMARY

The player class extends the entity class.
    -   It must handle the movement of the player by linking the keyhandler to the entity movement methods
    -   It must override the update() entity method.

    - TO DO: It must handle the player death in order to restart the game.


class status: currently ok, but for the player death
 */


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

    }


}
