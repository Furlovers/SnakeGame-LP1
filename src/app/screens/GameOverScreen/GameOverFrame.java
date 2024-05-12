package app.screens.GameOverScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.screens.GameScreen.Panels.GamePanel;

public class GameOverFrame implements ActionListener {
    private GamePanel gamePanel;
    private int highScore;
    private int score;

    public GameOverFrame(GamePanel gamePanel, Graphics g) {
        
        this.gamePanel = gamePanel;
        this.score = gamePanel.score;
        
        if(score > highScore) {
            highScore = score;
        }
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
        String highScoreString = "High Score: " + this.highScore;
        g.drawString(highScoreString, (gamePanel.getWidth() - g.getFontMetrics().stringWidth(highScoreString)) / 2, gamePanel.getHeight() / 2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
