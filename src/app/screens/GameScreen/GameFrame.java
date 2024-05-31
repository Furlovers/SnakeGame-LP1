package app.screens.GameScreen;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;

import app.db.ConnFactory;
import app.db.User;
import app.screens.GameScreen.Panels.GamePanel;
import app.screens.GameScreen.Panels.ScorePanel;

public class GameFrame {

    private int height;
    private int width;
    private int delay;

    private Connection conn = null;

    private GamePanel gamePanel;

    public GameFrame(int level, String name) {

        // creates the user object
        User user = new User(name, 0);

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
        ScorePanel scorePanel = new ScorePanel(level, user);
        container.add(scorePanel, BorderLayout.EAST);

        // adds the game to the container
        gamePanel = new GamePanel(scorePanel, container, height, width, delay, user);
        container.add(gamePanel, BorderLayout.CENTER);
        gamePanel.requestFocus();

        // disconsiders the top bar
        container.pack();

        container.setVisible(true);

        try {

            ConnFactory bd = new ConnFactory();
            conn = bd.getConnection();

            try {
                user.readUser(conn);
            } catch (Error e) {
                user.createUser(conn);
            }
            
            scorePanel.updatePersonalHighScore(user.getHighScore());
            
        }
        catch (Exception e) {
            e.printStackTrace();
            if(conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    System.out.println(e1.getStackTrace());
                }
            }
        }
        
    }

}
