package Listeners;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author dimitris
 */
public class MouseInfo implements MouseMotionListener, MouseListener {

    /**
     * Συντεταγμένες του ποντικιού
     */
    public static Point position;

    /**
     * Αντιπροσωπεύει το κλικ του ποντικιού
     */
    public static boolean izq;

    /**
     *
     */
    public MouseInfo() {
        position = new Point();
        izq = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            izq = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            izq = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        position.setLocation(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position.setLocation(e.getX(), e.getY());

    }

}
