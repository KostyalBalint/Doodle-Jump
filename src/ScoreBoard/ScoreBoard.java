package ScoreBoard;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

/**
 * ScoreBoard window
 * This renders a table with all the scores
 */
public class ScoreBoard extends JFrame {
    private ScoreBoardData data;

    /**
     * Constructor creats a new ScoreBoard window
     */
    public ScoreBoard() {
        super("Score Board");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            data = ScoreBoardData.getInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setMinimumSize(new Dimension(500, 400));
        initComponents();
    }

    /**
     * Initialize the components
     * Add the table to the window
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());

        //Table
        JTable table = new JTable(data);
        table.setRowSorter(new TableRowSorter<TableModel>(table.getModel()));
        table.setFillsViewportHeight(true);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
