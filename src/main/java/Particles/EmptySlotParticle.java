package Particles;

import java.awt.*;

public class EmptySlotParticle extends Particle{
    public EmptySlotParticle(int x, int y) {
        super(x, y, new Color(58, 43, 43, 74));
    }
    public EmptySlotParticle(){
        super();
    }

    @Override
    public int[] update(Particle[][] grid) {
        return new int[]{x, y};
    }
}
