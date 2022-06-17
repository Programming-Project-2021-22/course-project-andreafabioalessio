import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;




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


/***
 * Abstract class for all types of entities
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

    public static boolean win = false;

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

    /***
     * Plays a specific audio file
     * @param x index of the sound that wants to be played. Refers to the array in the Sound class
     */
    public void playSFX(int x){
        sound.setFile(x);
        sound.play();
    }
    public void stopMusic(){
        sound.stop();
    }

    //UPDATE METHODS
    public abstract void update();

    /***
     * Draws the Player
     * @param g: Graphics engine
     * @param lvlOffset: Current leveloffset for the scrolling level
     */
    public void draw(Graphics g, int lvlOffset) {
        g.drawImage(getCurrentImage(), (int) (hitbox.x - xOffset) - lvlOffset, (int) (hitbox.y - yOffset), tileSize, tileSize, null);

        //TESTING: draw the entity hitbox
        //drawHitBox(g);
    }

    /***
     * Loads the Data of the current level
     * @param lvlData 2D array of level data
     */
    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    //MOVEMENT METHODS

    /***
     * sets the x acceleration to 0
     */
    public void still() {xAcc = 0;}

    /***
     * sets the direction of the player and updates the x Acceleration
     */
    public void left(){
        direction = -1;
        xAcc = -entitySpeed;
    }

    /***
     * sets the direction of the player and updates the x Acceleration
     */
    public void right() {
        direction = 1;
        xAcc = entitySpeed;
    }

    /***
     * Plays jump sound and updates the y Acceleration
     */
    public void jump() {
        if(!jumping) {
            playSFX(2);
            yAcc -= jumpStrength;
            setJumping(true);
        }
    }

    /***
     * Checks for a collision on the horizontal axis
     */
    public void checkHorizontalCollision(){
        //If xAcc = 0, no collision is possible, so no check is needed
        if(xAcc != 0){
            //general collision check
            if(!checkHitboxCollision( xAcc, 0 )) {
                xAcc = 0;
            }
        }
    }

    /***
     * Checks for collision on top of the player
     */
    public void checkTopCollision(){
        //if yAcc is not <0 the player is not going up, so no check is necessary
        if(yAcc < 0) {
            if (!checkHitboxCollision(0, yAcc)) {
                yAcc = weight;
                falling = true;
                jumping = true;
            }
        }
    }

    /***
     * Checks if there is a solid block underneath the player.
     */
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

    /***
     * If the intended movement expressed in x and y cause a collision with a block, return false. Else: return true.
     * @param x: x coordinate
     * @param y: y coordinate
     * @return return true if at that coordinate there no block.
     */
    public boolean checkHitboxCollision(int x, int y){
        return (HelpMethods.CanMoveHere(hitbox.x +x, hitbox.y + y, hitbox.width, hitbox.height, lvlData));
    }

    /***
     * teleports the player to the beginning of the level
     */
    public void teleportToBeginning(){
        win = false;
        x = 200;
        y = 300;
        xAcc = 0;
        yAcc = 0;
    }

    /***
     * checks if the player has come to the end of the level. If so it plays a winning song,
     * teleports the player to the beginning and changes GameState
     */
    public void checkWin(){
        //14240
        if(x>=14084){
            playSFX(1);

            //User user = Menu.getUser();
            try {
                Menu.updateUserAfterWin();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            try {
//                Menu.updateUserLevelInArray(user);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            teleportToBeginning();
            KeyHandler.resetKeyHandler();
            Gamestate.state = Gamestate.MAINMENU;
            //testprint
            //System.out.println("LEVEL WON");
        }
    }

    /***
     * Sets the correct sprite based on the direction of the player
     */
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

    /***
     * sets the correct jumping sprite based on the direction of the player
     */
    public void skinAnimation(){
        if(xAcc != 0 || falling || jumping) {
            //Right movement
            if (direction == 1) {
                if (jumping || falling) {
                    currentImage = entitySkin.jump(1);
                } else {
                    currentImage = entitySkin.rightAnimation();
                }
            } else { //Left movement
                if (jumping || falling) {
                    currentImage = entitySkin.jump(-1);
                } else {
                    currentImage = entitySkin.leftAnimation();
                }
            }
        }
    }

    //HITBOX METHODS

    /***
     * Draws the player hitbox
     * @param g: Graphic engine
     */
    protected void drawHitBox(Graphics g) {
        g.setColor(Color.black);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    /***
     * Creates the player hitbox
     * @param x: coordinate
     * @param y: coordinate
     * @param width: width of the hitbox
     * @param height: height of the hitbox
     */
    protected void createHitBox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    /***
     * Updates the hitbox position
     */
    protected void updateHitBox() {
        hitbox.x = x;
        hitbox.y = y;
    }

    /***
     * Prints variables on the console for testing
     */
    public void testprintVariables(){
        //teleportToBeginning();
        System.out.println("falling " + falling + "\njumping " + jumping + "\nyAcc " + yAcc + "\nxAcc " + xAcc + "\nx " + x + "\ny " + y + "\ncollisions: t "+ !checkHitboxCollision(0, yAcc+weight)  + "\n\n");
    }


    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -//

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