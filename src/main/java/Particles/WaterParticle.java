package Particles;
import java.awt.Color;
import java.util.Random;

public class WaterParticle extends Particle {
    private static final Random random = new Random();

    public WaterParticle(int x, int y) {
        super(x, y, new Color(50, 150, 255));
    }

    @Override
    public int[] update(Particle[][] grid) {
        int gridHeight = grid[0].length;
        int gridWidth = grid.length;

        // This is the new check to see if the particle is on the floor.
        // If it is, no movement logic is processed.
        if (y == gridHeight - 1) {
            return new int[]{x, y};
        }

        // 1. Try to fall straight down
        if (y + 1 < gridHeight && grid[x][y + 1] == null) {
            return new int[]{x, y + 1};
        }

        // 2. Try to fall diagonally (if straight down is blocked)
        if (y + 1 < gridHeight) {
            int dir = random.nextBoolean() ? 1 : -1;
            int newX = x + dir;
            int otherX = x - dir;

            if (newX >= 0 && newX < gridWidth && grid[newX][y + 1] == null) {
                return new int[]{newX, y + 1};
            }
            if (otherX >= 0 && otherX < gridWidth && grid[otherX][y + 1] == null) {
                return new int[]{otherX, y + 1};
            }
        }

        // This is the new, combined logic as you requested.
        // It now only considers horizontal movement if the space below is empty.
        boolean anyParticleBelow = (y + 1 < gridHeight && grid[x][y + 1] != null);

        if (!anyParticleBelow) {
            int dir = random.nextBoolean() ? 1 : -1;
            int newX = x + dir;
            int otherX = x - dir;

            if (newX >= 0 && newX < gridWidth && grid[newX][y] == null) {
                return new int[]{newX, y};
            }
            if (otherX >= 0 && otherX < gridWidth && grid[otherX][y] == null) {
                return new int[]{otherX, y};
            }
        }

        // 4. No movement
        return new int[]{x, y};
    }
}