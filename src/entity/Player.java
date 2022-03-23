package entity;
import Main.KeyHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity {

    KeyHandler movement = new KeyHandler();

    public Player(int x, int y, int speed, int jumpStrenght, int weight, Skin skin) {
        super(x, y, speed, jumpStrenght, weight, skin);
        this.xAcc = 0;
        this.yAcc = 0;
        this.direction = 1;
    }

    public void update(){

        //currentImage = entitySkin.animate();

        x += xAcc*entitySpeed;
        y += yAcc;
        currentImage = entitySkin.center(1);
        movement();

    }

    public void movement() {


            if (KeyHandler.upPressed) {

                yAcc = -1;

            } else if (KeyHandler.downPressed) {

                yAcc = 1*weight;

            } else if (KeyHandler.leftPressed) {

                xAcc = -1;
                direction = -1;
                System.out.println("left");
                currentImage = entitySkin.leftAnimation();

            } else if (KeyHandler.rightPressed) {

                xAcc = 1;
                direction = 1;
                System.out.println("right");
                currentImage = entitySkin.rightAnimation();

            }
            else{
                xAcc = 0;
                yAcc = 0;
                entitySkin.center(1);
            }

    }
}
