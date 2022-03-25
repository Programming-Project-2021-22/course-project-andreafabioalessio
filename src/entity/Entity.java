package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

// parent class => Whatever entity moves
//Qui si instanziano i diversi utenti, npc, ecc
public class Entity {

    protected BufferedImage currentImage;
    protected int x, y, xAcc, yAcc;
    protected Skin entitySkin;
    protected int entitySpeed;
    protected int weight;
    protected int jumpStrenght;
    protected boolean falling = true;
    protected boolean jumping = false;
    protected Rectangle hitbox;
    protected int direction; //direction should be used only to choose which image will be shown, not to establish physics => -1 = sx; 0= center; 1=dx


    //Constructor
    public Entity(int x, int y,int speed, int jumpStrenght, int weight, Skin skin) {

        this.x = x;
        this.y = y;
        this.entitySpeed = speed;
        this.xAcc = 0;
        this.yAcc = 0;
        this.direction = 0;
        this.jumpStrenght = jumpStrenght;
        this.weight = weight;
        this.entitySkin = skin;
        createHitBox();

    }

    protected void drawHitBox(Graphics2D g2){
        // For debugging purposes
        g2.setColor(Color.black);
        g2.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

    }

    private void createHitBox() {

        hitbox = new Rectangle(x,y,48,48);

    }

    protected void updateHitBox(){
        hitbox.x = x;
        hitbox.y = y;
    }

    public Rectangle getHitbox(){
        return hitbox;
    }


    //Getters
    public boolean getFalling(){return falling;}
    public boolean getJumping(){return jumping;}
    public int getX(){return x;}
    public int getY(){return y;}
    public int getXAcc(){return xAcc;}
    public int getYAcc(){return yAcc;}
    public int getEntitySpeed(){return entitySpeed;};
    public float getWeight(){return weight;}
    public int getJumpStrenght(){return jumpStrenght;}
    public int getDirection(){return direction;}
    public Skin getSkin(){return entitySkin;}
    public BufferedImage getCurrentImage(){return currentImage;}
    //Setters
    public void setFalling(boolean isfalling){falling = isfalling;}
    public void setJumping(boolean isjumping){jumping = isjumping;}
    public void setX(int newX){ x = newX;}
    public void setY(int newY){ y = newY;}
    public void setXAcc(int newXAcc){ xAcc = newXAcc;}
    public void setYAcc(int newYAcc){ yAcc = newYAcc;}
    public void setEntitySpeed(int newEntitySpeed){ entitySpeed = newEntitySpeed;}
    public void setWeight(int newWeight){ weight = newWeight;}
    public void setJumpStrenght(int newJumpStrenght){ jumpStrenght = newJumpStrenght;}
    public void setDirection(int newDirection){ direction = newDirection;}
    public void setSkin(Skin newSkin){ entitySkin = newSkin;}
    public void setCurrenImage(BufferedImage newImage){currentImage = newImage;}



    //MOVEMENT METHODS




    public void right(){
         direction = 1;
    }
    public void stop(){
        direction = 0;
    }
    public void jump(){

        yAcc = -jumpStrenght;
        jumpStrenght -= weight;

    }
    public void die(){}

}