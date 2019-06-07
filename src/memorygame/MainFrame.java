package memorygame;

import Menu.GameMenuBar;
import Menu.MenuPanel;
import javax.swing.*;

/**
 *
 * @author dimitris 14/4/2019
 */
public class MainFrame extends JFrame {

    /**
     *
     */
    public MainFrame() {
        super("Memory Game");
        initComponents();
    }

    private void initComponents() {
        //gameMenu = new GameMenuBar();
        //this.setJMenuBar(gameMenu);

        this.menuPanel = new MenuPanel(this);
        this.add(this.menuPanel);

        this.setSize(640, 480);
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private MenuPanel menuPanel;
}
