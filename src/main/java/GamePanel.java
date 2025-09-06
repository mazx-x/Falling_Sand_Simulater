import Particles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    private final int PIXEL_SIZE = 4;
    private final int GRID_WIDTH = 1280 / PIXEL_SIZE;
    private final int GRID_HEIGHT = 720 / PIXEL_SIZE;


    public int brushRadius = 1;

    // Initialize the grid directly here
    private Particle[][] grid = new Particle[GRID_WIDTH][GRID_HEIGHT];
    private Thread gameThread;

    // Class variable to store the button pressed
    private int pressedButton = MouseEvent.NOBUTTON;
    private BufferedImage bufferImage;
    private Graphics2D bufferGraphics;


    //Selected Particle
    char selectedParticle = 'S';

    //Slider for changing brush size
    JSlider brushSizeChanger;
    JLabel brushSize;


    public GamePanel() {
        brushSizeChanger = new JSlider(brushRadius,10,brushRadius);
        brushSizeChanger.setBounds(20,50,200,30);
        brushSizeChanger.setFocusable(false);
        brushSizeChanger.setSnapToTicks(true);

        brushSize = new JLabel();
        brushSize.setBounds(240,50,500,50);
        brushSize.setText("Brush Size: " + brushRadius);

        bufferImage = new BufferedImage(GRID_WIDTH * PIXEL_SIZE, GRID_HEIGHT * PIXEL_SIZE, BufferedImage.TYPE_INT_RGB);
        bufferGraphics = bufferImage.createGraphics();
        bufferGraphics.setBackground(Color.BLACK);


        setPreferredSize(new Dimension(1280, 720));
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);

        add(brushSizeChanger);
        add(brushSize);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                var pressedKey = e.getKeyCode();
                switch (pressedKey){
                    case KeyEvent.VK_R -> {
                        selectedParticle = 'R';
                    }
                    case KeyEvent.VK_1 -> {
                        selectedParticle = 'S';
                    }
                    case KeyEvent.VK_2 -> {
                        selectedParticle = 'W';
                    }
                    case KeyEvent.VK_3 -> {
                        selectedParticle = 's';
                    }
                    case KeyEvent.VK_4 -> {
                        selectedParticle = 'A';
                    }
                    case KeyEvent.VK_5 -> {
                        selectedParticle = 'F';
                    }
                    case KeyEvent.VK_6 -> {
                        selectedParticle = 'N';
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressedButton = e.getButton();
                placeParticle(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressedButton = MouseEvent.NOBUTTON;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Now we use the stored button value
                placeParticle(e.getX(), e.getY());
            }
        });
    }

    private void placeParticle(int mouseInputX, int mouseInputY) {

        int mouseX = mouseInputX / PIXEL_SIZE;
        int mouseY = mouseInputY / PIXEL_SIZE;

        for (int i = -brushRadius; i <= brushRadius; i++) {
            for (int j = -brushRadius; j <= brushRadius; j++) {
                int x = mouseX + i;
                int y = mouseY + j;
                if (x >= 0 && x < GRID_WIDTH && y >= 0 && y < GRID_HEIGHT && grid[x][y] == null) {
                    if (selectedParticle == 'S') {
                        grid[x][y] = new SandParticle(x, y);
                    } else if (selectedParticle == 'W') {
                        grid[x][y] = new WoodParticle(x, y);
                    }  else if (selectedParticle == 's') {
                        grid[x][y] = new SmokeParticle(x, y);
                    } else if (selectedParticle == 'A') {
                        grid[x][y] = new WaterParticle(x, y);
                    }  else if (selectedParticle == 'F') {
                        grid[x][y] = new FireParticle(x, y);
                    }  else if (selectedParticle == 'N') {
                        grid[x][y] = new StoneParticle(x, y);
                    }
                } else if(x >= 0 && x < GRID_WIDTH && y >= 0 && y < GRID_HEIGHT) {
                    if (selectedParticle == 'R') {
                        grid[x][y] = null;
                    }
                }
            }
        }
    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000.0 / 60; // 60 FPS
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            drawToBuffer();
            repaint();

            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1_000_000;
                if (remainingTime < 0) remainingTime = 0;
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        brushRadius = brushSizeChanger.getValue();
        brushSize.setText("Brush Size: " + brushRadius);
        Particle[][] nextGrid = new Particle[GRID_WIDTH][GRID_HEIGHT];

        for (int y = GRID_HEIGHT - 1; y >= 0; y--) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                if (grid[x][y] != null) {
                    int[] newPos = grid[x][y].update(grid);
                    int newX = newPos[0];
                    int newY = newPos[1];

                    // Check for the sentinel value (-1, -1) to remove the particle
                    if (newX == -1 && newY == -1) {
                        continue; // Skip this particle, it's removed from the next grid
                    }

                    if (nextGrid[newX][newY] == null) {
                        nextGrid[newX][newY] = grid[x][y];
                        nextGrid[newX][newY].x = newX;
                        nextGrid[newX][newY].y = newY;
                    } else {
                        if (nextGrid[x][y] == null) {
                            nextGrid[x][y] = grid[x][y];
                        }
                    }
                }
            }
        }
        grid = nextGrid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bufferImage, 0, 0, this);
    }
    private void drawToBuffer() {
        bufferGraphics.clearRect(0, 0, bufferImage.getWidth(), bufferImage.getHeight()); // Clear the buffer
        if (selectedParticle == 'S'){
            bufferGraphics.setColor(new Color(255, 204, 102));
        } else if (selectedParticle == 'W'){
            bufferGraphics.setColor(new Color(139, 69, 19));
        } else if (selectedParticle == 's'){
            bufferGraphics.setColor(new Color(100, 100, 100));
        } else if (selectedParticle == 'A'){
            bufferGraphics.setColor(new Color(50, 150, 255));
        } else if (selectedParticle == 'R'){
            bufferGraphics.setColor(new Color(188, 188, 188));
        } else if (selectedParticle == 'F'){
            bufferGraphics.setColor(new Color(255, 100, 0));
        } else if (selectedParticle == 'N'){
            bufferGraphics.setColor(new Color(60, 63, 65));
        }
        bufferGraphics.drawString("Selected : " + selectedParticle,20 ,20);
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                if (grid[x][y] != null) {
                    bufferGraphics.setColor(grid[x][y].getColor());
                    bufferGraphics.fillRect(x * PIXEL_SIZE, y * PIXEL_SIZE, PIXEL_SIZE -1 , PIXEL_SIZE -1);
                }
            }
        }
    }
}