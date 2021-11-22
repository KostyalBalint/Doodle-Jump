package com.mygdx.doodlejump;

import com.badlogic.gdx.math.Rectangle;

public class Platform extends Rectangle {

    private static float width = 100;
    private static float height = 20;

    /**
     * Constructor for the platform
     * @param x center cooridnate of the platform
     * @param y center cooridnate of the platform
     */
    public Platform(float x, float y) {
        super(x - (width/2), y - (height/2), width, height);
    }


}
