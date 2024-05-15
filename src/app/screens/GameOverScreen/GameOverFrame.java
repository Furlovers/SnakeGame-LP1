package app.screens.GameOverScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.screens.GameScreen.Panels.GamePanel;
import app.screens.db.HighScoreManager;

public class GameOverFrame implements ActionListener {
    private GamePanel gamePanel;
    private int score;
    private int highScore;

    public GameOverFrame(GamePanel gamePanel, Graphics g) {
        
        this.gamePanel = gamePanel;
        this.score = gamePanel.score;
        HighScoreManager.updateHighScore(score);
        this.highScore = HighScoreManager.getHighScore();
        
        draw(g);
    }

    // draws the game over frame
    public void draw(Graphics g) {
                
        // screen Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());

        // Game Over String
        String gameOverMsg = "Game Over";
        g.setFont(new Font("Arial", Font.BOLD, 50));

        // draws game over string
        g.setColor(Color.green);
        g.drawString(gameOverMsg, (gamePanel.getWidth() - g.getFontMetrics().stringWidth(gameOverMsg)) / 2, gamePanel.getHeight() / 3);

        // Score String
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String scoreString = "Score: " + score;
        g.drawString(scoreString, (gamePanel.getWidth() - g.getFontMetrics().stringWidth(scoreString)) / 2, gamePanel.getHeight() / 2 - 50);

        // High score string
        String highScoreString = "High Score: " + highScore;
        g.drawString(highScoreString, (gamePanel.getWidth() - g.getFontMetrics().stringWidth(highScoreString)) / 2, gamePanel.getHeight() / 2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
