//import Handlers.KeyHandler;
//import Handlers.MouseHandler;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class GamePanel_unused extends JPanel implements Runnable{
//    //screen settings
//    final int tileSize = 48;//48x48 pixels
//    final int maxScreenCol = 25;
//    final int maxScreenRow = 15;
//    final int screenWidth = 1200;
//    final int screenHeight = 720;
//
//    //Tile[][] tiles = new Tile[maxScreenCol][maxScreenRow];
//    int[][] cells = new int[maxScreenCol][maxScreenRow];
//
//    int SAND = elements.SAND.ordinal();
//    int EMPTY = elements.EMPTY.ordinal();
//
//
//    //FPS
//    int FPS = 60;
//
//    KeyHandler keyHandler = new KeyHandler();
//    MouseHandler mouseHandler = new MouseHandler();
//
//    Thread gameThread;
//
//
//    public GamePanel_unused(){
//        setPreferredSize(new Dimension(screenWidth,screenHeight));
//        setDoubleBuffered(true);
//        //addKeyListener(keyHandler);
//        addMouseListener(mouseHandler);
//        setFocusable(true);
//
//        for (int i = 0; i < cells.length; i++){
//            for (int j = 0; j < cells[0].length ; j++) {
//                cells[i][j] = 0;
//            }
//        }
//
//    }
//
//    public void startGameThread(){
//        gameThread = new Thread(this);
//        gameThread.start();
//    }
//    @Override
//    public void run() {
//        double drawInterval = 1000000000/FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//
//        long timer = 0;
//        int drawCount = 0;
//        //Game Loop
//        while(gameThread != null){
//            currentTime = System.nanoTime();
//
//            delta += (currentTime - lastTime) / drawInterval;
//            timer += (currentTime - lastTime);
//            lastTime = currentTime;
//
//            if (delta >= 1) {
//                update();
//                repaint();
//                delta--;
//                drawCount++;
//            }
//            if (timer >= 1000000000){
//                //System.out.println("FPS: " + drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
//        }
//    }
//
//    public void update(){
//        if(MouseHandler.currentMouseX !=0 && MouseHandler.currentMouseY !=0) {
//            int clickedMouseTileX = MouseHandler.currentMouseX / tileSize;
//            int clickedMouseTileY = MouseHandler.currentMouseY / tileSize;
//            cells[clickedMouseTileX][clickedMouseTileY] = 1;
//        }
//
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(Color.GRAY);
//        for (int i = 0; i < cells.length; i++){
//            for (int j = 0; j < cells[0].length ; j++) {
//                if (cells[i][j] == SAND) {
//                    g2.setColor(Color.ORANGE);
//                    g2.fillRect(i * tileSize, j * tileSize, tileSize - 1, tileSize - 1);
//
//                } else if (cells[i][j] == EMPTY) {
//                    g2.setColor(Color.GRAY);
//                    g2.fillRect(i * tileSize, j * tileSize, tileSize - 1, tileSize - 1);
//
//                }
//            }
//        }
//
//        g2.dispose();
//    }
//    private void calcCells(){
//        for (int i = 0; i < cells.length; i++){
//            for (int j = 0; j < cells[0].length ; j++) {
//                //
//            }
//        }
//    }
//}
