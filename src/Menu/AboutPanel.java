package Menu;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author dimitris
 */
public class AboutPanel extends JPanel {

    public AboutPanel() {
        initComponents();
    }
    
    private void initComponents() {
        this.textArea = new JTextArea(10, 20);

        scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);
        textArea.append("\t\tΣχετικά με το πρόγραμμα\n\n");
        textArea.append("Το πρόγραμμα αναπτύχθηκε στα πλαίσια του εργαστηριακού μέρους \nτου μαθήματος Αρχές Τεχνολογίας Λογισμικού \nγια το ακαδημαϊκό έτος 2018-2019.");
        textArea.append("\n\n\n\n\n\t\tΜήλιος Δημήτρης - 4282");

        scroll.setPreferredSize(new Dimension(480, 240));

        textArea.setWrapStyleWord(true);

        this.add(scroll);
    }
    
    private JTextArea textArea;
    private JScrollPane scroll;
    
}
