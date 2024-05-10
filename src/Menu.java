import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener {
    private JButton startBtn;
    private JButton quitBtn;
    private JLabel title;
    public int highScore = 0;
    private int level = 0;

    public Menu() {
        super("Snake Game");
        
        
        JFrame container = new JFrame();
        container.setLayout(new GridLayout(3, 1));
        container.setSize(500, 400);
        container.setLocationRelativeTo(null);
        container.getContentPane().setBackground(Color.BLACK);

        // start game button
        startBtn = new JButton("Start Game");
        startBtn.setBackground(Color.green);
        startBtn.setPreferredSize(new Dimension(100, 50));
        startBtn.addActionListener(this);
        startBtn.setBorderPainted(false);
        startBtn.setFocusPainted(false);

        // quit button
        quitBtn = new JButton("Quit");
        quitBtn.setBackground(Color.green);
        quitBtn.setPreferredSize(new Dimension(100, 50));
        quitBtn.addActionListener(this);
        quitBtn.setBorderPainted(false);
        quitBtn.setFocusPainted(false);

        // menu title
        title = new JLabel("Snake Game");
        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.green);

        // panel with the button and highscore
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(startBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonsPanel.add(quitBtn);
        buttonsPanel.setBackground(Color.BLACK);

        // snake image
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/snakeImage.jpg"));
        Image smallerImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); 
        ImageIcon smallerIcon = new ImageIcon(smallerImage);
        JLabel imageLabel = new JLabel(smallerIcon);

        System.out.println(level);

        container.add(title);
        container.add(imageLabel);
        container.add(buttonsPanel);

        buttonsPanel.setSize(400, 400);

        container.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        level++;
        if (e.getSource() == startBtn) {
            new GameFrame(level);
        }
        else if (e.getSource() == quitBtn) {
            System.exit(0);
        }
    }
}
