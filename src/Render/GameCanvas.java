package Render;

import java.awt.*;

public class GameCanvas extends Canvas {

    GameCanvas() {
        setBackground(Color.white);
    }

    public void paint(Graphics g, Iterable<RenderableIF> items) {
        super.paint(g);
        for (RenderableIF item : items) {
            item.render(g);
        }
    }
}
