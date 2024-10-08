package app.screens.GameScreen.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.db.ConnFactory;
import app.db.User;

public class ScorePanel extends JPanel {

    private int score;

    private JLabel scoreLabel;
    private JLabel playerNameLabel;
    private JLabel personalHighScore;
    private JLabel highScoreLabel;

    private Connection conn = null;

    public ScorePanel(User user) {

        setBackground(Color.black);
        setPreferredSize(new Dimension(300, 600));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.black);

        // Label with the player name
        playerNameLabel = new JLabel("Player: " + user.getName());
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        playerNameLabel.setAlignmentX(CENTER_ALIGNMENT);
        playerNameLabel.setForeground(Color.green);

        // Label with the score
        scoreLabel = new JLabel("Score: " + 0);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        scoreLabel.setForeground(Color.orange);


        // Label with the personal high score
        personalHighScore = new JLabel("Personal High Score: " + user.getHighScore());
        personalHighScore.setFont(new Font("Arial", Font.BOLD, 20));
        personalHighScore.setAlignmentX(CENTER_ALIGNMENT);
        personalHighScore.setForeground(Color.green);

        // Label with the high score
        try {
            conn = ConnFactory.getConnection();
            if (User.getMaxHighScore(conn) == 0) {
                highScoreLabel = new JLabel("High Score: 0");
            } else {
                highScoreLabel = new JLabel("High Score: " + User.getMaxHighScore(conn) + " by " + User.getPlayerWithMaxHighScore(conn));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        highScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        highScoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        highScoreLabel.setForeground(Color.green);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(playerNameLabel);


        add(Box.createRigidArea(new Dimension(0, 20)));
        add(personalHighScore);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(highScoreLabel);

        add(Box.createRigidArea(new Dimension(0, 40)));
        add(scoreLabel);
    }

    public void updateScore(int score) {
        this.score = score;
        scoreLabel.setText("Score: " + score);
    }

    public int getScore() {
        return this.score;
    }

    public void updatePersonalHighScore(int highScore) {
        personalHighScore.setText("Personal High Score: " + highScore);
    }
}
