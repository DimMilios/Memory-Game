package memorygame;

import Game.GameContentPanel;
import Game.GamePanel;
import History.EventLogPanel;
import Menu.GameMenuBar;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

/**
 *
 * @author dimitris
 */
public class PlayFrame extends JFrame {

    /**
     *
     * @param difficulty
     */
    public PlayFrame(String difficulty) {
        super("Memory Game");
        this.difficulty = difficulty;
        initComponents();
    }

    /**
     *
     * @param difficulty
     * @param username
     */
    public PlayFrame(String difficulty, String username) {
        super("Memory Game");
        this.difficulty = difficulty;
        this.username = username;
        initComponents();
    }

    private void initComponents() {

        switch (difficulty) {
            case "Easy":
                X = easyX;
                Y = easyY;
                break;
            case "Normal":
                X = normalX;
                Y = normalY;
                break;
            case "Hard":
                X = hardX;
                Y = hardY;
                break;
            default:
                break;
        }
        this.setJMenuBar(new GameMenuBar(this));

        this.setSize(X, Y);
        
        gameContentPanel = new GameContentPanel(difficulty, username);
        this.add(gameContentPanel);

        //this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     *
     * @return
     */
    public String getDifficulty() {
        return difficulty;
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
    public GameContentPanel getGameContentPanel() {
        return gameContentPanel;
    }  

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }  

    private GameContentPanel gameContentPanel;
    private GamePanel gamePanel;
    private EventLogPanel eventPanel;
    private String difficulty, username;

    private final int easyX = 1040, easyY = 600;
    private final int normalX = 1240, normalY = 800;
    private final int hardX = 1440, hardY = 1040;
    private int X, Y;


}
