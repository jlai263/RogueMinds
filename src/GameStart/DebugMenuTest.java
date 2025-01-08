package GameStart;

import main.GamePanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author         Darren Chen
 * @version         1.0
 * @since           1.0
 * JUnit test cases for the DebugMenu class.
 * <p>
 * Tests the functionality of the DebugMenu class, including drawing and handling click events.
 */
class DebugMenuTest {

    private DebugMenu debugMenu;
    private Component mockComponent;

    /**
     * Sets up the test environment by creating a DebugMenu instance and a mock Component.
     */
    @BeforeEach
    void setUp() {
        boolean[][] volumeStates = new boolean[3][10]; // Example size, adjust according to your needs
        for (int i = 0; i < volumeStates.length; i++) {
            for (int j = 0; j < volumeStates[i].length; j++) {
                volumeStates[i][j] = true; // Set initial volume state, adjust as necessary
            }
        }
        SoundManager soundManager = new SoundManager(volumeStates);
        GamePanel gamePanel = new GamePanel();
        debugMenu = new DebugMenu(soundManager, gamePanel);
    }

    /**
     * Tests the drawing method of the DebugMenu class.
     */
    @Test
    void testDraw() throws NoSuchFieldException, IllegalAccessException {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        // Test drawing method
        debugMenu.draw(g2, mockComponent);

        // Accessing private fields using reflection
        Field debugMenuIconField = DebugMenu.class.getDeclaredField("debugMenuIcon");
        debugMenuIconField.setAccessible(true);
        ImageIcon debugMenuIcon = (ImageIcon) debugMenuIconField.get(debugMenu);

        Field backButtonIconField = DebugMenu.class.getDeclaredField("backButtonIcon");
        backButtonIconField.setAccessible(true);
        ImageIcon backButtonIcon = (ImageIcon) backButtonIconField.get(debugMenu);

        Field soundManagerField = DebugMenu.class.getDeclaredField("soundManager");
        soundManagerField.setAccessible(true);
        SoundManager soundManager = (SoundManager) soundManagerField.get(debugMenu);

        Field gamePanelField = DebugMenu.class.getDeclaredField("gamePanel");
        gamePanelField.setAccessible(true);
        GamePanel gamePanel = (GamePanel) gamePanelField.get(debugMenu);

        Field squareWidthField = DebugMenu.class.getDeclaredField("squareWidth");
        squareWidthField.setAccessible(true);
        int squareWidth = (int) squareWidthField.get(debugMenu);

        Field squareHeightField = DebugMenu.class.getDeclaredField("squareHeight");
        squareHeightField.setAccessible(true);
        int squareHeight = (int) squareHeightField.get(debugMenu);

        Field squareXField = DebugMenu.class.getDeclaredField("squareX");
        squareXField.setAccessible(true);
        int squareX = (int) squareXField.get(debugMenu);

        Field squareY1Field = DebugMenu.class.getDeclaredField("squareY1");
        squareY1Field.setAccessible(true);
        int squareY1 = (int) squareY1Field.get(debugMenu);

        Field squareY2Field = DebugMenu.class.getDeclaredField("squareY2");
        squareY2Field.setAccessible(true);
        int squareY2 = (int) squareY2Field.get(debugMenu);

        // Validate that the debug menu icon and back button are drawn
        assertNotNull(debugMenuIcon);
        assertNotNull(backButtonIcon);

        // Validate that the squares are drawn
        assertTrue(image.getRGB(squareX, squareY1) != Color.BLACK.getRGB()); // Check square 1
        assertTrue(image.getRGB(squareX, squareY2) != Color.BLACK.getRGB()); // Check square 2
    }

    /**
     * Tests the handleClick method of the DebugMenu class.
     */
    @Test
    void testHandleClick() throws NoSuchFieldException, IllegalAccessException {
        // Accessing private fields using reflection
        Field infiniteCoinsOnField = DebugMenu.class.getDeclaredField("infiniteCoinsOn");
        infiniteCoinsOnField.setAccessible(true);

        Field hyperspeedOnField = DebugMenu.class.getDeclaredField("hyperspeedOn");
        hyperspeedOnField.setAccessible(true);

        // Click on the first square (Infinite Coins)
        debugMenu.handleClick(530, 295, mockComponent);
        assertTrue((boolean) infiniteCoinsOnField.get(debugMenu));

        // Click on the second square (Hyperspeed)
        debugMenu.handleClick(530, 343, mockComponent);
        assertTrue((boolean) hyperspeedOnField.get(debugMenu));
    }
}
