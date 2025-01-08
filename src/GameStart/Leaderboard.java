package GameStart;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard extends JFrame {
    private List<ScoreEntry> scores;
    private JTextArea leaderboardTextArea;

    public Leaderboard() {
        super("Leaderboard");
        scores = new ArrayList<>();
        leaderboardTextArea = new JTextArea();
        leaderboardTextArea.setEditable(false);
        leaderboardTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(leaderboardTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(230, 230, 230));
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addScore(String playerName, int score) {
        scores.add(new ScoreEntry(playerName, score));
        Collections.sort(scores);
        displayLeaderboard();
    }

    private void displayLeaderboard() {
        StringBuilder leaderboardText = new StringBuilder();
        leaderboardText.append("Rank\tPlayer Name\tScore\n");
        List<ScoreEntry> topScores = getTopScores(10);
        for (int i = 0; i < topScores.size(); i++) {
            ScoreEntry entry = topScores.get(i);
            leaderboardText.append(i + 1).append("\t").append(entry.getPlayerName()).append("\t").append(entry.getScore()).append("\n");
        }
        leaderboardTextArea.setText(leaderboardText.toString());
    }

    private List<ScoreEntry> getTopScores(int numTopScores) {
        return scores.subList(0, Math.min(numTopScores, scores.size()));
    }

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addScore("Player 1", 100);
        leaderboard.addScore("Player 2", 150);
        leaderboard.addScore("Player 3", 80);
        leaderboard.addScore("Player 4", 200);
        leaderboard.addScore("Player 5", 120);
    }
}