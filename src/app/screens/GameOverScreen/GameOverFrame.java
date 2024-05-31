package app.screens.GameOverScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import app.db.ConnFactory;
import app.db.User;
import app.screens.GameScreen.Panels.GamePanel;

public class GameOverFrame implements ActionListener {
    private GamePanel gamePanel;
    private int score;
    private int personalHighScore;

    private Connection conn = null;

    public GameOverFrame(GamePanel gamePanel, Graphics g) {

        // sets the game over frame variables
        this.gamePanel = gamePanel;
        this.score = gamePanel.score;
        this.personalHighScore = gamePanel.getUser().getHighScore();

        // sets the user's high score if the current score is higher
        gamePanel.getUser().setHighScore(score);

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
            g.setColor(Color.red);
            g.drawString(gameOverMsg, (gamePanel.getWidth() - g.getFontMetrics().stringWidth(gameOverMsg)) / 2,
                    gamePanel.getHeight() / 3);
        }

        // Score String
        g.setColor(Color.green);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String scoreString = "Score: " + score;
        g.drawString(scoreString, (gamePanel.getWidth() - g.getFontMetrics().stringWidth(scoreString)) / 2,
                gamePanel.getHeight() / 2 - 50);

        // High score string
        String highScoreString = "Personal High Score: " + personalHighScore;
        g.drawString(highScoreString, (gamePanel.getWidth() - g.getFontMetrics().stringWidth(highScoreString)) / 2,
                gamePanel.getHeight() / 2);

        // gets the user data and updates the user in the database
        try {
            ConnFactory bd = new ConnFactory();
            conn = bd.getConnection();

            gamePanel.getUser().updateUser(conn);

            // gets the high score and the player with the high score
            String highScoreString2 = "High Score: " + User.getMaxHighScore(conn) + " by "
                    + User.getPalyerWithMaxHighScore(conn);
            g.drawString(highScoreString2,
                    (gamePanel.getWidth() - g.getFontMetrics().stringWidth(highScoreString2)) / 2,
                    gamePanel.getHeight() / 2 + 50);

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
