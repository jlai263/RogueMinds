package GameStart;

import main.GamePanel;
import javax.swing.*;
import java.awt.*;

public class DebugMenu {
    private ImageIcon debugMenuIcon;
    private ImageIcon backButtonIcon;
    private SoundManager soundManager;
    private GamePanel gamePanel;

    // Define squares positions and sizes
    private final int squareWidth = 45;
    private final int squareHeight = 45;
    private final int squareX = 530; // Adjust these positions as needed
    private final int squareY1 = 295; // Y position for the first square
    private final int squareY2 = squareY1 + squareHeight + 43; // Y position for the second square, with some spacing

    // State tracking for features
    private boolean infiniteCoinsOn = false;
    private boolean hyperspeedOn = false;

    public DebugMenu(SoundManager soundManager, GamePanel gamePanel) {
        this.soundManager = soundManager;
        this.gamePanel = gamePanel;
        // Load the icons
        debugMenuIcon = new ImageIcon(getClass().getResource("/loginUi/debugMenu.png"));
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/loginUi/backButton.png"));
        Image backButton = backIcon.getImage().getScaledInstance((int) (backIcon.getIconWidth() * 0.30), (int) (backIcon.getIconHeight() * 0.75), Image.SCALE_SMOOTH);
        backButtonIcon = new ImageIcon(backButton);
    }

    public void draw(Graphics g, Component component) {
        // Draw the background and back button
        debugMenuIcon.paintIcon(component, g, 0, 0);
        backButtonIcon.paintIcon(component, g, 650, 125);

        // Draw two squares based on their state
        g.setColor(Color.WHITE);
        if (infiniteCoinsOn) {
            g.fillRect(squareX, squareY1, squareWidth, squareHeight); // Filled for "Infinite Coins" activated
        } else {
            g.drawRect(squareX, squareY1, squareWidth, squareHeight); // Empty for "Infinite Coins" deactivated
        }
        if (hyperspeedOn) {
            g.fillRect(squareX, squareY2, squareWidth, squareHeight); // Filled for "Hyperspeed" activated
        } else {
            g.drawRect(squareX, squareY2, squareWidth, squareHeight); // Empty for "Hyperspeed" deactivated
        }
    }

    public void handleClick(int x, int y, Component component) {
        // Check and toggle for the first square (Infinite Coins)
        if (x >= squareX && x <= squareX + squareWidth && y >= squareY1 && y <= squareY1 + squareHeight) {
            infiniteCoinsOn = !infiniteCoinsOn;
            soundManager.playSound("/sound/selectSF.wav"); // Play sound here
            if (infiniteCoinsOn) {
                gamePanel.player.hasCoin = 99;
                System.out.println(gamePanel.player.hasCoin);
            } else {
                gamePanel.player.hasCoin = 0; // Reset to default when toggling off
            }
            System.out.println("Infinite Coins: " + (infiniteCoinsOn ? "Activated" : "Deactivated"));
            component.repaint(); // Refresh the UI to reflect the change
        }
        // Check and toggle for the second square (Hyperspeed)
        else if (x >= squareX && x <= squareX + squareWidth && y >= squareY2 && y <= squareY2 + squareHeight) {
            hyperspeedOn = !hyperspeedOn;
            soundManager.playSound("/sound/selectSF.wav"); // Play sound here
            // Here you can add the logic for toggling hyperspeed in the player.
            System.out.println("Hyperspeed: " + (hyperspeedOn ? "Activated" : "Deactivated"));
            component.repaint(); // Refresh the UI to reflect the change
        }
    }
}
