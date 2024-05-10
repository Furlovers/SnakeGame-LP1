import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    // Setting the size of the tiles and delay
    private int tile_size = 25;
    private int height;
    private int width;
    private int delay;

    // Creating the snake
    private Tile snake;
    private ArrayList<Tile> snakeBody;

    // Creating the apple
    private Tile apple;

    // Random Tile Generator
    private RandomPoint randomTile;
    // private Random random;
    // private int randomImgTileX;
    // private int randomImgTileY;

    // Game Timer
    private Timer timer;

    // Snake velocity
    private int velocityX;
    private int velocityY;

    // Player Score
    public int score;

    // verifies if the game has started
    public boolean running = false;

    private ScorePanel scorePanel;
    private JFrame container;

    public GamePanel(ScorePanel scorePanel, JFrame container, int height, int width, int delay) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.black);

        // listen to keys pressed by the user
        addKeyListener(this);

        // focuses the keys pressed by the user on the game
        setFocusable(true);
        this.scorePanel = scorePanel;
        this.container = container;

        // sets the panel
        this.height = height;
        this.width = width;
        this.delay = delay;

        // starts the game
        startGame();
    }

    public void startGame() {
        running = true;
        randomTile = new RandomPoint(width, height, tile_size);

        // random obstacle mode
        // random = new Random();
        // randomImgTileX = random.nextInt(width/tile_size);
        // randomImgTileY = random.nextInt(height/tile_size);

        // sets the time in which the screen is redrawn (100 ms)
        timer = new Timer(delay, this);
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
        setBackground(Color.red);
        JOptionPane.showMessageDialog(null, "gameOver");
        container.dispose();
    }

    public void move() {
        // Increases the snake size
        if (isSameTile(snake, apple)) {
            scorePanel.setText();
            snakeBody.add(new Tile(apple.x, apple.y));
            apple = randomTile.RandomTile();
            // randomImgTileX = random.nextInt(width/tile_size);
            // randomImgTileY = random.nextInt(height/tile_size);
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

        // random obstacle mode
        // if (snake.x == randomImgTileX && snake.y == randomImgTileY) {
        //     endGame();
        // }

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

        // Surprise Image
        // ImageIcon icon = new ImageIcon(getClass().getResource("/images/calvettiImage.jpeg"));
        // Image smallerImage = icon.getImage().getScaledInstance(tile_size, tile_size, Image.SCALE_SMOOTH); 
        // ImageIcon smallerIcon = new ImageIcon(smallerImage);
        // smallerIcon.paintIcon(container, g, randomImgTileX*tile_size, randomImgTileY*tile_size);
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
