package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Base class for all interactable objects in the game.
 * <p>
 * This class provides the fundamental attributes and methods shared among different game objects, such as
 * their visual representation, location in the game world, collision properties, and the ability to be drawn
 * within the game panel. It also defines a standard collision area for interactions.
 * 
 * @author Gurshaan Gill
 * @version 1.3
 * @since 1.0
 */

public class SuperObject {
    public BufferedImage image;
    public String name;
    public Boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    /**
     * Draws the object within the game panel, adjusting its position based on the player's viewpoint.
     * 
     * @param g2 The Graphics2D object used for drawing.
     * @param gp The GamePanel object which provides context for drawing, including the player's position.
     */
    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

}
