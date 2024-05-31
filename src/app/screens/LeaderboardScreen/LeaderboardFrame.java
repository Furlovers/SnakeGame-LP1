package app.screens.LeaderboardScreen;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;

import app.db.ConnFactory;
import app.db.User;

public class LeaderboardFrame extends JFrame {

    private Connection conn = null;

    public LeaderboardFrame() {
        setTitle("Leaderboard");
        setLayout(new GridLayout(11, 1));
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        setVisible(true);

        JLabel title = new JLabel("Leaderboard");
        title.setFont(title.getFont().deriveFont(40.0f));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);

        try {
            ConnFactory bd = new ConnFactory();
            conn = bd.getConnection();

            for (User user : User.getTopTenUsers(conn)) {
                JLabel userLabel = new JLabel(user.getName() + " - " + user.getHighScore());
                userLabel.setFont(userLabel.getFont().deriveFont(20.0f));
                userLabel.setHorizontalAlignment(JLabel.CENTER);
                add(userLabel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
