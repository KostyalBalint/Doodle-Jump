package ScoreBoard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testPlayer() {
        Player player = new Player("test", 0);
        assertEquals("Player name must be set", player.getName(), "test");
        assertEquals("Player score must be set", player.getScore(), 0);
    }

    @Test
    public void testSetName() {
        Player player = new Player("test", 0);
        player.setName("test2");
        assertEquals("Player name must be set", player.getName(), "test2");
    }

    @Test
    public void testSetScore() {
        Player player = new Player("test", 0);
        player.setScore(10);
        assertEquals("Player score must be set", player.getScore(), 10);
    }

    @Test
    public void testSetScore2() {
        Player player = new Player("test", 1000);
        player.setScore(50);
        assertEquals("Player score must be the higher value", player.getScore(), 1000);
    }
}
