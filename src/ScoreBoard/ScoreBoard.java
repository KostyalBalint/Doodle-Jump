package ScoreBoard;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScoreBoard extends JFrame {
    private ScoreBoardData data;

    public ScoreBoard() {
        super("Score Board");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            data = ScoreBoardData.getInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

            }
        });

        setMinimumSize(new Dimension(500, 400));
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());

        //Table
        JTable table = new JTable(data);
        table.setRowSorter(new TableRowSorter<TableModel>(table.getModel()));
        table.setFillsViewportHeight(true);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
