
package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author dimitris
 */
public class Image {
    
    /**
     *
     * @param imgSize
     */
    public Image(int imgSize) {
        this.imagesSize = imgSize;
        initComponents();
    }
    
    /**
     * Διαβάζει τις κάρτες από το file images.
     */
    private void initComponents() {
        
        try {
            backGround = ImageIO.read(new File("src/images/background.jpg"));
        } catch (IOException s) {
            s.printStackTrace();
        }
        
        try {
            empty = ImageIO.read(new File("src/images/empty.png"));
        } catch (IOException s) {
            s.printStackTrace();
        }

        try {
            backSide = ImageIO.read(new File("src/images/flipped.png"));
        } catch (IOException s) {
            s.printStackTrace();
        }

        // Το images array έχει όλες τις εικόνες (1-20)
        images = new BufferedImage[imagesSize];
        for (int i = 0; i < images.length; i++) {
            try {
                images[i] = ImageIO.read(new File("src/images/" + (i + 1) + ".png"));
            } catch (IOException s) {
                s.printStackTrace();
            }
        }
        
    }

    /**
     *
     * @return
     */
    public BufferedImage[] getImages() {
        return images;
    }

    /**
     *
     * @return
     */
    public BufferedImage getBackSide() {
        return backSide;
    }

    /**
     *
     * @return
     */
    public BufferedImage getBackGround() {
        return backGround;
    }

    /**
     *
     * @return
     */
    public BufferedImage getEmpty() {
        return empty;
    }   
    
    private BufferedImage[] images;
    private BufferedImage backSide;
    private BufferedImage backGround;
    private BufferedImage empty;
    
    private final int imagesSize;
}
