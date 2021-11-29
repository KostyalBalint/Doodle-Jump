package Item;

import org.junit.Before;
import org.junit.Test;
import util.Vector2;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class BlackHoleTest {

    BlackHole blackHole;

    @Before
    public void setUp() {
        blackHole = new BlackHole(new Vector2(0, 0));
    }

    @Test
    public void createBlackHole() {

        assertNotNull("Black hole must exist", blackHole);
    }

    @Test
    public void getImage() {
        assertNotNull("Must have an image", blackHole.getImage());
    }

    @Test
    public void getHitbox() {
        assertNotEquals("Must have a hitbox", blackHole.getSize().width, 0);
        assertNotEquals("Must have a hitbox", blackHole.getSize().height, 0);
    }
}
