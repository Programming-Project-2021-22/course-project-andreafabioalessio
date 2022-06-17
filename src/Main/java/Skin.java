import java.awt.image.BufferedImage;

/*

CLASS SUMMARY


class status: currently ok,but a way to implement entity death might be added
 */

/***
 * creates the player animation
 */
public class Skin {
    //variables

    //TO FIX
    public BufferedImage[] center;
    public BufferedImage[] left;
    public BufferedImage[] right;
    public BufferedImage image = null;
    public int spriteNum = 1;
    public int spriteCounter = 0;
    public int spriteFPS = 7;



    //constructor
    public Skin() {
    }


//Getters & Setters
    public void setSpriteNum(int spriteNum) { this.spriteNum = spriteNum;}

    /***
     *
     * @return Image with the left animation
     */
    public BufferedImage leftAnimation(){
        //spriteNum = 0;
        return animate(left);
    }

    /***
     *
     * @return Image with the right animation
     */
    public BufferedImage rightAnimation(){
        //spriteNum = 0;
        return animate(right);
    }

    /***
     * creates the walking animation
     * @return returns a Bufferedimage with the specific player sprite.
     */
    public BufferedImage animate(BufferedImage[] images){

        spriteCounter++;
        if (spriteCounter > spriteFPS){
            spriteNum++;
            spriteNum = spriteNum%images.length;
            spriteCounter = 0;
        }
        return images[spriteNum];
    }

    /***
     *
     * @param direction
     * @return returns a BufferedImage with the correct center Direction
     */
    public BufferedImage center(int direction){
        if (direction == -1){
            image = center[0];
        }else{
            image = center[1];
        }
        return image;
    }

    /***
     *
     * @param direction
     * @return returns a BufferedImage with the correct jump sprite
     */
    public BufferedImage jump(int direction){
        if (direction == -1){
            image = left[0];  //frame 1 is always a jump frame
        }else if (direction == 1){
            image = right[0];
        }
        return image;
    }

}


