package Listeners;

import memorygame.PlayFrame;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author dimitris
 */
public class RestartListener implements ActionListener {

    /**
     *
     * @param frame
     * @param username
     */
    public RestartListener(PlayFrame frame, String username) {
        this.frame = frame;
        this.username = username;
    }

    /**
     * Δημιουργεί νέο PlayFrame
     * Τα πεδία του GamePanel επιστρέφουν στην αρχική τους κατάσταση.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int result = JOptionPane.showOptionDialog(null, "Are you sure?", "Restart",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

        switch (result) {
            case 0:
                String diff = frame.getDifficulty();
                frame.dispose();
                EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        frame = new PlayFrame(diff, username);
                        frame.getGameContentPanel().getGamePanel().setup();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
                break;
            case 1:
                break;
        }
    }

    private PlayFrame frame;
    private String username;
}
