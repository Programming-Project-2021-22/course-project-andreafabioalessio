

import java.awt.*;
import java.awt.image.BufferedImage;


/*

CLASS SUMMARY

    This class might implement the CanMoveHere() method in a way that using
    as parameters  (intended x movement, intended y movement, hitbox width, hitbox height, int[][] level Data), the
    method returns false if the intended movement causes a collision.


class status: not sure if it works, there might be some structure problems, but currently ok
 */

/***
 * Class that stores Methods that can get accessed by any class
 */
public class HelpMethods {

    /***
     * the method returns false if the intended movement causes a collision.
     * @param x: Coordinate
     * @param y: Coordinate
     * @param width
     * @param height
     * @param lvlData: current Data of the level
     * @return true if the intended movement doesn't cause a collision
     */
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!IsSolid(x, y, lvlData))
            if (!IsSolid(x + width, y + height, lvlData))
                if (!IsSolid(x + width, y, lvlData))
                    if (!IsSolid(x, y + height, lvlData))
                        return true;
        return false;
    }

    /***
     * checks if at the given coordinates there is a solid block
     * @param x
     * @param y
     * @param lvlData
     * @return true if the there is a soldi block
     */
    private static boolean IsSolid(float x, float y, int[][] lvlData) {

        float xIndex = x / Game.tileSize;
        float yIndex = y / Game.tileSize;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value != 14) {  //14 is the only non solid block. the number referes to the sprite Altas of the level
            return true;
        }

        return false;
    }

    /***
     * Analyzes the red rgb values of the level image and creates an array with those informations
     * @param img: image of the Level
     * @return returns a 2D in array with the red values taken from the level image
     */
    public static int[][] GetLevelData(BufferedImage img) {

        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int i = 0; i < img.getHeight(); i++)
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int value = color.getRed();
                if (value >= 18)
                    value = 0;
                lvlData[i][j] = value;
            }
        return lvlData;

    }

}
