package memorygame;

import java.awt.EventQueue;

/**
 *
 * @author dimitris
 */
public class MemoryGame {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    menu = new MainFrame();
                    menu.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        
        });
    }
    
    private static MainFrame menu;    
}
