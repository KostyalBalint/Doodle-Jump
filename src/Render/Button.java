package Render;

import Item.Item;
import util.Vector2;

import java.awt.*;

/**
 * Custom button class, used to render buttons on the screen.
 */
public class Button extends Item implements RenderableIF {
    private Image image;

    /**
     * Constructor for the button class.
     *
     * @param position The position of the button.
     * @param image    The image of the button.
     */
    public Button(Vector2 position, Image image) {
        super(position, new Dimension(image.getWidth(null), image.getHeight(null)));
        this.image = image;
    }

    /**
     * Render the button.
     *
     * @param graphics The graphics object to render to.
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(image, (int) getX(), (int) getY(), null);
    }
}
