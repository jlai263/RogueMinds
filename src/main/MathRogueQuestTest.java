package main;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author          Darren Chen
 * @version         1.0
 * @since           1.0
 * This class contains JUnit tests for the MathRogueQuest class.
 * <p>
 * It includes test cases for the main method, ensuring that it initializes the game window
 * and starts the game properly. The tests verify if the window is created, the game panel is added,
 * and the game thread is started.
 */

class MathRogueQuestTest {

	/**
	 * Test the main method of MathRogueQuest class
	 * This test verifies that the main method initializes the game window and starts the game
	 * It checks if the window is created, the game panel is added, and the game thread is started
	 */
    @Test
    void testMain() {
        MathRogueQuest.main(new String[]{});
        assertNotNull(MathRogueQuest.window);
        assertTrue(MathRogueQuest.window.isVisible());
        assertNotNull(MathRogueQuest.window.getContentPane().getComponent(0));
        assertTrue(MathRogueQuest.window.getContentPane().getComponent(0) instanceof GamePanel);
        assertTrue(((GamePanel) MathRogueQuest.window.getContentPane().getComponent(0)).gameThread.isAlive());
    }
}
