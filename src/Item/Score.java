package Item;

import Game.Game;
import Render.RenderableIF;

import java.awt.*;

public class Score implements RenderableIF {
    @Override
    public void render(Graphics graphics) {
        //Print absoluteMaxHeight as a score
        graphics.setColor(Color.BLACK);
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 30));
        graphics.drawString(String.valueOf(Game.getInstance().getScore()), 20, 40);
    }
}
