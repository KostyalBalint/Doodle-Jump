package Item;

import math.Vector2;

public class Doodler extends Item implements UpdatableIF{

    private float g = 0.5f;     //Gravity
    private float vy = 0;       //Velocity in Y coordinate
    private float vx = 0;       //Velocity in X coordinate

    //Put the doodler in the middle of the screen
    public Doodler(Vector2 position) {
        super(position.add(-50/2.0f, -25/2.0f), new Vector2(50, 75));
    }

    @Override
    public void update() {
        this.setY((this.getPosition().y + vy));
        this.setX((this.getPosition().x + vx));


        if(this.getBottomCenter().y > 600){
            vy *= -1;
        }else{
            vy += g;
        }
    }
}
