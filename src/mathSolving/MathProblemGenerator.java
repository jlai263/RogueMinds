package mathSolving;
import javax.swing.*;
import javax.swing.text.*;
import entities.Player;
import java.awt.*;
import java.awt.event.*;
import main.UI;

/**
 * A JFrame class that generates math problems for the player to solve.
 * The problems are displayed in a window with an input field for the answer and a submit button.
 * If the answer is correct, the player gains a coin. Otherwise, the correct answer is displayed.
 * @author Justin Lai
 * @version 1.1
 * @since 1.0
 */

public class MathProblemGenerator extends JFrame {
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel correctCountLabel; // Label to display correct answers count
    private int num1, num2, answer;
    private int correctAnswersCount; // Counter for correct answers

    /**
     * Constructs a MathProblemGenerator object.
     * Initializes the JFrame properties, sets the layout, and creates the components.
     * Also generates the first math problem.
     */
    public MathProblemGenerator() {
        setTitle("Answer the Questions!");
        setSize(300, 200); // Increased height to accommodate the correct count label
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.DARK_GRAY);

        questionLabel = new JLabel();
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBounds(20, 20, 250, 20);
        add(questionLabel);

        answerField = new JTextField();
        answerField.setBackground(Color.LIGHT_GRAY);
        answerField.setBounds(20, 70, 100, 20);
        ((AbstractDocument) answerField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (newText.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        add(answerField);

        submitButton = new JButton("Submit");
        submitButton.setBackground(Color.WHITE);
        submitButton.setForeground(Color.BLACK);
        submitButton.setBounds(130, 70, 80, 20);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        correctCountLabel = new JLabel("You have answered: " + correctAnswersCount + "/3 questions correctly"); // Initialize correct count label
        correctCountLabel.setForeground(Color.WHITE);
        correctCountLabel.setBounds(20, 100, 250, 20);
        add(correctCountLabel);

        createQuestion();

        setLocationRelativeTo(null);
    }
    /**
     * Generates a new math problem.
     * Randomly selects addition or subtraction and two numbers between 0 and 20.
     * Displays the problem in the question label.
     */
    public void createQuestion() {
    	if (Player.difficulty==1) {
            num1 = (int) (Math.random() * 10);
            num2 = (int) (Math.random() * 10);
    	}
    	else if (Player.difficulty == 2){
            num1 = (int) (Math.random() * 20);
            num2 = (int) (Math.random() * 20);
    	}
    	else if (Player.difficulty == 3){
            num1 = (int) (Math.random() * 25);
            num2 = (int) (Math.random() * 25);
    	}
    	else if (Player.difficulty == 4){
            num1 = (int) (Math.random() * 50);
            num2 = (int) (Math.random() * 50);
    	}

        if (num1 < num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }

        if (Math.random() < 0.5) {
            answer = num1 + num2;
            questionLabel.setText(num1 + " + " + num2 + " = ?");
        } else {
            answer = num1 - num2;
            questionLabel.setText(num1 + " - " + num2 + " = ?");
        }

        answerField.setText("");
    }
    /**
     * Checks the user's answer and provides feedback.
     * If the answer is correct, the player gains a coin.
     * If the answer is incorrect, the correct answer is displayed.
     */
    public void checkAnswer() {
        try {
            int userAnswer = Integer.parseInt(answerField.getText());
            if (userAnswer == answer) {
                JOptionPane.showMessageDialog(this, "Correct!", "Result", JOptionPane.INFORMATION_MESSAGE);
                correctAnswersCount++; // Increment correct answers count
                
                Player.answeringQuestion = true;
                updateCorrectCountLabel(); // Update correct count label
                if (correctAnswersCount == 3) { // Check if 3 correct answers have been reached
                    Player.hasCoin++;
                    Player.gp.playSE(1);
                    Player.answeringQuestion = false;
                    Player.keyH.upPressed = false;
                    Player.keyH.downPressed = false;
                    Player.keyH.leftPressed = false;
                    Player.keyH.rightPressed = false;
                	dispose(); // Close the window if 3 correct answers are reached
                	Player.difficulty++;
                } else {
                    createQuestion(); // Generate new question
                    answerField.setText(""); // Clear answer field
                }
     
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect! The correct answer is " + answer, "Result", JOptionPane.ERROR_MESSAGE);
                UI.lives--;
                if(UI.lives==0) {
                	Player.answeringQuestion = false;

                	dispose(); 
                }
                else {
                    

                    answerField.setText("");
                    createQuestion();
                }

            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Updates the correct count label to display the number of correct answers out of 3.
     */
    public void updateCorrectCountLabel() {
        correctCountLabel.setText("You have answered: " + correctAnswersCount + "/3 questions correctly"); // Update correct count label
    }

    public static void main(String[] args) {
    	//Run the 2 commands below to start the math questions
        MathProblemGenerator mathProblemGenerator = new MathProblemGenerator();
        mathProblemGenerator.setVisible(true);
    }
}




