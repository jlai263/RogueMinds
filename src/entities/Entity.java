package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author      Gurshaan Gill 
 * @version     1.1
 * @since       1.0 
 * Represents an entity within the game world.
 * <p>
 * An entity is a member in the game that has a sprite
 */
public class Entity {
    // World coordinates of the entity
    public int worldX, worldY;
    
    // Movement speed of the entity
    public int speed;

    // Sprites for animation in different directions
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    
    // Current direction of the entity
    public String direction;
    
    // Counter to manage sprite animation
    public int spriteCounter = 0;
    
    // Number to determine which sprite to show for animation
    public int spriteNum = 1;
    
    // Area that defines the entity's solid space for collision detection
    public Rectangle solidArea;
    
    // Default x and y offsets for the solid area, used in collision detection
    public int solidAreaDefaultX, solidAreaDefaultY;
    
    // Flag to indicate if the entity is currently colliding with another object
    public Boolean collisionOn = false;
}

