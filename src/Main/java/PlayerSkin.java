import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/***
 * Loads the Player skins
 */
public class PlayerSkin extends Skin{

    public PlayerSkin() {

        try {
            left = new BufferedImage[6];
            center = new BufferedImage[2];
            right = new BufferedImage[6];

            center[0] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_0.png"));
            left[0] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_1.png"));
            left[1] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_2.png"));
            left[2] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_3.png"));
            left[3] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_4.png"));
            left[4] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_5.png"));
            left[5] = ImageIO.read(getClass().getResourceAsStream("/player/red_left_6.png"));
            center[1] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_0.png"));
            right[0] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_1.png"));
            right[1] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_2.png"));
            right[2] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_3.png"));
            right[3] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_4.png"));
            right[4] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_5.png"));
            right[5] = ImageIO.read(getClass().getResourceAsStream("/player/red_right_6.png"));
        }catch (IOException ioe){

            ioe.printStackTrace();
        } catch (Exception e){
            //testprint
            System.out.println("skin error: " + e);
        }
    }
}
