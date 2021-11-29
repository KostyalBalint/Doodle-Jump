package ScoreBoard;

/**
 * Player class stores the name and score of a player.
 */
public class Player {
    private String name;
    private int score;

    /**
     * Constructor for Player class.
     *
     * @param name  name of the player
     * @param score score of the player
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the name of the player.
     *
     * @return name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name Name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the score of the player.
     *
     * @return score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score of the player, if the score is grater than the previous score.
     *
     * @param score Score of the player
     */
    public void setScore(int score) {
        if (score > this.score) {
            this.score = score;
        }
    }
}
