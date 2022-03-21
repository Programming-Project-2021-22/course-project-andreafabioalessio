package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 300;
        y = 300;
        speed = 4;
        direction = "left";
    }

    public void getPlayerImage(){

        try {

            left0 = ImageIO.read(getClass().getResourceAsStream("/player/red_left_0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/red_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/red_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/red_left_3.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/player/red_right_0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/red_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/red_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/red_right_3.png"));

        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void update(){

        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {
            if(keyH.upPressed){
                direction = "up";
                y -= speed;
            }else if(keyH.downPressed){
                direction = "down";
                y += speed;
            }else if(keyH.leftPressed){
                direction = "left";
                x -= speed;
            }else if(keyH.rightPressed){
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if(spriteCounter > 8){
                if (spriteNum == 0){
                    spriteNum = 1;
                }else if(spriteNum == 1){
                    spriteNum = 3;
                }else if(spriteNum == 3){
                    spriteNum = 2;
                }else if (spriteNum == 2){
                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
        }


    }

    public void draw(Graphics2D g2){

        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction){
            case "left":
                if (spriteNum == 0){
                    image = left0;
                }
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                if (spriteNum == 3){
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 0){
                    image = right0;
                }
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                if (spriteNum == 3){
                    image = right3;
                }
                break;
            case "up":
                image = left0;
                break;
            case "down":
                image = left0;
                break;

        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
}
