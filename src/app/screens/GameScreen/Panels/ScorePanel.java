package app.screens.GameScreen.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {

    private JLabel scoreLabel;
    private int score = 0;
    private JLabel levLabel;

    public ScorePanel(int level) {
        scoreLabel = new JLabel("Score: " + 0);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        levLabel = new JLabel("Level: " + level);
        levLabel.setFont(new Font("Arial", Font.BOLD, 20));

        setBackground(Color.WHITE);
        add(scoreLabel);
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(levLabel);
    }

    public void setText() {
        score++;
        scoreLabel.setText("Score: " + score);
    }

    public int getScore() {
        return this.score;
    }
}
