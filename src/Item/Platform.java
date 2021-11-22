package Item;

import math.Vector2;

import javax.swing.*;

public class Platform  extends Item implements UpdatableIF {

    public Platform(Vector2 position) {
        super(position, new Vector2(100, 20));
    }

    @Override
    public void update() {

    }
}
