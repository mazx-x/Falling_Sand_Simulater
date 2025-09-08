package Particles;

import SettingComponents.ParticleSettings;

import java.awt.Color;
import java.util.Random;

public class SmokeParticle extends Particle {
    private static final Random random = new Random();
    private int lifetime;
    public static final int DEFAULT_LIFE_TIME = 800;

    public SmokeParticle(int x, int y , int lifeTime) {
        super(x, y, new Color(100, 100, 100)); // Dark gray smoke color
        this.lifetime = lifeTime; // Example lifetime
    }
    public SmokeParticle(int x, int y) {
        super(x, y, new Color(100, 100, 100));
        this.lifetime = ParticleSettings.smokeLifetime; // Or use the settings here
    }

    @Override
    public int[] update(Particle[][] grid) {
        int gridHeight = grid[0].length;
        int gridWidth = grid.length;

        // Decrease lifetime and check if it should disappear
        this.lifetime--;
        if (this.lifetime <= 0) {
            return new int[]{-1, -1}; // A sentinel value to signal removal
        }

        // Check if there's space directly above
        if (y - 1 >= 0 && grid[x][y - 1] == null) {
            return new int[]{x, y - 1};
        }
        // Check for diagonal movement
        else if (y - 1 >= 0) {
            int dir = random.nextBoolean() ? 1 : -1;
            int newX = x + dir;

            if (newX >= 0 && newX < gridWidth && grid[newX][y - 1] == null) {
                return new int[]{newX, y - 1};
            }
        }

        // No movement
        return new int[]{x, y};
    }
}