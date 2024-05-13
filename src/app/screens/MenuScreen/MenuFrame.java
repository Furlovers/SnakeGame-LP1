package app.screens.MenuScreen;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import app.screens.GameScreen.GameFrame;

public class MenuFrame extends JFrame implements ActionListener {
    private JButton startBtn;
    private JButton quitBtn;
    private JLabel title;
    public int highScore = 0;
    private int level = 0;
    private JFrame container;

    public MenuFrame() {
        super("Snake Game");

        // Menu container
        container = new JFrame();
        container.setLayout(new GridLayout(3, 1, 0, 10));
        container.setSize(500, 550);
        container.setLocationRelativeTo(null);
        container.getContentPane().setBackground(Color.BLACK);

        // menu title
        title = new JLabel("Snake Game");
        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.green);

        // snake image
        ImageIcon icon = new ImageIcon(getClass().getResource("./assets/snakeImage.jpg"));
        Image smallerImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon smallerIcon = new ImageIcon(smallerImage);
        JLabel imageLabel = new JLabel(smallerIcon);

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

        // panel with the button and highscore
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(startBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonsPanel.add(quitBtn);
        buttonsPanel.setBackground(Color.BLACK);
        buttonsPanel.setSize(400, 400);

        container.add(title);
        container.add(imageLabel);
        container.add(buttonsPanel);

        container.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        level++;
        if (e.getSource() == startBtn) {
            new GameFrame(level);
        } else if (e.getSource() == quitBtn) {
            System.exit(0);
        }
    }
}
