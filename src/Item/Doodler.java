package Item;

import Render.RenderableIF;
import math.Vector2;

import java.awt.*;

public class Doodler extends Item implements UpdatableIF, RenderableIF {

    public static int maxJumpHeight = 200;
    private float g = 0.5f;     //Gravity
    private float ax = 0.0f;    //Acceleration in X coordinate, controlled by the player
    private float vy = 0;       //Velocity in Y coordinate
    private float vx = 0;       //Velocity in X coordinate
    private float absoluteMaxHeight = 0;

    //Put the doodler in the middle of the screen
    public Doodler(Vector2 position) {
        super(position.add(-50 / 2.0f, -25 / 2.0f), new Vector2(50, 75));
        absoluteMaxHeight = this.getPosition().y;
    }

    public float getAbsoluteMaxHeight(){
        return absoluteMaxHeight;
    }

    @Override
    public void update() {
        this.setY((this.getPosition().y + vy));
        this.setX((this.getPosition().x + vx));
        //Coordinate system is inverted, so we need to invert the height
        if(this.getPosition().y < absoluteMaxHeight) {
            absoluteMaxHeight = this.getPosition().y;
        }

        if (this.getBottomCenter().y > 600) {
            vy *= -1;
        } else {
            vy += g;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        Vector2 drawPosition = this.getRenderCoordinate();
        graphics.fillRect((int)drawPosition.x, (int)drawPosition.y, this.getSize().width, this.getSize().height);
    }
}
