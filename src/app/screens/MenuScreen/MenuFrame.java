package app.screens.MenuScreen;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import app.screens.GameScreen.GameFrame;
import app.screens.LeaderboardScreen.LeaderboardFrame;

public class MenuFrame extends JFrame implements ActionListener {

    private JButton startBtn;
    private JButton quitBtn;
    private JButton leaderboardBtn;

    private JLabel title;
    private JTextField nameTextField;
    private JLabel nameLabel;

    private String name;
    public int highScore = 0;
    private int level = 0;

    private JFrame container;

    public MenuFrame() {
        super("Snake Game");

        // Menu container
        container = new JFrame();
        container.setLayout(new GridLayout(4, 1, 0, 10));
        container.setSize(500,400);
        container.setLocationRelativeTo(null);
        container.getContentPane().setBackground(Color.BLACK);

        // menu title
        title = new JLabel("Snake Game");
        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.green);

        // name text label
        nameLabel = new JLabel("Enter your nickname:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setForeground(Color.green);

        // name text field
        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(200, 50));
        nameTextField.setFont(new Font("Arial", Font.BOLD, 20));
        nameTextField.setHorizontalAlignment(JTextField.CENTER);
        nameTextField.setBackground(Color.green);
        nameTextField.setForeground(Color.black);
        nameTextField.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        nameTextField.addActionListener(this);

        // snake image
        // ImageIcon icon = new ImageIcon(getClass().getResource("./assets/snakeImage.jpg"));
        // Image smallerImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        // ImageIcon smallerIcon = new ImageIcon(smallerImage);
        // JLabel imageLabel = new JLabel(smallerIcon);

        // start game button
        startBtn = new JButton("Start Game");
        startBtn.setBackground(Color.green);
        startBtn.setPreferredSize(new Dimension(200, 50));
        startBtn.addActionListener(this);
        startBtn.setBorderPainted(false);
        startBtn.setFocusPainted(false);
        startBtn.setFont(new Font("Arial", Font.BOLD, 25));

        // quit button
        quitBtn = new JButton("Quit");
        quitBtn.setBackground(Color.green);
        quitBtn.setPreferredSize(new Dimension(200, 50));
        quitBtn.addActionListener(this);
        quitBtn.setBorderPainted(false);
        quitBtn.setFocusPainted(false);
        quitBtn.setFont(new Font("Arial", Font.BOLD, 25));

        // leaderboard button
        leaderboardBtn = new JButton("Leaderboard");
        leaderboardBtn.setBackground(Color.green);
        leaderboardBtn.setPreferredSize(new Dimension(200, 50));
        leaderboardBtn.addActionListener(this);
        leaderboardBtn.setBorderPainted(false);
        leaderboardBtn.setFocusPainted(false);
        leaderboardBtn.setFont(new Font("Arial", Font.BOLD, 25));

        // panel with the name field
        JPanel namePanel = new JPanel();
        namePanel.add(nameLabel);  
        namePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        namePanel.add(nameTextField);
        namePanel.setBackground(Color.BLACK);

        // panel with the start and quit buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(startBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonsPanel.add(quitBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonsPanel.setBackground(Color.BLACK);
        buttonsPanel.setSize(400, 400);

        // panel with the leaderboard button
        JPanel buttonLeaderBoardPanel = new JPanel();
        buttonLeaderBoardPanel.add(leaderboardBtn);
        buttonLeaderBoardPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonLeaderBoardPanel.setBackground(Color.BLACK);
        buttonLeaderBoardPanel.setSize(400, 300);

        container.add(title);
        // container.add(imageLabel);
        container.add(namePanel);
        container.add(buttonsPanel);
        container.add(buttonLeaderBoardPanel);
        container.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        level++;
        if (e.getSource() == startBtn) {
            // gets the user name
            name = nameTextField.getText();
            if (name.equals("")) {
                name = "Unnamed Player";
            }
            // creates the game frame
            new GameFrame(level, name);
        } else if (e.getSource() == quitBtn) {
            System.exit(0);
        } else if (e.getSource() == leaderboardBtn) {
            // creates the leaderboard frame
            new LeaderboardFrame();
        }
    }
}