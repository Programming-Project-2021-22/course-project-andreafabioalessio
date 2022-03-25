package entity;
import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity {

    KeyHandler movement = new KeyHandler();
    public int centDirection = 1;
    GamePanel gp;

    public Player(GamePanel gp, int x, int y, int speed, int jumpStrenght, int weight, Skin skin) {
        super(x, y, speed, jumpStrenght, weight, skin);
        this.gp = gp;
        this.xAcc = 0;
        this.yAcc = 1;
        this.direction = 1;
    }

    public void update(){
        updatePosition();
        updateHitBox();
        movement();
    }

    public void draw(Graphics2D g2){
        g2.drawImage(getCurrentImage(), getX(), getY(), gp.tileSize, gp.tileSize , null);
        drawHitBox(g2);
    }

    public void updatePosition(){
        x += xAcc*entitySpeed;
        y += yAcc;
        if (jumping || falling){
            yAcc += weight;
            System.out.println("sto cadendo");
        }
    }

    public void movement() {

        if (centDirection == 1){
            currentImage = entitySkin.center(1);
            //System.out.println("destra");
        }else{
            currentImage = entitySkin.center(-1);
            //System.out.println("sinistra");
        }

            if (KeyHandler.upPressed) {
                //setJumping(true);
                //jump();
                System.out.println("salto");
                if (centDirection == 1){
                    currentImage = entitySkin.jump(1);
                }else{
                    currentImage = entitySkin.jump(-1);
                }
            } else if (KeyHandler.downPressed) {
                yAcc = 1*weight;
            } else if (KeyHandler.leftPressed) {
                centDirection = -1;
                xAcc = -1;
                direction = -1;
                //System.out.println("left");
                currentImage = entitySkin.leftAnimation();
            } else if (KeyHandler.rightPressed) {
                centDirection = 1;
                xAcc = 1;
                direction = 1;
                //System.out.println("right");
                currentImage = entitySkin.rightAnimation();
            }
            else{
                xAcc = 0;
                //yAcc = 0;
            }
    }



    public int getSpeed(){
        return entitySpeed;
    }
}
