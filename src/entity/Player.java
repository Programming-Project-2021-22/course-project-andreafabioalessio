package entity;
import Main.KeyHandler;

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

        //currentImage = entitySkin.animate();

        x += xAcc*entitySpeed;
        y += yAcc;

        if (jumping || falling){
            yAcc += weight;
            System.out.println("sto cadendo");
        }

        if (centDirection == 1){
            currentImage = entitySkin.center(1);
            //System.out.println("destra");
        }else{
            currentImage = entitySkin.center(-1);
            //System.out.println("sinistra");
        }

        movement();

    }

    public void movement() {


            if (KeyHandler.upPressed) {


                //setJumping(true);
                //jump();

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
