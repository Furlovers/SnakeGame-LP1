package app.screens.GameScreen;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import app.screens.GameScreen.Panels.GamePanel;
import app.screens.GameScreen.Panels.ScorePanel;

public class GameFrame {

    private int height;
    private int width;
    private int delay;
    private int highScore = 0;

    private GamePanel gamePanel;

    public GameFrame(int level) {

        JFrame container = new JFrame("Snake Game");

        height = 600;
        width = 600;
        delay = 100;

        // container aspects
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setSize(width, height);
        container.setLocationRelativeTo(null);
        container.setResizable(false);

        // score label
        ScorePanel scorePanel = new ScorePanel(level);
        container.add(scorePanel, BorderLayout.SOUTH);

        // adds the game to the container
        gamePanel = new GamePanel(scorePanel, container, height, width, delay);
        container.add(gamePanel, BorderLayout.CENTER);
        gamePanel.requestFocus();

        // disconsiders the top bar
        container.pack();

        container.setVisible(true);
    }

    public void setHighScore() {
        if(this.highScore < gamePanel.score) {
            this.highScore = gamePanel.score;
        }
    }

    public int getHighScore() {
        return this.highScore;
    }

}
