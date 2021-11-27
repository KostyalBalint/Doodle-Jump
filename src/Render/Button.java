package Render;

import javax.imageio.ImageIO;
import java.awt.*;
import Item.Item;
import util.Vector2;

public class Button extends Item implements RenderableIF {
    private Image image;

    public Button(Vector2 position, Image image){
        super(position, new Dimension(image.getWidth(null), image.getHeight(null)));
        this.image = image;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(image, (int)getX(), (int)getY(), null);
    }
}
