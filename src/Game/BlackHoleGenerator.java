package Game;

import Item.BlackHole;
import math.Vector2;

import java.util.Random;

public class BlackHoleGenerator {
    private static Random random = new Random();

    public static BlackHole generateBlackHole(BlackHole current, int score, Vector2 screenSize) {
        if(current == null){
            if(score > 1000){
                return new BlackHole(new Vector2(random.nextInt((int) screenSize.x - BlackHole.width), -1 * (random.nextInt((int) screenSize.y) + score + screenSize.y)));
            }else{
                return null;
            }
        }else{
            int distanceFromScreenBottom = (int) screenSize.y - (int) current.getRenderCoordinate().y;
            if(distanceFromScreenBottom < -5000 && random.nextFloat() > 0.9f){
                return new BlackHole(new Vector2(random.nextInt((int) screenSize.x - BlackHole.width), -1 * (random.nextInt((int) screenSize.y) + score + screenSize.y)));
            }else{
                return current;
            }
        }
    }
}
