package Item;

import Render.RenderableIF;
import math.Vector2;

import java.awt.*;
import java.util.LinkedList;

public class Doodler extends Item implements UpdatableIF, RenderableIF {
    public static enum Dir{
        LEFT,
        RIGHT,
        NONE,
    }
    private Vector2 screenSize;
    private LinkedList<Platform> platforms; //TODO: Delete this => It's Ugliest Code Ever

    public static int maxJumpHeight = 300;
    private float g = 0.5f;     //Gravity
    private float ax = 0.0f;    //Acceleration in X coordinate, controlled by the player
    private float vy = 0;       //Velocity in Y coordinate
    private float vx = 0;       //Velocity in X coordinate
    private float absoluteMaxHeight = 0;

    //Put the doodler in the middle of the screen
    public Doodler(Vector2 position, Vector2 screenSize) {
        super(position.add(-50 / 2.0f, -25 / 2.0f), new Vector2(50, 75));
        absoluteMaxHeight = this.getPosition().y;
        this.screenSize = screenSize;
    }

    public float getAbsoluteMaxHeight(){
        return absoluteMaxHeight;
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
        if(this.getPosition().y < absoluteMaxHeight) {
            absoluteMaxHeight = this.getPosition().y;
        }

        //Check if the doodler is on a platform
        Boolean onPlatform = false;
        for(Platform platform : platforms) {
            if(platform.isCollidingFromTop(this) && vy > 0) {
                onPlatform = true;
                this.setY(platform.getPosition().y - this.getSize().height);
                //vy = -1f * (float)Math.sqrt(vy * vy - 2 * g * (this.getPosition().y - platform.getPosition().y - platform.getHeight()));
                vy = -1f *(float) Math.sqrt(2 * g * maxJumpHeight);
            }
        }
        if(!onPlatform) vy += g;    //Affect doodler by gravity

        if(ax == 0) vx *= 0.85f;    //Slow down doodler when not moving vertically
        if(Math.abs(vx) <= 20){
            vx += ax;   //Affect doodler by acceleration
        }

        //Limit the doodler into the screen
        if(this.getPosition().x < 0) {
            this.setX(screenSize.x);
        }
        if(this.getPosition().x > screenSize.x) {
            this.setX(0);
        }
    }

    public void update(LinkedList<Platform> platforms) {
        this.platforms = platforms;
        update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        Vector2 drawPosition = this.getRenderCoordinate();
        graphics.fillRect((int)drawPosition.x, (int)drawPosition.y, this.getSize().width, this.getSize().height);
    }
}
