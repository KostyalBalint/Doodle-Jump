package ScoreBoard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ScoreBoardDataTest {

    @Test
    public void ScoreBoardData() {
        ScoreBoardData data = new ScoreBoardData();
        assertNotNull("Score Board Data must be created", data);
    }

    @Test
    public void getScoreBoardData() {
        ScoreBoardData data = new ScoreBoardData();
        assertEquals("Score Board Data default player count must be 0", data.getPlayers().size(), 0);
    }

    @Test
    public void getPlayerCount() {
        ScoreBoardData data = new ScoreBoardData();
        assertEquals("Score Board Data default player count must be 0", data.getPlayers().size(), 0);
    }

    @Test
    public void addPlayer() {
        ScoreBoardData data = new ScoreBoardData();
        data.addPlayer(new Player("Player 1", 10));
        assertEquals("Score Board Data player count must be 1", data.getPlayers().size(), 1);
    }

    @Test
    public void removePlayer() {
        ScoreBoardData data = new ScoreBoardData();
        data.addPlayer(new Player("Player 1", 10));
        data.addPlayer(new Player("Player 2", 10));
        data.removePlayer(new Player("Player 1", 10));
        assertEquals("Score Board Data player count must be 1", data.getPlayers().size(), 1);
        assertEquals("Score Board Data player name must be Player 2", data.getPlayers().get(0).getName(), "Player 2");
    }

}
