package Particles;
import java.awt.Color;

public class StoneParticle extends Particle {
    public StoneParticle(int x, int y) {
        super(x, y, new Color(60, 63, 65)); // A nice brown color for wood
    }

    @Override
    public int[] update(Particle[][] grid) {
        // Wood does not move, so it always returns its current position
        return new int[]{x, y};
    }
}