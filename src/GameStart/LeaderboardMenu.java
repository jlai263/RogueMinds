package GameStart;

import main.GamePanel;
import javax.swing.*;
import java.awt.*;

public class LeaderboardMenu {
    private ImageIcon debugMenuIcon;
    private ImageIcon backButtonIcon;
    private SoundManager soundManager;
    private GamePanel gamePanel;

    // Define squares positions and sizes

    // State tracking for features

    private String[] columnNames = {"Name", "Time"};
    private Object[][] data = {
            {"Justin H", "1:30"},
            {"Ryan", "2:00"},
            {"Gurshaan", "2:15"},
            {"Darren", "2:35"},
            {"Justin L", "2:55"}
    };

    public LeaderboardMenu(SoundManager soundManager, GamePanel gamePanel) {
        this.soundManager = soundManager;
        this.gamePanel = gamePanel;
        // Load the icons
        debugMenuIcon = new ImageIcon(getClass().getResource("/loginUi/EmptyFrame.png"));
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/loginUi/backButton.png"));
        Image backButton = backIcon.getImage().getScaledInstance((int) (backIcon.getIconWidth() * 0.30), (int) (backIcon.getIconHeight() * 0.75), Image.SCALE_SMOOTH);
        backButtonIcon = new ImageIcon(backButton);
    }

    public void draw(Graphics g) {
        // Create the leaderboard table
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        // Adjust frame size to match table size
        Dimension tableSize = table.getPreferredSize();
        int tableWidth = (int) tableSize.getWidth();
        int tableHeight = (int) tableSize.getHeight();

        // Create and configure the JFrame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Use absolute layout for precise positioning
        frame.setSize(tableWidth + 50, tableHeight + 90); // Add some padding
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Add the scroll pane to the frame
        scrollPane.setBounds(0, 0, tableWidth+50, tableHeight+50);
        frame.add(scrollPane);

        // Draw the background and back button
        debugMenuIcon.paintIcon(gamePanel, g, 0, 0);
        backButtonIcon.paintIcon(gamePanel, g, 650, 125);

        // Make the frame visible
        frame.setVisible(true);
    }
}
