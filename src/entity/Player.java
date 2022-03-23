package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    KeyHandler movement = new KeyHandler();

    public Player(int x, int y, int speed, int jumpStrenght, int weight, Skin skin) {
        super(x, y, speed, jumpStrenght, weight, skin);
        this.xAcc = 0;
        this.yAcc = 0;
        this.direction = 1;
    }

    public void update(){

        currenImage = entitySkin.movement;


        movement();
        x += xAcc*entitySpeed;
        y += yAcc*weight;

    }

    public void movement() {
        if (movement.getLeft() || movement.getRight() || movement.getUp() || movement.getDown()) {

            if (movement.getUp()) {

                yAcc = -1;

            } else if (movement.getDown()) {

                yAcc = 1;

            } else if (movement.getLeft()) {

                xAcc = -1;

            } else if (movement.getRight()) {

                xAcc = 1;

            }
        }
    }
}
