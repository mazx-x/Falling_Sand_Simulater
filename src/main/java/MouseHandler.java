import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener , MouseMotionListener {
    static int mouseX;
    static int mouseY;
    static int currentMouseX;
    static int currentMouseY;
    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println(e.getX() + " "+ e.getY());
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentMouseX= e.getX();
        currentMouseY= e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
