package Game;

import Item.BlackHole;
import util.Vector2;

import java.awt.*;
import java.util.Random;

/**
 * Generates black holes
 */
public class BlackHoleGenerator {
    private static Random random = new Random();

    /**
     * Generates a black hole at a random position when new black hole can be generated
     * The first black hole is generated when the player reaches Score of 1000
     * After that every 5000 points a new black hole is generated randomly
     *
     * @param current    The current black hole if any
     * @param score      The current score
     * @param screenSize The size of the screen
     * @return New black hole if any, else the old black hole
     */
    public static BlackHole generateBlackHole(BlackHole current, int score, Dimension screenSize) {
        if (current == null) {
            if (score > 1000) {
                return new BlackHole(new Vector2(random.nextInt((int) screenSize.width - BlackHole.width), -1 * (random.nextInt((int) screenSize.height) + score + screenSize.height)));
            } else {
                return null;
            }
        } else {
            int distanceFromScreenBottom = (int) screenSize.height - (int) current.getRenderCoordinate().y;
            if (distanceFromScreenBottom < -5000 && random.nextFloat() > 0.9f) {
                return new BlackHole(new Vector2(random.nextInt((int) screenSize.width - BlackHole.width), -1 * (random.nextInt((int) screenSize.height) + score + screenSize.height)));
            }else{
                return current;
            }
        }
    }
}
