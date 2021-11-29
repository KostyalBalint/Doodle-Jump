package ScoreBoard;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;

/**
 * Data model for the scoreboard.
 * Player name is a UNIQUELY identifying string.
 */
public class ScoreBoardData extends AbstractTableModel {

    private static ScoreBoardData instance;
    private static LinkedList<Player> players;
    private static Player currentPlayer;

    /**
     * Constructor sets the players list to an empty LinkedList.
     */
    ScoreBoardData() {
        players = new LinkedList<>();
    }

    /**
     * Returns the instance of the ScoreBoardData.
     *
     * @return instance of ScoreBoardData
     */
    public static ScoreBoardData getInstance() {
        if (instance == null) {
            instance = new ScoreBoardData();
        }
        return instance;
    }

    /**
     * Adds a player to the scoreboard.
     * If the player already exists, the score is updated.
     *
     * @param player the player to add
     */
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

    /**
     * Remove a player by name from the scoreboard.
     *
     * @param player Player to remove
     */
    public void removePlayer(Player player) {
        players.removeIf(p -> p.getName().equals(player.getName()));
    }

    /**
     * Returns the current player, current player is always the last player added.
     *
     * @return current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Get the players
     *
     * @return all the players in the scoreboard
     */
    public LinkedList<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the number of players in the scoreboard.
     *
     * @return number of players
     */
    @Override
    public int getRowCount() {
        return players.size();
    }

    /**
     * Returns the number of columns in the scoreboard.
     *
     * @return number of columns
     */
    @Override
    public int getColumnCount() {
        return 2;
    }

    /**
     * Returns the name of the column at the given index.
     *
     * @param columnIndex index of the column
     * @return name of the column
     */
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

    /**
     * Returns the value at the given row and column.
     *
     * @param rowIndex    index of the row
     * @param columnIndex index of the column
     * @return value at the given row and column
     */
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
