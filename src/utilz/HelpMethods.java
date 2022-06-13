package utilz;

import Main.Game;
import gamestates.Menu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;


/*

CLASS SUMMARY

    This class might implement the CanMoveHere() method in a way that using
    as parameters  (intended x movement, intended y movement, hitbox width, hitbox height, int[][] level Data), the
    method returns false if the intended movement causes a collision.


class status: not sure if it works, there might be some structure problems, but currently ok
 */
public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (!IsSolid(x, y, lvlData))
            if (!IsSolid(x + width, y + height, lvlData))
                if (!IsSolid(x + width, y, lvlData))
                    if (!IsSolid(x, y + height, lvlData))
                        return true;
        return false;
    }


    private static boolean IsSolid(float x, float y, int[][] lvlData) {


        //NOT USED (bugged because of JPanel size bug)
        /*
        //if the player exits the screen return true
        if (x < 0 || x >= Game.screenWidth) {
            //testprint
            System.out.println("out of X bound at " + x);
            return true;
        }
        if (y < 0 || y >= Game.screenHeight) {

            //testprint
            System.out.println("out of Y bound at " + y);
            return true;
        }

         */




        float xIndex = x / Game.tileSize;
        float yIndex = y / Game.tileSize;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value != 11) {  //48 è il numero di sprites per il livello. 11 è lo sprite vuoto
            return true;
        }

        return false;
    }

    public static int[][] GetLevelData(BufferedImage img) {

        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 16)
                    value = 0;
                lvlData[j][i] = value;
            }
        return lvlData;

    }

}
