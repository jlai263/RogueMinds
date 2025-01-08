package main;

import entities.Player;
import object.Key;
import object.Heart; 
import object.HeartEmpty; 

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Manages and renders the user interface elements in the game, including on-screen messages, game state screens,
 * inventory displays, and the game timer.
 * <p>
 * This class is responsible for drawing text and images directly related to the game's UI, such as pause and end game screens,
 * inventory icons, and the elapsed game time. It supports showing temporary messages on the screen and formatting the play time.
 * 
 * @author Gurshaan Gill
 * @version 1.3
 * @since 1.0
 */

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font font, font_bold;
    BufferedImage keyImage;
    BufferedImage heartIcon;
    BufferedImage heartIconEmpty;
    public Boolean messageOn = false;
    public String message = "";
    int messageTimer = 0;
    public Boolean endGame = false;
    public double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public static int lives = 3;
  
      /**
     * Constructs a UI object associated with a specific GamePanel.
     * 
     * @param gp The GamePanel instance this UI is part of.
     */
  
    public UI (GamePanel gp){
        this.gp = gp;
        font = new Font("Arial", Font.PLAIN, 40);
        font_bold = new Font("Arial", Font.BOLD, 80);
        Key key = new Key();
        Heart heart = new Heart(); 
        HeartEmpty heartEmpty = new HeartEmpty(); 
        keyImage = key.image;
        heartIcon = heart.image; 
        heartIconEmpty = heartEmpty.image; 
        keyImage = key.image;
    }
  
    /**
     * Displays a temporary on-screen message.
     * 
     * @param text The text of the message to display.
     */
  
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
  
    /**
     * Draws UI elements based on the current game state.
     * 
     * @param g2 The Graphics2D object used for drawing.
     */
  
    public void draw(Graphics2D g2){
        gp.player.time = playTime;
        this.g2 = g2;
        g2.setFont(font);
        g2.setColor(Color.white);

        if (gp.gameState == gp.playState){

        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if (endGame == true){
            g2.setFont(font);
            g2.setColor(Color.white);

            String text = "You found the Treasure!!!";
            int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            int x = gp.screenWidth/2 - textLength/2;
            int y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Your time is : " + dFormat.format(playTime) + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);

            g2.setFont(font_bold);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null; // End the game
        }

        else {
            g2.setFont(font);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
          
            if(lives == 3) {
            	g2.drawImage(heartIcon, gp.tileSize/2, (gp.tileSize/2)+75, gp.tileSize, gp.tileSize, null);
                g2.drawImage(heartIcon, (gp.tileSize/2)+50, (gp.tileSize/2)+75, gp.tileSize, gp.tileSize, null);
                g2.drawImage(heartIcon, (gp.tileSize/2)+100, (gp.tileSize/2)+75, gp.tileSize, gp.tileSize, null);
            }
            
            if(lives == 2) {
            	g2.drawImage(heartIcon, gp.tileSize/2, (gp.tileSize/2)+75, gp.tileSize, gp.tileSize, null);
                g2.drawImage(heartIcon, (gp.tileSize/2)+50, (gp.tileSize/2)+75, gp.tileSize, gp.tileSize, null);
            	g2.drawImage(heartIconEmpty, (gp.tileSize/2)+100, (gp.tileSize/2)+75, gp.tileSize, gp.tileSize, null);
            }
            if(lives == 1) {
            	g2.drawImage(heartIcon, gp.tileSize/2, (gp.tileSize/2)+75, gp.tileSize, gp.tileSize, null);
            	g2.drawImage(heartIconEmpty, (gp.tileSize/2)+50, (gp.tileSize/2)+75, gp.tileSize, gp.tileSize, null);
            	g2.drawImage(heartIconEmpty, (gp.tileSize/2)+100, (gp.tileSize/2)+75, gp.tileSize, gp.tileSize, null);
            }
            if(lives == 0) {
            	g2.drawImage(heartIconEmpty, gp.tileSize/2, (gp.tileSize/2)+75, gp.tileSize, gp.tileSize, null);
            	g2.drawImage(heartIconEmpty, (gp.tileSize/2)+50, (gp.tileSize/2)+75, gp.tileSize, gp.tileSize, null);
            	g2.drawImage(heartIconEmpty, (gp.tileSize/2)+100, (gp.tileSize/2)+75, gp.tileSize, gp.tileSize, null);
            	String text = "Oh no, you lost!";
                int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

                int x = gp.screenWidth/2 - textLength/2;
                int y = gp.screenHeight/2 - (gp.tileSize*3);
                g2.drawString(text, x, y);
            	gp.gameThread = null;
            }
          
            g2.drawString(" x  "+ gp.player.hasCoin, 74, 65);

            // Time
            playTime += (double) 1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);

            // Screen Text
            if (messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize * 5);

                messageTimer++;

                if (messageTimer > 120){
                    messageTimer = 0;
                    messageOn = false;
                }
            }
        }

    }
      /**
     * Draws the pause screen.
     */
    public void drawPauseScreen(){
        String text = "PAUSED";


        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        int y = gp.screenHeight/2 + 13;
        g2.drawString(text, x, y);
    }
}
