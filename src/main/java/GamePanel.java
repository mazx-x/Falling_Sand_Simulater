import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    // Grid dimensions
    private final int GRID_WIDTH = 100;
    private final int GRID_HEIGHT = 80;
    private final int PIXEL_SIZE = 8;

    // Materials
    //private final int EMPTY = 0;
    //private final int SAND = 1;

    private int[][] grid;
    private Thread gameThread;
    private Random random = new Random();

    public GamePanel() {
        setPreferredSize(new Dimension(GRID_WIDTH * PIXEL_SIZE, GRID_HEIGHT * PIXEL_SIZE));
        setBackground(Color.BLACK);

        grid = new int[GRID_WIDTH][GRID_HEIGHT];

        // Mouse listener for placing sand
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / PIXEL_SIZE;
                int y = e.getY() / PIXEL_SIZE;

                if (x >= 0 && x < GRID_WIDTH && y >= 0 && y < GRID_HEIGHT) {
                    grid[x][y] = elements.SAND.ordinal();
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX() / PIXEL_SIZE;
                int y = e.getY() / PIXEL_SIZE;

                if (x >= 0 && x < GRID_WIDTH && y >= 0 && y < GRID_HEIGHT) {
                    grid[x][y] = elements.SAND.ordinal();;
                }
            }
        });
    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000.0 / 60; // 60 updates per second
        double nextDrawTime = System.nanoTime() + drawInterval;

        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1_000_000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        // Iterate from bottom to top, left to right
        for (int y = GRID_HEIGHT - 1; y >= 0; y--) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                if (grid[x][y] == elements.SAND.ordinal()) {
                    // Check below
                    if (y + 1 < GRID_HEIGHT && grid[x][y+1] == elements.EMPTY.ordinal()) {
                        grid[x][y+1] = elements.SAND.ordinal();;
                        grid[x][y] = elements.EMPTY.ordinal();
                    }
                    // Check diagonals
                    else if (y + 1 < GRID_HEIGHT) {
                        // Randomly choose left or right
                        int direction = random.nextBoolean() ? 1 : -1;
                        int newX = x + direction;

                        if (newX >= 0 && newX < GRID_WIDTH && grid[newX][y+1] == elements.EMPTY.ordinal()) {
                            grid[newX][y+1] = elements.SAND.ordinal();
                            grid[x][y] = elements.EMPTY.ordinal();
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                if (grid[x][y] == elements.SAND.ordinal()) {
                    g.setColor(new Color(255, 204, 102)); // Sand color
                    g.fillRect(x * PIXEL_SIZE, y * PIXEL_SIZE, PIXEL_SIZE -1, PIXEL_SIZE -1);
                }
            }
        }
    }
}