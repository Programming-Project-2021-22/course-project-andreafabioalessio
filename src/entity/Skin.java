package entity;

import java.awt.image.BufferedImage;

public class Skin {
    //variables

    //TO FIX
    public BufferedImage[] center;
    public BufferedImage[] left;
    public BufferedImage[] right;
    public BufferedImage image = null;
    public int spriteNum = 1;
    public int spriteCounter = 0;
    public int spriteFPS = 11;



    //constructor
    public Skin() {
    }


//Getters & Setters
    public void setSpriteNum(int spriteNum) { this.spriteNum = spriteNum;}


    public BufferedImage leftAnimation(){
        //spriteNum = 0;
        return animate(left);
    }
    public BufferedImage rightAnimation(){
        //spriteNum = 0;
        return animate(right);
    }



    public BufferedImage animate(BufferedImage[] images){

        spriteCounter++;
        if (spriteCounter > 7){
            spriteNum++;
            spriteNum = spriteNum%images.length;
            spriteCounter = 0;
        }
        return images[spriteNum];
    }

    public BufferedImage center(int direction){
        if (direction == -1){
            image = center[0];
        }else{
            image = center[1];
        }
        return image;
    }


    public BufferedImage jump(int direction){
        if (direction == -1){
            image = left[0];  //frame 1 is always a jump frame
        }else if (direction == 1){
            image = right[0];
        }
        return image;
    }



}



    //Old skin system that was in player, might be useful to implement this one
    /*
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
            }else if (color == 2){
                left0 = ImageIO.read(getClass().getResourceAsStream("/player/green_left_0.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/player/green_left_1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/player/green_left_2.png"));
                left3 = ImageIO.read(getClass().getResourceAsStream("/player/green_left_3.png"));
                left4 = ImageIO.read(getClass().getResourceAsStream("/player/green_left_4.png"));
                left5 = ImageIO.read(getClass().getResourceAsStream("/player/green_left_5.png"));
                left6 = ImageIO.read(getClass().getResourceAsStream("/player/green_left_6.png"));
                right0 = ImageIO.read(getClass().getResourceAsStream("/player/green_right_0.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/player/green_right_1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/player/green_right_2.png"));
                right3 = ImageIO.read(getClass().getResourceAsStream("/player/green_right_3.png"));
                right4 = ImageIO.read(getClass().getResourceAsStream("/player/green_right_4.png"));
                right5 = ImageIO.read(getClass().getResourceAsStream("/player/green_right_5.png"));
                right6 = ImageIO.read(getClass().getResourceAsStream("/player/green_right_6.png"));
            }else if (color == 3){
                left0 = ImageIO.read(getClass().getResourceAsStream("/player/pink_left_0.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/player/pink_left_1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/player/pink_left_2.png"));
                left3 = ImageIO.read(getClass().getResourceAsStream("/player/pink_left_3.png"));
                left4 = ImageIO.read(getClass().getResourceAsStream("/player/pink_left_4.png"));
                left5 = ImageIO.read(getClass().getResourceAsStream("/player/pink_left_5.png"));
                left6 = ImageIO.read(getClass().getResourceAsStream("/player/pink_left_6.png"));
                right0 = ImageIO.read(getClass().getResourceAsStream("/player/pink_right_0.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/player/pink_right_1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/player/pink_right_2.png"));
                right3 = ImageIO.read(getClass().getResourceAsStream("/player/pink_right_3.png"));
                right4 = ImageIO.read(getClass().getResourceAsStream("/player/pink_right_4.png"));
                right5 = ImageIO.read(getClass().getResourceAsStream("/player/pink_right_5.png"));
                right6 = ImageIO.read(getClass().getResourceAsStream("/player/pink_right_6.png"));
            }


        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void update(){

        playerMovements();
        playerPhysics();

    }


    public void playerMovements(){
        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            if(keyH.upPressed){
                direction = "up";
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

    //This method implements physics into the game
    public void playerPhysics(){

        if(jumpstrenght != 0){ // TO CHANGE
            gravity = 1;
        }
        else{
            jumpstrenght -= 1;
            weight = 0;
        }
        y -= gravity * weight;
        jump -= 1;
    }


    public void draw(Graphics2D g2){

        BufferedImage image = null;

        // decidere che immagine mostrare
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

                if (Objects.equals(direction, "right")){
                    image = right1;
                }else{
                    image = left1;
                }
                if(jump == 0) {
                    jump = jumpstrenght;
                }
                System.out.println(jumpstrenght);
                System.out.println(y);
              //if (y >= floorHeight){
                   // y=floorHeight;
              //}

                //jump();
                break;
            case "down":
                //image = left0;
                break;

        }
        // mostrare l'immagine
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

    public void jump(){

    }


     */


