package app.screens.GameScreen.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {

    private int score;

    private JLabel scoreLabel;
    private JLabel levLabel;

    public ScorePanel(int level) {
        scoreLabel = new JLabel("Score: " + 0);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));

        levLabel = new JLabel("Level: " + level);
        levLabel.setFont(new Font("Arial", Font.BOLD, 20));

        add(scoreLabel);
        setBackground(Color.WHITE);
        
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(levLabel);
    }

    public void updateScore(int score) {
        this.score = score;
        scoreLabel.setText("Score: " + score);
    }

    public int getScore() {
        return this.score;
    }
}
