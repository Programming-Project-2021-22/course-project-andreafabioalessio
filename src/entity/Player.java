package entity;



public class Player extends Entity {


    public Player(int x, int y, int speed, int jumpStrenght, int weight, Skin skin) {
        super(x, y, speed, jumpStrenght, weight, skin);
        this.xAcc = 0;
        this.yAcc = 0;
        this.direction = 1;
    }

}
