package Item;

import math.Vector2;

public class Platform extends Item implements UpdatableIF {

    public static int width = 100;
    public static int height = 20;

    public Platform(Vector2 position) {
        super(position, new Vector2(width, height));
    }

    @Override
    public void update() {
        render();
    }
}
