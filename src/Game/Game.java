package Game;

import Camera.Camera;
import Item.*;
import Render.RenderableIF;
import util.Vector2;

import java.awt.*;
import java.util.LinkedList;

import static Game.BlackHoleGenerator.generateBlackHole;
import static Game.PlatformGenerator.generatePlatformsIfNeeded;

public class Game implements UpdatableIF {
    private static Game instance;

    private Dimension windowSize = new Dimension(600, 800);
    private GameFrame gameFrame;

    private Doodler doodler;
    private BlackHole blackHole;
    private LinkedList<Platform> platformList;
    private Score scoreRenderer;

    private Game() {
        platformList = new LinkedList<Platform>();
        doodler = new Doodler(new Vector2(windowSize.width / 2.0f, windowSize.height / 2.0f), windowSize);
        //Ensure that the doodler is always have one platform underneath at start
        platformList.add(new Platform(new Vector2((windowSize.width - Platform.width) / 2.0f, windowSize.height)));
        scoreRenderer = new Score();
        gameFrame = new GameFrame(windowSize, doodler);

        //Init camera
        Camera.getInstance().setPosition(0, 0);
    }

    public void startGame() {
        gameFrame.showGame();
        GameLoop.getInstance().setUpdateFunction(this);
        GameLoop.getInstance().start();
    }

    public void stopGame(){
        gameFrame.getGameCanvas().renderGameOver();
        gameFrame.repaint();
        GameLoop.getInstance().stop();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    @Override
    public void update() {
        doodler.update(platformList, blackHole);   //Update doodler
        //platformList.forEach(Item.Platform::update);//Update platforms
        if (doodler.isDead()) {
            //If doodler is dead, camera will fall down with the doodler
            boolean gameOver = Camera.getInstance().fallDown(doodler, windowSize);
            if (gameOver) {
                Game.getInstance().stopGame();
            }
        } else {
            //If doodler is not dead, camera follows doodler
            Camera.getInstance().setY(-1 * (doodler.getAbsoluteMaxHeight() - (windowSize.height / 3.0f)));
        }
        Camera.getInstance().update();  //Update the camera

        //Generate black hole
        blackHole = generateBlackHole(this.blackHole, getScore(), windowSize);

        //Generate platforms if needed
        generatePlatformsIfNeeded(doodler, windowSize, platformList);

        //Remove platforms that are out of the screen
        platformList.removeIf(platform -> platform.getRenderCoordinate().y > windowSize.height);

        //Render everything
        render();
    }

    private void render() {
        LinkedList<RenderableIF> renderableList = (LinkedList<RenderableIF>) platformList.clone();
        if (blackHole != null) {
            renderableList.addFirst(blackHole);
        }
        renderableList.addLast(doodler);
        renderableList.addLast(scoreRenderer);
        gameFrame.getGameCanvas().paint(gameFrame.getGameCanvas().getGraphics(), renderableList);
        gameFrame.repaint();
    }

    public int getScore() {
        return (-1 * (int) doodler.getAbsoluteMaxHeight()) + windowSize.height / 2;
    }

    public Dimension getWindowSize() {
        return windowSize;
    }

    public GameFrame getGameFrame(){
        return gameFrame;
    }

    public Doodler getDoodler() {
        return doodler;
    }

    public void setDoodler(Doodler doodler) {
        this.doodler = doodler;
    }

    public BlackHole getBlackHole() {
        return blackHole;
    }

    public void setBlackHole(BlackHole blackHole) {
        this.blackHole = blackHole;
    }

    public LinkedList<Platform> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(LinkedList<Platform> platformList) {
        this.platformList = platformList;
    }
}
