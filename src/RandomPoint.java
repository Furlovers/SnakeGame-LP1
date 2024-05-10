import java.util.Random;

public class RandomPoint {
    private Random random;
    private Tile tile;
    int width;
    int height;
    int tile_size;
    int x;
    int y;

    public RandomPoint(int width, int height, int tile_size) {
        this.width = width;
        this.height = height;
        this.tile_size = tile_size;
    }

    public Tile RandomTile() {
        random = new Random();
        tile = new Tile(random.nextInt(width / tile_size), random.nextInt(height / tile_size));
        this.x = tile.x;
        this.y = tile.y;
        return tile;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    
}
