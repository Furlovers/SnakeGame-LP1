package app.screens.GameScreen.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.screens.db.LevelManager;

public class ScorePanel extends JPanel {

    private int score;

    private JLabel scoreLabel;
    private JLabel levLabel;
    private JLabel minScorLabel;
    private JLabel playerNameLabel;

    private int level = 1;

    private LevelManager levelManager = new LevelManager();

    public ScorePanel(int level, String name) {

        setBackground(Color.black);
        setPreferredSize(new Dimension(300, 600));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.black);

        // Label with the player name
        playerNameLabel = new JLabel("Player: " + name);
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        playerNameLabel.setAlignmentX(CENTER_ALIGNMENT);
        playerNameLabel.setForeground(Color.green);

        // Label with the score
        scoreLabel = new JLabel("Score: " + 0);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        scoreLabel.setForeground(Color.green);

        // Label with the level
        levLabel = new JLabel("Level: " + this.level);
        levLabel.setFont(new Font("Arial", Font.BOLD, 20));
        levLabel.setAlignmentX(CENTER_ALIGNMENT);
        levLabel.setForeground(Color.green);

        // Label with the minimum score
        minScorLabel = new JLabel("Minimum Score: " + levelManager.getMinScore(this.level));
        minScorLabel.setFont(new Font("Arial", Font.BOLD, 20));
        minScorLabel.setAlignmentX(CENTER_ALIGNMENT);
        minScorLabel.setForeground(Color.green);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(playerNameLabel);

        add(Box.createRigidArea(new Dimension(0, 20)));

        add(scoreLabel);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(levLabel);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(minScorLabel);

    }

    public void updateScore(int score) {
        this.score = score;
        scoreLabel.setText("Score: " + score);
    }

    public int getScore() {
        return this.score;
    }

    public void updateLevel(int level) {
        this.level = level;
        levLabel.setText("Level " + this.level);
    }

    public void updateMinScore(int level) {
        minScorLabel.setText("Minimum Score: " + levelManager.getMinScore(level));
    }
}
