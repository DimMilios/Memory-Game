package memorygame;

import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import History.MyFileWriter;
import Menu.MenuPanel;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dimitris
 */
public class PlayButtonActionListener implements ActionListener {

    /**
     *
     * @param nameTextInput
     * @param playFrame
     * @param panel
     */
    public PlayButtonActionListener(TextField nameTextInput, PlayFrame playFrame, MenuPanel panel) {
        this.nameTextInput = nameTextInput;
        this.playFrame = playFrame;
        this.menuPanel = panel;  
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Ελέγχει αν ο χρήστης συμπλήρωσε username αλλιώς βγάζει αντίστοιχο μήνυμα
        // Το username πρέπει να αποτελείται από κεφαλαία/πεζά γράμματα, αριθμούς ή _
        // Έχει μήκος 3 εώς 20 χαρακτήρες και δεν μπορεί να ξεκινάει από _
        if (nameTextInput.getText().matches("^(?=.{1,20}$)(?![_])[a-zA-Z0-9_]+(?<![_])$")) {
            // Ανοίγει καινούριο PlayFrame όταν ο χρήστης πατήσει PLAY
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {                        
                        selectedButton = menuPanel.getSelectedButtonText();
                        if (selectedButton != null) {
                            playFrame = new PlayFrame(selectedButton, nameTextInput.getText());
                            playFrame.setVisible(true);                            
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "You must select a difficulty", 
                                    "Missing difficulty", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else {
            //JOptionPane.showMessageDialog(null, "You must enter a username to play", "Missing username", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Username is not valid\n Username must be "
                    + "3-20 characters long and consist off letters(a-z, A-Z),"
                    + " numbers or _", "Invalid username", JOptionPane.ERROR_MESSAGE);
        }

        writeGameStartTime();
    }
    
    private void writeGameStartTime() {
        
        MyFileWriter myWriter = new MyFileWriter();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());        
        
        try {
            myWriter.writeFile(timestamp.toString() + ": Game start\n");
            myWriter.appendFile(timestamp.toString() + ": Player username set to: " + nameTextInput.getText() + "\n");
            myWriter.appendFile(timestamp.toString() + ": Game difficulty set to: " + menuPanel.getSelectedButtonText() + "\n");
            myWriter.appendFile(timestamp.toString() + ": Player "+ nameTextInput.getText() + " is playing\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
        
        myWriter.closeWriterAndBuffer();
        
    }

    private TextField nameTextInput;
    private PlayFrame playFrame;
    private MenuPanel menuPanel;
    private String selectedButton;
    
    //private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
}
