package Game;

import Item.BlackHole;
import Item.Doodler;
import Item.Platform;
import util.Vector2;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class PlatformGenerator {

    private static Random random = new Random();
    private int platformsGeneratedHeight;

    public PlatformGenerator(Game game) {
        platformsGeneratedHeight = game.getWindowSize().height;
    }

    public void generatePlatformsIfNeeded(Doodler doodler, Dimension windowSize, LinkedList<Platform> platformList, BlackHole blackHole) {
        if (doodler.getAbsoluteMaxHeight() - windowSize.height < platformsGeneratedHeight) {
            platformsGeneratedHeight = generatePlatforms(platformsGeneratedHeight, platformsGeneratedHeight - windowSize.height, windowSize, platformList, blackHole);
            //System.out.println("Current platform count: " + platformList.size());
        }
    }

    private int generatePlatforms(int startY, int endY, Dimension screenSize, LinkedList<Platform> platformList, BlackHole blackHole) {
        int currentY = startY;

        while (currentY > endY) {

            boolean overlaps = false;
            Platform platform;
            do {
                int x = random.nextInt((int) (screenSize.width - Platform.width));
                //Maximum height difference between platforms must be lower than the maxJumpHeight of the Doodler
                int dH = random.nextInt((Doodler.maxJumpHeight / 2) - (Platform.height * 3)) + (Platform.height * 3);

                //Generate more platforms near the black hole
                if (blackHole != null && Math.abs(blackHole.getCenterY() - currentY) < screenSize.height / 2) {
                    dH *= 0.8;
                }

                platform = new Platform(new Vector2(x, dH + currentY));

                overlaps = false;
                //Check if platform overlaps with any other platform
                for (Platform p : platformList) {
                    if (p.isColliding(platform)) {
                        overlaps = true;
                    }
                }
                //Check if platform overlaps with the black hole
                if (blackHole != null && platform.isColliding(blackHole)) {
                    overlaps = true;
                }

                currentY -= dH;
            } while (overlaps);

            platformList.add(platform);
        }
        return currentY;
    }
}
