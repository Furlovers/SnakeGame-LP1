import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener {
    private JButton startBtn;
    private JLabel title;

    public Menu() {
        super("Snake Game");
        
        
        JFrame container = new JFrame();
        container.setLayout(new GridLayout(3, 1));
        container.setSize(500, 400);
        container.setLocationRelativeTo(null);
        container.getContentPane().setBackground(Color.BLACK);

        startBtn = new JButton("Start Game");
        startBtn.setBackground(Color.green);
        startBtn.setPreferredSize(new Dimension(100, 50));
        startBtn.addActionListener(this);

        title = new JLabel("Snake Game");
        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.green);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(startBtn);
        buttonsPanel.setBackground(Color.BLACK);

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/snakeImage.jpg"));
        Image smallerImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Change size as needed
        ImageIcon smallerIcon = new ImageIcon(smallerImage);
        JLabel imageLabel = new JLabel(smallerIcon);

        container.add(title);
        container.add(imageLabel);
        container.add(buttonsPanel);

        buttonsPanel.setSize(400, 400);

        container.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new GameFrame();
    }
}
