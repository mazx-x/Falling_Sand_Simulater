import WarningWindow.WarningPanel;
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
        frame.setResizable(false);
        frame.setVisible(true);

        JOptionPane.showMessageDialog(frame,
                "WARNING:If your game is lagging press the clear button a few times.\n" +
                        "if it doesn't work contact Developer",
                "WARNING",JOptionPane.WARNING_MESSAGE);

        gamePanel.startGameLoop();
    }
}