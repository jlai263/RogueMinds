package entities;

import main.GamePanel;

import main.KeyHandler;
import mathSolving.MathProblemGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author      Gurshaan Gill 
 * @version     1.2
 * @since       1.0 
 * Represents the player character in the game, extending the generic Entity class with additional features.
 * <p>
 * This class manages the player's movements, animations, interactions with objects in the game world, and collision detection.
 * It initializes the player's starting position, loads the player's sprite images, and updates the player's state in each game loop iteration.
 */

public class Player extends Entity{
    // Reference to the GamePanel for accessing game settings and utilities
    public static GamePanel gp;
    
    // Reference to the KeyHandler for processing keyboard input
  	public static KeyHandler keyH;
  
    // Screen position for drawing the player sprite
    public final int screenX;
    public final int screenY;
  
  	// Tracks the number of coins the player has collected
    public static int hasCoin = 0;
    public double time;
    public String username;
  
	public static boolean answeringQuestion = false;
    public static int difficulty = 1;

    /**
     * Constructs a Player with references to the game panel and key handler.
     * Initializes the player's position, solid area for collision, and loads the sprite images.
     *
     * @param gp   Reference to the GamePanel for accessing game settings and utilities.
     * @param keyH Reference to the KeyHandler for processing keyboard input.
     */
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 28;
        solidArea.height = 28;

        setDefaultValues();
        getPlayerImage();
    }
  
    /**
     * Sets the player's default values including the starting position and movement speed.
     */
  
    public void setDefaultValues(){
        System.out.println(hasCoin);
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

      /**
     * Loads the player's sprite images from the resources directory.
     */
    public void getPlayerImage(){

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/d1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/d2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/l1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/l2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/r1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/r2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

      /**
     * Updates the player's state in each game loop iteration.
     * Handles player movement based on keyboard input and manages collision detection and sprite animation.
     */
    public void update(){
       
    	if (answeringQuestion == false) { 
	    	if ((keyH.upPressed == true || keyH.downPressed == true ||
	            keyH.leftPressed == true || keyH.rightPressed) == true){
	
	            if (keyH.upPressed == true){
	                direction = "up";
	            	
	            }
	            else if (keyH.downPressed == true){
	                direction = "down";
	
	            }
	            else if( keyH.leftPressed == true){
	                direction = "left";
	
	            }
	            else if (keyH.rightPressed == true){
	                direction = "right";
	
	            }
	      
	
	            // Checking Tile Collision
	            collisionOn = false;
	            gp.cChecker.checkTile(this);
	
	            // Checking Object Collision
	            int objIndex = gp.cChecker.checkObject(this, true);
	            pickUpObject(objIndex);
	
	            //If Collision is false
	            if(collisionOn == false){
	                switch (direction){
	                    case "up":
	                        worldY -= speed;
	                        break;
	
	                    case "down":
	                        worldY += speed;
	                        break;
	
	                    case "left":
	                        worldX -= speed;
	                        break;
	
	                    case "right":
	                        worldX += speed;
	                        break;
	                }
	            }
	
	            spriteCounter++;
	            if (spriteCounter > 10){
	                if (spriteNum == 1) spriteNum = 2;
	                else if (spriteNum == 2) spriteNum =1;
	                spriteCounter = 0;
	            }
	
	        }
    	}
    

    }

    /**
     * Handles the player's interaction with objects in the game world.
     * For example, collecting coins or opening doors.
     *
     * @param index The index of the object in the game world that the player interacts with.
     */
    public void pickUpObject (int index){
        // If there is object collision
        if (index != 999){
            String objectName = gp.obj[index].name;

            switch (objectName){
                case "Key":
                    gp.playSE(1);
                    hasCoin++;
                    gp.obj[index] = null;
                    gp.ui.showMessage("You got a Coin!");
                    break;

                case "Door":
                    if (hasCoin > 1){
                        gp.playSE(3);
                        gp.obj[index] = null;
                        hasCoin = hasCoin - 2;
                        gp.ui.showMessage("You opened the Door!");
                    }
                    else gp.ui.showMessage("You need 2 Coins to open the Door!");
                    break;

                case "Chest":
                    gp.ui.endGame = true;
                    gp.stopMusic();
                    gp.playSE(2);
                    break;
                case "enemyOne":
                    answeringQuestion = true;
                    MathProblemGenerator mathProblemGenerator = new MathProblemGenerator();
                    mathProblemGenerator.setVisible(true);
                    gp.obj[index] = null;
                    break;
                case "enemyTwo":
                    answeringQuestion = true;
                    MathProblemGenerator mathProblemGenerator2 = new MathProblemGenerator();
                    mathProblemGenerator2.setVisible(true);
                    gp.obj[index] = null;
                    break;
                case "enemyThree":
                    answeringQuestion = true;
                    MathProblemGenerator mathProblemGenerator3 = new MathProblemGenerator();
                    mathProblemGenerator3.setVisible(true);
                    gp.obj[index] = null;
                    break;
                case "enemyFour":
                    answeringQuestion = true;
                    MathProblemGenerator mathProblemGenerator4 = new MathProblemGenerator();
                    mathProblemGenerator4.setVisible(true);
                    gp.obj[index] = null;
                    break;
                case "npcOne":
                	gp.ui.showMessage("Hello!");
                	break;
                case "npcTwo":
                	gp.ui.showMessage("Beware of the enemies!");
                	break;
                case "npcThree":
                	gp.ui.showMessage("I hope you brought your calculator!");
                	break;
                case "npcFour":
                	gp.ui.showMessage("I love CS2212!");
                	break;
                	
            }
        }
    }
  
    /**
     * Draws the player sprite on the game panel.
     * The sprite image is selected based on the player's direction and animation state.
     *
     * @param g2 Graphics2D object used for drawing the player sprite.
     */
  
    public void draw(Graphics2D g2){
        // white rect eg
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction){
            case("up"):
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
                break;

            case("down"):
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
                break;

            case ("left"):
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
                break;

            case("right"):
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}

