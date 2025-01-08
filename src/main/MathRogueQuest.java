package main;

import javax.swing.*;

/**
 * Main class for the MathQuest: Rogue Minds game.
 * <p>
 * This class initializes the game window and sets up the primary game panel where all game activities occur.
 * It configures the window properties, including size, title, and close operation, and starts the game thread
 * to begin game processing. This is the starting point for launching the game.
 * 
 * @author Gurshaan Gill
 * @version 1.2
 * @since 1.0
 */

public class MathRogueQuest {

    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame("Simple 2D Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("MathQuest: Rogue Minds");

        GamePanel panel = new GamePanel();
        window.add(panel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.setGameObj();
        panel.startGameThread();

    }
}
