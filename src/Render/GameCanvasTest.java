package Render;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GameCanvasTest {

    @Test
    public void testGameCanvas() {
        GameCanvas gc = new GameCanvas();
        assertNotNull("Game canvas must be created", gc);
    }
}
