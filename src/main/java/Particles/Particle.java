package Particles;

import java.awt.Color;

public abstract class Particle {
    public int x;
    public int y;
    protected Color color;

    public Particle(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Color getColor() { return color; }

    public abstract int[] update(Particle[][] grid);
}