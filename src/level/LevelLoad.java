package level;

import Main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LevelLoad {

    public static final String LEVEL_ATLAS = "outside_sprites_1png.png";
    public static final String LEVEL_DATA_1 = "level_data_1.png";

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

    public static int[][] GetLevelData() {

        BufferedImage img = GetSpriteAtlas(LEVEL_DATA_1);
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
