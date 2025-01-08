package GameStart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage{
    private JPanel panel;
    public JTextField usernameField;
    private JTextField passwordField;
    private JTextField confirmPasswordField; // Only visible for New Game
    private boolean isNewGame;

    public LoginPage(JPanel panel) {
        this.panel = panel;
        initializeComponents();
    }


    private void initializeComponents() {

        // Initialize text fields
        usernameField = new JTextField("new Username");
        passwordField = new JTextField("Password");
        confirmPasswordField = new JTextField("Confirm Password");


        // Set bounds or layout management here if necessary
        // For example:
        int fieldWidth = 200;
        int fieldHeight = 30;
        int startX = (panel.getWidth() - fieldWidth) / 2; // Center horizontally
        int startY = 300; // Position vertically as needed
        usernameField.setBounds(startX, startY, fieldWidth, fieldHeight);
        passwordField.setBounds(startX, startY + 40, fieldWidth, fieldHeight);
        confirmPasswordField.setBounds(startX, startY + 80, fieldWidth, fieldHeight);

        // Add components to the panel but don't set them visible yet
        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(confirmPasswordField);

        // Initially invisible
        setVisibility(false);
    }

    public void setVisibility(boolean visible) {
        // Toggle visibility based on the isNewGame flag
        usernameField.setVisible(visible);
        passwordField.setVisible(visible);
        confirmPasswordField.setVisible(visible && isNewGame); // Only show confirm for New Game
    }

    public void setIsNewGame(boolean isNewGame) {
        this.isNewGame = isNewGame;
        confirmPasswordField.setVisible(isNewGame); // Adjust confirm password field visibility
    }
    public String getUsernameInput() {
        return usernameField.getText(); // Retrieve the text entered in the username field
    }



    // Additional methods for managing the login process...
}
