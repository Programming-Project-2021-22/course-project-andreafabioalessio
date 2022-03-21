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
    int color;

    public Player(GamePanel gp, KeyHandler keyH, int color){
        this.gp = gp;
        this.keyH = keyH;
        this.color = color;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 300;
        y = 300;
        speed = 4;
        direction = "right";
    }

    public void getPlayerImage(){
        try {
            if (color == 1){
                left0 = ImageIO.read(getClass().getResourceAsStream("/player/red_left_0.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/player/red_left_1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/player/red_left_2.png"));
                left3 = ImageIO.read(getClass().getResourceAsStream("/player/red_left_3.png"));
                left4 = ImageIO.read(getClass().getResourceAsStream("/player/red_left_4.png"));
                left5 = ImageIO.read(getClass().getResourceAsStream("/player/red_left_5.png"));
                left6 = ImageIO.read(getClass().getResourceAsStream("/player/red_left_6.png"));
                right0 = ImageIO.read(getClass().getResourceAsStream("/player/red_right_0.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/player/red_right_1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/player/red_right_2.png"));
                right3 = ImageIO.read(getClass().getResourceAsStream("/player/red_right_3.png"));
                right4 = ImageIO.read(getClass().getResourceAsStream("/player/red_right_4.png"));
                right5 = ImageIO.read(getClass().getResourceAsStream("/player/red_right_5.png"));
                right6 = ImageIO.read(getClass().getResourceAsStream("/player/red_right_6.png"));
            }


        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void update(){

        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            if(keyH.upPressed){
                direction = "up";
                //y -= speed;
            }else if(keyH.downPressed){
                direction = "down";
                //y += speed;
            }else if(keyH.leftPressed){
                direction = "left";
                x -= speed;
            }else if(keyH.rightPressed){
                direction = "right";
                x += speed;
            }

            //ciclo di sprite
            spriteCounter++;
            if(spriteCounter > 7){
                if (spriteNum == 0){
                    spriteNum = 1;
                }else if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 3;
                }else if (spriteNum == 3){
                    spriteNum = 4;
                }else if (spriteNum == 4){
                    spriteNum = 5;
                }else if (spriteNum == 5){
                    spriteNum = 6;
                }else if (spriteNum == 6){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }else {
            spriteNum = 0;
        }

    }

    public void draw(Graphics2D g2){

        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        // decidere che imagine mostrare
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
                if (spriteNum == 4){
                    image = left4;
                }
                if (spriteNum == 5){
                    image = left5;
                }
                if (spriteNum == 6){
                    image = left6;
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
                if (spriteNum == 4){
                    image = right4;
                }
                if (spriteNum == 5){
                    image = right5;
                }
                if (spriteNum == 6){
                    image = right6;
                }
                break;
            case "up":
                image = left0;
                break;
            case "down":
                image = left0;
                break;

        }
        // mostrare l'immagine
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
}
