import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class LevelLoad {

    public static final String LEVEL_ATLAS = "level_sprites.png";
    public static final String BG_IMAGE = "background_sky.png";
    public static final String CLOUDS = "clouds.png";

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

    public static BufferedImage[] getAllLevels(){
//        URL url = LevelLoad.class.getClassLoader().getResource("/level/lvls");
//        File file = null;

//        System.out.println("Ho trovato il path");
//
//        File[] files = file.listFiles();
//        File[] filesSorted = new File[files.length];
//
//        for (int i = 0; i < filesSorted.length; i++){
//            for (int j = 0; j < files.length; j++){
//                if (files[j].getName().equals((i+1) + ".png"))
//                    filesSorted[i] = files[j];
//            }
//        }

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
