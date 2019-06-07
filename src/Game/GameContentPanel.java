package Game;

import Menu.AboutPanel;
import History.EventLogPanel;
import java.awt.CardLayout;
import javax.swing.*;

/**
 *
 * @author dimitris
 */
public class GameContentPanel extends JPanel {
    
    /**
     *
     * @param diff
     * @param username
     */
    public GameContentPanel(String diff, String username) {
        this.difficulty = diff;
        this.username = username;
        initComponents();
    }
        
    private void initComponents() {
        
        cl = new CardLayout();
        this.setLayout(cl);   
        
        player = new User(username);
        player.setPlaying();
        computer = new User("Computer");
        
        
        gamePanel = new GamePanel(difficulty, player, computer);
        eventPanel = new EventLogPanel();
        aboutPanel = new AboutPanel();
        
        //computer.setRandom(gamePanel.getDeck().getRandomList());
        computer.setCardList(gamePanel.getCardList());
        
        this.add(gamePanel, "gamePanel");
        this.add(eventPanel, "eventPanel");     
        this.add(aboutPanel, "aboutPanel");        
    }   
    
    /**
     *
     * @param s
     * Αλλάζει το Panel που βλέπει ο χρήστης σύμφωνα με την επιλογή του Menu
     * που έχει δώσει.
     */
    public void changePanel(String s) {
        if (s.equals("game")) {
            cl.show(this, "gamePanel");            
        }
        else if (s.equals("event")){
            cl.show(this, "eventPanel");            
        }
        else if (s.equals("about")) {
            cl.show(this, "aboutPanel");
        }
    }

    public EventLogPanel getEventPanel() {
        return eventPanel;
    } 
    
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    
    private CardLayout cl;
    
    private User player, computer;
    
    private GamePanel gamePanel;
    private EventLogPanel eventPanel;
    private AboutPanel aboutPanel;
    
    private String difficulty, username;
}
