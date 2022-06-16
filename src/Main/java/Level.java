import java.awt.image.BufferedImage;



public class Level {

    private BufferedImage img;
    private int[][] lvlData;
    private int lvlTilesWide;
    private int maxTilesOffset;
    private int maxLvlOffsetX;

    public Level(BufferedImage img) {
        this.img = img;
        createLevelData();
        calcLvlOffset();
    }

    private void calcLvlOffset() {
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide - Game.maxScreenCol;
        maxLvlOffsetX = Game.tileSize * maxTilesOffset;
    }

    private void createLevelData() {
        lvlData = HelpMethods.GetLevelData(img);
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLvlData(){
        return lvlData;
    }

    public int getLvlOffset(){
        return maxLvlOffsetX;
    }
}
