package Game;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author dimitris
 */
public class User {

    /**
     *
     * @param username
     */
    public User(String username) {
        this.username = username;
        this.playing = false;
        this.winner = false;
    }

    /**
     *
     * @return
     */
    public Card play() {
//        Card card = null;
//        while(card == null) {
            //int index = new Random().nextInt(cardList.size());
//            card = cardList.get(index);
//            cardList.remove(index);
//        }
Card c = null;
        int index;
        do {
            index = new Random().nextInt(cardList.size());
            c = cardList.get(index);
        } while (c.isFound());

        //Card c = cardList.get(index);
        //cardList.remove(index);
        //System.out.println("Computer is playing");
       //return cardList.get(index);
        //return card;
       return c;

        //return null;
    }
    
//    public void play() {
//        
//    }
    
    /**
     *
     * @param points
     * Προσθέτει πόντους στο συνολικό score του παίκτη.
     */
    public void scorePoints(int points) {
        this.score += points;
    }
    
    
    public void removeCard(Card c) {
        this.cardList.remove(c);
    }

//    public Card play() {
//        int randomA = new Random().nextInt(cardList.size());
//        
//        
//            try {
//                Card c = cardList.get(randomA);
//                return c;
//            } catch(IndexOutOfBoundsException e) {
//                e.printStackTrace();
//            }
//                        
//        }
//        return null;
//    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public static int getRounds() {
        return rounds;
    }

    /**
     *
     */
    public static void setRounds() {
        rounds++;
    }
    
    /**
     *
     */
    public static void setRounds(int val) {
        rounds = val;
    }

    /**
     *
     * @param newValue
     */
    public void setScore(int newValue) {
        score = newValue;
    }

    /**
     *
     */
    public void setPlaying() {
        this.playing = true;
    }

    /**
     *
     * @param b
     */
    public void setPlaying(boolean b) {
        this.playing = b;
    }

    /**
     *
     */
    public void setWinner() {
        this.winner = true;
    }

    /**
     *
     * @return
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     *
     * @param randomList
     */
    public void setRandom(ArrayList<Integer> randomList) {
        this.randomList = randomList;
    }

    /**
     *
     * @param cardList
     */
    public void setCardList(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }
    

    private String username;

    private int score;
    private static int rounds = 0;

    private boolean playing;
    private boolean winner;

    private ArrayList<Integer> randomList = new ArrayList<>();
    private ArrayList<Card> cardList = new ArrayList<Card>();
    
    private ArrayList<Integer> pickedList = new ArrayList<>();
}
