package Game;

import Camera.Camera;
import Item.*;
import Render.RenderableIF;
import util.Vector2;

import java.awt.*;
import java.util.LinkedList;

import static Game.BlackHoleGenerator.generateBlackHole;

/**
 * Main game class. Handles most of the game logic.
 */
public class Game implements UpdatableIF {
    private static Game instance;

    private Dimension windowSize = new Dimension(600, 800);
    private GameFrame gameFrame;

    private Doodler doodler;
    private BlackHole blackHole;
    private LinkedList<Platform> platformList;
    private PlatformGenerator platformGenerator;
    private Score scoreRenderer;

    /**
     * Constructor of the game
     * Initializes the game components and the camera
     */
    private Game() {
        platformList = new LinkedList<Platform>();
        initComonents(this);
        gameFrame = new GameFrame(this.windowSize, this.doodler);

        //Init camera
        Camera.getInstance().setPosition(0, 0);
    }

    /**
     * Gets the instance of the game, if it does not exist, it creates a new one
     *
     * @return Game The instance of the game
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Resets the game to a start stage with tha same gameFrame as before
     *
     * @return Game The new Game instance
     */
    public static Game reinitialize() {
        Game game = Game.getInstance();
        game.initComonents(game);
        game.gameFrame.setDoodler(game.doodler);

        //Init camera
        Camera.getInstance().setPosition(0, 0);

        //Reset the gameLoop
        GameLoop.reinitialize().setUpdateFunction(instance);
        return instance;
    }

    /**
     * Initializes the game components
     *
     * @param game The game instance
     */
    private void initComonents(Game game) {
        game.platformList = new LinkedList<Platform>();
        game.doodler = new Doodler(new Vector2(game.windowSize.width / 2.0f, game.windowSize.height / 2.0f));
        //Ensure that the doodler is always have one platform underneath at start
        game.platformList.add(new Platform(new Vector2((game.windowSize.width - Platform.width) / 2.0f, game.windowSize.height)));
        game.platformGenerator = new PlatformGenerator(game);
        game.scoreRenderer = new Score();
    }

    /**
     * Starts the game, initializes the game and starts the game loop
     */
    public void startGame() {
        //Start the gameLoop
        GameLoop.getInstance().setUpdateFunction(Game.getInstance());

        gameFrame.showGame();
        GameLoop.getInstance().setUpdateFunction(this);
        GameLoop.getInstance().start();
    }

    /**
     * Stops the game loop, and tells the GameFrame to render the Game Over screen
     */
    public void stopGame() {
        gameFrame.showGameOver();
        gameFrame.repaint();
        GameLoop.getInstance().stop();
    }

    /**
     * Update loop, this is called for every frame
     * This updates the game components and the camera
     */
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
        platformGenerator.generatePlatformsIfNeeded(doodler, windowSize, platformList, blackHole);

        //Remove platforms that are out of the screen
        platformList.removeIf(platform -> platform.getRenderCoordinate().y > windowSize.height);

        //Render everything
        render();
    }

    /**
     * Renders all the game components
     */
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

    /**
     * Gets the player's current score
     *
     * @return int The player's current score
     */
    public int getScore() {
        return (-1 * (int) doodler.getAbsoluteMaxHeight()) + windowSize.height / 2;
    }

    /**
     * Gets the window size
     *
     * @return Dimension The window size
     */
    public Dimension getWindowSize() {
        return windowSize;
    }

    /**
     * Gets the gameFrame, this is where all the canvases are rendered
     *
     * @return GameFrame The gameFrame
     */
    public GameFrame getGameFrame() {
        return gameFrame;
    }

    /**
     * Gets the doodler form the game
     *
     * @return Doodler The doodler
     */
    public Doodler getDoodler() {
        return doodler;
    }

    /**
     * Sets the doodler form the game
     *
     * @param doodler The doodler to set
     */
    public void setDoodler(Doodler doodler) {
        this.doodler = doodler;
    }

    /**
     * Gets the black hole form the game
     *
     * @return BlackHole The black hole
     */
    public BlackHole getBlackHole() {
        return blackHole;
    }

    /**
     * Sets the black hole to the game
     *
     * @param blackHole The black hole to set
     */
    public void setBlackHole(BlackHole blackHole) {
        this.blackHole = blackHole;
    }

    /**
     * Gets all the current platforms
     *
     * @return LinkedList<Item.Platform> All the platforms
     */
    public LinkedList<Platform> getPlatformList() {
        return platformList;
    }

    /**
     * Sets the platform list
     *
     * @param platformList The list of platforms to set
     */
    public void setPlatformList(LinkedList<Platform> platformList) {
        this.platformList = platformList;
    }
}
