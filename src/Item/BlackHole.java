package Item;

import Render.RenderableIF;
import math.Vector2;

import java.awt.*;

public class BlackHole extends Item implements RenderableIF {

    public static int width = 150;
    public static int height = 150;

    public BlackHole(Vector2 position) {
        super(position, new Vector2(width, height));
    }

    @Override
    public void render(Graphics graphics) {
        //Print the black hole position as a text
        //graphics.setColor(Color.BLACK);
        //graphics.drawString("Black Hole: X: " + (int)this.getPosition().x + " Y: " + (int)this.getPosition().y, 20, 60);

        graphics.setColor(Color.BLACK);
        graphics.fillOval((int)this.getRenderCoordinate().x, (int)this.getRenderCoordinate().y, this.getSize().width, this.getSize().height);
    }
}
