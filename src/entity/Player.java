package entity;
import Main.Game;
import Main.GamePanel;
import Main.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilz.HelpMethods.CanMoveHere;

public class Player extends Entity {

    KeyHandler movement = new KeyHandler();
    private Game game;
    public int centDirection = 1;
    private int [][] lvlData;
    private boolean moving = false;

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
        g.drawImage(getCurrentImage(), getX(), getY(), Game.tileSize, Game.tileSize , null);
        drawHitBox(g);
    }

    public void updatePosition(){
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
        if (direction == 1){
            currentImage = entitySkin.center(1);
            //System.out.println("destra");
        }else{
            currentImage = entitySkin.center(-1);
            //System.out.println("sinistra");
        }
    }

    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
    }

    public void movement() {

        float xSpeed = 0, ySpeed = 0;

            if (KeyHandler.upPressed) {
                setJumping(true);
                yAcc = 1;
                ySpeed = -entitySpeed;
                jump();
                if (direction == 1){
                    currentImage = entitySkin.jump(1);
                }else{
                    currentImage = entitySkin.jump(-1);
                }
            } else if (KeyHandler.downPressed) {
                ySpeed = entitySpeed;
               // yAcc = 1*weight;
            } else if (KeyHandler.leftPressed) {
                xAcc = -1;
                xSpeed = -entitySpeed;
                direction = -1;
                if (jumping || falling){
                    currentImage = entitySkin.jump(-1);
                }else{
                    currentImage = entitySkin.leftAnimation();
                }

            } else if (KeyHandler.rightPressed) {
                xAcc = 1;
                direction = 1;
                xSpeed = entitySpeed;
                if (jumping || falling){
                    currentImage = entitySkin.jump(1);
                }else{
                    currentImage = entitySkin.rightAnimation();
                }
            }
            else{
                xAcc = 0;

            }

        if (CanMoveHere(x + xSpeed, y + ySpeed, Game.tileSize, Game.tileSize, lvlData)){
            x += xAcc*entitySpeed;
            y += yAcc;
        }

    }




    public int getSpeed(){
        return entitySpeed;
    }
}
