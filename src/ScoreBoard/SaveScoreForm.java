package ScoreBoard;

import javax.swing.*;
import java.awt.*;

/**
 * Form for saving scores
 */
public class SaveScoreForm extends JFrame {

    private JTextField nameField;

    /**
     * Constructor creates the form
     * The form contains a text field for the name of the player and a button for saving the score
     * When the button is pressed, the score is saved to memory and file as well
     */
    public SaveScoreForm() {
        setTitle("Save Score");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(2, 1));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Name:"));
        nameField = new JTextField(10);
        panel.add(nameField);
        add(panel);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        JButton saveButton = new JButton("Save");
        panel2.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        panel2.add(cancelButton);

        cancelButton.addActionListener(e -> dispose());
        saveButton.addActionListener(e -> {
            if (nameField.getText().isEmpty()) {
                //When the name field is empty, show an error message
                JOptionPane.showMessageDialog(this, "Please enter a name", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ScoreBoardData.getInstance().addPlayer(new Player(nameField.getText(), Game.Game.getInstance().getScore()));
                ScoreSaver.saveScores(ScoreBoardData.getInstance(), "scores.xml");
                JOptionPane.showMessageDialog(this, "Saved score", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        add(panel2);
        setVisible(true);

    }
}
