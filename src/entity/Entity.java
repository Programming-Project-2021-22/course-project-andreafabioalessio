package entity;

import java.awt.image.BufferedImage;

// parent class
//Qui si instanziano i diversi utenti, npc, ecc
public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage left0, left1, left2, left3, left4, left5, left6, right0, right1, right2, right3, right4, right5, right6;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
