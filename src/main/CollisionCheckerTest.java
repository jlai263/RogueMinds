package main;

import entities.Entity;
import main.CollisionChecker;
import main.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import tile.Tile;
import java.awt.Rectangle;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author      Darren Chen
 * @version     1.0
 * @since       1.0 
 * The CollisionCheckerTest class contains unit tests for the CollisionChecker class.
 * <p>
 * It verifies the functionality of collision detection methods against tiles and objects.
 * These tests ensure that entities properly interact with game elements without clipping through walls or objects.
 */

class CollisionCheckerTest {

    private GamePanel gamePanel;
    private CollisionChecker collisionChecker;
    private Entity entity;

    /**
     * Sets up the environment before each test method execution.
     * Initializes the game panel, collision checker, and entity with appropriate attributes.
     */
    @BeforeEach
    void setUp() {
        // Initialize the game panel and collision checker
        gamePanel = new GamePanel();
        collisionChecker = new CollisionChecker(gamePanel);
        
        // Creating an entity with initial coordinates and solid area
        entity = new Entity();
        entity.direction = "down"; // Assuming initial direction
        entity.worldX = 0; // Assuming initial coordinates
        entity.worldY = 0; // Assuming initial coordinates
        entity.solidArea = new Rectangle(0, 0, 16, 16); // Assuming initial solid area
        
        // Assuming there's a wall tile
        gamePanel.tileM.tile[1] = new Tile();
        gamePanel.tileM.tile[1].collision = true; // Assuming wall tile is a collision tile
    }

    /**
     * Cleans up the environment after each test method execution.
     * Resets any changes made during the test to maintain test independence.
     */
    @AfterEach
    void tearDown() {
        // Reset entity or any other objects if needed
        entity = null;
    }

    /**
     * Tests the checkTile method when there is no collision with tiles.
     * Verifies that the collision flag remains false.
     */
    @Test
    void checkTile_NoCollision() {
        // Resetting collision flag to false before testing
        entity.collisionOn = false;
        
        // Assuming entity is not colliding initially
        assertFalse(entity.collisionOn);
        collisionChecker.checkTile(entity);
        // Since we're assuming no collision at (0,0), the collision flag should remain false
        assertFalse(entity.collisionOn);
    }

    /**
     * Tests the checkTile method when there is collision with tiles.
     * Verifies that the collision flag becomes true upon collision.
     */
    @Test
    void checkTile_Collision() {
        // Placing a wall at the position of the entity
        gamePanel.tileM.mapTileNum[0][0] = 1; // Assuming tile 1 is a wall
        assertTrue(entity.solidArea.intersects(gamePanel.tileM.tile[gamePanel.tileM.mapTileNum[0][0]].image.getWidth(), gamePanel.tileM.tile[gamePanel.tileM.mapTileNum[0][0]].image.getHeight(), 0, 0));
        collisionChecker.checkTile(entity);
        // Since the entity is colliding with the wall at (0,0), the collision flag should become true
        assertTrue(entity.collisionOn);
    }

    /**
     * Tests the checkObject method when there is no collision with objects.
     * Verifies that the collision flag remains false and the index matches the default value.
     */
    @Test
    void checkObject_NoCollision() {
        // Assuming entity is not colliding initially
        assertFalse(entity.collisionOn);
        int collisionIndex = collisionChecker.checkObject(entity, true);
        // Since we're assuming no collision with objects, the collision flag should remain false and the index should be 999
        assertFalse(entity.collisionOn);
        assertEquals(999, collisionIndex);
    }

    /**
     * Tests the checkObject method when there is collision with objects.
     * Verifies that the collision flag becomes true and the index matches the colliding object's index.
     */
    @Test
    void checkObject_Collision() {
        // Resetting collision flag to false before testing
        entity.collisionOn = false;
        
        // Creating an object which would collide with the entity
        Entity object = new Entity();
        object.solidArea = new Rectangle(0, 0, 16, 16); // Assuming same solid area as entity
        object.worldX = 0; // Setting the world X coordinate
        object.worldY = 0; // Setting the world Y coordinate
        assertTrue(entity.solidArea.intersects(object.solidArea));
        int collisionIndex = collisionChecker.checkObject(entity, true);
        // Since the entity is colliding with the object, the collision flag should become true and the index should match the object's index in the array
        assertTrue(entity.collisionOn);
        assertEquals(0, collisionIndex);
    }
}
