package entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

// parent class => Whatever entity moves
//Qui si instanziano i diversi utenti, npc, ecc
public abstract class Entity {

    protected GamePanel gp;
    protected BufferedImage currentImage;
    protected int x, y, xAcc, yAcc;
    protected Skin entitySkin;
    public int entitySpeed;
    protected int weight;
    protected int jumpStrenght;
    protected boolean falling = false;
    protected boolean jumping = false;
    protected Rectangle2D.Float hitbox;
    protected int direction; //direction should be used only to choose which image will be shown, not to establish physics => -1 = sx; 0= center; 1=dx


    //Constructor
    public Entity(int x, int y, int speed, int jumpStrenght, int weight, Skin skin) {

        this.x = x;
        this.y = y;
        this.entitySpeed = speed;
        this.xAcc = 0;
        this.yAcc = 0;
        this.direction = 0;
        this.jumpStrenght = jumpStrenght;
        this.weight = weight;
        this.entitySkin = skin;

    }

    protected void drawHitBox(Graphics g) {
        // For debugging purposes
        g.setColor(Color.black);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);

    }

    protected void createHitBox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    protected void updateHitBox() {
        hitbox.x = x;
        hitbox.y = y;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }


    //Getters
    public boolean getFalling() {
        return falling;
    }

    public boolean getJumping() {
        return jumping;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXAcc() {
        return xAcc;
    }

    public int getYAcc() {
        return yAcc;
    }

    public int getEntitySpeed() {
        return entitySpeed;
    }

    ;

    public float getWeight() {
        return weight;
    }

    public int getJumpStrenght() {
        return jumpStrenght;
    }

    public int getDirection() {
        return direction;
    }

    public Skin getSkin() {
        return entitySkin;
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    //Setters
    public void setFalling(boolean isfalling) {
        falling = isfalling;
    }

    public void setJumping(boolean isjumping) {
        jumping = isjumping;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public void setXAcc(int newXAcc) {
        xAcc = newXAcc;
    }

    public void setYAcc(int newYAcc) {
        yAcc = newYAcc;
    }

    public void setEntitySpeed(int newEntitySpeed) {
        entitySpeed = newEntitySpeed;
    }

    public void setWeight(int newWeight) {
        weight = newWeight;
    }

    public void setJumpStrenght(int newJumpStrenght) {
        jumpStrenght = newJumpStrenght;
    }

    public void setDirection(int newDirection) {
        direction = newDirection;
    }

    public void setSkin(Skin newSkin) {
        entitySkin = newSkin;
    }

    public void setCurrenImage(BufferedImage newImage) {
        currentImage = newImage;
    }


    //MOVEMENT METHODS


    public void right() {
        direction = 1;
    }

    public void stop() {
        direction = 0;
    }

    public void jump() {
        yAcc -= jumpStrenght;

    }

    public void die() {
    }

}