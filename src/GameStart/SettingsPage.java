package GameStart;

import javax.swing.*;
import java.awt.*;

/**
 * @author      Justin Edwin Lee
 * @version     1.3
 * @since       1.0
 * Represents the settings page in the game, responsible for displaying and managing game settings such as volume control.
 * <p>
 * This class uses a combination of ImageIcons to represent the volume controls visually
 * and integrates with a SoundManager to handle actual volume adjustments and sound feedback.
 */

public class SettingsPage {
    private ImageIcon settingsBackgroundIcon;
    private ImageIcon scaledBackIcon;
    private ImageIcon volumeOffIcon;
    private ImageIcon volumeOnIcon;
    private boolean[][] volumeStates;
    private SoundManager soundManager; // Reference to SoundManager

      /**
     * Constructs a new {@code SettingsPage} with the specified images and volume state.
     *
     * @param settingsBgIcon The background image for the settings page.
     * @param scaledBack The image for the back button.
     * @param vOffIcon The image for volume off state.
     * @param vOnIcon The image for volume on state.
     * @param volStates A matrix representing the on/off state of volume levels.
     * @param soundManager The {@link SoundManager} for managing sound effects and volume.
     */
    public SettingsPage(ImageIcon settingsBgIcon, ImageIcon scaledBack, ImageIcon vOffIcon, ImageIcon vOnIcon, boolean[][] volStates, SoundManager soundManager) {
        this.settingsBackgroundIcon = settingsBgIcon;
        this.scaledBackIcon = scaledBack;
        this.volumeOffIcon = vOffIcon;
        this.volumeOnIcon = vOnIcon;
        this.volumeStates = volStates;
        this.soundManager = soundManager; // Initialize SoundManager
    }
    /**
     * Draws the settings page on the specified component.
     * 
     * @param component The {@link JComponent} on which the settings page is drawn.
     * @param g The {@link Graphics} context used for drawing.
     */
    public void drawSettingsPage(JComponent component, Graphics g) {
        settingsBackgroundIcon.paintIcon(component, g, 0, 0);
        scaledBackIcon.paintIcon(component, g, 650, 125);
        int startX = 525;
        int startY = 255;
        for (int i = 0; i < 3; i++) {
            for (int col = 0; col < 10; col++) {
                ImageIcon iconToDraw = volumeStates[i][col] ? volumeOnIcon : volumeOffIcon;
                iconToDraw.paintIcon(component, g, startX + col * (volumeOffIcon.getIconWidth() + 2), startY + i * (volumeOffIcon.getIconHeight() + 48));
            }
        }
    }
    /**
     * Handles click events on the settings page, specifically for adjusting volume controls.
     * 
     * @param x The x-coordinate of the mouse click.
     * @param y The y-coordinate of the mouse click.
     * @param component The component on which the settings page is being drawn. Used to trigger repaint.
     */
    public void handleClick(int x, int y, JComponent component) {
        int startX = 525;
        int startY = 255;
        int gap = 48;
        int rowHeight = volumeOffIcon.getIconHeight() + gap;
        for (int row = 0; row < volumeStates.length; row++) {
            for (int col = 0; col < volumeStates[row].length; col++) {
                int iconX = startX + col * (volumeOffIcon.getIconWidth() + 2);
                int iconY = startY + row * rowHeight;
                if (x >= iconX && x <= iconX + volumeOffIcon.getIconWidth() &&
                    y >= iconY && y <= iconY + volumeOffIcon.getIconHeight()) {
                    for (int i = 0; i <= col; i++) {
                        volumeStates[row][i] = true;
                    }
                    for (int i = col + 1; i < volumeStates[row].length; i++) {
                        volumeStates[row][i] = false;
                    }
                    soundManager.playSound("/sound/selectSF.wav"); // Use SoundManager to play sound
                    component.repaint();
                    return; // Stop further processing
                }
            }
        }
    }
}
