import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        JFrame frame = new JFrame("Falling Sand Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel, SwingConstants.CENTER);// Adjusts the window size to fit the components
        frame.pack();
        frame.setLocationRelativeTo(null);// Centers the window
        frame.setResizable(true);
        frame.setVisible(true);

        gamePanel.startGameLoop();
    }
}