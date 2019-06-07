package Menu;

import memorygame.PlayButtonActionListener;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;
import memorygame.MainFrame;
import memorygame.PlayFrame;

/**
 *
 * @author dimitris
 */
public class MenuPanel extends JPanel {

    /**
     *
     * @param frame
     */
    public MenuPanel(MainFrame frame) {
        initComponents();
    }

    private void initComponents() {

        buttonGroup1 = new ButtonGroup();
        nameLabel = new JLabel();
        nameTextInput = new TextField();
        jSeparator1 = new JSeparator();
        easyButton = new JRadioButton();
        normalButton = new JRadioButton();
        hardButton = new JRadioButton();
        diffLabel = new JLabel();
        playButton = new JButton();
        memTitle = new JLabel();

        setBackground(new Color(204, 204, 204));

        nameLabel.setText("Enter your name: ");

        nameTextInput.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        nameTextInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nameTextInputActionPerformed(evt);
            }
        });

        easyButton.setText("Easy");
        easyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                easyButtonActionPerformed(evt);
            }
        });

        normalButton.setText("Normal");

        hardButton.setText("Hard");

        // Remove outline when buttons are pressed
        easyButton.setRequestFocusEnabled(false);
        normalButton.setRequestFocusEnabled(false);
        hardButton.setRequestFocusEnabled(false);

        diffLabel.setText("Choose difficulty:");

        playButton.setFont(new Font("Comic Sans MS", 1, 14));
        playButton.setText("PLAY");

        playButton.addActionListener(new PlayButtonActionListener(nameTextInput, playFrame, this));

        // Add buttons to button group
        buttonGroup1.add(easyButton);
        buttonGroup1.add(normalButton);
        buttonGroup1.add(hardButton);

        memTitle.setFont(new Font("Comic Sans MS", 1, 48));
        memTitle.setText("MEMORY GAME");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(nameLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nameTextInput, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(diffLabel)
                                                .addGap(29, 29, 29)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(normalButton)
                                                        .addComponent(easyButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(hardButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(playButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(memTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(157, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(memTitle, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nameTextInput, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(14, 14, 14)
                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(easyButton)
                                        .addComponent(diffLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(normalButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(hardButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(playButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(45, Short.MAX_VALUE))
        );
    }                       

    private void easyButtonActionPerformed(ActionEvent evt) {
        
    }

    private void nameTextInputActionPerformed(ActionEvent evt) {
        
    }

    /**
     * 
     * @return το κουμπί που επιλέχθηκε από το χρήστη.
     */

    public String getSelectedButtonText() {
        if (easyButton.isSelected()) {
            return easyButton.getText();
        } else if (normalButton.isSelected()) {
            return normalButton.getText();
        } else if (hardButton.isSelected()) {
            return hardButton.getText();
        }

        return null;
    }

    /**
     *
     * @return
     */
    public TextField getNameTextInput() {
        return nameTextInput;
    }  
                   
    private ButtonGroup buttonGroup1;
    private JLabel nameLabel;
    private JLabel diffLabel;
    private JLabel memTitle;
    private JRadioButton easyButton;
    private JRadioButton normalButton;
    private JRadioButton hardButton;
    private JSeparator jSeparator1;
    private JButton playButton;
    private TextField nameTextInput;

    private static PlayFrame playFrame;                  

}
