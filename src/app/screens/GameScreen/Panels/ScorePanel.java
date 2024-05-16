package app.screens.GameScreen.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.screens.db.LevelManager;

public class ScorePanel extends JPanel {

    private int score;

    private JLabel scoreLabel;
    private JLabel levLabel;
    private JLabel minScorLabel;
    private int level = 1;
    private LevelManager levelManager = new LevelManager();

    public ScorePanel(int level) {
        scoreLabel = new JLabel("Score: " + 0);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));

        levLabel = new JLabel("Level: " + this.level);
        levLabel.setFont(new Font("Arial", Font.BOLD, 20));

        minScorLabel = new JLabel("Minimum Score: " + levelManager.getMinScore(this.level));
        minScorLabel.setFont(new Font("Arial", Font.BOLD, 20));

        add(scoreLabel);

        add(Box.createRigidArea(new Dimension(20, 0)));
        add(levLabel);

        add(Box.createRigidArea(new Dimension(20, 0)));
        add(minScorLabel);

        setBackground(Color.white);
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
