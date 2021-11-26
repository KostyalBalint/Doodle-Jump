package Game;

import Item.Doodler;
import Item.Platform;
import math.Vector2;

import java.util.LinkedList;
import java.util.Random;

public class PlatformGenerator {

    private static Random random = new Random();

    public static int generatePlatforms(int startY, int endY, Vector2 screenSize, LinkedList<Platform> platformList) {
        int currentY = startY;

        while (currentY > endY) {

            int x = random.nextInt((int) (screenSize.x - Platform.width));

            //Maximum height difference between platforms must be lower than the maxJumpHeight of the Doodler
            int dH = random.nextInt((Doodler.maxJumpHeight / 2) - Platform.height*3) + Platform.height*3;

            Platform platform = new Platform(new Vector2(x, dH + currentY));
            currentY -= dH;

            platformList.add(platform);
        }
        return currentY;
    }
}
