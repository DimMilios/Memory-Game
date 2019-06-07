package Menu;

import javax.swing.JMenuBar;
import memorygame.PlayFrame;

/**
 *
 * @author dimitris
 */
public class GameMenuBar extends JMenuBar {

    /**
     *
     * @param frame
     */
    public GameMenuBar(PlayFrame frame) {
        this.frame = frame;
        initGameMenu();
    }

    /**
     * Περιέχει τις 3 επιλογές του κυρίος Menu.
     */
    private void initGameMenu() {
        // Οι κύριες επιλογές στο menu του παιχνιδιού.
        game = new GameMenuItem("Game", frame);
        options = new GameMenuItem("Options", frame);
        help = new GameMenuItem("Help", frame);

        this.add(game);
        this.add(options);
        this.add(help);
    }

    private GameMenuItem game, options, help;
    private PlayFrame frame;
}
