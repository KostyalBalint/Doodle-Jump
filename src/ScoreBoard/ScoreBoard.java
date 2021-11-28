package ScoreBoard;

import java.util.LinkedList;

public class ScoreBoard {

    private static ScoreBoard instance;
    private static LinkedList<Player> players;
    private static Player currentPlayer;

    private ScoreBoard() {
        players = new LinkedList<>();
    }

    public static ScoreBoard getInstance() {
        if (instance == null) {
            instance = new ScoreBoard();
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
}
