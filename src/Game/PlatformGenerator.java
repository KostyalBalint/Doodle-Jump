package Game;

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

    public void generatePlatformsIfNeeded(Doodler doodler, Dimension windowSize, LinkedList<Platform> platformList) {
        if (doodler.getAbsoluteMaxHeight() - windowSize.height < platformsGeneratedHeight) {
            platformsGeneratedHeight = generatePlatforms(platformsGeneratedHeight, platformsGeneratedHeight - windowSize.height, windowSize, platformList);
            //System.out.println("Current platform count: " + platformList.size());
        }
    }

    private int generatePlatforms(int startY, int endY, Dimension screenSize, LinkedList<Platform> platformList) {
        int currentY = startY;

        //TODO: Prevent platforms from being generated on top of each other
        //TODO: Prevent platforms from being generated on top of the black hole

        while (currentY > endY) {

            int x = random.nextInt((int) (screenSize.width - Platform.width));

            //Maximum height difference between platforms must be lower than the maxJumpHeight of the Doodler
            int dH = random.nextInt((Doodler.maxJumpHeight / 2) - (Platform.height * 3)) + (Platform.height * 3);

            Platform platform = new Platform(new Vector2(x, dH + currentY));
            currentY -= dH;

            platformList.add(platform);
        }
        return currentY;
    }
}
