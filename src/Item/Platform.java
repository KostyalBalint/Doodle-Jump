package Item;

import math.Vector2;
import Render.RenderableIF;

import java.awt.*;

public class Platform extends Item implements UpdatableIF, RenderableIF {

    public static int width = 100;
    public static int height = 10;

    public Platform(Vector2 position) {
        super(position, new Vector2(width, height));
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        Vector2 drawPosition = this.getRenderCoordinate();
        graphics.fillRect((int)drawPosition.x, (int)drawPosition.y, this.getSize().width, this.getSize().height);
    }
}
