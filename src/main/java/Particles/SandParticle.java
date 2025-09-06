package Particles;

import java.awt.Color;
import java.util.Random;

public class SandParticle extends Particle {
    private static final Random random = new Random();

    public SandParticle(int x, int y) {
        super(x, y, new Color(255, 204, 102)); // Sand color
    }

    @Override
    public int[] update(Particle[][] grid) {
        int gridHeight = grid[0].length;
        int gridWidth = grid.length;

        // Fall down
        if (y + 1 < gridHeight && grid[x][y + 1] == null) {
            return new int[]{x, y + 1};
        }
        // Fall diagonally
        else if (y + 1 < gridHeight) {
            int dir = random.nextBoolean() ? 1 : -1;
            int newX = x + dir;

            if (newX >= 0 && newX < gridWidth && grid[newX][y + 1] == null) {
                return new int[]{newX, y + 1};
            }
        }
        // No movement
        return new int[]{x, y};
    }
}