import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Falling Sand Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel sandPanel = new GamePanel();
        frame.add(sandPanel);
        frame.pack(); // Adjusts the window size to fit the components

        frame.setLocationRelativeTo(null); // Centers the window
        frame.setVisible(true);

        sandPanel.startGameLoop();
    }
}