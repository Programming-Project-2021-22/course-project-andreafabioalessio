/*

CLASS SUMMARY

The player class extends the entity class.
    -   It must handle the movement of the player by linking the keyhandler to the entity movement methods
    -   It must override the update() entity method.

    - TO DO: It must handle the player death in order to restart the game.


class status: currently ok, but for the player death
 */

/***
 * Creates the Player and implements all the entity methods
 */
public class Player extends Entity {

    public Player(int x, int y, int speed, int jumpStrenght, int weight, Skin skin) {
        super(x, y, speed, jumpStrenght, weight, skin);
        this.xAcc = 0;
        this.yAcc = 1;
        this.direction = 1;
        this.xOffset = 4 * scale;
        this.yOffset = 0;
        createHitBox(x, y, 8 * scale, 16 * scale);
    }

    /***
     * The update must execute in this order:
     *         -1 get left/right/up keys and update xAcc and yAcc
     *         -2 check right and left collision: move the player(x-- or x++) and set xAcc to 0
     *         -3 check top collision
     *         -4 check bottom collision: if there is a bottom collision move the player up(y--) and set yAcc to 0
     *         -5 set skin direction
     *         -6 skin animation
     *         -7 update x and y based on xAcc and yAcc
     *         -8 update hitbox.
     */
    @Override
    public void update() {

    //-hole check
    //if the player fall into a hole, teleport to the beginning
    if (y > 580) {
        playSFX(4);
        teleportToBeginning();
        //testprint
        //System.out.println("spikes");
    }
    //-1 done
        playerMovement();
    //-2 should work (right only)
        checkHorizontalCollision();
    //-3 to do
        checkTopCollision();
    //-4 should work
        checkGravity();
    //-5 done
        setCentDirection();
    //-8 should work
        skinAnimation();
    //-7 done

        x += xAcc;
        y += yAcc;
    //-8 done
        updateHitBox();

        // temporary: avoid out of bound
        if(x < 0){x = 0;}
        if(y < 0){y = 0;}

    //-checkwin
    checkWin();
    }


    /***
     * exectutes a specific movement based on the Keyboard button pressed
     */
    public void playerMovement() {

        if (KeyHandler.upPressed) {
            jump();
        }
        if (KeyHandler.leftPressed) {
            left();
        } else if (KeyHandler.rightPressed) {
            right();
        }else {
            still();
        }
    }
}
