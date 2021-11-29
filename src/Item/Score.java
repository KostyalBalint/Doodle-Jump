package Item;

import Game.Game;
import Render.RenderableIF;

import java.awt.*;

/**
 * The score display in the top left corner of the screen.
 */
public class Score implements RenderableIF {
    /**
     * Render function, this get's the score and displays it.
     *
     * @param graphics The graphics object to draw on.
     */
    @Override
    public void render(Graphics graphics) {
        //Print absoluteMaxHeight as a score
        graphics.setColor(Color.BLACK);
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 30));
        graphics.drawString(String.valueOf(Game.getInstance().getScore()), 20, 40);
    }
}
