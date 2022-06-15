package entity;

import Main.Game;
import Main.GamePanel;
import Main.Sound;

import Main.KeyHandler;
import gamestates.Gamestate;
import gamestates.Playing;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import static utilz.HelpMethods.CanMoveHere;

import Main.KeyHandler;


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

    public Sound sound = new Sound();

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

    public void playSFX(int x){
        sound.setFile(x);
        sound.play();
    }
    public void stopMusic(){
        sound.stop();
    }

    //UPDATE METHODS
    public abstract void update();

    public void draw(Graphics g, int lvlOffset) {
        g.drawImage(getCurrentImage(), (int) (hitbox.x - xOffset) - lvlOffset, (int) (hitbox.y - yOffset), tileSize, tileSize, null);

        //TESTING: draw the entity hitbox
        //drawHitBox(g);
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    //MOVEMENT METHODS
    public void still() {xAcc = 0;}


    public void left(){
        direction = -1;
        xAcc = -entitySpeed;
    }


    public void right() {
        direction = 1;
        xAcc = entitySpeed;
    }

    public void jump() {

        if(!jumping) {
            playSFX(2);
            yAcc -= jumpStrength;
            setJumping(true);
        }
    }

    //should work
    //if [xAcc] causes a collision set [xAcc] to 0.
    public void checkHorizontalCollision(){
        //If xAcc = 0, no collision is possible, so no check is needed
        if(xAcc != 0){
            //general collision check
            if(!checkHitboxCollision( xAcc, 0 )) {
                xAcc = 0;
                //testprint
                //System.out.println("horizontal collision");
            }
        }
    }


    //if [yAcc] causes a top collision, set [yAcc] to the weight.
    public void checkTopCollision(){
        //if yAcc is not <0 the player is not going up, so no check is necessary
        if(yAcc < 0) {
            if (!checkHitboxCollision(0, yAcc)) {
                yAcc = weight;
                falling = true;
                jumping = true;
                //testprint
                //System.out.println("top collision");
            }
        }
    }
    
    public void checkGravity() {
        try {
            //NO FLOOR COLLISION
            if (checkHitboxCollision(0, yAcc + weight)) {
                yAcc += weight;
                falling = true;
                jumping = true;
                //testprint
                //System.out.println("floor collision");


            } else { //FLOOR COLLISION
                yAcc = 0;
                jumping = false;
                falling = false;
                //testprint
                //System.out.println("floor collision");
            }


        } catch(ArrayIndexOutOfBoundsException e){
            teleportToBeginning();
            //testprint
            System.out.println("array out of bound bug");
        } catch(Exception e){
            teleportToBeginning();
            //testprint
            System.out.println("not array out of bound bug");
        }
    }

    //If the intended movement expressed in x and y cause a collision with a block, return false. Else: return true.
    public boolean checkHitboxCollision(int x, int y){
        return (CanMoveHere(hitbox.x +x, hitbox.y + y, hitbox.width, hitbox.height, lvlData));
    }

    //DIE
    public void teleportToBeginning(){
        x = 200;
        y = 300;
        xAcc = 0;
        yAcc = 0;
    }

    //WIN
    public void checkWin(){
        //14240
        if(x>=14084){
            playSFX(1);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            teleportToBeginning();
            KeyHandler.resetKeyHandler();
            Gamestate.state = Gamestate.MAINMENU;
            //testprint
            System.out.println("LEVEL WON");
        }
    }


    //SKIN METHODS
    public void setCentDirection() {
        if (direction == 1) {
            currentImage = entitySkin.center(1);
            //test print
            //System.out.println("right skin");
        } else {
            currentImage = entitySkin.center(-1);
            //test print
            //System.out.println("left skin");
        }
    }

    public void skinAnimation(){
        if(xAcc != 0 || falling || jumping) {
            //Right movement
            if (direction == 1) {
                if (jumping || falling) {
                    currentImage = entitySkin.jump(1);
                    //testprint
                    //System.out.println("right jump skin");
                } else {
                    currentImage = entitySkin.rightAnimation();
                    //testprint
                    //System.out.println("right animation");
                }
            } else { //Left movement
                if (jumping || falling) {
                    currentImage = entitySkin.jump(-1);
                    //testprint
                    //System.out.println("left jump skin");
                } else {
                    currentImage = entitySkin.leftAnimation();
                    //testprint
                    //System.out.println("left animation");
                }
            }
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

    //TESTING METHODS
    public void testprintVariables(){
        //teleportToBeginning();
        System.out.println("falling " + falling + "\njumping " + jumping + "\nyAcc " + yAcc + "\nxAcc " + xAcc + "\nx " + x + "\ny " + y + "\ncollisions: t "+ !checkHitboxCollision(0, yAcc+weight)  + "\n\n");
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