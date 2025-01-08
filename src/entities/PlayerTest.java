package entities;

import main.GamePanel;
import main.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author         Darren Chen
 * @version         1.0
 * @since           1.0
 * This class contains JUnit test cases for the Player class.
 * <p>
 * It ensures that the functionality of the Player class is tested thoroughly,
 * covering various aspects such as default values, movement, collision detection,object interaction, and drawing.
 * Each test method focuses on specific functionalities of the Player class, verifying that they behave as expected under different scenarios.
 */
class PlayerTest {

    private GamePanel gamePanel; // The game panel instance for testing
    private KeyHandler keyHandler; // The key handler instance for testing
    private Player player; // The player instance for testing

    /**
     * Sets up the test environment by creating instances of GamePanel, KeyHandler, and Player.
     */
    @BeforeEach
    void setUp() {
        gamePanel = new GamePanel();
        keyHandler = new KeyHandler(gamePanel);
        player = new Player(gamePanel, keyHandler);
    }

    /**
     * Tests default values of the player instance upon initialization.
     */
    @Test
    void testDefaultValues() {
        assertEquals(gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2), player.screenX);
        assertEquals(gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2), player.screenY);
        assertEquals(0, Player.hasCoin);
        assertEquals("down", player.direction);
    }

    /**
     * Tests player movement and collision detection for different directions.
     */
    @Test
    void testUpdate() {
        // Test movement and collision detection for different directions
        player.keyH.downPressed = true;
        player.update();
        assertEquals(gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2) + player.speed, player.worldY);

        player.keyH.downPressed = false;
        player.keyH.upPressed = true;
        player.update();
        assertEquals(gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2) - player.speed, player.worldY);

        player.keyH.upPressed = false;
        player.keyH.leftPressed = true;
        player.update();
        assertEquals(gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2) - player.speed, player.worldX);

        player.keyH.leftPressed = false;
        player.keyH.rightPressed = true;
        player.update();
        assertEquals(gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2) + player.speed, player.worldX);

        // Test pickUpObject method
        player.keyH.rightPressed = false;
        player.worldX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        player.worldY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);
        player.pickUpObject(1); // Assuming index 1 represents a coin
        assertEquals(1, Player.hasCoin);
    }

    /**
     * Tests the drawing method of the player.
     */
    @Test
    void testDraw() {
        // Test drawing method
        assertNotNull(player);

        // Create a mock Graphics2D object for testing
        BufferedImage image = new BufferedImage(gamePanel.screenWidth, gamePanel.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        // Test each direction and animation state
        testDirectionAnimationState("up", 1, player.up1, g2);
        testDirectionAnimationState("up", 2, player.up2, g2);

        testDirectionAnimationState("down", 1, player.down1, g2);
        testDirectionAnimationState("down", 2, player.down2, g2);

        testDirectionAnimationState("left", 1, player.left1, g2);
        testDirectionAnimationState("left", 2, player.left2, g2);

        testDirectionAnimationState("right", 1, player.right1, g2);
        testDirectionAnimationState("right", 2, player.right2, g2);

    }

    /**
     * Helper method to test drawing for a specific direction and animation state.
     *
     * @param direction      The direction of the player.
     * @param spriteNum      The sprite number.
     * @param expectedImage  The expected image.
     * @param g2             The Graphics2D object for testing.
     */
    private void testDirectionAnimationState(String direction, int spriteNum, BufferedImage expectedImage, Graphics2D g2) {
        player.direction = direction;
        player.spriteNum = spriteNum;
        player.draw(g2);

        BufferedImage actualImage = new BufferedImage(expectedImage.getWidth(), expectedImage.getHeight(), expectedImage.getType());
        g2.drawImage(expectedImage, 0, 0, null); // Draw the expected image onto the actual image
        assertTrue(imagesAreEqual(expectedImage, actualImage)); // Check if the drawn image matches the expected image
    }

    /**
     * Helper method to compare two BufferedImages.
     *
     * @param expected  The expected image.
     * @param actual    The actual image.
     * @return          True if the images are equal, false otherwise.
     */
    private boolean imagesAreEqual(BufferedImage expected, BufferedImage actual) {
        if (expected.getWidth() != actual.getWidth() || expected.getHeight() != actual.getHeight()) {
            return false;
        }
        for (int x = 0; x < expected.getWidth(); x++) {
            for (int y = 0; y < expected.getHeight(); y++) {
                if (expected.getRGB(x, y) != actual.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
}
