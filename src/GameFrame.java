
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameFrame {

    private int height;
    private int width;
    private int delay;

    public GameFrame(int level) {

        JFrame container = new JFrame("Snake Game");

        if (level < 5) {
            height = 600;
            width = 600;
            delay = 100/level;
        }
        else {
            height = 600/level;
            width = 600/level;
            delay = 100;
        }

        System.out.println(level);

        // container aspects
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setSize(600, 600);
        container.setLocationRelativeTo(null);
        container.setResizable(false);

        // score label
        ScorePanel scorepanel = new ScorePanel(level);
        container.add(scorepanel, BorderLayout.SOUTH);

        // adds the game to the container
        GamePanel panel = new GamePanel(scorepanel, container, height, width, delay / level);
        container.add(panel, BorderLayout.CENTER);
        panel.requestFocus();

        // disconsiders the top bar
        container.pack();

        container.setVisible(panel.running);
    }

}
