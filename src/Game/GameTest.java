package Game;

import Item.Doodler;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void gameExists() {
        Game game = Game.getInstance();
        assertNotNull("Game must exist", game);
    }

    @Test
    public void getDoodler() {
        Game game = Game.getInstance();
        assertNotNull("Doodler must exist", game.getDoodler());
    }

    @Test
    public void getScore() {
        Game game = Game.getInstance();
        assertTrue("Score must be low", game.getScore() < 100);
    }

    @Test
    public void reintializeGame() {
        Game game1 = Game.getInstance();
        Doodler doodler1 = game1.getDoodler();
        Game.reinitialize();
        Game game2 = Game.getInstance();
        assertSame("Game must be different", game1, game2);
        assertNotSame("Doodler must be different", doodler1, game2.getDoodler());
    }

}
