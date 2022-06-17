import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


/***
 * Class that has methods that load the level sprites
 */
public class LevelLoad {

    public static final String LEVEL_ATLAS = "level_sprites.png";
    public static final String BG_IMAGE = "background_sky.png";
    public static final String CLOUDS = "clouds.png";

    /***
     * Loads the Given image
     * @param fileName
     * @return Returns a BufferedImage
     */
    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LevelLoad.class.getResourceAsStream("/level/" + fileName);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    /***
     *
     * @return returns an array of BufferedImage containing alle the levels
     */
    public static BufferedImage[] getAllLevels(){

        BufferedImage[] imgs = new BufferedImage[4];
        InputStream one = LevelLoad.class.getResourceAsStream("/level/lvls/1.png");
        InputStream two = LevelLoad.class.getResourceAsStream("/level/lvls/2.png");
        InputStream three = LevelLoad.class.getResourceAsStream("/level/lvls/3.png");
        InputStream four = LevelLoad.class.getResourceAsStream("/level/lvls/4.png");

        try {
            imgs[0] = ImageIO.read(one);
            imgs[1] = ImageIO.read(two);
            imgs[2] = ImageIO.read(three);
            imgs[3] = ImageIO.read(four);
        }catch (Exception e){
            e.printStackTrace();
        }

        return imgs;
    }
}
