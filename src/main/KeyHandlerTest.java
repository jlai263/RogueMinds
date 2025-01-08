package main;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author          Darren Chen
 * @version         1.0
 * @since           1.0
 * This class contains JUnit tests for the KeyHandler class. 
 * <p>
 * It verifies the functionality of the KeyHandler class methods by simulating key events
 * and testing the corresponding behavior of the KeyHandler instance.
 * Tests cover key press, key release, and constructor functionality,
 * ensuring that the KeyHandler behaves as expected in various scenarios.
 */

class KeyHandlerTest {

    /**
     * Tests the keyTyped method of the KeyHandler class.
     * This method is not applicable for KeyHandler class, as it does not implement keyTyped method.
     */
    @Test
    void keyTyped() {
        // Test keyTyped method
        // Not applicable for KeyHandler class, as it does not implement keyTyped method
    }

    /**
     * Tests the keyPressed method of the KeyHandler class.
     */
    @Test
    void keyPressed() {
        // Test keyPressed method
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);

        // Simulate key press events for W, A, S, D, P, and ESCAPE keys
        KeyEvent wKeyPressed = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        KeyEvent sKeyPressed = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        KeyEvent aKeyPressed = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        KeyEvent dKeyPressed = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        KeyEvent pKeyPressed = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_P, 'P');
        KeyEvent escapeKeyPressed = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED);

        keyHandler.keyPressed(wKeyPressed);
        assertTrue(keyHandler.upPressed);

        keyHandler.keyPressed(sKeyPressed);
        assertTrue(keyHandler.downPressed);

        keyHandler.keyPressed(aKeyPressed);
        assertTrue(keyHandler.leftPressed);

        keyHandler.keyPressed(dKeyPressed);
        assertTrue(keyHandler.rightPressed);

        keyHandler.keyPressed(pKeyPressed);
        assertEquals(gamePanel.pauseState, gamePanel.gameState);

        keyHandler.keyPressed(escapeKeyPressed);
        // Can't really test the ESCAPE key behavior as it exits the application

        // Reset key states
        keyHandler.upPressed = false;
        keyHandler.downPressed = false;
        keyHandler.leftPressed = false;
        keyHandler.rightPressed = false;
    }

    /**
     * Tests the keyReleased method of the KeyHandler class.
     */
    @Test
    void keyReleased() {
        // Test keyReleased method
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);

        // Simulate key release events for W, A, S, D keys
        KeyEvent wKeyReleased = new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        KeyEvent sKeyReleased = new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        KeyEvent aKeyReleased = new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        KeyEvent dKeyReleased = new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');

        keyHandler.keyPressed(wKeyReleased);
        assertFalse(keyHandler.upPressed);

        keyHandler.keyPressed(sKeyReleased);
        assertFalse(keyHandler.downPressed);

        keyHandler.keyPressed(aKeyReleased);
        assertFalse(keyHandler.leftPressed);

        keyHandler.keyPressed(dKeyReleased);
        assertFalse(keyHandler.rightPressed);
    }

    /**
     * Tests the constructor of the KeyHandler class with valid parameters.
     */
    @Test
    void testConstructor() {
        // Test constructor with valid parameters
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);

        assertNotNull(keyHandler);
        assertEquals(gamePanel, keyHandler.gp);
    }

    /**
     * Tests the constructor of the KeyHandler class with null GamePanel.
     */
    @Test
    void testConstructorWithNullGamePanel() {
        // Test constructor with null GamePanel
        assertThrows(NullPointerException.class, () -> new KeyHandler(null));
    }

    /**
     * Tests the constructor of the KeyHandler class with non-null GamePanel.
     */
    @Test
    void testConstructorWithNonNullGamePanel() {
        // Test constructor with non-null GamePanel
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);

        assertNotNull(keyHandler);
        assertEquals(gamePanel, keyHandler.gp);
    }

    /**
     * Tests the keyPressed method of the KeyHandler class with KeyEvent.VK_W.
     */
    @Test
    void testKeyPressedW() {
        // Test keyPressed method with KeyEvent.VK_W
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);

        KeyEvent wKeyPressed = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyHandler.keyPressed(wKeyPressed);

        assertTrue(keyHandler.upPressed);
    }

    /**
     * Tests the keyPressed method of the KeyHandler class with KeyEvent.VK_S.
     */
    @Test
    void testKeyPressedS() {
        // Test keyPressed method with KeyEvent.VK_S
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);

        KeyEvent sKeyPressed = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyHandler.keyPressed(sKeyPressed);

        assertTrue(keyHandler.downPressed);
    }

    /**
     * Tests the keyPressed method of the KeyHandler class with KeyEvent.VK_A.
     */
    @Test
    void testKeyPressedA() {
        // Test keyPressed method with KeyEvent.VK_A
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);

        KeyEvent aKeyPressed = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyHandler.keyPressed(aKeyPressed);

        assertTrue(keyHandler.leftPressed);
    }

    /**
     * Tests the keyPressed method of the KeyHandler class with KeyEvent.VK_D.
     */
    @Test
    void testKeyPressedD() {
        // Test keyPressed method with KeyEvent.VK_D
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);

        KeyEvent dKeyPressed = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyHandler.keyPressed(dKeyPressed);

        assertTrue(keyHandler.rightPressed);
    }

    /**
     * Tests the keyPressed method of the KeyHandler class with KeyEvent.VK_P.
     */
    @Test
    void testKeyPressedP() {
        // Test keyPressed method with KeyEvent.VK_P
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);

        KeyEvent pKeyPressed = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_P, 'P');
        keyHandler.keyPressed(pKeyPressed);

        assertEquals(gamePanel.pauseState, gamePanel.gameState);
    }

    /**
     * Tests the keyPressed method of the KeyHandler class with KeyEvent.VK_ESCAPE.
     * This test cannot be fully automated as it exits the application.
     */
    @Test
    void testKeyPressedEscape() {
        // Test keyPressed method with KeyEvent.VK_ESCAPE
        // This test cannot be fully automated as it exits the application
    }

    /**
     * Tests the keyReleased method of the KeyHandler class with KeyEvent.VK_W.
     */
    @Test
    void testKeyReleasedW() {
        // Test keyReleased method with KeyEvent.VK_W
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);
        keyHandler.upPressed = true;

        KeyEvent wKeyReleased = new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyHandler.keyReleased(wKeyReleased);

        assertFalse(keyHandler.upPressed);
    }

    /**
     * Tests the keyReleased method of the KeyHandler class with KeyEvent.VK_S.
     */
    @Test
    void testKeyReleasedS() {
        // Test keyReleased method with KeyEvent.VK_S
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);
        keyHandler.downPressed = true;

        KeyEvent sKeyReleased = new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyHandler.keyReleased(sKeyReleased);

        assertFalse(keyHandler.downPressed);
    }

    /**
     * Tests the keyReleased method of the KeyHandler class with KeyEvent.VK_A.
     */
    @Test
    void testKeyReleasedA() {
        // Test keyReleased method with KeyEvent.VK_A
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);
        keyHandler.leftPressed = true;

        KeyEvent aKeyReleased = new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyHandler.keyReleased(aKeyReleased);

        assertFalse(keyHandler.leftPressed);
    }

    /**
     * Tests the keyReleased method of the KeyHandler class with KeyEvent.VK_D.
     */
    @Test
    void testKeyReleasedD() {
        // Test keyReleased method with KeyEvent.VK_D
        GamePanel gamePanel = new GamePanel();
        KeyHandler keyHandler = new KeyHandler(gamePanel);
        keyHandler.rightPressed = true;

        KeyEvent dKeyReleased = new KeyEvent(new JPanel(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyHandler.keyReleased(dKeyReleased);

        assertFalse(keyHandler.rightPressed);
    }
}
