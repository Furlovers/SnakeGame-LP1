package app.screens.GameOverScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import app.screens.GameScreen.Panels.GamePanel;
import app.screens.db.ConnFactory;

public class GameOverFrame implements ActionListener {
    private GamePanel gamePanel;
    private int score;
    private int highScore;

    private Connection conn = null;

    public GameOverFrame(GamePanel gamePanel, Graphics g) {

        this.gamePanel = gamePanel;
        this.score = gamePanel.score;
        gamePanel.getUser().setHighScore(score);
        this.highScore = gamePanel.getUser().getHighScore();

        draw(g);
    }

    // draws the game over frame
    public void draw(Graphics g) {

        // screen Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());

        // Game Over String
        if (gamePanel.getLevel() > 4) {
            String gameWinMsg = "You Win!";
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.setColor(Color.green);
            g.drawString(gameWinMsg, (gamePanel.getWidth() - g.getFontMetrics().stringWidth(gameWinMsg)) / 2,
                    gamePanel.getHeight() / 3);
        } else {
            String gameOverMsg = "Game Over";
            g.setFont(new Font("Arial", Font.BOLD, 50));

            // draws game over string
            g.setColor(Color.green);
            g.drawString(gameOverMsg, (gamePanel.getWidth() - g.getFontMetrics().stringWidth(gameOverMsg)) / 2,
                    gamePanel.getHeight() / 3);
        }

        // Score String
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String scoreString = "Score: " + score;
        g.drawString(scoreString, (gamePanel.getWidth() - g.getFontMetrics().stringWidth(scoreString)) / 2,
                gamePanel.getHeight() / 2 - 50);

        // High score string
        String highScoreString = "High Score: " + highScore;
        g.drawString(highScoreString, (gamePanel.getWidth() - g.getFontMetrics().stringWidth(highScoreString)) / 2,
                gamePanel.getHeight() / 2);

        try {
            ConnFactory bd = new ConnFactory();
            conn = bd.getConnection();

            gamePanel.getUser().updateUser(conn);
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    System.out.println(e1.getStackTrace());
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
