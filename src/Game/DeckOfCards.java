package Game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Logger;

/**
 *
 * @author dimitris
 */
public class DeckOfCards {

    /**
     *
     * @param gamePanel
     * @param images
     * @param rows
     * @param cols
     */
    public DeckOfCards(GamePanel gamePanel, BufferedImage[] images, int rows, int cols) {
        this.gamePanel = gamePanel;
        this.images = images;
        this.rows = rows;
        this.cols = cols;
        initComponents();
    }

    private void initComponents() {

        makeNewCards();      

        // Ανακάτεμα των καρτών
        Collections.shuffle(cardList);
    }
    
    private void makeNewCards() {
    
        // Η λίστα γεμίζει με μοναδικές κάρτες
        int rand;
        for (int i = 0; i < rows * cols / 2; i++) {
            do {
                rand = new Random().nextInt(imagesSize);
            } while (randomList.contains(rand));

            randomList.add(rand);

            Card card = new Card(images[rand], gamePanel);
            cardList.add(card);
        }
        
        // Στη λίστα εισάγωνται οι ίδιες κάρτες δεύτερη φορά
        int size = cardList.size();
        for (int i = 0; i < size; i++) {
            cardList.add(new Card(cardList.get(i).getCardImage(), gamePanel));
        }
        
        size = randomList.size();
        for(int i = 0; i < size; i++) {
            randomList.add(randomList.get(i));
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Card> getCardList() {
        return cardList;
    }   

    /**
     *
     * @return
     */
    public ArrayList<Integer> getRandomList() {
        return randomList;
    }   
    
    private GamePanel gamePanel;

    private int rows, cols;
    private final int imagesSize = 20;
    private BufferedImage[] images;

    private ArrayList<Integer> randomList = new ArrayList<>();
    private ArrayList<Card> cardList = new ArrayList<Card>();
}
