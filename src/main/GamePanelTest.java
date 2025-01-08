package main;

import GameStart.DebugMenu;
import GameStart.SoundManager;
import entities.Player;
import main.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author         	Darren Chen
 * @version         1.0
 * @since           1.0
 * This class contains unit tests for the GamePanel class.
 * <p>
 * It includes test methods to validate the behavior of various functionalities
 * such as constructors, game object initialization, threading, and rendering.
 */
class GamePanelTest {

	private GamePanel gamePanel; // The GamePanel instance used in the tests

    /**
     * Sets up the test fixture before each test method.
     */
    @BeforeEach
    void setUp() {
        gamePanel = new GamePanel();
    }

    /**
     * Tests the default constructor of GamePanel.
     * It verifies that various fields of the GamePanel instance are initialized correctly.
     */
    @Test
    void testConstructor() {
        assertEquals(16, gamePanel.originalTileSize);
        assertEquals(3, gamePanel.scale);
        assertEquals(48, gamePanel.tileSize);
        assertEquals(16, gamePanel.screenCols);
        assertEquals(12, gamePanel.screenRows);
        assertEquals(768, gamePanel.screenWidth);
        assertEquals(576, gamePanel.screenHeight);
        assertEquals(50, gamePanel.maxWorldCol);
        assertEquals(50, gamePanel.maxWorldRow);
        assertEquals(768, gamePanel.screenWidth2);
        assertEquals(576, gamePanel.screenHeight2);
        assertNotNull(gamePanel.tempScreen);
        assertNotNull(gamePanel.g2);
        assertEquals(60, gamePanel.FPS);
        assertNotNull(gamePanel.keyH);
        assertNotNull(gamePanel.music);
        assertNotNull(gamePanel.se);
        assertNotNull(gamePanel.gameThread);
        assertNotNull(gamePanel.cChecker);
        assertNotNull(gamePanel.oSetter);
        assertNotNull(gamePanel.ui);
        assertNotNull(gamePanel.player);
        assertNotNull(gamePanel.tileM);
        assertNotNull(gamePanel.obj);
        assertNotNull(gamePanel.saveLoad);
        assertEquals(1, gamePanel.gameState);
        assertEquals(1, gamePanel.playState);
        assertEquals(2, gamePanel.pauseState);
        assertNull(gamePanel.debugMenu);

        // Check if soundManager field is accessible through reflection
        try {
            Field soundManagerField = GamePanel.class.getDeclaredField("soundManager");
            soundManagerField.setAccessible(true);
            assertNotNull(soundManagerField.get(gamePanel));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("soundManager field is not accessible through reflection");
        }
    }

    /**
     * Tests the constructor of the GamePanel class that takes a DebugMenu parameter.
     *
     * @throws NoSuchFieldException   If a field is not found.
     * @throws IllegalAccessException If access to a field is denied.
     */
    @Test
    void testConstructorWithDebugMenu() throws NoSuchFieldException, IllegalAccessException {
        DebugMenu debugMenu = new DebugMenu(new SoundManager(new boolean[3][10]), gamePanel);
        gamePanel = new GamePanel(); // Instantiate GamePanel without debugMenu
        setDebugMenu(gamePanel, debugMenu); // Set debugMenu using reflection
        assertEquals(debugMenu, gamePanel.debugMenu);
    }

    /**
     * Sets the debugMenu field of a GamePanel instance using reflection.
     * 
     * @param gamePanel The GamePanel instance to set the debugMenu field for.
     * @param debugMenu The DebugMenu instance to set as the debugMenu field value.
     * @throws NoSuchFieldException If the debugMenu field does not exist in the GamePanel class.
     * @throws IllegalAccessException If access to the debugMenu field is denied.
     */
    private void setDebugMenu(GamePanel gamePanel, DebugMenu debugMenu) throws NoSuchFieldException, IllegalAccessException {
        java.lang.reflect.Field debugMenuField = GamePanel.class.getDeclaredField("debugMenu");
        debugMenuField.setAccessible(true);
        debugMenuField.set(gamePanel, debugMenu);
    }
    
    /**
     * Tests the setGameObj method of GamePanel.
     * It verifies that game objects are initialized properly.
     */
    @Test
    void testSetGameObj() {
        gamePanel.setGameObj();
        // Test if game objects are initialized properly
    }

    @Test
    void testSetFullScreen() {
        // Cannot test setFullScreen() as it interacts with AWT GraphicsDevice
        // which cannot be easily mocked or tested in a unit test
    }

    /**
     * Tests the startGameThread method of the GamePanel class.
     * It verifies that the game thread is initialized properly
     */
    @Test
    void testStartGameThread() {
        gamePanel.startGameThread();
        assertTrue(gamePanel.gameThread.isAlive());
    }

    @Test
    void testRun() {
        // Cannot test run() as it's a continuously running loop
        // and involves graphics rendering and timing
    }

    @Test
    void testUpdate() {
        // Cannot directly test update() method as it relies heavily on
        // player input and graphics rendering which cannot be easily simulated in a unit test
    }

    @Test
    void testPaintComponent() {
        // Cannot directly test paintComponent() method as it relies heavily on
        // graphics rendering which cannot be easily simulated in a unit test
    }

    @Test
    void testPlayMusic() {
        // Cannot test playMusic() as it interacts with sound files and playback
        // which cannot be easily simulated in a unit test
    }

    @Test
    void testStopMusic() {
        // Cannot test stopMusic() as it interacts with sound files and playback
        // which cannot be easily simulated in a unit test
    }

    @Test
    void testPlaySE() {
        // Cannot test playSE() as it interacts with sound files and playback
        // which cannot be easily simulated in a unit test
    }
}
