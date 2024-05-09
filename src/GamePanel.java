import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    // Setting the size of the tiles
    private int tile_size = 25;
    private int height = 600;
    private int width = 600;

    // Creating the snake
    private Tile snake;
    private ArrayList<Tile> snakeBody;

    // Creating the apple
    private Tile apple;

    // Random Tile Generator
    private RandomPoint randomTile;

    // Game Timer
    private Timer timer;

    // Snake velocity
    private int velocityX;
    private int velocityY;

    // Player Score
    public int score;

    // verifies if the game has started
    public boolean running = false;

    private JLabel gameOver;

    private ScorePanel scorePanel;

    public GamePanel(ScorePanel scorePanel) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.black);

        // listen to keys pressed by the user
        addKeyListener(this);

        // focuses the keys pressed by the user on the game
        setFocusable(true);
        this.scorePanel = scorePanel;

        // starts the game
        startGame();
    }

    public void startGame() {
        running = true;
        randomTile = new RandomPoint(width, height, tile_size);

        // sets the time in which the screen is redrawn (100 ms)
        timer = new Timer(100, this);
        timer.start();

        // placing the snake and the apple
        snakeBody = new ArrayList<Tile>();
        snake = new Tile(5, 5);
        apple = randomTile.RandomTile();

        // sets the initial velocity
        velocityX = 0;
        velocityY = 0;

    }

    public void endGame() {
        running = false;
        gameOver = new JLabel("Game over!");
        gameOver.setFont(new Font("Arial", Font.BOLD, 100));
        setBackground(Color.red);
        JOptionPane.showMessageDialog(null, "gameOver");
        new Menu();
    }

    public void move() {
        // Increases the snake size
        if (isSameTile(snake, apple)) {
            scorePanel.setText();
            snakeBody.add(new Tile(apple.x, apple.y));
            apple = randomTile.RandomTile();
        }

        // moves the snake body
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakeTile = snakeBody.get(i);
            if (i == 0) {
                snakeTile.x = snake.x;
                snakeTile.y = snake.y;
            } else {
                Tile previousnakeTile = snakeBody.get(i - 1);
                snakeTile.x = previousnakeTile.x;
                snakeTile.y = previousnakeTile.y;
            }
        }

        // moves the snake head
        snake.x += velocityX;
        snake.y += velocityY;

        // endGame conditions
        for (Tile tile : snakeBody) {
            if (isSameTile(snake, tile)) {
                endGame();
            }
        }

        if (snake.x * tile_size < 0 || snake.x * tile_size >= width || snake.y * tile_size < 0
                || snake.y * tile_size >= height) {
            endGame();
        }

    }

    public boolean isSameTile(Tile tile1, Tile tile2) {
        return (tile1.x == tile2.x && tile1.y == tile2.y);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Grid
        for (int i = 0; i < width / tile_size; i++) {
            g.drawLine(i * tile_size, 0, i * tile_size, height);
            g.drawLine(0, i * tile_size, width, i * tile_size);
        }

        // Drawing the Snake Head
        g.setColor(Color.green);
        g.fillRect(snake.x * tile_size, snake.y * tile_size, tile_size, tile_size);

        // Drawing the Snake Body
        g.setColor(Color.green);
        for (Tile tile : snakeBody) {
            g.fillRect(tile.x * tile_size, tile.y * tile_size, tile_size, tile_size);
        }

        // Drawing the Apple
        g.setColor(Color.red);
        g.fillRoundRect(apple.x * tile_size, apple.y * tile_size, tile_size, tile_size, tile_size, tile_size);
        g.setColor(Color.green);
        g.fillRoundRect(apple.x * tile_size + tile_size / 2, apple.y * tile_size, tile_size / 4, tile_size / 4,
                tile_size, tile_size);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            // Move the snake
            move();
            // ReRender the screen (redraw)
            repaint();

        }

    }

    // reads the key pressed by the user
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    // Unnedded funcitons
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
