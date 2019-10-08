package Game;

import History.MyFileWriter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import Listeners.MainTimerActionListener;
import Listeners.MouseInfo;
import Listeners.StartTimerActionListener;
import java.io.IOException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

/**
 *
 * @author dimitris
 */
public class GamePanel extends JPanel {

    // Προσθέτει μια καθυστέρηση στη σχεδίαση του backside των καρτών
    // οταν οι δύο κάρτες που έχουν επιλεγεί δεν είναι ζευγάρι
    private Timer timer = new Timer(500, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            c1.setFlipped();
            c2.setFlipped();
            timer.stop();
        }
    });

    /**
     * Μέγιστη δυνατή διάρκεια παιχνιδιού.
     * Αν η διάρκεια του(3 λεπτά) τελειώσει το παιχνίδι 
     * τερματίζει και εμφανίζεται ο νικητής
     */
    private Timer gameTimer = new Timer(180000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //gameOver = true;
            System.out.println("Time is over");
            //gameTimer.stop();
            findWinner();
        }
    });

    /**
     *
     * @param difficultyLevel
     */
    public GamePanel(String difficultyLevel) {
        this.images = null;
        this.difficultyLevel = difficultyLevel;
        initComponents();
    }

    /**
     *
     * @param difficultyLevel
     * @param u1
     * @param u2
     */
    public GamePanel(String difficultyLevel, User u1, User u2) {
        this.images = null;
        this.difficultyLevel = difficultyLevel;
        this.player = u1;
        this.computer = u2;
        initComponents();
    }

    private void initComponents() {

        numberOfMatches = 0;
        this.setBackground(Color.blue);

        mouse = new MouseInfo();
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        this.imageReader = new Image(imagesSize);

        this.backGround = imageReader.getBackGround();
        this.backSide = imageReader.getBackSide();
        this.empty = imageReader.getEmpty();
        this.images = imageReader.getImages();

        //Οι γραμμές και οι στήλες παίρνουν τιμές ανάλογα 
        //με το βαθμό δυσκολίας που επέλεξε ο χρήστης
        switch (difficultyLevel) {
            case "Easy":
                rows = 3;
                cols = 4;
                startDelay = 3000;
                break;
            case "Normal":
                rows = 4;
                cols = 5;
                startDelay = 2000;
                break;
            case "Hard":
                rows = 6;
                cols = 6;
                startDelay = 1500;
                break;
            default:
                break;
        }

        gameTimer.start();

        deck = new DeckOfCards(this, images, rows, cols);

        this.cardList = deck.getCardList();

        // Timer για εμφάνιση των εικόνων για ένα μικρο χρονικό διάστημα στην
        // αρχή του κάθε παιχνιδιού        
        startTimer = new Timer(startDelay, new StartTimerActionListener(this, cardList));
        startTimer.setInitialDelay(0);
        startTimer.setRepeats(false);
        startTimer.start();

        mainTimer = new Timer(delay, new MainTimerActionListener(this, cardList));
        mainTimer.setInitialDelay(3000);
        mainTimer.start();
    }
    
    /**
     * Το παιχνίδι επιστρέφει στην αρχική του κατάσταση
     */
    public void setup() {
        
        player.setScore(0);
        computer.setScore(0);
        User.setRounds(0);
        gameTimer.restart();
        numberOfMatches = 0;
        gameOver = false;
    }

    @Override
    public void paintComponent(Graphics g) {
         
        super.paintComponent(g);

        g.drawImage(backGround, 0, 0, null);

        paintCards(g);

        drawCurrentUsername(g);
    }

    /**
     * Ζωγραφίζει το board του παιχνιδιού.
     * Όταν ο χρήστης επιλέξει 2 κάρτες
     * γίνεται έλεγχος για το αν είναι ίδες ή όχι.
     * Γράφει στο history.txt τα συμβάντα κάθε γύρου.
     * @param g 
     */
    private void paintCards(Graphics g) {

        int x = 0;
        int y = 0;
        for (int i = 0; i < cardList.size(); i++) {
            cardList.get(i).render(g, 100 + x * 170, y * 160);
            x++;
            if (x == cols) {
                x = 0;
                y++;
            }
        }

        // CHANGE HERE
        if (computer.isPlaying() && gameTimer.isRunning()) {

            Card c = null;
            do {
                c = computer.play();
            } while(!c.isFlipped() || c.isFound());
            
            matchedList.add(c);
            c.setFlipped(false);
        }
        if (matchedList.size() == 2 && !gameOver) {

            c1 = matchedList.get(0);
            c2 = matchedList.get(1);

            // Οι κάρτες δεν είναι οι ίδιες
            if (!c1.getCardImage()
                    .equals(c2.getCardImage())) {

                if (player.isPlaying()) {
                    strB.append("Player ").append(player.getUsername()).append(" didn't find a pair");
                } else if (computer.isPlaying()) {
                    strB.append("Computer ").append(" didn't find a pair");
                }
                
                if (player.isPlaying()) {
                    c1.setFlipped(true);
                    c2.setFlipped(true);                    
                }

                //System.out.println("PICTURES DON'T MATCH!!!!");
                timer.start();

            } else {    // Βρέθηκε ζευγάρι
                c1.setFound();
                c2.setFound();

                numberOfMatches++;

                if (player.isPlaying()) {
                    player.scorePoints(10);
                    strB.append("Player ").append(player.getUsername()).append(" found a pair");
                } else if (computer.isPlaying()) {
                    computer.scorePoints(10);
                    strB.append("Computer ").append("found a pair");
                }

                strB.append("\nScore is now: Player: ").append(player.getScore()).append(" Computer: ").append(computer.getScore()).append("");

                findWinner();
            }
            

            if (gameTimer.isRunning()) {
                int rounds = User.getRounds();

                writeUserTurn(strB.toString());
                strB.delete(0, strB.length());
                changePlayer(rounds);

                User.setRounds();
                matchedList.clear();
            }

        }
    }

    /**
     * Υπολογίζει το timestamp του κάθε συμβάντος
     * και το γράφει στο history.txt
     * @param str 
     */
    private void writeUserTurn(String str) {
        MyFileWriter myWriter = new MyFileWriter();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        try {
            myWriter.appendFile(timestamp.toString() + ": " + str + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        myWriter.closeWriterAndBuffer();
    }

    /**
     * Οι παίκτες αλλάζουν σειρά (ξεκινάει ο Player πάντα)
     * @param rounds 
     */
    private void changePlayer(int rounds) {

        if (rounds % 2 == 1) {
            player.setPlaying(true);
            computer.setPlaying(false);
        } else if (rounds % 2 == 0) {
            computer.setPlaying(true);
            player.setPlaying(false);
        }

    }

    /**
     * Βρίσκει το νικητή της εφαρμογής αναλόγως τους πόντους που έχουν συγκεντρώσει
     * και εμφανίζει κατάλληλο μήνυμα μέσω JOptionPane.
     */
    private void findWinner() {       
        
        // Σύνολο ζευγαριών που μπορούν να γίνουν(6 για Easy, 10 για Normal, 18 για Hard)
        if (numberOfMatches == (rows * cols) / 2 || !gameTimer.isRunning()) {
            gameOver = true;
            gameTimer.stop();
            strB.append(("\nGame is over!")).append("\nFinal score: ").append(player.getScore()).append(", ").append(computer.getScore());
            if (computer.getScore() > player.getScore()) {
                computer.setWinner();
                //System.out.println("Computer has won");
                JOptionPane.showMessageDialog(null, "Computer has won!", "Winner", JOptionPane.INFORMATION_MESSAGE);
            } else if (computer.getScore() < player.getScore()) {
                player.setWinner();
                //System.out.println("Player has won");
                JOptionPane.showMessageDialog(null, "Player " + player.getUsername() + " has won!", "Winner",  JOptionPane.INFORMATION_MESSAGE);
            } else {
                //System.out.println("It's a DRAW!");
                JOptionPane.showMessageDialog(null, "It's a DRAW!", "Winner", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Γράφει το username και το score του παίκτη που έχει σειρά.
     * @param g 
     */
    private void drawCurrentUsername(Graphics g) {

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 25));

        switch (difficultyLevel) {
            case "Easy":
                if (player.isPlaying()) {
                    g.drawString("Playing: " + player.getUsername(), 780, 50);
                } else if (computer.isPlaying()) {
                    g.drawString("Playing: " + computer.getUsername(), 780, 50);
                }

                g.drawString("Player score: " + player.getScore(), 780, 80);
                g.drawString("Computer score: " + computer.getScore(), 780, 110);
                break;
            case "Normal":
                if (player.isPlaying()) {
                    g.drawString("Playing: " + player.getUsername(), 940, 50);
                } else if (computer.isPlaying()) {
                    g.drawString("Playing: " + computer.getUsername(), 940, 50);
                }

                g.drawString("Player score: " + player.getScore(), 940, 80);
                g.drawString("Computer score: " + computer.getScore(), 940, 110);
                break;
            case "Hard":
                if (player.isPlaying()) {
                    g.drawString("Playing: " + player.getUsername(), 1120, 50);
                } else if (computer.isPlaying()) {
                    g.drawString("Playing: " + computer.getUsername(), 1120, 50);
                }

                g.drawString("Player score: " + player.getScore(), 1120, 80);
                g.drawString("Computer score: " + computer.getScore(), 1120, 110);
                break;
            default:
                break;
        }
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
    public BufferedImage getEmpty() {
        return empty;
    }

    /**
     *
     * @return
     */
    public int getRows() {
        return rows;
    }

    /**
     *
     * @return
     */
    public int getCols() {
        return cols;
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
    public ArrayList<Card> getMatchedList() {
        return matchedList;
    }

    /**
     *
     * @return
     */
    public User getPlayer() {
        return player;
    }

    /**
     *
     * @return
     */
    public User getComputer() {
        return computer;
    }

    /**
     *
     * @return
     */
    public Timer getTimer() {
        return timer;
    }

    public Timer getGameTimer() {
        return gameTimer;
    }   

    /**
     *
     * @return
     */
    public DeckOfCards getDeck() {
        return deck;
    }

    /**
     *
     * @return
     */
    public int getImagesSize() {
        return imagesSize;
    }

    /**
     *
     * @return
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     *
     * @param flag
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     *
     */
    public void setNumberOfMatches() {
        numberOfMatches += 1;
    }

    private final String difficultyLevel;
    private int rows, cols;
    private int numberOfMatches;

    private Image imageReader;
    private BufferedImage[] images;
    private BufferedImage backSide;
    private BufferedImage backGround;
    private BufferedImage empty;
    private final int imagesSize = 20;

    private DeckOfCards deck;

    private Card c1, c2;
    private ArrayList<Card> cardList = new ArrayList<Card>();
    private ArrayList<Card> matchedList = new ArrayList<Card>();

    private MouseInfo mouse;

    private Timer mainTimer;
    private final int FPS = 30;
    private final int delay = 1000 / FPS;
    private int startDelay;

    private Timer startTimer;

    private User player;
    private User computer;

    private boolean gameOver = false;
    private boolean flag = true;

    private StringBuilder strB = new StringBuilder();

}
