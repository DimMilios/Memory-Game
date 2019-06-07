package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import Listeners.MouseInfo;

/**
 *
 * @author dimitris
 */
public class Card {

    /**
     *
     * @param img
     * @param panel
     */
    public Card(BufferedImage img, GamePanel panel) {

        this.gamePanel = panel;
        this.img = img;

        this.flipped = true;
        this.interacting = false;
        this.found = false;
        initComponents();
    }

    private void initComponents() {
        this.player = gamePanel.getPlayer();
        this.computer = gamePanel.getComputer();
    }

    /**
     *
     * @param g
     * @param x
     * @param y Ζωγραφίζει την κάθε κάρτα Το dimension είναι το εμβαδό της
     * κάρτας
     */
    public void render(Graphics g, int x, int y) {

        dimension = new Rectangle(x, y, sideSize, sideSize);
        //System.out.println("called");
        checkInteraction();

        // Change player.isPlaying() to test
        // && player.isPlaying()
        if (dimension.contains(MouseInfo.position) && MouseInfo.izq && gamePanel.getGameTimer().isRunning() && flipped) {
            flipped = false;
            gamePanel.getMatchedList().add(this);
        } // CHANGE HERE
       // else if (computer.isPlaying()) {
//           else if (flipped && !found && !gamePanel.getTimer().isRunning()) {
//                flipped = false;
//                gamePanel.getMatchedList().add(this);
//                System.out.println("called");
//            }
       // }

//        else if (computer.isPlaying() && flipped) {
//            flipped = false;
//            gamePanel.getMatchedList().add(computer.play());
//            System.out.println("COMPUTER CALLED TO PLAY");
//        }
        repaintCard(g, x, y);

        // Αντικατάσταση της εικόνας με empty αν έχει βρεθεί ζευγάρι
//        if (found) {
//            g.drawImage(gamePanel.getEmpty(), x, y, sideSize, sideSize, Color.BLACK, null);
//        }

        drawHoverEffect(g, x, y);
    }

    private void checkInteraction() {

        if (dimension.contains(MouseInfo.position) && flipped) {
            interacting = true;
        } else {
            interacting = false;
        }
    }

    /**
     *
     * @param g
     * @param x
     * @param y Η κάρτα αλλάζει εικόνα αν επιλεγεί
     */
    private void repaintCard(Graphics g, int x, int y) {
        // CHANGE
        if (!flipped) {
            g.drawImage(gamePanel.getBackSide(), x, y, sideSize, sideSize, null);

        } else {
            g.drawImage(img, x, y, sideSize, sideSize, null);

        }
    }

    /**
     *
     * @param g
     * @param x
     * @param y Ζωγραφίζει περίγραμμα γύρο από την κάρτα αν το Mouse του χρήστη
     * βρίσκεται στο dimension της.
     */
    private void drawHoverEffect(Graphics g, int x, int y) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(3));

        g2d.setColor(Color.RED);

        if (interacting) {
            g2d.drawRect(x, y, sideSize, sideSize);
        }
    }

    /**
     *
     * @return
     */
    public BufferedImage getCardImage() {
        return img;
    }

    /**
     *
     * @return
     */
    public boolean isFlipped() {
        return flipped;
    }

    /**
     *
     */
    public void setFlipped() {
        this.flipped = true;
    }

    /**
     *
     * @param b
     */
    public void setFlipped(boolean b) {
        this.flipped = b;
    }

    /**
     *
     * @return
     */
    public boolean isFound() {
        return found;
    }

    /**
     *
     */
    public void setFound() {
        this.found = true;
    }

    private boolean flipped;
    private boolean interacting;
    private boolean found;

    private BufferedImage img;

    private GamePanel gamePanel;

    private Rectangle dimension;
    private static final int sideSize = 150;

    private User player, computer;
}
