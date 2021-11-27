package Item;

import math.Vector2;
import Render.RenderableIF;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Platform extends Item implements UpdatableIF, RenderableIF {

    public static int width = 100;
    public static int height = 20;

    public Platform(Vector2 position) {
        super(position, new Dimension(width, height));
        try {
            this.setImage(ImageIO.read(new File("src/assets", "tile.png")));
            this.setSize(new Dimension(this.getImage().getWidth(null), this.getImage().getHeight(null)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        Vector2 drawPosition = this.getRenderCoordinate();
        //graphics.fillRect((int)drawPosition.x, (int)drawPosition.y, this.getSize().width, this.getSize().height);
        graphics.drawImage(this.getImage(), (int)drawPosition.x, (int)drawPosition.y, null);
    }
}
