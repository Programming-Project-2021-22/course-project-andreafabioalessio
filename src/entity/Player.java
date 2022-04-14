package entity;
import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity {

    KeyHandler movement = new KeyHandler();
    public int centDirection = 1;

    public Player(int x, int y, int speed, int jumpStrenght, int weight, Skin skin) {
        super(x, y, speed, jumpStrenght, weight, skin);
        this.xAcc = 0;
        this.yAcc = 1;
        this.direction = 1;
    }

    public void update(){
        updatePosition();
        updateHitBox();
    }

    public void draw(Graphics g){
        g.drawImage(getCurrentImage(), getX(), getY(), 48, 48 , null);
        drawHitBox(g);
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
