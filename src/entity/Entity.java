package entity;

import Main.Game;
import Main.GamePanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static utilz.HelpMethods.CanMoveHere;


/*
CLASS SUMMARY

It's the parent class for every moving entity, including: the player, hostile entities and NPCs, etc...

- UPDATE: it must update everything

- MOVEMENT: It must handle the position of the entity [x,y], 
            the Acceleration of the entity[xAcc,yAcc] meaning how much [x,y] are increasing.
            the [x] movement is influenced by [entitySpeed]
            the [y] movement is influenced by [weight]
            the jump is influenced by [jumpStrength]
            it must handle the death of the entity


- SKIN:     it must handle the skin of the entity[entitySkin]
            the movement influences the direction [direction] expressed as an int => -1 = sx; 0= center; 1=dx
            based on the direction, the image shown [currentImage] must change.


- Hitbox:   it must handle the hitbox of the entity [hitbox].
            The left-right collision works in a way that if the left() or right() methods are called and there is a left / right
            collision, the method does not increase [xAcc] / [yAcc]



class status: collision and gravity need to be fixed
*/



public abstract class Entity {

    protected BufferedImage currentImage;
    protected int x, y, xAcc, yAcc;
    protected Skin entitySkin;
    public int entitySpeed;
    protected int weight;
    protected int jumpStrength;

    //old
    protected boolean falling = false;
    protected boolean jumping = false;


    protected Rectangle2D.Float hitbox;
    public int[][] lvlData;
    public int scale = 3;
    public int tileSize = 16 * scale;
    public float xOffset;
    public float yOffset;
    protected int direction; //WARNING:direction should be used only to choose which image will be shown, 
                             //        not to establish physics => -1 = sx; 0= center; 1=dx


    //Constructor
    public Entity(int x, int y, int speed, int jumpStrength, int weight, Skin skin) {

        this.x = x;
        this.y = y;
        this.entitySpeed = speed;
        this.xAcc = 0;
        this.yAcc = 0;
        this.direction = 0;
        this.jumpStrength = jumpStrength;
        this.weight = weight;
        this.entitySkin = skin;
        this.xOffset = 4 * scale;
        this.yOffset = 0;

    }

    //UPDATE METHODS
    public void update(){
        checkGravity();
        setCentDirection();

        updateHitBox();
        x += xAcc;
        y += yAcc;
    }

    public void draw(Graphics g) {
        g.drawImage(getCurrentImage(), (int) (hitbox.x - xOffset), (int) (hitbox.y - yOffset), tileSize, tileSize, null);

        //TESTING: draw the entity hitbox
        drawHitBox(g);
    }



    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }



    //MOVEMENT METHODS

    public void still() {xAcc = 0;}


    public void left(){
        direction = -1;

        if(checkHitboxCollision( x-entitySpeed, y )) {
            xAcc = -entitySpeed;

            //animation based on jumping
            if (jumping || falling) {
                currentImage = entitySkin.jump(-1);
            } else {
                currentImage = entitySkin.leftAnimation();
            }
        }else {
            xAcc = 0;

            //testprint
            System.out.println("left collision");
        }
    }


    public void right() {
        direction = 1;

        if(checkHitboxCollision(x +  entitySpeed, y )) {
            xAcc = entitySpeed;

            //animation based on jumping
            if (jumping || falling) {
                currentImage = entitySkin.jump(1);
            } else {
                currentImage = entitySkin.rightAnimation();
            }
        }else {
            xAcc = 0;

            //testprint
            System.out.println("right collision");
        }

    }

    public void jump() {
        yAcc -= jumpStrength;

         setJumping(true);
            yAcc = 1;
            if (direction == 1) {
                currentImage = entitySkin.jump(1);
            } else {
                currentImage = entitySkin.jump(-1);
            }

    }

    //TO DO
    public void die() {
    }

    public void checkGravity() {
        yAcc -= weight;
        if (jumping || falling) {
            yAcc += weight;

            if(yAcc > 10){yAcc = 10;}
            //System.out.println("sto cadendo");
        } else {
            yAcc = 0;
        }
    }

    //If the intended movement expressed in x and y cause a collision with a block, return false. Else: return true.
    //TO FIX
    public boolean checkHitboxCollision(int x, int y){

        //return (CanMoveHere(x, y, hitbox.x - xOffset, hitbox.height - yOffset, lvlData));

        //test
        return (CanMoveHere(x, y, hitbox.x, hitbox.height, lvlData));

    }



    //SKIN METHODS

    public void setCentDirection() {
        if (direction == 1) {
            currentImage = entitySkin.center(1);
            //test print
            //System.out.println("destra");
        } else {
            currentImage = entitySkin.center(-1);
            //test print
            //System.out.println("sinistra");
        }
    }



    //HITBOX METHODS

    //method used while testing to show the player hitbox: show the hitbox as a rectangle
    protected void drawHitBox(Graphics g) {
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


    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    //Getters and Setters

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }
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


    public float getWeight() {
        return weight;
    }

    public int getJumpStrength() {
        return jumpStrength;
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

    public void setJumpStrength(int newJumpStrength) {
        jumpStrength = newJumpStrength;
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


    

}