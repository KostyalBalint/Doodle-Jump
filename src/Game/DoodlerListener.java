package Game;

import Item.Doodler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles the key events of the game.
 * Moves the doodler in the direction of the key pressed.
 */
public class DoodlerListener implements KeyListener {

    Doodler doodler;

    /**
     * Sets the doodler.
     *
     * @param doodler the doodler
     */
    public DoodlerListener(Doodler doodler) {
        this.doodler = doodler;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Sets the Doodler acceleration in the direction of the key pressed.
     *
     * @param e the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            doodler.setAcceleration(Doodler.Dir.LEFT);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            doodler.setAcceleration(Doodler.Dir.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        doodler.setAcceleration(Doodler.Dir.NONE);
    }
}
