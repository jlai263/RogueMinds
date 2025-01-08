package GameStart;

import main.GamePanel;
import main.MathRogueQuest;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Main extends JFrame {
    private ImageIcon titleCardIcon;
    private ImageIcon helpScreenIcon;
    private ImageIcon scaledBackIcon;
    private ImageIcon settingsBackgroundIcon;
    private ImageIcon volumeOffIcon;
    private ImageIcon volumeOnIcon;
    private ImageIcon emptyFrameIcon;
    private boolean showHelpScreen = false;
    private boolean showSettingsPage = false;
    protected boolean showNewGameScreen = false;
    protected boolean showLoadGameScreen = false;
    private boolean showDebugMenu = false;
    private boolean showLeaderboardMenu = false;

    private boolean[][] volumeStates;
    private SoundManager soundManager;
    private DebugMenu debugMenu;
    private LeaderboardMenu leaderboardMenu;
    private GamePanel gamePanel;
    private JTextField newGameUserField, newGamePasswordField, loadGameUserField, loadGamePasswordField, confirmNewGamePasswordField;


    public Main() {
        setTitle("Math Quest: Rogue Minds");
        setSize(1000, 750);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        titleCardIcon = new ImageIcon(getClass().getResource("/loginUi/titleCard.png"));
        helpScreenIcon = new ImageIcon(getClass().getResource("/loginUi/helpScreen.png"));
        settingsBackgroundIcon = new ImageIcon(getClass().getResource("/loginUi/settingScreen.png"));
        emptyFrameIcon = new ImageIcon(getClass().getResource("/loginUi/EmptyFrame.png"));
        volumeOffIcon = new ImageIcon(getClass().getResource("/loginUi/vOff.png"));
        volumeOnIcon = new ImageIcon(getClass().getResource("/loginUi/vOn.png"));
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/loginUi/backButton.png"));
        Image backButton = backIcon.getImage().getScaledInstance((int) (backIcon.getIconWidth() * 0.30), (int) (backIcon.getIconHeight() * 0.75), Image.SCALE_SMOOTH);
        scaledBackIcon = new ImageIcon(backButton);
        volumeStates = new boolean[3][10];
        for (int row = 0; row < volumeStates.length; row++) {
            for (int col = 0; col < volumeStates[row].length; col++) {
                volumeStates[row][col] = col < 5;
            }
        }
        GamePanel gamePanel = new GamePanel();
        soundManager = new SoundManager(volumeStates);
        SettingsPage settingsPage = new SettingsPage(settingsBackgroundIcon, scaledBackIcon, volumeOffIcon, volumeOnIcon, volumeStates, soundManager);
        debugMenu = new DebugMenu(soundManager, gamePanel);
        leaderboardMenu = new LeaderboardMenu(soundManager, gamePanel);

        // Initialize JTextField components here, before JPanel initialization
        
        newGameUserField = new JTextField("Enter a username");
        newGamePasswordField = new JTextField("Enter a password (must contain 1-8 character)");
        confirmNewGamePasswordField = new JTextField("Confirm Password");
        loadGameUserField = new JTextField("Username");
        loadGamePasswordField = new JTextField("Password");

    
        // Configure JTextField components (size, visibility, etc.)
        // This should be done after panel is initialized if you're adding them to the panel
    
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (showSettingsPage) {
                    settingsPage.drawSettingsPage(this, g);
                } else if (showHelpScreen) {
                    helpScreenIcon.paintIcon(this, g, 0, 0);
                    scaledBackIcon.paintIcon(this, g, 650, 125);
                } else if (showNewGameScreen || showLoadGameScreen) {
                    emptyFrameIcon.paintIcon(this, g, 0, 0);
                    scaledBackIcon.paintIcon(this, g, 650, 125); // Assume this is the position for the back button
                } else if (showDebugMenu) {
                    debugMenu.draw(g, this);
                } else if (showLeaderboardMenu) {
                    leaderboardMenu.draw(g);

                } else {
                    titleCardIcon.paintIcon(this, g, 0, 0);
                    drawIcons(g);
                }
            }

            private void drawIcons(Graphics g) {
                ImageIcon trophyIcon = new ImageIcon(getClass().getResource("/loginUi/trophy.png"));
                ImageIcon debugIcon = new ImageIcon(getClass().getResource("/loginUi/debug.png"));
                ImageIcon helpIcon = new ImageIcon(getClass().getResource("/loginUi/help.png"));
            
                Graphics2D g2d = (Graphics2D) g.create(); // Create a copy of the Graphics instance for safe modifications
            
                g2d.setColor(Color.BLACK); // Set color for the squares
            
                // Set the stroke to a thicker line
                Stroke originalStroke = g2d.getStroke(); // Preserve the original stroke
                g2d.setStroke(new BasicStroke(3f)); // 3f defines the thickness of the stroke
            
                int padding = 5; // Padding around icon for the square
                // Drawing black squares around icons with drawRect (for the outline) and thicker stroke
                g2d.drawRect(10 - padding, 10 - padding, trophyIcon.getIconWidth() + 2 * padding, trophyIcon.getIconHeight() + 2 * padding);
                g2d.drawRect(10 - padding, 75 - padding, debugIcon.getIconWidth() + 2 * padding, debugIcon.getIconHeight() + 2 * padding);
                g2d.drawRect(10 - padding, 140 - padding, helpIcon.getIconWidth() + 2 * padding, helpIcon.getIconHeight() + 2 * padding);
            
                // Reset the stroke to the original to avoid affecting other graphics operations
                g2d.setStroke(originalStroke);
            
                // Paint the icons over the squares
                trophyIcon.paintIcon(this, g, 10, 10); // Draw trophy icon
                debugIcon.paintIcon(this, g, 10, 75); // Draw bug icon
                helpIcon.paintIcon(this, g, 10, 140); // Draw help icon
                
                g2d.dispose(); // Dispose of the graphics copy to release system resources
            }
        };
        panel.setLayout(null); // Set the layout manager to null for absolute positioning of text fields
        panel.setBackground(Color.decode("#3db6d1"));
    
        // Set bounds and add text fields to panel here, after JPanel initialization
        // Example bounds setting, adjust according to your layout
        int fieldWidth = 200;
        int fieldHeight = 30;
        int startX = (getWidth() - fieldWidth) / 2; // Center horizontally
        int startY = 300; // Position vertically as needed
        newGameUserField.setBounds(startX, startY, fieldWidth, fieldHeight);
        newGamePasswordField.setBounds(startX, startY + 35, fieldWidth, fieldHeight);
        loadGameUserField.setBounds(startX, startY, fieldWidth, fieldHeight);
        loadGamePasswordField.setBounds(startX, startY + 35, fieldWidth, fieldHeight);
        confirmNewGamePasswordField.setBounds(startX, startY + 70, fieldWidth, fieldHeight);
    
        // Initially make text fields invisible
        newGameUserField.setVisible(false);
        newGamePasswordField.setVisible(false);
        loadGameUserField.setVisible(false);
        loadGamePasswordField.setVisible(false);
        confirmNewGamePasswordField.setVisible(false);

        // Add text fields to the panel
        panel.add(newGameUserField);
        panel.add(newGamePasswordField);
        panel.add(loadGameUserField);
        panel.add(loadGamePasswordField);
        panel.add(confirmNewGamePasswordField);
        
        addMouseListenerAndKeyListeners(panel, settingsPage);
        getContentPane().add(panel);
        setVisible(true);
    }
    

    private void addMouseListenerAndKeyListeners(JPanel panel, SettingsPage settingsPage) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                // Check for back button click across different screens
                if ((showHelpScreen || showSettingsPage || showNewGameScreen || showLoadGameScreen || showDebugMenu || showLeaderboardMenu) && x >= 650 && x <= (650 + scaledBackIcon.getIconWidth()) && y >= 125 && y <= (125 + scaledBackIcon.getIconHeight())) {
                    soundManager.playSound("/sound/selectSF.wav");
                    // Reset screen flags
                    showHelpScreen = false;
                    showSettingsPage = false;
                    showNewGameScreen = false;
                    showLoadGameScreen = false;
                    showDebugMenu = false;
                    showLeaderboardMenu = false;

                    // Hide text fields explicitly
                    newGameUserField.setVisible(false);
                    newGamePasswordField.setVisible(false);
                    confirmNewGamePasswordField.setVisible(false);
                    loadGameUserField.setVisible(false);
                    loadGamePasswordField.setVisible(false);
        
                    // Repaint the panel to reflect changes
                    panel.repaint();
                } else if (showSettingsPage) {
                    settingsPage.handleClick(e.getX(), e.getY(), panel);
                } else if (showDebugMenu) {
                    debugMenu.handleClick(e.getX(), e.getY(), panel);

                }else if (!showHelpScreen && !showSettingsPage && !showNewGameScreen && !showLoadGameScreen) {
                    checkIconClick(x, y);
                }
            }

            private void checkIconClick(int x, int y) {
                if (x >= 10 && x <= 60 && y >= 10 && y <= 60) {
                    soundManager.playSound("/sound/selectSF.wav");
                    toggleLeaderboardMenu();
                    String[] leaderBoard = new String[0];
                    Leaderboard.main(leaderBoard);
                } else if (x >= 30 && x <= 220 && y >= 585 && y <= 645) {
                    toggleNewGameScreen();
                } else if (x >= 287 && x <= 477 && y >= 587 && y <= 647) {
                    toggleLoadGameScreen();
                } else if (x >= 10 && x <= 60 && y >= 80 && y <= 130) {
                    soundManager.playSound("/sound/selectSF.wav");
                    toggleDebugMenu();
                } else if (x >= 10 && x <= 60 && y >= 150 && y <= 200) {
                    soundManager.playSound("/sound/selectSF.wav");
                    toggleHelpScreen();
                } else if (x >= 539 && x <= 729 && y >= 587 && y <= 647) {
                    soundManager.playSound("/sound/selectSF.wav");
                    toggleSettingsPage();
                } else if (x >= 781 && x <= 971 && y >= 587 && y <= 647) {
                    System.exit(0);
                }
            }
            private void toggleDebugMenu() {
                showDebugMenu = !showDebugMenu;
                showSettingsPage = false;
                showHelpScreen = false;
                showNewGameScreen = false;
                showLoadGameScreen = false;
                panel.repaint();
            }
            private void toggleLeaderboardMenu() {
                showLeaderboardMenu = !showLeaderboardMenu;
                showSettingsPage = false;
                showHelpScreen = false;
                showNewGameScreen = false;
                showLoadGameScreen = false;
                panel.repaint();
            }
            private void toggleSettingsPage() {
                showSettingsPage = !showSettingsPage;
                showHelpScreen = false;
                showNewGameScreen = false;
                showLoadGameScreen = false;
                panel.repaint();
            }

            private void toggleHelpScreen() {
                showHelpScreen = !showHelpScreen;
                showSettingsPage = false;
                showNewGameScreen = false;
                showLoadGameScreen = false;
                panel.repaint();
            }

            private void toggleNewGameScreen() {
                String newGame[] = new String[0];

                Login.main(newGame);


                showNewGameScreen = !showNewGameScreen;
                soundManager.playSound("/sound/selectSF.wav");
                // Ensure other screens are hidden
                showHelpScreen = false;
                showSettingsPage = false;
                showLoadGameScreen = false;
                // Update visibility and bounds of text fields for the New Game screen
                updateTextFieldVisibilityAndPosition();


            }
            
            private void toggleLoadGameScreen() {
                String loadGame[] = new String[0];

                showLoadGameScreen = true;

                Login.main(loadGame);
                ;
                soundManager.playSound("/sound/selectSF.wav");
                // Ensure other screens are hidden
                showHelpScreen = false;
                showSettingsPage = false;
                showNewGameScreen = false;
                // Update visibility and bounds of text fields for the Load Game screen
                updateTextFieldVisibilityAndPosition();
            }
            
            private void updateTextFieldVisibilityAndPosition() {
                int fieldWidth = 200;
                int fieldHeight = 30;
                // Use Main.this to refer to the enclosing JFrame instance
                int startX = (Main.this.getWidth() - fieldWidth) / 2; // Center horizontally
                int startY = 250; // Adjusted for additional field, position vertically as needed
            
                // Adjusting visibility based on the current screen
                newGameUserField.setVisible(showNewGameScreen);
                newGamePasswordField.setVisible(showNewGameScreen);
                confirmNewGamePasswordField.setVisible(showNewGameScreen); // Only for New Game
                loadGameUserField.setVisible(showLoadGameScreen);
                loadGamePasswordField.setVisible(showLoadGameScreen);
            
                // Setting bounds; adjust startY for each subsequent field to stack them vertically
                if (showNewGameScreen) {
                    newGameUserField.setBounds(startX, startY, fieldWidth, fieldHeight);
                    confirmNewGamePasswordField.setBounds(startX, startY + 40, fieldWidth, fieldHeight); // Confirm Password field
                    newGamePasswordField.setBounds(startX, startY + 80, fieldWidth, fieldHeight);
                }
                if (showLoadGameScreen) {
                    loadGameUserField.setBounds(startX, startY, fieldWidth, fieldHeight);
                    loadGamePasswordField.setBounds(startX, startY + 40, fieldWidth, fieldHeight);
                }
            
                // Ensure fields related to the other screen are not visible
                if (!showNewGameScreen) {
                    confirmNewGamePasswordField.setVisible(false); // Hide Confirm Password field when not on New Game screen
                }
            
                // Use Main.this to refer to the JFrame for these methods
                Main.this.validate();
                Main.this.repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}

