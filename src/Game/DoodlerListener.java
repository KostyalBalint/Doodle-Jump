package Game;

import Item.Doodler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DoodlerListener implements KeyListener {

    Doodler doodler;

    public DoodlerListener(Doodler doodler) {
        this.doodler = doodler;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

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
