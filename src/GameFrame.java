
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameFrame implements ActionListener {

    private JButton startBtn;

    public GameFrame() {

        JFrame container = new JFrame("Snake Game");

        startBtn = new JButton("Start Game");
        startBtn.setBounds(10, 20, 30, 40);

        // container aspects
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setSize(600, 600);
        container.setLocationRelativeTo(null);
        container.setVisible(true);
        container.setResizable(false);
        container.add(startBtn, BorderLayout.SOUTH);

        // score label
        ScorePanel scorepanel = new ScorePanel();
        container.add(scorepanel, BorderLayout.SOUTH);

        // adds the game to the container
        GamePanel panel = new GamePanel(scorepanel);
        container.add(panel, BorderLayout.CENTER);
        panel.requestFocus();

        // disconsiders the top bar
        container.pack();

        startBtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startBtn) {

        }
    }

}
