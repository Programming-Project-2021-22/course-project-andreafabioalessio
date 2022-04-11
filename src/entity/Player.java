package entity;
import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity {

    KeyHandler movement = new KeyHandler();
    public int centDirection = 1;

    public Player(GamePanel gp, int x, int y, int speed, int jumpStrenght, int weight, Skin skin) {
        super(gp, x, y, speed, jumpStrenght, weight, skin);
        this.gp = gp;
        this.xAcc = 0;
        this.yAcc = 1;
        this.direction = 1;
    }

    public void update(){
        updatePosition();
        updateHitBox();
    }

    public void draw(Graphics2D g2){
        g2.drawImage(getCurrentImage(), getX(), getY(), gp.tileSize, gp.tileSize , null);
        drawHitBox(g2);
    }

    public void updatePosition(){

        x += xAcc*entitySpeed;
        y += yAcc;

        checkGravity();

        setCentDirection();

        movement();

    }

    public void checkGravity(){
        if (jumping || falling){
            yAcc += weight;
            System.out.println("sto cadendo");
        }else{
            yAcc = 0;
        }
    }

    public void setCentDirection(){
        if (centDirection == 1){
            currentImage = entitySkin.center(1);
            //System.out.println("destra");
        }else{
            currentImage = entitySkin.center(-1);
            //System.out.println("sinistra");
        }

    }

    public void movement() {

            if (KeyHandler.upPressed) {

                setJumping(true);
                yAcc = 1;
                jump();
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

                if (jumping || falling){
                    currentImage = entitySkin.jump(-1);
                }else{
                    currentImage = entitySkin.leftAnimation();
                }

                //System.out.println("left");

            } else if (KeyHandler.rightPressed) {

                centDirection = 1;
                xAcc = 1;
                direction = 1;

                if (jumping || falling){
                    currentImage = entitySkin.jump(1);
                }else{
                    currentImage = entitySkin.rightAnimation();
                }

                //System.out.println("right");

            }
            else{
                xAcc = 0;

            }
    }



    public int getSpeed(){
        return entitySpeed;
    }
}
