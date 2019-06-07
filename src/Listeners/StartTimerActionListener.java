
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
public class StartTimerActionListener implements ActionListener {

    /**
     *
     * @param panel
     * @param list
     */
    public StartTimerActionListener(GamePanel panel, ArrayList<Card> list) {
        this.panel = panel;
        this.cardList = panel.getCardList();
    }
    
    /**
     * Εμφανίζεται το περιεχόμενο των εικόνων.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        for(Card c : cardList) {
            c.setFlipped(false);
        }
        panel.repaint();
        
    }
 
 
    private ArrayList<Card> cardList;
    private GamePanel panel;
}
