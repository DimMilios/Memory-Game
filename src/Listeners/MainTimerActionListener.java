package Listeners;

import Game.Card;
import Game.GamePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author dimitris
 */
public class MainTimerActionListener implements ActionListener {

    /**
     *
     * @param panel
     * @param list
     */
    public MainTimerActionListener(GamePanel panel, ArrayList<Card> list) {
        this.panel = panel;
        this.cardList = list;
       // this.flag = true;
    }

    /**
     * Αλλάζει τις εικόνες ώστε να φαίνεται η πίσω πλευρά τους
     * μετά τη σύντομη εμφάνιση τους από τον startTimer.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (flag == true) {
            for (Card c : cardList) {
                c.setFlipped(true);
            }
            flag = false;
        }
        
        panel.repaint();
    }

    /**
     *
     * @param b
     */
    public void setFlag(boolean b) {
        this.flag = b;
    }

    private ArrayList<Card> cardList;
    private GamePanel panel;
    private boolean flag = true;
}
