package Item;

import Render.RenderableIF;
import util.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Doodler extends Item implements UpdatableIF, RenderableIF {
    public static enum Dir {
        LEFT,
        RIGHT,
        NONE,
    }

    private Dimension screenSize; //TODO: Remove this, and use Game.getWindowSize() instead
    private LinkedList<Platform> platforms;
    private BlackHole blackHole;
    private Image leftImage;
    private Image rightImage;

    public static int maxJumpHeight = 300;
    private float g = 0.5f;     //Gravity
    private float ax = 0.0f;    //Acceleration in X coordinate, controlled by the player
    private float vy = 0;       //Velocity in Y coordinate
    private float vx = 0;       //Velocity in X coordinate
    private float absoluteMaxHeight = 0;
    private boolean died = false;

    //Put the doodler in the middle of the screen
    public Doodler(Vector2 position, Dimension screenSize) {
        super(position.add(-50 / 2.0f, -25 / 2.0f), new Dimension(50, 50));
        absoluteMaxHeight = this.getPosition().y;
        this.screenSize = screenSize;
        try {
            leftImage = ImageIO.read(new File("src/assets/doodler-left.png"));
            rightImage = ImageIO.read(new File("src/assets/doodler-right.png"));
            this.setImage(leftImage);
            //Collision box is different size than the doodler image, this is because the left and right images
            this.setSize(new Dimension(this.getImage().getWidth(null) / 2, this.getImage().getHeight(null)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getAbsoluteMaxHeight() {
        return absoluteMaxHeight;
    }

    public Vector2 getVelocity() {
        return new Vector2(vx, vy);
    }

    public boolean isDead() {
        if (this.getRenderCoordinate().y > screenSize.height) {
            died = true;
        }
        return died;
    }

    public void setAcceleration(Dir ax) {
        switch (ax) {
            case LEFT:
                this.ax = -1.5f;
                break;
            case RIGHT:
                this.ax = 1.5f;
                break;
            case NONE:
                this.ax = 0f;
                break;
        }
    }

    public void update() {
        this.setY((this.getPosition().y + vy));
        this.setX((this.getPosition().x + vx));
        //Coordinate system is inverted, so we need to invert the height
        if (this.getPosition().y < absoluteMaxHeight) {
            absoluteMaxHeight = this.getPosition().y;
        }

        if (ax == 0) vx *= 0.85f;    //Slow down doodler when not moving vertically
        if (Math.abs(vx) <= 20) {
            vx += ax;   //Affect doodler by acceleration
        }

        if (vx > 0) {
            //Right image
            this.setImage(rightImage);
        } else {
            //Left image
            this.setImage(leftImage);
        }

        //Limit the doodler into the screen
        if (this.getBottomCenter().x < 0) {
            this.setX(screenSize.width - this.getSize().width / 2);
        }
        if (this.getBottomCenter().x > screenSize.width) {
            this.setX(this.getSize().width / 2);
        }

        //Check if the doodler is on a platform
        Boolean onPlatform = false;
        for (Platform platform : platforms) {
            if (platform.isCollidingFromTop(this) && vy > 0) {
                onPlatform = true;
                //this.setY(platform.getPosition().y - this.getSize().height);
                vy = -1f * (float) Math.sqrt(2 * g * maxJumpHeight);
            }
        }
        if (!onPlatform) vy += g;    //Affect doodler by gravity

        //Check if doodler is colliding with the black hole
        if(blackHole != null && this.isColliding(blackHole)) {
            died = true;
            vx = 0;
            vy = 0;
        }
    }

    public void update(LinkedList<Platform> platforms, BlackHole blackHole) {
        this.platforms = platforms;
        this.blackHole = blackHole;
        update();
    }

    @Override
    public void render(Graphics graphics) {
        //Print the coordinates of the doodler
        //graphics.drawString("X: " + this.getPosition().x + "Y: " + this.getPosition().y, 20, 40);

        graphics.setColor(Color.BLUE);
        Vector2 drawPosition = this.getRenderCoordinate();
        //graphics.fillRect((int) drawPosition.x, (int) drawPosition.y, this.getSize().width, this.getSize().height);
        graphics.drawImage(this.getImage(), (int) (drawPosition.x - ((this.getImage().getWidth(null) - this.getWidth()) / 2)), (int) drawPosition.y, /*this.getSize().width, this.getSize().height,*/ null);
    }
}
