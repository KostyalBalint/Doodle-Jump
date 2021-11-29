package Item;

import Render.RenderableIF;
import util.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Black hole item.
 */
public class BlackHole extends Item implements RenderableIF {

    public static int width = 150;
    public static int height = 150;

    /**
     * Constructor of the BlackHole class.
     * This loads the image of the black hole from file
     *
     * @param position The position of the black hole.
     */
    public BlackHole(Vector2 position) {
        super(position, new Dimension(width, height));
        try {
            this.setImage(ImageIO.read(new File("src/assets", "blackhole.png")));
            this.setSize(new Dimension(this.getImage().getWidth(null), this.getImage().getHeight(null)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renders the black hole
     * @param graphics The graphics object to draw on.
     */
    @Override
    public void render(Graphics graphics) {
        //Print the black hole position as a text
        //graphics.setColor(Color.BLACK);
        //graphics.drawString("Black Hole: X: " + (int)this.getPosition().x + " Y: " + (int)this.getPosition().y, 20, 60);

        graphics.setColor(Color.BLUE);
        //graphics.fillRect((int)this.getRenderCoordinate().x, (int)this.getRenderCoordinate().y, this.getSize().width, this.getSize().height);
        graphics.drawImage(this.getImage(), (int) this.getRenderCoordinate().x, (int) this.getRenderCoordinate().y, null);
    }
}
