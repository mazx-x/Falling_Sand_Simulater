package Particles;

import java.awt.Color;

public class WoodParticle extends Particle {
    public WoodParticle(int x, int y) {
        super(x, y, new Color(139, 69, 19)); // A nice brown color for wood
    }

    @Override
    public int[] update(Particle[][] grid) {
        // Wood does not move, so it always returns its current position
        return new int[]{x, y};
    }
}