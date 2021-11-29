package ScoreBoard;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

public class ScoreBoardData extends AbstractTableModel {

    private static ScoreBoardData instance;
    private static LinkedList<Player> players;
    private static Player currentPlayer;

    private ScoreBoardData() {
        players = new LinkedList<>();
    }

    public static ScoreBoardData getInstance() {
        if (instance == null) {
            instance = new ScoreBoardData();
        }
        return instance;
    }

    public void addPlayer(Player player) {
        currentPlayer = player;
        Boolean found = false;
        for (Player p : players) {
            if (p.getName().equals(player.getName())) {
                p.setScore(player.getScore());
                found = true;
            }
        }
        if (!found) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        players.removeIf(p -> p.getName().equals(player.getName()));
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public int getRowCount() {
        return players.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Name";
            case 1:
                return "Score";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player player = players.get(rowIndex);
        if (player == null) {
            return null;
        }

        switch (columnIndex) {
            case 0:
                return player.getName();
            case 1:
                return player.getScore();
        }
        return null;
    }
}
