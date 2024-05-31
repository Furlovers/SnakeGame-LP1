package app.screens.LeaderboardScreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import app.db.ConnFactory;
import app.db.User;
import app.screens.MenuScreen.MenuFrame;

public class LeaderboardFrame extends JFrame implements ActionListener{

    private Connection conn = null;
    private JButton menuBtn;
    private JFrame container;

    public LeaderboardFrame() {
        super("Snake Game");

        // leaderboard container
        container = new JFrame();
        container.setLayout(new GridLayout(0, 1, 0, 10));
        container.setSize(500,700);
        container.setLocationRelativeTo(null);
        container.getContentPane().setBackground(Color.BLACK);

        // leaderboard title
        JLabel title = new JLabel("Leaderboard");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.GREEN); 
        title.setHorizontalAlignment(JLabel.CENTER);
        container.add(title);

        try {
            ConnFactory bd = new ConnFactory();
            conn = bd.getConnection();

            for (User user : User.getTopTenUsers(conn)) {
                JLabel userLabel = new JLabel("Jogador: " + user.getName() + " - " + "Pontuação: " + user.getHighScore());
                userLabel.setFont(new Font("Arial", Font.PLAIN, 20)); 
                userLabel.setForeground(Color.GREEN); 
                userLabel.setHorizontalAlignment(JLabel.CENTER);
                userLabel.setPreferredSize(new Dimension(400, 30));
                container.add(userLabel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // back to menu button
        menuBtn = new JButton("Menu");
        menuBtn.setBackground(Color.GREEN);
        menuBtn.setPreferredSize(new Dimension(200, 50));
        menuBtn.addActionListener(this);
        menuBtn.setBorderPainted(false);
        menuBtn.setFocusPainted(false);
        menuBtn.setFont(new Font("Arial", Font.BOLD, 25));

        JPanel menuButton = new JPanel();
        menuButton.add(menuBtn);
        menuButton.add(Box.createRigidArea(new Dimension(20, 0)));
        menuButton.setBackground(Color.BLACK);
        menuButton.setSize(400, 100);

        container.add(menuButton);
        container.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuBtn) {
            new MenuFrame();
            container.dispose();
        } 
    }
}



