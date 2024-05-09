
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameFrame {


    public GameFrame() {

        JFrame container = new JFrame("Snake Game");

        // container aspects
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setSize(600, 600);
        container.setLocationRelativeTo(null);
        container.setResizable(false);

        // score label
        ScorePanel scorepanel = new ScorePanel();
        container.add(scorepanel, BorderLayout.SOUTH);

        // adds the game to the container
        GamePanel panel = new GamePanel(scorepanel, container);
        container.add(panel, BorderLayout.CENTER);
        panel.requestFocus();

        // disconsiders the top bar
        container.pack();

        container.setVisible(panel.running);
    }

}
