
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {

    private JLabel scoreLabel;
    private int score = 0;

    public ScorePanel() {
        scoreLabel = new JLabel("Score: " + 0);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        setBackground(Color.WHITE);
        add(scoreLabel);
    }

    public void setText() {
        score++;
        scoreLabel.setText("Score: " + score);
    }
}
