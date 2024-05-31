package app.db;

import java.util.Map;
import java.util.HashMap;

public class LevelManager {

    private static Map<Integer, Integer> delay = new HashMap<>();
    private static Map<Integer, Integer> minScore = new HashMap<>();

    // runs only once
    static {
        // inicializes the delay map (level: delay)
        delay.put(1, 100);
        delay.put(2, 80);
        delay.put(3, 60);
        delay.put(4, 40);

        minScore.put(1, 30);
        minScore.put(2, 20);
        minScore.put(3, 15);
        minScore.put(4, 10);
    }

    public int getDelay(int level) {
        return level <= 4 ? delay.get(level) : 100;
    }

    public int getMinScore(int level) {
        return level <= 4 ? minScore.get(level) : 0;
    }
}
