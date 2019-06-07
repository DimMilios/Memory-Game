package History;

import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author dimitris
 */
public class EventLogPanel extends JPanel {

    /**
     *
     */
    public EventLogPanel() {
        initComponents();
    }

    private void initComponents() {

        this.textArea = new JTextArea(10, 20);

        scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);

        scroll.setPreferredSize(new Dimension(840, 640));

        textArea.setWrapStyleWord(true);

        this.add(scroll);
        refreshTextArea();

    }
    
    /**
     * Γράφει στο history.txt τα συμβάντα του παιχνιδιού.
     * Ανανεώνει το textArea.
     * 
     */
    public void refreshTextArea() {
        File file = new File("./src/History/history.txt");
        InputStream targetStream = null;
        try {
            targetStream = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EventLogPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(targetStream));
        String line;
        while (true) {
            try {
                line = reader.readLine(); // blocks until next line available
                if(line == null)
                    break;
                textArea.append(line + "\n");
                // do whatever You want with line
            } catch (IOException ex) {
                
            }
        }
    }

    private JTextArea textArea;

    private JScrollPane scroll;

}
