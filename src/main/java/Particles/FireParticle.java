package Particles;

import java.awt.Color;
import java.util.Random;

public class FireParticle extends Particle {
    private static final Random random = new Random();
    private int lifetime;

    public FireParticle(int x, int y) {
        super(x, y, new Color(255, 100, 0)); // A bright orange color for fire
        this.lifetime = 350; // Example lifetime (60 FPS * 2 seconds)
    }

    @Override
    public int[] update(Particle[][] grid) {
        int gridHeight = grid[0].length;
        int gridWidth = grid.length;

        // 1. Check for interactions with other particles (spread or extinguish)
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int checkX = x + i;
                int checkY = y + j;

                if (checkX >= 0 && checkX < gridWidth && checkY >= 0 && checkY < gridHeight) {
                    Particle neighbor = grid[checkX][checkY];
                    if (neighbor != null) {
                        // Extinguish if next to water
                        if (neighbor instanceof WaterParticle) {
                            return new int[]{-1, -1}; // Fire is removed
                        }
                        // Spread if next to wood
                        if (neighbor instanceof WoodParticle) {
                            grid[checkX][checkY] = new FireParticle(checkX, checkY);
                        }
                    }
                }
            }
        }

        // Decrease lifetime and check if it should disappear
        this.lifetime--;
        if (this.lifetime <= 0) {
            return new int[]{-1, -1}; // Fire is removed
        }

        // 2. Movement logic (Fire rises)
        // Try to move straight up
        if (y - 1 >= 0 && grid[x][y - 1] == null) {
            return new int[]{x, y - 1};
        }

        // Try to move diagonally up
        if (y - 1 >= 0) {
            int dir = random.nextBoolean() ? 1 : -1;
            int newX = x + dir;
            int otherX = x - dir;

            if (newX >= 0 && newX < gridWidth && grid[newX][y - 1] == null) {
                return new int[]{newX, y - 1};
            }
            if (otherX >= 0 && otherX < gridWidth && grid[otherX][y - 1] == null) {
                return new int[]{otherX, y - 1};
            }
        }

        // No movement
        return new int[]{x, y};
    }
}