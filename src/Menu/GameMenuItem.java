package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import memorygame.PlayFrame;
import Listeners.RestartListener;
import java.io.File;

/**
 *
 * @author dimitris
 */
public class GameMenuItem extends JMenu {

    /**
     *
     * @param name
     * @param frame
     */
    public GameMenuItem(String name, PlayFrame frame) {
        this.name = name;
        this.frame = frame;
        this.x = frame.getX();
        this.y = frame.getY();
        initComponents();
    }

    private void initComponents() {

        this.setText(name);

        switch (name) {
            case "Game":
                addGameMenuItems();
                break;
            case "Options":
                addOptionsMenuItems();
                break;
            case "Help":
                about = new JMenuItem("About");
                about.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setSize(520, 320);
                        frame.getGameContentPanel().changePanel("about");
                    }
                });
                this.add(about);
                break;
            default:
                break;
        }
    }

    /**
     * Προσθέτει τις επιλογές στο Game τμήμα του menu.
     */
    private void addGameMenuItems() {

        newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("./src/History/history.txt");
                file.delete();
                frame.getGameContentPanel().getGamePanel().setup();
                frame.dispose();
            }
        });
        restart = new JMenuItem("Restart");
        restart.addActionListener(new RestartListener(frame, frame.getUsername()));
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(JFrame.EXIT_ON_CLOSE);
            }
        });

        this.add(newGame);
        this.add(restart);
        this.add(exit);
    }

    /**
     * Προσθέτει τις επιλογές στο Options τμήμα του menu.
     */
    private void addOptionsMenuItems() { 
        
        historySubMenu = new JMenu("History");
        showHistory = new JMenuItem("Show History");
        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setSize(860, 710);
                frame.getGameContentPanel().changePanel("event");
                frame.getGameContentPanel().getEventPanel().refreshTextArea();
            }            
        });
        
        hideHistory = new JMenuItem("Hide History");
        hideHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setSize(x, y);
                frame.getGameContentPanel().changePanel("game");
            }            
        });
        
        historySubMenu.add(showHistory);
        historySubMenu.add(hideHistory);

        this.add(historySubMenu);
    }

    private JMenu historySubMenu;
    private JMenuItem showHistory, hideHistory;

    private JMenuItem newGame, restart, exit;
    private JMenuItem about;
    private String name;
    
    private int x,y;
    
    private PlayFrame frame;
}
