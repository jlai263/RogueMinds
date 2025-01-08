package GameStart;

import main.MathRogueQuest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    // Define a static user database using a HashMap
    static UserDatabase ud = new UserDatabase();

    // Populate the user database with some dummy data
    static {
        ud.addUser("user1", "password1");
        ud.addUser("user2", "password2");
        // Add more users if needed
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Screen");
        frame.setLayout(null);
        JLabel userLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField userTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        userLabel.setBounds(20, 20, 80, 25);
        passwordLabel.setBounds(20, 50, 80, 25);
        userTextField.setBounds(100, 20, 165, 25);
        passwordField.setBounds(100, 50, 165, 25);
        loginButton.setBounds(100, 80, 80, 25);

        frame.add(userLabel);
        frame.add(passwordLabel);
        frame.add(userTextField);
        frame.add(passwordField);
        frame.add(loginButton);

        // Set the size of the frame
        frame.setSize(400, 250);

        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the position of the frame
        int xPos = (screenSize.width - frame.getWidth()) / 2;
        int yPos = (screenSize.height - frame.getHeight()) / 2;

        // Set the location of the frame to be centered
        frame.setLocation(xPos, yPos);

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the frame visible
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passwordField.getPassword());


                // Check if the user exists in the database
                Main game = new Main();
                String newGame[] = new String[0];
                System.out.println(game.showLoadGameScreen);
                if (!ud.containsUser(username)){
                    ud.addUser(username, password);
                    JOptionPane.showMessageDialog(frame, "Successfully created new user!");
                        frame.setVisible(false);
                        game.setVisible(false);

                        MathRogueQuest.main(newGame);

                }
                else if (ud.containsUser(username)) {
                    User user = ud.getUser(username);
                    if (password.equals(user.getPassword())) {
                        JOptionPane.showMessageDialog(frame, "Login Successful");
                        frame.setVisible(false);
                        game.setVisible(false);

                        MathRogueQuest.main(newGame);

                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect Password");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "User not found");
                }
            }
        });
    }
}